package com.rgh.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * @author: rgh  mail:817094@qq.com
 * @description: 文章标签关联表
 */
@TableName("rgh_article_tag")
@Data
@Schema(description = "ArticleTag")
public class ArticleTag {
    @Schema(description = "id")
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer articleId;
    private Integer tagId;

}
