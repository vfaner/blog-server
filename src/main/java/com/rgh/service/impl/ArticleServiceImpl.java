package com.rgh.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rgh.dto.ArticleDto;
import com.rgh.dto.EditArticleDto;
import com.rgh.entity.*;
import com.rgh.mapper.ArticleMapper;
import com.rgh.service.*;
import com.rgh.vo.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

import static com.rgh.constant.Constant.El_PLUS_TAGS;

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Resource
    private ArticleCategoryServiceImpl articleCategoryService;
    @Resource
    private ArticleTagServiceImpl articleTagService;
    @Resource
    private CategoryServiceImpl categoryService;
    @Resource
    private TagServiceImpl tagService;
    @Resource
    private com.rgh.mapper.CommentMapper commentMapper;

    private static final String ARTICLE_SELECT_BY_CATE = "category";
    private static final String ARTICLE_SELECT_BY_TAG = "tag";

    /** 统计文章已审核通过的评论数 */
    private int countComments(Integer articleId) {
        com.baomidou.mybatisplus.core.conditions.query.QueryWrapper<com.rgh.entity.Comment> qw =
                new com.baomidou.mybatisplus.core.conditions.query.QueryWrapper<>();
        qw.eq("article_id", articleId).eq("state", 1);
        return Math.toIntExact(commentMapper.selectCount(qw));
    }

    @Override
    public void incrementView(Integer id) {
        Article a = getById(id);
        if (a != null) {
            a.setViewCount((a.getViewCount() == null ? 0 : a.getViewCount()) + 1);
            updateById(a);
        }
    }

    @Override
    public int likeArticle(Integer id) {
        Article a = getById(id);
        if (a != null) {
            int likes = (a.getLikeCount() == null ? 0 : a.getLikeCount()) + 1;
            a.setLikeCount(likes);
            updateById(a);
            return likes;
        }
        return 0;
    }

    @Override
    public ArticlePage findArticlePage(Article.Criteria criteria, PageRequest pageRequest) {
        Page<Article> page = new Page<>(pageRequest.getPageNum(), pageRequest.getPageSize());
        QueryWrapper<Article> wrapper = new QueryWrapper<>();
        if (criteria.getTitle() != null && !criteria.getTitle().isEmpty()) {
            wrapper.like("title", criteria.getTitle());
        }
        if (criteria.getAuthor() != null && !criteria.getAuthor().isEmpty()) {
            wrapper.eq("author", criteria.getAuthor());
        }
        if (criteria.getKeyword() != null && !criteria.getKeyword().isEmpty()) {
            wrapper.and(w -> w.like("title", criteria.getKeyword()).or().like("content", criteria.getKeyword()));
        }
        wrapper.orderByDesc("top", "create_time");

        Page<Article> result = page(page, wrapper);
        List<ArticleVo> content = result.getRecords().stream()
                .map(this::toArticleVo)
                .collect(Collectors.toList());

        ArticlePage articlePage = new ArticlePage();
        articlePage.setContent(content);
        articlePage.setPageNum((int) result.getCurrent());
        articlePage.setPageSize((int) result.getSize());
        articlePage.setTotal((int) result.getTotal());
        return articlePage;
    }

    @Override
    public ArticleVo findArticleVoById(Integer articleId) {
        // XML 多表关联查询：resultMap 聚合后返回列表，取第一个
        List<ArticleVo> list = baseMapper.selectArticleVoById(articleId);
        if (list.isEmpty()) return null;
        ArticleVo vo = list.get(0);
        vo.setCommentCount(countComments(articleId));
        return vo;
    }

    private ArticleVo toArticleVo(Article article) {
        ArticleVo vo = new ArticleVo();
        vo.setArticle(article);

        List<Integer> cateIds = articleCategoryService.findCategoryIdsByArticleId(article.getId());
        if (!cateIds.isEmpty()) {
            List<Category> categories = categoryService.listByIds(cateIds);
            vo.setCategories(categories.stream().map(c -> {
                ElTagVo el = new ElTagVo();
                el.setId(c.getId());
                el.setName(c.getName());
                el.setType(El_PLUS_TAGS.get(new SecureRandom().nextInt(5)));
                return el;
            }).collect(Collectors.toList()));
        } else {
            vo.setCategories(Collections.emptyList());
        }

        List<Integer> tagIds = articleTagService.findTagIdsByArticleId(article.getId());
        if (!tagIds.isEmpty()) {
            List<Tag> tags = tagService.listByIds(tagIds);
            vo.setTags(tags.stream().map(t -> {
                ElTagVo el = new ElTagVo();
                el.setId(t.getId());
                el.setName(t.getName());
                el.setType(El_PLUS_TAGS.get(new SecureRandom().nextInt(5)));
                return el;
            }).collect(Collectors.toList()));
        } else {
            vo.setTags(Collections.emptyList());
        }
        return vo;
    }

    @Override
    @Transactional
    public ArticleDto addArticle(ArticleDto model) {
        Article article = new Article();
        BeanUtils.copyProperties(model, article);
        article.setCreatedTime(new Date());
        article.setUpdatedTime(new Date());
        save(article);

        if (model.getCategories() != null) {
            for (Integer cateId : model.getCategories()) {
                ArticleCategory ac = new ArticleCategory();
                ac.setArticleId(article.getId());
                ac.setCategoryId(cateId);
                articleCategoryService.save(ac);
            }
        }
        if (model.getTags() != null) {
            for (Integer tagId : model.getTags()) {
                ArticleTag at = new ArticleTag();
                at.setArticleId(article.getId());
                at.setTagId(tagId);
                articleTagService.save(at);
            }
        }

        ArticleDto result = new ArticleDto();
        BeanUtils.copyProperties(article, result);
        result.setCategories(model.getCategories());
        result.setTags(model.getTags());
        return result;
    }

    @Override
    @Transactional
    public ArticleDto modifyArticle(EditArticleDto model) {
        Article article = new Article();
        BeanUtils.copyProperties(model, article);
        article.setUpdatedTime(new Date());
        updateById(article);

        articleCategoryService.deleteByArticleId(model.getId());
        if (model.getCategories() != null) {
            for (ElTagVo cate : model.getCategories()) {
                ArticleCategory ac = new ArticleCategory();
                ac.setArticleId(model.getId());
                ac.setCategoryId(cate.getId());
                articleCategoryService.save(ac);
            }
        }

        articleTagService.deleteByArticleId(model.getId());
        if (model.getTags() != null) {
            for (ElTagVo tag : model.getTags()) {
                ArticleTag at = new ArticleTag();
                at.setArticleId(model.getId());
                at.setTagId(tag.getId());
                articleTagService.save(at);
            }
        }

        ArticleDto result = new ArticleDto();
        BeanUtils.copyProperties(article, result);
        return result;
    }

    @Override
    @Transactional
    public void deleteArticleById(Integer id) {
        removeById(id);
        articleCategoryService.deleteByArticleId(id);
        articleTagService.deleteByArticleId(id);
    }

    @Override
    public ArticleWithCateAndTag findUnderArticlesById(String type, Integer id) {
        ArticleWithCateAndTag result = new ArticleWithCateAndTag();
        List<ArticleVo> articles;

        if (ARTICLE_SELECT_BY_CATE.equals(type)) {
            Category cate = categoryService.getById(id);
            result.setCate(cate);
            // XML 多表关联查询：一次 SQL 取分类下所有文章+分类+标签
            articles = baseMapper.selectArticlesByCategoryIdPage(id);
        } else if (ARTICLE_SELECT_BY_TAG.equals(type)) {
            Tag tag = tagService.getById(id);
            result.setTag(tag);
            // XML 多表关联查询：一次 SQL 取标签下所有文章+分类+标签
            articles = baseMapper.selectArticlesByTagIdPage(id);
        } else {
            articles = Collections.emptyList();
        }
        // 填充每篇文章的评论数
        articles.forEach(vo -> {
            if (vo.getArticle() != null) {
                vo.setCommentCount(countComments(vo.getArticle().getId()));
            }
        });
        result.setArticles(articles);
        return result;
    }

    @Override
    public ArticleListVo findListShow(PageRequest pageRequest) {
        List<Article> allArticles = list(new LambdaQueryWrapper<Article>().eq(Article::isState, true));
        List<Article> sorted = allArticles.stream()
                .sorted(Comparator.comparing(Article::isTop).reversed()
                        .thenComparing(Article::getCreatedTime, Comparator.reverseOrder()))
                .collect(Collectors.toList());

        int skip = (pageRequest.getPageNum() - 1) * pageRequest.getPageSize();
        List<ArticleListVo.ArticleShow> pagedArticles = sorted.stream()
                .skip(skip)
                .limit(pageRequest.getPageSize())
                .map(a -> {
                    ArticleListVo.ArticleShow show = new ArticleListVo.ArticleShow();
                    show.setId(a.getId());
                    show.setTitle(a.getTitle());
                    show.setTop(a.isTop());
                    show.setTime(a.getCreatedTime());
                    return show;
                }).collect(Collectors.toList());

        ArticleListVo vo = new ArticleListVo();
        vo.setArticleList(pagedArticles);
        vo.setPageNum(pageRequest.getPageNum());
        vo.setPageSize(pageRequest.getPageSize());
        vo.setTotal(allArticles.size());

        // Today's count
        long todayStart = LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();
        long todayEnd = System.currentTimeMillis();
        long todayCount = allArticles.stream()
                .filter(a -> {
                    long t = a.getCreatedTime() != null ? a.getCreatedTime().getTime() : 0;
                    return t >= todayStart && t <= todayEnd;
                }).count();
        vo.setTodayUpdate(todayCount);
        return vo;
    }

    @Override
    public List<ArticleAsideVo> randomLimit(String type, Integer limit) {
        List<Article> all = list(new LambdaQueryWrapper<Article>().eq(Article::isState, true));
        if ("0".equals(type)) {
            return all.stream()
                    .sorted(Comparator.comparing(Article::getCreatedTime, Comparator.reverseOrder()))
                    .limit(limit)
                    .map(this::toAsideVo)
                    .collect(Collectors.toList());
        } else if ("1".equals(type)) {
            List<Integer> ids = all.stream().map(Article::getId).collect(Collectors.toList());
            Collections.shuffle(ids);
            Set<Integer> picked = ids.stream().limit(limit).collect(Collectors.toSet());
            return all.stream()
                    .filter(a -> picked.contains(a.getId()))
                    .map(this::toAsideVo)
                    .collect(Collectors.toList());
        } else {
            // Hot: no implementation yet, return empty
            return Collections.emptyList();
        }
    }

    private ArticleAsideVo toAsideVo(Article article) {
        ArticleAsideVo vo = new ArticleAsideVo();
        vo.setId(article.getId());
        vo.setTitle(article.getTitle());
        vo.setCover(article.getCover());
        vo.setViewCount(article.getViewCount() == null ? 0 : article.getViewCount());
        vo.setTime(article.getCreatedTime());
        return vo;
    }

    @Override
    public CateWithArticleVo findCateUnderArticlesById(Integer cateId, Integer limit) {
        Category category = categoryService.getById(cateId);
        List<Integer> articleIds = articleCategoryService.findArticleIdsByCategoryId(cateId);
        List<Article> matchingArticles;
        if (!articleIds.isEmpty()) {
            matchingArticles = listByIds(articleIds).stream()
                    .filter(Article::isState)
                    .sorted(Comparator.comparing(Article::getCreatedTime, Comparator.reverseOrder()))
                    .limit(limit)
                    .collect(Collectors.toList());
        } else {
            matchingArticles = Collections.emptyList();
        }

        CateWithArticleVo vo = new CateWithArticleVo();
        vo.setId(cateId);
        vo.setName(category != null ? category.getName() : "");
        vo.setArticles(matchingArticles.stream().map(a -> {
            ArticleListVo.ArticleIndexWithCover awc = new ArticleListVo.ArticleIndexWithCover();
            awc.setId(a.getId());
            awc.setTitle(a.getTitle());
            awc.setCover(a.getCover());
            awc.setContent(a.getContent());
            awc.setTime(a.getCreatedTime());
            return awc;
        }).collect(Collectors.toList()));
        return vo;
    }
}
