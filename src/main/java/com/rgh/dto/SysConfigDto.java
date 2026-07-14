package com.rgh.dto;

import com.rgh.vo.SysAdVo;
import lombok.Data;

import java.util.List;

/**
 * @author: rgh
 * @description: 系统设置
 */
@Data
public class SysConfigDto {
    private String name;
    private String author;
    private String version;
    private String compatibility;
    private String title;
    private String keyword;
    private String description;
    private String logo;
    private String favicon;
    private String advisory;
    private List<SysAdVo> adv;
    private String card;
    private String card1;
    private String card2;
    private Boolean mobileNavEnable;
    private List<MobileNav> mobileNavs;

    @Data
    public static class MobileNav {
        private String icon;
        private String title;
        private String link;
    }
}
