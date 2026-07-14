package com.rgh.filter;


import com.rgh.constant.Constant;
import com.rgh.handler.LoginFailureHandler;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: rgh  mail:817094@qq.com
 * @description: TODO
 */
@Component
public class CaptchaFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        filterChain.doFilter(request,response);
    }

//    @Autowired
//    private LoginFailureHandler loginFailureHandler;
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//
//        String url=request.getRequestURI();
//        if(Constant.PATH_LOGIN.equals(url)&& request.getMethod().equals("POST")){
//            try{
//                //校验验证码
//                validate(request);
//            }catch (Exception c){
//                //交给失败处理器
//               loginFailureHandler.onAuthenticationFailure(request,response,c);
//            }
//
//        }
//       filterChain.doFilter(request,response);
//    }
//
//    private void validate(HttpServletRequest request) {
//        String code=request.getParameter("code");//验证码
//        String key=request.getParameter("token");//验证码key
//
//        if(StringUtils.isBlank(code)||StringUtils.isBlank(key)){
//            throw new CaptchaException(ExceptionEnum.CAPTCHA_ERROR.getMsg());
//        }
//        if(!code.equals(redisUtil.hget(CommonConstant.CAPTCHA_KEY,key))){
//            throw new CaptchaException(ExceptionEnum.CAPTCHA_ERROR.getMsg());
//        }
//        //一次性使用
//        redisUtil.hdel(CommonConstant.CAPTCHA_KEY,key);
//    }
}
