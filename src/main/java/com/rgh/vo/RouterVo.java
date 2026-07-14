package com.rgh.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * &#064;author:  rgh
 * &#064;description: 路由展示类
 */
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class RouterVo {

    private String path;

    private String component;

    private boolean alwaysShow;

    private String name;

    private Meta meta;

    @Data
    @AllArgsConstructor
    public static class Meta {
        private String title;
        private String icon;
        private Object[] roles;
    }
    private List<RouterVo> children =new ArrayList<>();

}
