package com.rgh.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rgh.dto.ArticleDto;
import com.rgh.dto.EditArticleDto;
import com.rgh.entity.Article;
import com.rgh.vo.*;

import java.util.List;

public interface ArticleService extends IService<Article> {
    ArticlePage findArticlePage(Article.Criteria criteria, PageRequest pageRequest);
    ArticleVo findArticleVoById(Integer articleId);
    ArticleDto addArticle(ArticleDto model);
    ArticleDto modifyArticle(EditArticleDto model);
    void deleteArticleById(Integer id);
    ArticleWithCateAndTag findUnderArticlesById(String type, Integer id);
    ArticleListVo findListShow(PageRequest pageRequest);
    List<ArticleAsideVo> randomLimit(String type, Integer limit);
    CateWithArticleVo findCateUnderArticlesById(Integer cateId, Integer limit);
    void incrementView(Integer id);
    int likeArticle(Integer id);
}
