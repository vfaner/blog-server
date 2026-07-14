package com.rgh.vo;

import com.rgh.entity.Comment;
import lombok.Data;

import java.util.List;

/**
 * @author: rgh
 * @description: 回复
 */
@Data
public class Reply {
    private List<Comment> list;
    private Integer total;
}
