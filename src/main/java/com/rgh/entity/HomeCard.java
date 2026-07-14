package com.rgh.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@TableName("rgh_home_card")
@Schema(description = "首页动态卡片配置")
public class HomeCard {
    @Schema(description = "主键ID")
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer categoryId;
    private String title;
    private Integer articleLimit;
    private Integer sortOrder;
    private boolean enable;
}
