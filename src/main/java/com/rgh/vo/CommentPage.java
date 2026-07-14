package com.rgh.vo;

import lombok.Data;

import java.util.List;

/**
 * @author: rgh
 * @description: 后台表格展示
 */
@Data
public class CommentPage {
    private List<CommentVo2> commentVo2;
    private Integer pageNum;
    private Integer pageSize;
    private Integer total;
}
