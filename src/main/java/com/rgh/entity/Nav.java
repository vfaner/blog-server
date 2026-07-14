package com.rgh.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@TableName("rgh_nav")
public class Nav {
    @Schema(name = "id",description ="主键")
    @TableId(type = IdType.AUTO)
    private Integer id;
    @Schema(name = "parentId",description ="菜单的父级id")
    private Integer parentId;
    @Schema(name = "label",description ="菜单名称")
    private String label;
    //权限标识
    @Schema(name = "code",description ="权限标识")
    private String code;
    //路由地址
    @Schema(name = "path",description ="路由地址")
    private String path;
    //路由名称
    @Schema(name = "name",description ="路由名称")
    private String name;
    //路由页面对应的路径
    @Schema(name = "url",description ="路由页面对应的路径")
    private String url;
    //序号
    @Schema(name = "orderNum",description ="序号")
    private Integer orderNum;
    //类型  (0 目录 1菜单)
    @Schema(name = "type",description ="类型  (0 目录 1菜单)")
    private String type;
    //菜单图标
    @Schema(name = "icon",description ="菜单图标")
    private String icon;
    //是否显示在前端
    @Schema(name = "enable",description ="是否显示在前端")
    private boolean enable;
    //菜单的子级
    //不是数据库的字段需要排除
    //实体类与json互转的时候 属性值为null的不参与序列化
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @TableField(exist = false)
    @Schema(hidden = true)
    private List<Nav> children = new ArrayList<>();
    //用于前端判断是菜单 、目录 、按钮
    @TableField(exist = false)
    @Schema(hidden = true)
    private String value;
    //该字段为非表字段
    @TableField(exist = false)
    @Schema(hidden = true)
    private Boolean open;


    @Getter
    @Setter
    @ToString
    @Schema(description = "Nav.Criteria")
    public static class Criteria {

        private Integer id;
        private String name;
        private String label;
        private String type;

    }
}
