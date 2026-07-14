package com.rgh.vo;

import lombok.Data;

import java.util.List;

/**
 * @author: rgh
 * @description: 分页查询文章
 */
@Data
public class ArticlePage {
    private List<ArticleVo> content;
    private Integer pageNum;
    private Integer pageSize;
    private Integer total;
}
