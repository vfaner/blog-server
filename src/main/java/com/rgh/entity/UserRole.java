package com.rgh.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author: rgh  mail:817094@qq.com
 * @description: 用户角色表
 */
@TableName("rgh_user_role")
@Data
public class UserRole {
    @Schema(name = "id",description="主键")
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer userId;
    private Integer roleId;


}
