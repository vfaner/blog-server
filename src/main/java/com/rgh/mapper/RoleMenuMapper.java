package com.rgh.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rgh.entity.RoleMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RoleMenuMapper extends BaseMapper<RoleMenu> {

    /** 通过角色 id 查询已分配的菜单 id 列表 */
    @Select("SELECT menu_id FROM rgh_role_menu WHERE role_id = #{roleId}")
    List<Integer> findMenuIdsByRoleId(Integer roleId);
}
