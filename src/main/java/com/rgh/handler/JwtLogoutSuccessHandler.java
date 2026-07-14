package com.rgh.handler;

import com.rgh.util.JJwtUtil;
import com.rgh.util.JacksonUtil;
import com.rgh.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: rgh  mail:817094@qq.com
 * @description: 退出登录成功处理器 清空token
 */
//退出操作
    @Component
public class JwtLogoutSuccessHandler implements LogoutSuccessHandler {
    @Autowired
    JJwtUtil jJwtUtil;
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
       if(authentication != null){
           new SecurityContextLogoutHandler().logout(request,response,authentication);
       }
        response.setContentType("application/json;charset=UTF-8");
        ServletOutputStream outputStream=response.getOutputStream();

        //生成jwt,并放置请求头里面

        response.setHeader(jJwtUtil.getHeader(),"");

        outputStream.write(JacksonUtil.objectToByte(ResultUtil.success()));
        outputStream.flush();
        outputStream.close();
    }
}
