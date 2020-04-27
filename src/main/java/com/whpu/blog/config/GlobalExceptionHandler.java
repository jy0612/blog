package com.whpu.blog.config;

import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName GlobalExceptionHandler
 * @Description: 全局异常处理类
 * @Author jy
 * @Date 2020/4/20
 **/
@Configuration
public class GlobalExceptionHandler implements HandlerExceptionResolver {

    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @SneakyThrows
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        ModelAndView mv = new ModelAndView();
        logger.error("Request URL : {}, Exception : {}",request.getRequestURL(),ex);
        if(AnnotationUtils.findAnnotation(ex.getClass(), ResponseStatus.class) != null){
            throw ex;
        }
        mv.addObject("url",request.getRequestURL());
        mv.addObject("exception",ex);
        mv.setViewName("error/error");
        return mv;
    }
}
