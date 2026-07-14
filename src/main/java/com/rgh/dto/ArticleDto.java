package com.rgh.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * @author: rgh
 * @date: 2022/10/26 17:15
 * @description: 查询单个，更新和添加文章用的
 */
@Data
public class ArticleDto {
    private Integer id;
    private String code;
    private String title;
    private String cover;
    private String author;
    private String content;
    private boolean state;
    private boolean downloadEnable;
    private String downloadName;
    private String downloadUrl;
    private String downloadSize;
    private String downloadDesc;
    private List<Integer> categories;
    private List<Integer> tags;
    @JsonFormat(pattern =
            "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdTime;
    @JsonFormat(pattern =
            "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date modifiedTime;

}
