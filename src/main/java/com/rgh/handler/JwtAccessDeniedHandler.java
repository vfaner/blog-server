package com.rgh.handler;


import com.rgh.util.JacksonUtil;
import com.rgh.util.ResultUtil;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: rgh  mail:817094@qq.com
 * @description: 鉴权
 */
@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {
    //鉴权
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        ServletOutputStream outputStream=response.getOutputStream();
        outputStream.write(JacksonUtil.objectToByte(
                        ResultUtil.fail(HttpServletResponse.SC_FORBIDDEN,accessDeniedException.getMessage()))
        );
        outputStream.flush();
        outputStream.close();
    }
}
