package com.rgh.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rgh.entity.Link;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author: rgh
 * @description: 网站友联(Link)表数据库访问层
 */

@Mapper
public interface LinkMapper extends BaseMapper<Link> {

}