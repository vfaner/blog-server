package com.rgh.vo;

import com.rgh.entity.Article;
import lombok.Data;

import java.util.List;

/**
 * @author: rgh

 * @description: 文章展示
 */
@Data
public class ArticleVo {
    private Article article;
    private List<ElTagVo> categories;
    private List<ElTagVo> tags;
    private Integer commentCount;
}
