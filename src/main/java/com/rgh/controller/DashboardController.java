package com.rgh.controller;

import com.rgh.constant.Constant;
import com.rgh.service.ArticleService;
import com.rgh.service.CategoryService;
import com.rgh.service.CommentService;
import com.rgh.service.TagService;
import com.rgh.service.impl.RoleServiceImpl;
import com.rgh.service.impl.UserServiceImpl;
import com.rgh.util.ResultUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * 仪表盘统计接口
 */
@RestController
@RequestMapping(Constant.PATH_PREFIX + "/dashboard")
@Tag(name = "仪表盘统计接口", description = "仪表盘数据统计")
public class DashboardController {

    @Autowired
    private ArticleService articleService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private TagService tagService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private UserServiceImpl userService;

    @Operation(summary = "统计概览")
    @GetMapping("/stats")
    public ResultUtil stats() {
        Map<String, Object> data = new HashMap<>();
        data.put("articleCount", articleService.count());
        data.put("categoryCount", categoryService.count());
        data.put("tagCount", tagService.count());
        data.put("userCount", userService.count());
        data.put("commentCount", commentService.count());

        // 各分类文章数（饼图数据）
        List<Map<String, Object>> categoryStats = new ArrayList<>();
        categoryService.findAll().forEach(cate -> {
            Map<String, Object> item = new HashMap<>();
            item.put("name", cate.getName());
            // 统计该分类下文章数
            com.baomidou.mybatisplus.core.conditions.query.QueryWrapper<com.rgh.entity.ArticleCategory> qw =
                    new com.baomidou.mybatisplus.core.conditions.query.QueryWrapper<>();
            qw.eq("category_id", cate.getId());
            long cnt = ((com.baomidou.mybatisplus.extension.service.IService<com.rgh.entity.ArticleCategory>)
                    articleCategoryService).count(qw);
            item.put("value", cnt);
            categoryStats.add(item);
        });
        data.put("categoryStats", categoryStats);

        return ResultUtil.success("查询成功", data);
    }

    @Autowired
    private com.rgh.service.ArticleCategoryService articleCategoryService;
}
