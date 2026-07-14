package com.rgh.controller;

import com.rgh.constant.Constant;
import com.rgh.entity.Nav;
import com.rgh.service.NavService;
import com.rgh.util.ResultUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: rgh
 * @description: 前端首页导航路由
 */
@RestController
@Tag(name = "前端导航路由", description = "前端导航路由操作接口")
@RequestMapping(Constant.PATH_PREFIX + "/nav")
public class NavController {
    @Autowired
    private NavService navService;

    @Operation(summary ="菜单列表")
    @GetMapping("/list")
    public ResultUtil getNavList(){
        List<Nav> navList = navService.getNavList();
        return ResultUtil.success("菜单读取成功",navList);
    }

    @Operation(summary ="上级菜单列表")
    @GetMapping("/parent")
    public ResultUtil getParentNavList(){
        List<Nav> navList = navService.getParentList();
        return ResultUtil.success("上级菜单读取成功",navList);
    }
    @Operation(summary ="新增")
    @PostMapping
    public ResultUtil addOne(Nav nav){
        return ResultUtil.success(Constant.OPERATION_ADD_SUCCESS, navService.insert(nav));
    }

    @Operation(summary ="更新")
    @PutMapping
    public ResultUtil putOne(Nav nav){
        return ResultUtil.success(Constant.OPERATION_EDIT_SUCCESS, navService.update(nav));
    }

    @Operation(summary ="删除")
    @DeleteMapping
    public ResultUtil deleteOne(Integer id){
        return ResultUtil.success(Constant.OPERATION_DELETE_SUCCESS, navService.deleteById(id));
    }
}
