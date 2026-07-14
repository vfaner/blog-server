package com.rgh.controller;

import com.rgh.constant.Constant;
import com.rgh.entity.HomeCard;
import com.rgh.service.HomeCardService;
import com.rgh.util.ResultUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 首页动态卡片配置控制层
 */
@RestController
@RequestMapping(Constant.PATH_PREFIX + "/home-card")
@Tag(name = "首页卡片配置接口", description = "首页卡片配置相关接口")
public class HomeCardController {

    @Autowired
    private HomeCardService homeCardService;

    @Operation(summary = "获取首页渲染卡片（前台）")
    @GetMapping("/render")
    public ResultUtil render() {
        return ResultUtil.success("查询成功", homeCardService.getRenderCards());
    }

    @Operation(summary = "卡片配置列表（后台）")
    @GetMapping("/list")
    public ResultUtil list() {
        return ResultUtil.success("查询成功", homeCardService.getEnabledCards());
    }

    @Operation(summary = "新增卡片配置")
    @PostMapping
    public ResultUtil add(@RequestBody HomeCard card) {
        homeCardService.save(card);
        return ResultUtil.success(Constant.OPERATION_ADD_SUCCESS, card);
    }

    @Operation(summary = "更新卡片配置")
    @PutMapping
    public ResultUtil update(@RequestBody HomeCard card) {
        homeCardService.updateById(card);
        return ResultUtil.success(Constant.OPERATION_EDIT_SUCCESS, card);
    }

    @Operation(summary = "删除卡片配置")
    @DeleteMapping("/{id}")
    public ResultUtil delete(@PathVariable Integer id) {
        homeCardService.removeById(id);
        return ResultUtil.success(Constant.OPERATION_DELETE_SUCCESS);
    }
}
