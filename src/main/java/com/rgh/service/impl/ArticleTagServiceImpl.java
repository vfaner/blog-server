package com.rgh.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rgh.entity.ArticleTag;
import com.rgh.entity.Tag;
import com.rgh.mapper.ArticleTagMapper;
import com.rgh.service.ArticleTagService;
import com.rgh.vo.TagWithCountVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ArticleTagServiceImpl extends ServiceImpl<ArticleTagMapper, ArticleTag>
        implements ArticleTagService {

    @Lazy
    @Autowired
    private TagServiceImpl tagService;

    @Override
    public List<Integer> findTagIdsByArticleId(Integer articleId) {
        LambdaQueryWrapper<ArticleTag> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ArticleTag::getArticleId, articleId);
        return list(wrapper).stream().map(ArticleTag::getTagId).collect(Collectors.toList());
    }

    @Override
    public List<Integer> findArticleIdsByTagId(Integer tagId) {
        LambdaQueryWrapper<ArticleTag> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ArticleTag::getTagId, tagId);
        return list(wrapper).stream().map(ArticleTag::getArticleId).collect(Collectors.toList());
    }

    @Override
    public List<TagWithCountVo> tagWithCount() {
        // 统计每个标签的文章数
        Map<Integer, Long> countMap = list().stream()
                .collect(Collectors.groupingBy(ArticleTag::getTagId, Collectors.counting()));
        if (countMap.isEmpty()) return new ArrayList<>();
        // 批量查所有标签名，避免 N+1
        List<Tag> tags = tagService.listByIds(countMap.keySet());
        Map<Integer, String> nameMap = tags.stream()
                .collect(Collectors.toMap(Tag::getId, Tag::getName));
        List<TagWithCountVo> result = new ArrayList<>();
        countMap.forEach((tagId, count) -> {
            TagWithCountVo vo = new TagWithCountVo();
            vo.setId(tagId);
            vo.setName(nameMap.getOrDefault(tagId, ""));
            vo.setCount(count.intValue());
            result.add(vo);
        });
        return result;
    }

    @Override
    public void deleteByArticleId(Integer articleId) {
        LambdaQueryWrapper<ArticleTag> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ArticleTag::getArticleId, articleId);
        remove(wrapper);
    }
}
