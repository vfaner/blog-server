package com.rgh.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rgh.entity.Tag;
import org.apache.ibatis.annotations.Mapper;

/**
 * 标签表(SysTag)表数据库访问层

 * @author rgh @mail 817094@qq.com
 */
@Mapper
public interface TagMapper extends BaseMapper<Tag> {

}


