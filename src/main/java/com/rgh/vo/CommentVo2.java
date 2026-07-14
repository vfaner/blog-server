package com.rgh.vo;

import com.rgh.entity.Comment;
import lombok.Data;

/**
 * @author: rgh
 * @description: 后台评论显示
 */
@Data
public class CommentVo2 extends Comment {
    private String articleName;
}
