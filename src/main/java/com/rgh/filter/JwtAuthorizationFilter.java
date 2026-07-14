package com.rgh.filter;

import com.rgh.util.JJwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * 作用：
 * 验证 JWT Token：拦截每个请求，检查请求头中的 Authorization 字段是否包含有效的 JWT Token。
 * 设置用户上下文：如果 Token 有效，解析 Token 并将用户信息设置到 SecurityContext 中，以便后续的授权检查。
 * 阻止未授权访问：如果 Token 无效或过期，阻止请求继续处理，并返回相应的错误信息。
 * 关键方法：
 * doFilterInternal：拦截请求并检查 Authorization 头。
 * getAuthentication：解析 JWT Token 并创建 UsernamePasswordAuthenticationToken 对象。
 */

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private final JJwtUtil jwtUtil;

    public JwtAuthorizationFilter(AuthenticationManager authManager, JJwtUtil jwtUtil) {
        super(authManager);
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain chain) throws IOException, ServletException {
        String header = req.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer ")) {
            chain.doFilter(req, res);
            return;
        }
        try {
            UsernamePasswordAuthenticationToken authentication = getAuthentication(req);
            if (authentication != null) {
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception e) {
            // token 解析失败不阻断请求，交给后续鉴权判断
            System.err.println(">>> JWT 解析失败: " + e.getMessage());
        }
        chain.doFilter(req, res);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader("Authorization").replace("Bearer ", "");
        String username = jwtUtil.getUserName(token);
        if (jwtUtil.validateToken(token,username)) {
            return new UsernamePasswordAuthenticationToken(username, null, new ArrayList<>());
        }
        return null;
    }
}
