package com.rgh.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

import static com.rgh.constant.Constant.DATE_TIME_FORMAT2;

/**
 * @author: rgh
 * @description: 侧栏文章,随机,推荐,热门
 */
@Data
public class ArticleAsideVo {
    private Integer id;
    private String title;
    private String cover;
    private Integer viewCount;
    @JsonFormat(pattern = DATE_TIME_FORMAT2,timezone = "GMT+8")
    private Date time;
}
