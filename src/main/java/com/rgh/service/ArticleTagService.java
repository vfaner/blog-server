package com.rgh.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rgh.entity.ArticleTag;
import com.rgh.vo.TagWithCountVo;

import java.util.List;

public interface ArticleTagService extends IService<ArticleTag> {
    List<Integer> findTagIdsByArticleId(Integer articleId);
    List<Integer> findArticleIdsByTagId(Integer tagId);
    List<TagWithCountVo> tagWithCount();
    void deleteByArticleId(Integer articleId);
}
