package com.rgh.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rgh.entity.UserRole;
import com.rgh.mapper.RoleMapper;
import com.rgh.mapper.UserMapper;
import com.rgh.mapper.UserRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * (UserRole)表服务实现类
 *
 * @author rgh @mail 817094@qq.com
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> {

    @Autowired
    private UserRoleMapper userRoleMapper;


    public List<String> getRoleCodesByUserId(Integer userId) {
        return userRoleMapper.findCodeByUserId(userId);
    }
}


