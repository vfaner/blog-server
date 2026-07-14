package com.rgh.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rgh.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 角色表(Role)表数据库访问层
 *
 * @author rgh @mail 817094@qq.com
 */

@Mapper
public interface RoleMapper extends BaseMapper<Role> {

    @Select("select sr.code from rgh_user_role sur left join rgh_role sr on sur.role_id = sr.id " +
            "where sur.user_id = #{id}")
    List<String> findRoleUserId(Integer id);

    @Select("select sur.user_id from rgh_user_role sur " +
            "where sur.role_id = #{id}")
    List<Integer> findUserId(Integer roleId);
}
