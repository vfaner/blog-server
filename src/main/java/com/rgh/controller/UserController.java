package com.rgh.controller;

import com.rgh.constant.Constant;
import com.rgh.entity.User;
import com.rgh.service.UserService;
import com.rgh.util.ResultUtil;
import com.rgh.vo.PageRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

@RestController
@RequestMapping(Constant.PATH_PREFIX + "/user")

@Tag(name = "用户数据接口", description = "用户数据操作接口")
public class UserController {

    @Autowired
    private UserService userService;

    @Operation(summary ="获取用户信息")
    @GetMapping("/getInfo")
    public ResultUtil getInfo(){
        // 从 SecurityContext 获取当前登录用户名
        org.springframework.security.core.Authentication authentication =
                org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication.getName() == null) {
            return ResultUtil.fail(401, "未登录");
        }
        String username = authentication.getName();
        com.rgh.entity.User user = userService.findByUsername(username);
        if (user == null) {
            return ResultUtil.fail(401, "用户不存在");
        }
        java.util.Map<String, Object> info = new java.util.HashMap<>();
        info.put("id", user.getId());
        info.put("username", user.getUsername());
        info.put("login_name", user.getNickName());
        info.put("nickName", user.getNickName());
        info.put("avatar", user.getAvatar());
        // 获取角色 code 列表
        java.util.List<String> roles = authentication.getAuthorities().stream()
                .map(org.springframework.security.core.GrantedAuthority::getAuthority)
                .collect(java.util.stream.Collectors.toList());
        info.put("roles", roles);
        return ResultUtil.success("查询成功", info);
    }
    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/{id}")
    public ResultUtil selectOne(@PathVariable Integer id) {

//        return ResultUtil.success(Constant.OPERATION_FIND_SUCCESS, this.userService.queryById(id));
        return ResultUtil.success(Constant.OPERATION_FIND_SUCCESS);
    }

    @GetMapping("/page-query")
    public ResultUtil findByPage(@RequestParam(required = false) String username,
                                 @RequestParam(defaultValue = "1") Integer pageNum,
                                 @RequestParam(defaultValue = "10") Integer pageSize){
        com.baomidou.mybatisplus.core.conditions.query.QueryWrapper<com.rgh.entity.User> qw =
                new com.baomidou.mybatisplus.core.conditions.query.QueryWrapper<>();
        if (username != null && !username.isEmpty()) qw.like("username", username);
        qw.orderByAsc("id");
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<com.rgh.entity.User> page =
                new com.baomidou.mybatisplus.extension.plugins.pagination.Page<>(pageNum, pageSize);
        return ResultUtil.success(Constant.OPERATION_FIND_SUCCESS, userService.page(page, qw));
    }

    /**
     * 添加
     */
    @PostMapping
    public ResultUtil add(@RequestBody User user) {
        Assert.hasText(user.getUsername(), "User#name");
        Assert.isNull(user.getId(), "User#id must empty");
//        return ResultUtil.success(Constant.OPERATION_ADD_SUCCESS, userService.insert(user));
        return ResultUtil.success(Constant.OPERATION_ADD_SUCCESS);
    }

    @RequestMapping(method = PUT, consumes = { APPLICATION_JSON_VALUE }, produces = { APPLICATION_JSON_VALUE })
    public ResultUtil update(@RequestBody User user) {
//        Assert.hasText(user.getUsername(), "User#name");
//        Assert.notNull(user.getId(), "id cannot empty");
//        User user1 = userService.queryById(user.getId());
//        user1.setUsername(user.getUsername());
//        user1.setLoginName(user.getLoginName());
//        user1.setUpdatedTime(new Date());
//        if(StringUtils.isNotBlank(user.getPassword())){
//            user1.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
//        }
//        user1.setAvatar(user.getAvatar());
//        return ResultUtil.success(Constant.OPERATION_ADD_SUCCESS, userService.update(user1));
        return ResultUtil.success(Constant.OPERATION_ADD_SUCCESS);
    }

    @GetMapping("/{id}/deletable")
    public ResultUtil deletable(@PathVariable("id") Integer id) {
//        return ResultUtil.success(Constant.OPERATION_SUCCESS,userService.queryById(id) != null);
        return ResultUtil.success(Constant.OPERATION_SUCCESS);
    }

    @DeleteMapping("/{id}")
    public ResultUtil delete(@PathVariable("id") Integer id) {
//        return ResultUtil.success(Constant.OPERATION_DELETE_SUCCESS, userService.deleteById(id));
        return ResultUtil.success(Constant.OPERATION_DELETE_SUCCESS);
    }
}
