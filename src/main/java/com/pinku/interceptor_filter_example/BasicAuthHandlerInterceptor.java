package com.pinku.interceptor_filter_example;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Slf4j
public class BasicAuthHandlerInterceptor implements HandlerInterceptor {

    private static final String USERNAME = "admin";
    private static final String PASSWORD = "admin";

    /*--header 'Authorization: Basic YWRtaW46YWRtaW4=' \*/
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("BasicAuthHandlerInterceptor::preHandle()");

        String authHeader = request.getHeader("Authorization");
        if(StringUtils.hasText(authHeader) && authHeader.startsWith("Basic "));
        String base64Creds = authHeader.substring("Basic ".length());
        byte[] decode = Base64.getDecoder().decode(base64Creds);
        String credentials = new String(decode, StandardCharsets.UTF_8);
        //admin:admin
        String[] parts = credentials.split(":");

        if(USERNAME.equals(parts[0]) && PASSWORD.equals(parts[1])){
            return true;
        }

        response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Unauthorized");

        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("BasicAuthHandlerInterceptor::postHandle()");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("BasicAuthHandlerInterceptor::afterCompletion()");
    }
}
