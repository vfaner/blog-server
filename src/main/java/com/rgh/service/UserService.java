package com.rgh.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rgh.entity.User;

public interface UserService extends IService<User> {
    User findByUsername(String username);
}
