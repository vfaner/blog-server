package com.rgh.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;

/**
 * @author: rgh
 * @description: 用户个人信息
 */
@Data
public class UserInfo implements Serializable {
    private Integer id;
    //用户名
    private String username;
    //头像
    private String avatar;
    //登录名
    private String login_name;
    //密码
    @JsonIgnore
    private String password;
    //邮箱
    private String email;
    //权限集合
    private Object[] roles;
}
