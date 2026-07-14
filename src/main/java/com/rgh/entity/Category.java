package com.rgh.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/**
 * @author: rgh  mail:817094@qq.com
 * @description: 文章分类
 */

@TableName("rgh_category")
@Data
public class Category {
    @Schema(description = "id")
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private String alias;
    private String icon;//图标
    private boolean enable;//是否显示在菜单
    private String description;

    @Getter
    @Setter
    @ToString
    @Schema(description = "Category.Criteria")
    public static class Criteria  {

        private Integer id;
        private String name;
        private String alias;

    }
}
