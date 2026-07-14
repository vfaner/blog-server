package com.rgh.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * @author: rgh
 * @description: 评论类
 */
@TableName("rgh_comment")
@Getter
@Setter
@ToString
public class Comment {
    @Schema(description = "uid")
    @TableId(type = IdType.AUTO)
    private Integer uid;
    private Integer parentId;
    private String username;
    private Integer articleId;
    private String avatar;
    private String link;
    private String address;
    private String content;
    private Integer level;
    @TableField("`like`")
    private Integer like;
    private Integer state;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdTime;
    @TableField(exist = false)
    private String createTime;

    @TableField(exist = false)
    private List<Comment> children;

    @Data
    @Schema(description = "Comment.Criteria")
    public static class Criteria{

        private Integer id;
        private Integer parentId;
        private String username;
        private Integer articleId;
        private String content;
        private Boolean filterRootIfEmpty;

    }
}
