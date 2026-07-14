package com.rgh.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rgh.entity.Category;
import org.apache.ibatis.annotations.Mapper;

/**
 * 文章分类表(Category)表数据库访问层
 *
 * @author rgh @mail 817094@qq.com
 */

@Mapper
public interface CategoryMapper extends BaseMapper<Category> {

}
