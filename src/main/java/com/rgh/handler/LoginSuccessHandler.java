package com.rgh.handler;

import com.rgh.entity.User;
import com.rgh.service.UserService;
import com.rgh.util.JJwtUtil;
import com.rgh.util.JacksonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: rgh  mail:817094@qq.com

 * @description: 登录成功后执行的方法
 */
@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    private JJwtUtil jJwtUtil;
    @Autowired
    private UserService service;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");
        ServletOutputStream outputStream=response.getOutputStream();
        //用户信息

        UserDetails user = (UserDetails) authentication.getPrincipal();
        User byUsername = service.findByUsername(user.getUsername());

        // 获取角色列表并转换为 List<String>
        List<String> roles = user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());


        byUsername.setRoles(roles);
//        LoginResult loginResult = new LoginResult();
//        loginResult.setCode(CommonConstant.SUCCESS_CODE);
//        loginResult.setUserId(user.getId());
//        //生成jwt,并放置请求头里面
        String jwt = jJwtUtil.generateToken(authentication.getName());

        response.setHeader(jJwtUtil.getHeader(),"Bearer "+jwt);
        outputStream.write(JacksonUtil.objectToByte(byUsername));
        outputStream.flush();
        outputStream.close();
    }
}
