package com.rgh.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rgh.entity.ArticleCategory;

import java.util.List;

public interface ArticleCategoryService extends IService<ArticleCategory> {
    List<Integer> findCategoryIdsByArticleId(Integer articleId);
    List<Integer> findArticleIdsByCategoryId(Integer categoryId);
    void deleteByArticleId(Integer articleId);
}
