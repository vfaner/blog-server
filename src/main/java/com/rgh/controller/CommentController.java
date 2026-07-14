package com.rgh.controller;

import com.rgh.constant.Constant;
import com.rgh.entity.Comment;
import com.rgh.service.CommentService;
import com.rgh.util.ResultUtil;
import com.rgh.vo.PageRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.ALL_VALUE;

/**
 * @author: rgh
 * @description: 评论控制层
 */
@RestController
@RequestMapping(Constant.PATH_PREFIX + "/comment")
@Tag(name = "文章评论数据接口", description = "文章评论数据相关的接口")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Operation(summary = "查询评论树（支持懒加载）")
    @GetMapping(value = "/tree", consumes = ALL_VALUE)
    public ResultUtil tree(
            @RequestParam(value = "lazy", required = false, defaultValue = "false") boolean lazy,
            @RequestParam(value = "pid", required = false) Integer pid,
            @RequestParam(value = "articleId", required = false) Integer articleId) {
        return ResultUtil.success(Constant.OPERATION_FIND_SUCCESS,
                commentService.showComment(lazy, pid, articleId));
    }

    @Operation(summary = "后台评论展示")
    @GetMapping(value = "/showList", consumes = ALL_VALUE)
    public ResultUtil showComments(Comment.Criteria criteria, PageRequest pageRequest) {
        return ResultUtil.success(Constant.OPERATION_FIND_SUCCESS,
                commentService.commentPage(criteria, pageRequest));
    }

    @Operation(summary = "提交评论")
    @PostMapping
    public ResultUtil submit(@RequestBody Comment comment) {
        return ResultUtil.success("评论提交成功，等待审核", commentService.addComment(comment));
    }

    @Operation(summary = "审核评论")
    @PutMapping("/approve/{uid}")
    public ResultUtil approve(@PathVariable Integer uid, @RequestParam Integer state) {
        commentService.approveComment(uid, state);
        return ResultUtil.success("审核完成");
    }

    @Operation(summary = "点赞/取消点赞")
    @PutMapping("/like/{uid}")
    public ResultUtil like(@PathVariable Integer uid, @RequestParam(defaultValue = "1") int delta) {
        commentService.likeComment(uid, delta);
        return ResultUtil.success("操作成功");
    }

    @Operation(summary = "删除评论")
    @DeleteMapping("/{uid}")
    public ResultUtil delete(@PathVariable Integer uid) {
        commentService.removeById(uid);
        return ResultUtil.success(Constant.OPERATION_DELETE_SUCCESS);
    }
}
