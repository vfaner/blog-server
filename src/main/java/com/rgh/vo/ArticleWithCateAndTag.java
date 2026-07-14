package com.rgh.vo;

import com.rgh.entity.Category;
import com.rgh.entity.Tag;
import lombok.Data;

import java.util.List;

/**
 * @author: rgh
 * @description: 分类下的文章
 */
@Data
public class ArticleWithCateAndTag {
    private List<ArticleVo> articles;
    private Category cate;
    private Tag tag;
}
