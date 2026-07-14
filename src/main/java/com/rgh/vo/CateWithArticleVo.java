package com.rgh.vo;

import lombok.Data;

import java.util.List;

/**
 * @author: rgh
 * @description: 首页卡片数据
 */
@Data
public class CateWithArticleVo {
    private Integer id;
    private String name;
    private List<ArticleListVo.ArticleIndexWithCover> articles;
}
