package com.rgh.controller;

import com.rgh.constant.Constant;
import com.rgh.entity.User;
import com.rgh.mapper.UserRoleMapper;
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

    @Autowired
    private UserRoleMapper userRoleMapper;

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
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<com.rgh.entity.User> result = userService.page(page, qw);
        // 填充每个用户的角色码列表（多对多，从 rgh_user_role join rgh_role.code 得到）
        if (result.getRecords() != null) {
            for (com.rgh.entity.User u : result.getRecords()) {
                try {
                    u.setRoles(userRoleMapper.findCodeByUserId(u.getId()));
                } catch (Exception ignore) { /* 单条查询失败不影响整页返回 */ }
            }
        }
        return ResultUtil.success(Constant.OPERATION_FIND_SUCCESS, result);
    }

    /**
     * 添加
     */
    @PostMapping
    public ResultUtil add(@RequestBody User user) {
        Assert.hasText(user.getUsername(), "User#name");
        Assert.isNull(user.getId(), "User#id must empty");
        // 密码 BCrypt 加密
        String rawPwd = user.getPassword();
        if (rawPwd == null || rawPwd.isEmpty()) rawPwd = "123456"; // 默认密码
        user.setPassword(new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder().encode(rawPwd));
        userService.save(user);
        // 保存用户-角色关联
        saveUserRoles(user.getId(), user.getRoleIds());
        return ResultUtil.success(Constant.OPERATION_ADD_SUCCESS, user);
    }

    @RequestMapping(method = PUT, consumes = { APPLICATION_JSON_VALUE }, produces = { APPLICATION_JSON_VALUE })
    public ResultUtil update(@RequestBody User user) {
        Assert.notNull(user.getId(), "id cannot empty");
        User exist = userService.getById(user.getId());
        Assert.notNull(exist, "用户不存在");
        // 只更新允许修改的字段
        exist.setNickName(user.getNickName());
        exist.setAvatar(user.getAvatar());
        exist.setEmail(user.getEmail());
        if (user.getPassword() != null && !user.getPassword().isEmpty()) {
            exist.setPassword(new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder().encode(user.getPassword()));
        }
        userService.updateById(exist);
        // 重新分配角色
        if (user.getRoleIds() != null) {
            saveUserRoles(user.getId(), user.getRoleIds());
        }
        return ResultUtil.success(Constant.OPERATION_EDIT_SUCCESS);
    }

    /** 保存用户角色关联：先清空再重建 */
    private void saveUserRoles(Integer userId, java.util.List<Integer> roleIds) {
        if (userId == null) return;
        try {
            org.springframework.jdbc.core.JdbcTemplate jdbc = jdbcTemplate();
            if (jdbc != null) {
                jdbc.update("DELETE FROM rgh_user_role WHERE user_id = ?", userId);
                if (roleIds != null) {
                    for (Integer roleId : roleIds) {
                        if (roleId != null) {
                            jdbc.update("INSERT INTO rgh_user_role (user_id, role_id) VALUES (?, ?)", userId, roleId);
                        }
                    }
                }
            }
        } catch (Exception ignore) { /* 静默 */ }
    }

    @Autowired(required = false)
    private org.springframework.jdbc.core.JdbcTemplate _jdbcTemplate;
    private org.springframework.jdbc.core.JdbcTemplate jdbcTemplate() { return _jdbcTemplate; }

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
