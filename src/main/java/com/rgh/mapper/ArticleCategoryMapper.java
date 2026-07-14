package com.rgh.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rgh.entity.ArticleCategory;
import org.apache.ibatis.annotations.Mapper;

/**
 * 文章-分类中间表(ArticleCategory)表数据库访问层
 *
 * @author rgh @mail 817094@qq.com
 */

@Mapper
public interface ArticleCategoryMapper extends BaseMapper<ArticleCategory> {

}