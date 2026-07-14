package com.rgh.vo;

import com.rgh.entity.Comment;
import lombok.Data;

/**
 * @author: rgh

 * @description: 文章评论展示
 */
@Data
public class CommentVo extends Comment {
    private Reply reply;
}
