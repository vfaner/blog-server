package com.rgh.controller;

import com.rgh.entity.User;
import com.rgh.service.UserService;
import com.rgh.service.impl.UserRoleServiceImpl;
import com.rgh.util.JJwtUtil;
import com.rgh.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 登录/注册控制器 — 不走 filter 链，直接处理
 */
@RestController
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JJwtUtil jwtUtil;
    @Autowired
    private UserService userService;

    @PostMapping("/rgh/api/login")
    public ResultUtil login(@RequestBody User loginUser, HttpServletResponse response) {
        try {
            // 先检查用户是否存在
            User dbUser = userService.findByUsername(loginUser.getUsername());
            if (dbUser == null) {
                return ResultUtil.fail(401, "用户不存在: " + loginUser.getUsername());
            }

            UsernamePasswordAuthenticationToken authToken =
                    new UsernamePasswordAuthenticationToken(loginUser.getUsername(), loginUser.getPassword());
            Authentication authentication = authenticationManager.authenticate(authToken);

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String jwt = jwtUtil.generateToken(userDetails.getUsername());
            response.setHeader(jwtUtil.getHeader(), "Bearer " + jwt);

            List<String> roles = userDetails.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority).collect(Collectors.toList());

            Map<String, Object> result = new HashMap<>();
            result.put("id", dbUser.getId());
            result.put("username", dbUser.getUsername());
            result.put("nickName", dbUser.getNickName());
            result.put("avatar", dbUser.getAvatar());
            result.put("roles", roles);
            result.put("token", "Bearer " + jwt);

            return ResultUtil.success("登录成功", result);
        } catch (Exception e) {
            System.err.println(">>> 登录失败: " + e.getClass().getName() + " - " + e.getMessage());
            e.printStackTrace();
            return ResultUtil.fail(401, "用户名或密码错误: " + e.getMessage());
        }
    }
}
