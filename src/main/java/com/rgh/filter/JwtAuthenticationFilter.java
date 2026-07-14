package com.rgh.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rgh.entity.User;
import com.rgh.util.JJwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 作用：
 * 处理用户的登录请求：拦截 /login 端点的 POST 请求，验证用户凭据（用户名和密码）。
 * 生成 JWT Token：如果认证成功，使用 JJwtUtil 生成 JWT Token，并将该 Token 返回给客户端。
 * 集成到 Spring Security 流程：确保所有认证逻辑都通过 Spring Security 框架管理，提供一致的安全性和错误处理机制。
 * 关键方法：
 * attemptAuthentication：从请求体中读取用户名和密码，并调用 AuthenticationManager 进行认证。
 * successfulAuthentication：认证成功后，生成 JWT Token 并将其添加到响应头中。
 */

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final JJwtUtil jwtUtil;

    public JwtAuthenticationFilter(AuthenticationManager authManager, JJwtUtil jwtUtil) {
        this.authenticationManager = authManager;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            User creds = new ObjectMapper().readValue(request.getInputStream(), User.class);
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            creds.getUsername(),
                            creds.getPassword()
                    )
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

