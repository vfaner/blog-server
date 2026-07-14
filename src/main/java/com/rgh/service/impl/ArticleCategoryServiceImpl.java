package com.rgh.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rgh.entity.ArticleCategory;
import com.rgh.mapper.ArticleCategoryMapper;
import com.rgh.service.ArticleCategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleCategoryServiceImpl extends ServiceImpl<ArticleCategoryMapper, ArticleCategory>
        implements ArticleCategoryService {

    @Override
    public List<Integer> findCategoryIdsByArticleId(Integer articleId) {
        LambdaQueryWrapper<ArticleCategory> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ArticleCategory::getArticleId, articleId);
        return list(wrapper).stream().map(ArticleCategory::getCategoryId).collect(Collectors.toList());
    }

    @Override
    public List<Integer> findArticleIdsByCategoryId(Integer categoryId) {
        LambdaQueryWrapper<ArticleCategory> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ArticleCategory::getCategoryId, categoryId);
        return list(wrapper).stream().map(ArticleCategory::getArticleId).collect(Collectors.toList());
    }

    @Override
    public void deleteByArticleId(Integer articleId) {
        LambdaQueryWrapper<ArticleCategory> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ArticleCategory::getArticleId, articleId);
        remove(wrapper);
    }
}
