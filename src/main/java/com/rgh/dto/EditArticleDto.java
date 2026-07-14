package com.rgh.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import com.rgh.vo.ElTagVo;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * @author: rgh
 * @description: 编辑文章
 */
@Data
public class EditArticleDto {
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
    private List<ElTagVo> categories;
    private List<ElTagVo> tags;
    @JsonFormat(pattern =
            "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdTime;
    @JsonFormat(pattern =
            "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date modifiedTime;
}
