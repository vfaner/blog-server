package com.rgh.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rgh.entity.Article;
import com.rgh.vo.ArticleVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 文章表(Article)表数据库访问层
 * 单表CRUD使用MyBatis-Plus内置方法
 * 多表关联查询使用XML Mapper（便于后续SQL优化）
 *
 * @author rgh @mail 817094@qq.com
 */
@Mapper
public interface ArticleMapper extends BaseMapper<Article> {

    /** 多表关联：按ID查文章+分类+标签（笛卡尔积聚合后返回列表，取第一个） */
    List<ArticleVo> selectArticleVoById(@Param("id") Integer id);

    /** 多表关联：批量查文章的分类ID */
    List<Map<String, Object>> selectCategoryIdsByArticleIds(@Param("articleIds") List<Integer> articleIds);

    /** 多表关联：批量查文章的标签ID */
    List<Map<String, Object>> selectTagIdsByArticleIds(@Param("articleIds") List<Integer> articleIds);

    /** 多表关联：按分类ID分页查文章（带分类+标签） */
    List<ArticleVo> selectArticlesByCategoryIdPage(@Param("categoryId") Integer categoryId);

    /** 多表关联：按标签ID分页查文章（带分类+标签） */
    List<ArticleVo> selectArticlesByTagIdPage(@Param("tagId") Integer tagId);
}
