package com.rgh.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @author: rgh  mail:817094@qq.com
 * @description:
 */

@TableName("rgh_article")
@Data
public class Article {
    @Schema(description = "主键ID")
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String title;
    private String cover;
    private String author;
    private String content;
    private boolean state;
    private boolean top;
    @Schema(description = "是否开启下载")
    @TableField("download_enable")
    private boolean downloadEnable;
    @Schema(description = "附件名称")
    @TableField("download_name")
    private String downloadName;
    @Schema(description = "下载地址")
    @TableField("download_url")
    private String downloadUrl;
    @Schema(description = "附件大小")
    @TableField("download_size")
    private String downloadSize;
    @Schema(description = "附件描述")
    @TableField("download_desc")
    private String downloadDesc;
    @Schema(description = "阅读量")
    @TableField("view_count")
    private Integer viewCount;
    @Schema(description = "点赞数")
    @TableField("like_count")
    private Integer likeCount;
    @Schema(hidden = true)
    @TableField("create_time")
    private Date createdTime;
    @Schema(hidden = true)
    @TableField("update_time")
    private Date updatedTime;

    @Getter
    @Setter
    @ToString
    @Schema(description = "Article.Criteria")
    public static class Criteria {
        private Integer id;
        private String title;
        private String author;
        private String keyword;
    }

}
