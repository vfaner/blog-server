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
 * @description: 角色类
 */
@TableName("rgh_role")
@Data
public class Role {
    @Schema(name = "id",description="主键")
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private String code;
    private String description;

    @Getter
    @Setter
    @ToString
    @Schema(description = "Role.Criteria")
    public static class Criteria {

        private Integer id;
        private String name;
        private String code;

    }
}
