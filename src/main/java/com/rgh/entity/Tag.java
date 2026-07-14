package com.rgh.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.security.SecureRandom;

import static com.rgh.constant.Constant.El_PLUS_TAGS;


/**
 * @author: rgh  mail:817094@qq.com
 * @description:
 */


@TableName("rgh_tag")
@Data
public class Tag {
    @Schema(name = "id",description="主键")
    @TableId(type = IdType.AUTO)
    private Integer id;
    @Schema(name = "name",description="标签名")
    private String name;
    private String alias;
    private String description;
    @TableField(exist = false)
    private String type = El_PLUS_TAGS.get(new SecureRandom().nextInt(5));


    @Getter
    @Setter
    @ToString
    @Schema(description = "Tag.Criteria")
    public static class Criteria {

        private Integer id;
        private String name;
        private String alias;

    }
}
