package com.rgh.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rgh.entity.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * (UserRole)表数据库访问层
 *
 * @author rgh @mail 817094@qq.com
 */
@Mapper
public interface UserRoleMapper extends BaseMapper<UserRole> {
    //通过userId查询code
    @Select("select code from rgh_role where id in (select role_id from rgh_user_role where user_id = #{userId})")
    List<String> findCodeByUserId(Integer userId);

}


