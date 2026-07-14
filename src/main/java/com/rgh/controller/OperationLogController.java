package com.rgh.controller;


import com.rgh.constant.Constant;
import com.rgh.entity.OperationLog;
import com.rgh.util.ResultUtil;
import com.rgh.vo.PageRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.ALL_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * @author: rgh
 * @description: 日志控制层
 */
@RestController
@RequestMapping(Constant.PATH_PREFIX + "/log")
@Tag(name = "操作日志数据接口", description = "操作日志数据操作接口")
public class OperationLogController {



    @Operation(summary ="按条件分页查询")
    @RequestMapping(value = "/page-query" , method = GET, consumes = ALL_VALUE)
    public ResultUtil pagingByCriteria(@Validated OperationLog.Criteria criteria, @Validated PageRequest pageRequest) {
//        return ResultUtil.success(Constant.OPERATION_FIND_SUCCESS, logService.findByCriteria(criteria, pageRequest));
        return ResultUtil.success(Constant.OPERATION_FIND_SUCCESS);
    }

//    @Operation(summary ="按主键删除")
//    @RequestMapping(value = "/{id}", method = DELETE, consumes = ALL_VALUE)
//    public ResultUtil deleteById(@PathVariable(name = "id") Integer id) {
//        logService.deleteById(id);
//        return ResultUtil.success(Constant.OPERATION_DELETE_SUCCESS);
//    }
}
