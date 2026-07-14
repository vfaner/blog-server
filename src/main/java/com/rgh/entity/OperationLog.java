package com.rgh.entity;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

import static com.rgh.constant.Constant.DATE_TIME_FORMAT;
import static com.rgh.constant.Constant.TIME_ZONE;


/**
 * @author: rgh
 * @description: 操作日志
 */
@TableName("rgh_operation_log")
@Data
public class OperationLog {

    @Schema(name = "id",description ="主键")
    @TableId(type = IdType.AUTO)
    private Integer id;

    public enum Type {
        CREATE, UPDATE, DELETE, OTHER
    }

    @EnumValue
    private Type operateType;

    private String targetId;

    private String message;
    private String operatorName;


    @DateTimeFormat(pattern = DATE_TIME_FORMAT)
    @JsonFormat(pattern = DATE_TIME_FORMAT, timezone = TIME_ZONE)
    private Date operateDate;

    @Schema(description = "OperationLog.Criteria")
    @Setter
    @Getter
    @ToString
    public static class Criteria {
        private Type operateType;
        private String operatorName;
        private String targetId;


    }


}

