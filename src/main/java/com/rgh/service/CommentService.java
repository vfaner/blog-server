package com.rgh.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rgh.entity.Comment;
import com.rgh.vo.CommentPage;
import com.rgh.vo.CommentVo;
import com.rgh.vo.PageRequest;

import java.util.List;

public interface CommentService extends IService<Comment> {
    List<CommentVo> showComment(boolean lazy, Integer pid, Integer articleId);
    CommentPage commentPage(Comment.Criteria criteria, PageRequest pageRequest);
    Comment addComment(Comment comment);
    void approveComment(Integer uid, Integer state);
    void likeComment(Integer uid, int delta);
}
