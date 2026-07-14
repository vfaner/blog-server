package com.rgh.controller;

import com.rgh.constant.Constant;
import com.rgh.entity.Category;
import com.rgh.service.CategoryService;
import com.rgh.util.ResultUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 文章分类表(Category)表控制层
 *
 * @author rgh @mail 817094@qq.com
 */
@RestController
@RequestMapping(Constant.PATH_PREFIX + "/category")
@Tag(name = "文章分类数据接口", description = "文章分类数据相关接口")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Operation(summary = "分类列表")
    @GetMapping({"/list", "/query"})
    public ResultUtil list() {
        List<Category> categories = categoryService.findAll();
        return ResultUtil.success(Constant.OPERATION_FIND_SUCCESS, categories);
    }

    @Operation(summary = "分类分页")
    @GetMapping("/page-query")
    public ResultUtil pageQuery(@RequestParam(defaultValue = "1") Integer pageNum,
                                @RequestParam(defaultValue = "10") Integer pageSize,
                                @RequestParam(required = false) String name) {
        com.baomidou.mybatisplus.core.conditions.query.QueryWrapper<Category> qw = new com.baomidou.mybatisplus.core.conditions.query.QueryWrapper<>();
        if (name != null && !name.isEmpty()) qw.like("name", name);
        qw.orderByAsc("id");
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Category> page =
                new com.baomidou.mybatisplus.extension.plugins.pagination.Page<>(pageNum, pageSize);
        return ResultUtil.success(Constant.OPERATION_FIND_SUCCESS, categoryService.page(page, qw));
    }

    @Operation(summary = "按主键查询")
    @GetMapping("/{id}")
    public ResultUtil findById(@PathVariable Integer id) {
        return ResultUtil.success(Constant.OPERATION_FIND_SUCCESS, categoryService.getById(id));
    }

    @Operation(summary = "新增分类")
    @PostMapping
    public ResultUtil add(@RequestBody Category category) {
        categoryService.save(category);
        return ResultUtil.success(Constant.OPERATION_ADD_SUCCESS, category);
    }

    @Operation(summary = "更新分类")
    @PutMapping
    public ResultUtil update(@RequestBody Category category) {
        categoryService.updateById(category);
        return ResultUtil.success(Constant.OPERATION_EDIT_SUCCESS, category);
    }

    @Operation(summary = "删除分类")
    @DeleteMapping("/{id}")
    public ResultUtil delete(@PathVariable Integer id) {
        categoryService.removeById(id);
        return ResultUtil.success(Constant.OPERATION_DELETE_SUCCESS);
    }
}
