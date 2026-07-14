package com.rgh.controller;

import com.rgh.constant.Constant;
import com.rgh.entity.Tag;
import com.rgh.service.ArticleTagService;
import com.rgh.service.TagService;
import com.rgh.util.ResultUtil;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 标签表(Tag)表控制层
 *
 * @author rgh @mail 817094@qq.com
 */
@RestController
@RequestMapping(Constant.PATH_PREFIX + "/tag")
@io.swagger.v3.oas.annotations.tags.Tag(name = "文章标签数据接口", description = "文章标签数据操作接口")
public class TagController {

    @Autowired
    private TagService tagService;

    @Autowired
    private ArticleTagService articleTagService;

    @Operation(summary = "标签列表")
    @GetMapping({"/list", "/query"})
    public ResultUtil list() {
        return ResultUtil.success(Constant.OPERATION_FIND_SUCCESS, tagService.findAll());
    }

    @Operation(summary = "标签分页")
    @GetMapping("/page-query")
    public ResultUtil pageQuery(@RequestParam(defaultValue = "1") Integer pageNum,
                                @RequestParam(defaultValue = "10") Integer pageSize,
                                @RequestParam(required = false) String name) {
        com.baomidou.mybatisplus.core.conditions.query.QueryWrapper<com.rgh.entity.Tag> qw = new com.baomidou.mybatisplus.core.conditions.query.QueryWrapper<>();
        if (name != null && !name.isEmpty()) qw.like("name", name);
        qw.orderByAsc("id");
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<com.rgh.entity.Tag> page =
                new com.baomidou.mybatisplus.extension.plugins.pagination.Page<>(pageNum, pageSize);
        return ResultUtil.success(Constant.OPERATION_FIND_SUCCESS, tagService.page(page, qw));
    }

    @Operation(summary = "标签云")
    @GetMapping("/tag-cloud")
    public ResultUtil elTagVos() {
        return ResultUtil.success(Constant.OPERATION_FIND_SUCCESS, tagService.getTagList());
    }

    @Operation(summary = "侧栏标签展示带文章数量")
    @GetMapping("/tag-count")
    public ResultUtil tagWithCount() {
        return ResultUtil.success(Constant.OPERATION_FIND_SUCCESS, articleTagService.tagWithCount());
    }

    @Operation(summary = "新增标签")
    @PostMapping
    public ResultUtil add(@RequestBody Tag tag) {
        tagService.save(tag);
        return ResultUtil.success(Constant.OPERATION_ADD_SUCCESS, tag);
    }

    @Operation(summary = "更新标签")
    @PutMapping
    public ResultUtil update(@RequestBody Tag tag) {
        tagService.updateById(tag);
        return ResultUtil.success(Constant.OPERATION_EDIT_SUCCESS, tag);
    }

    @Operation(summary = "删除标签")
    @DeleteMapping("/{id}")
    public ResultUtil delete(@PathVariable Integer id) {
        tagService.removeById(id);
        return ResultUtil.success(Constant.OPERATION_DELETE_SUCCESS);
    }
}
