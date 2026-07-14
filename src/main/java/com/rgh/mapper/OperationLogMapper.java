package com.rgh.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rgh.entity.OperationLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author: rgh
 * @description: 操作日志dao
 */

@Mapper
public interface OperationLogMapper extends BaseMapper<OperationLog> {

}