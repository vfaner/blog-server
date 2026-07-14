package com.rgh.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

import static com.rgh.constant.Constant.DATE_TIME_FORMAT1;

/**
 * @author: rgh
 * @description: 首页文章带统计的卡片数据
 */
@Data
public class ArticleListVo {
    private List<ArticleShow> articleList;
    private long todayUpdate;
    private Integer total;
    private Integer pageNum;
    private Integer pageSize;

    @Data
    public static class ArticleShow{
        private Integer id;
        private String title;
        private boolean top;
        @JsonFormat(pattern = DATE_TIME_FORMAT1,timezone = "GMT+8")
        private Date time;
    }

    /**
     *  文章首页带缩略图卡片模块
     */
    @Data
    public static class ArticleIndexWithCover{
        private Integer id;
        private String title;
        private String cover;
        private String content;
        @JsonFormat(pattern = DATE_TIME_FORMAT1,timezone = "GMT+8")
        private Date time;
    }
}
