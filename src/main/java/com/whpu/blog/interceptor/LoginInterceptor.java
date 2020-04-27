package com.whpu.blog.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName LoginInterceptor
 * @Description: 登录拦截器，未登录将拦截其他请求，返回登陆页面
 * @Author jy
 * @Date 2020/4/21
 **/
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object user = request.getSession().getAttribute("user");
        if(user != null){
            return true;
        }else{
            response.sendRedirect("/admin");
            return false;
        }
    }
}
