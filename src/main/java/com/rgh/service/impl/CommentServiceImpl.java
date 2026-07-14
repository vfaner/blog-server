package com.rgh.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rgh.entity.Article;
import com.rgh.entity.Comment;
import com.rgh.mapper.CommentMapper;
import com.rgh.service.CommentService;
import com.rgh.util.DateUtil;
import com.rgh.vo.CommentPage;
import com.rgh.vo.CommentVo;
import com.rgh.vo.CommentVo2;
import com.rgh.vo.PageRequest;
import com.rgh.vo.Reply;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Resource
    private ArticleServiceImpl articleService;

    @Override
    public List<CommentVo> showComment(boolean lazy, Integer pid, Integer articleId) {
        List<Comment> roots = getTrees(lazy, pid, articleId);
        List<CommentVo> commentVos = new ArrayList<>();
        for (Comment comment : roots) {
            CommentVo vo = new CommentVo();
            copyProps(comment, vo);
            vo.setCreateTime(DateUtil.getTimeDescriptionByDate(new Date(), comment.getCreatedTime()));

            Reply reply = new Reply();
            List<Comment> children = comment.getChildren();
            if (children != null && !children.isEmpty()) {
                List<Comment> processedChildren = timeTransform(children);
                reply.setList(processedChildren);
                reply.setTotal(processedChildren.size());
            } else {
                reply.setList(Collections.emptyList());
                reply.setTotal(0);
            }
            vo.setReply(reply);
            commentVos.add(vo);
        }
        return commentVos;
    }

    private List<Comment> getTrees(boolean lazy, Integer pid, Integer articleId) {
        LambdaQueryWrapper<Comment> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Comment::getArticleId, articleId);
        wrapper.eq(Comment::getState, 1);
        if (pid != null) {
            wrapper.eq(Comment::getParentId, pid);
        } else {
            wrapper.isNull(Comment::getParentId).or().eq(Comment::getParentId, 0);
        }
        wrapper.orderByAsc(Comment::getCreatedTime);

        List<Comment> comments = list(wrapper);
        if (comments.isEmpty()) {
            return Collections.emptyList();
        }

        List<Comment> processed = timeTransform(comments);

        if (!lazy) {
            for (Comment comment : processed) {
                List<Comment> children = getTrees(false, comment.getUid(), articleId);
                comment.setChildren(children);
            }
        }
        return processed;
    }

    private List<Comment> timeTransform(List<Comment> comments) {
        Date now = new Date();
        return comments.stream().peek(c -> {
            if (c.getCreatedTime() != null) {
                c.setCreateTime(DateUtil.getTimeDescriptionByDate(now, c.getCreatedTime()));
            }
        }).collect(Collectors.toList());
    }

    @Override
    public CommentPage commentPage(Comment.Criteria criteria, PageRequest pageRequest) {
        // XML 多表关联查询：评论 + 文章标题一次查完
        List<CommentVo2> allVo2List = baseMapper.selectCommentPage(criteria);
        Date now = new Date();
        for (CommentVo2 vo2 : allVo2List) {
            if (vo2.getCreatedTime() != null) {
                vo2.setCreateTime(DateUtil.getTimeDescriptionByDate(now, vo2.getCreatedTime()));
            }
        }

        // Java 内存分页
        int skip = (pageRequest.getPageNum() - 1) * pageRequest.getPageSize();
        List<CommentVo2> paged = allVo2List.stream()
                .skip(skip)
                .limit(pageRequest.getPageSize())
                .collect(Collectors.toList());

        CommentPage commentPage = new CommentPage();
        commentPage.setCommentVo2(paged);
        commentPage.setPageNum(pageRequest.getPageNum());
        commentPage.setPageSize(pageRequest.getPageSize());
        commentPage.setTotal(allVo2List.size());
        return commentPage;
    }

    @Override
    public Comment addComment(Comment comment) {
        // 如果是回复（有 parentId），从父评论继承 articleId，防止前端漏传
        if (comment.getParentId() != null && comment.getParentId() > 0) {
            if (comment.getArticleId() == null) {
                Comment parent = getById(comment.getParentId());
                if (parent != null) {
                    comment.setArticleId(parent.getArticleId());
                }
            }
        }
        comment.setState(0);
        comment.setCreatedTime(new Date());
        comment.setLike(0);
        save(comment);
        return comment;
    }

    @Override
    public void approveComment(Integer uid, Integer state) {
        Comment comment = getById(uid);
        if (comment != null) {
            comment.setState(state);
            updateById(comment);
        }
    }

    @Override
    public void likeComment(Integer uid, int delta) {
        Comment comment = getById(uid);
        if (comment != null) {
            comment.setLike(Math.max(0, comment.getLike() + delta));
            updateById(comment);
        }
    }

    private void copyProps(Comment src, Comment dst) {
        dst.setUid(src.getUid());
        dst.setParentId(src.getParentId());
        dst.setUsername(src.getUsername());
        dst.setArticleId(src.getArticleId());
        dst.setAvatar(src.getAvatar());
        dst.setLink(src.getLink());
        dst.setAddress(src.getAddress());
        dst.setContent(src.getContent());
        dst.setLevel(src.getLevel());
        dst.setLike(src.getLike());
        dst.setState(src.getState());
        dst.setCreatedTime(src.getCreatedTime());
        dst.setCreateTime(src.getCreateTime());
        dst.setChildren(src.getChildren());
    }
}
