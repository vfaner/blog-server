package com.rgh.handler;

import com.rgh.constant.Constant;
import com.rgh.util.JacksonUtil;
import com.rgh.util.ResultUtil;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: rgh  mail:817094@qq.com
 * @description: 还没有登录访问拦截资源
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        ServletOutputStream outputStream=response.getOutputStream();
        outputStream.write(JacksonUtil.objectToByte(
                        ResultUtil.fail(HttpServletResponse.SC_UNAUTHORIZED, Constant.LOGIN_AUTHORIZATION))
        );
        outputStream.flush();
        outputStream.close();
    }
}
