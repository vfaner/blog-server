package com.rgh.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rgh.entity.Comment;
import com.rgh.vo.CommentVo2;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 评论表(Comment)表数据库访问层
 * 单表CRUD使用MyBatis-Plus内置方法
 * 多表关联查询使用XML Mapper
 *
 * @author rgh
 */
@Mapper
public interface CommentMapper extends BaseMapper<Comment> {

    /** 多表关联：评论列表 + 文章标题（后台管理用） */
    List<CommentVo2> selectCommentPage(@Param("criteria") Comment.Criteria criteria);
}
