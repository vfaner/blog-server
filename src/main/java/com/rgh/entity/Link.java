package com.rgh.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


/**
 * @author: rgh
 * @description: 网站友联
 */
@Data
@TableName("rgh_link")
public class Link {
    @Schema(name = "id",description="主键")
    @TableId(type = IdType.AUTO)
    private Integer id;
    @Schema(name = "name",description="网站名称")
    private String name;
    @Schema(name = "link",description="网站链接")
    private String link;
    private String description;
    private boolean isEnable;

}
