package com.rgh.controller;

import com.rgh.constant.Constant;
import com.rgh.dto.ArticleDto;
import com.rgh.dto.EditArticleDto;
import com.rgh.entity.Article;
import com.rgh.service.ArticleService;
import com.rgh.util.ResultUtil;
import com.rgh.vo.PageRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.ALL_VALUE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * 文章表(Article)表控制层
 *
 * @author rgh @mail 817094@qq.com
 */
@RestController
@RequestMapping(Constant.PATH_PREFIX + "/article")
@Tag(name = "文章数据接口", description = "文章相关的操作接口")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Operation(summary = "按主键查询")
    @RequestMapping(value = "/{id}", method = GET, consumes = ALL_VALUE)
    public ResultUtil findById(@PathVariable(name = "id") Integer id) {
        // 阅读量 +1
        articleService.incrementView(id);
        return ResultUtil.success(Constant.OPERATION_FIND_SUCCESS, articleService.findArticleVoById(id));
    }

    @Operation(summary = "文章点赞")
    @PutMapping("/like/{id}")
    public ResultUtil like(@PathVariable Integer id) {
        int likes = articleService.likeArticle(id);
        return ResultUtil.success("点赞成功", likes);
    }

    @Operation(summary = "按条件分页查询")
    @RequestMapping(value = "/page-query", method = GET, consumes = ALL_VALUE)
    public ResultUtil pagingByCriteria(@Validated Article.Criteria criteria, @Validated PageRequest pageRequest) {
        return ResultUtil.success(Constant.OPERATION_FIND_SUCCESS, articleService.findArticlePage(criteria, pageRequest));
    }

    @Operation(summary = "新增")
    @RequestMapping(method = POST, consumes = {APPLICATION_JSON_VALUE}, produces = {APPLICATION_JSON_VALUE})
    public ResultUtil add(@RequestBody ArticleDto model) {
        return ResultUtil.success(Constant.OPERATION_ADD_SUCCESS, articleService.addArticle(model));
    }

    @Operation(summary = "更新")
    @RequestMapping(method = PUT, consumes = {APPLICATION_JSON_VALUE}, produces = {APPLICATION_JSON_VALUE})
    public ResultUtil modify(@RequestBody EditArticleDto model) {
        return ResultUtil.success(Constant.OPERATION_EDIT_SUCCESS, articleService.modifyArticle(model));
    }

    @Operation(summary = "按主键删除")
    @RequestMapping(value = "/{id}", method = DELETE, consumes = ALL_VALUE)
    public ResultUtil deleteById(@PathVariable(name = "id") Integer id) {
        articleService.deleteArticleById(id);
        return ResultUtil.success(Constant.OPERATION_DELETE_SUCCESS);
    }

    @GetMapping("/search")
    public ResultUtil searchKeyWord(String keyword) {
        Article.Criteria criteria = new Article.Criteria();
        criteria.setKeyword(keyword);
        return ResultUtil.success(Constant.OPERATION_FIND_SUCCESS, articleService.findArticlePage(criteria, new PageRequest()));
    }

    @Operation(summary = "某分类/标签下的文章")
    @GetMapping("/{type}/{id}")
    public ResultUtil underArticles(@PathVariable String type, @PathVariable Integer id) {
        return ResultUtil.success(Constant.OPERATION_FIND_SUCCESS, articleService.findUnderArticlesById(type, id));
    }

    @Operation(summary = "首页文章列表")
    @GetMapping("/list_show")
    public ResultUtil listShow(@Validated PageRequest pageRequest) {
        return ResultUtil.success(Constant.OPERATION_FIND_SUCCESS, articleService.findListShow(pageRequest));
    }

    @Operation(summary = "侧栏文章最新/随机/热门")
    @GetMapping("/random")
    public ResultUtil randomLimit(String type, Integer limit) {
        return ResultUtil.success(Constant.OPERATION_FIND_SUCCESS, articleService.randomLimit(type, limit));
    }

    @GetMapping("/category")
    public ResultUtil categoryArticles(@RequestParam(value = "cateId") Integer cateId,
                                       @RequestParam(required = false, defaultValue = "6") Integer limit) {
        return ResultUtil.success(Constant.OPERATION_FIND_SUCCESS, articleService.findCateUnderArticlesById(cateId, limit));
    }
}
