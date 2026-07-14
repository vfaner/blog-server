package com.rgh.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.rgh.constant.Constant;
import com.rgh.entity.Link;
import com.rgh.service.LinkService;
import com.rgh.util.ResultUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: rgh
 * @description: 网站友联访问层
 */
@RestController
@RequestMapping(Constant.PATH_PREFIX + "/link")
@Tag(name = "网站友联数据接口", description = "网站友联数据操作接口")
public class LinkController {

    @Autowired
    private LinkService linkService;

    @Operation(summary = "友联分页查询(后台)")
    @GetMapping("/page-query")
    public ResultUtil pageQuery(
            @RequestParam(defaultValue = "1") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String link,
            @RequestParam(required = false) Boolean isEnable) {
        IPage<Link> result = linkService.getPageLinks(pageNo, pageSize, name, link, isEnable);
        return ResultUtil.success(Constant.OPERATION_FIND_SUCCESS, result);
    }

    @Operation(summary = "友联列表(前台展示)")
    @GetMapping("/query")
    public ResultUtil query() {
        return ResultUtil.success(Constant.OPERATION_FIND_SUCCESS, linkService.getAllEnabledLinks());
    }

    @Operation(summary = "新增友联")
    @PostMapping
    public ResultUtil add(@RequestBody Link link) {
        linkService.save(link);
        return ResultUtil.success(Constant.OPERATION_ADD_SUCCESS);
    }

    @Operation(summary = "更新友联")
    @PutMapping
    public ResultUtil update(@RequestBody Link link) {
        linkService.updateById(link);
        return ResultUtil.success(Constant.OPERATION_EDIT_SUCCESS);
    }

    @Operation(summary = "删除友联")
    @DeleteMapping("/{id}")
    public ResultUtil delete(@PathVariable Integer id) {
        linkService.removeById(id);
        return ResultUtil.success(Constant.OPERATION_DELETE_SUCCESS);
    }
}
