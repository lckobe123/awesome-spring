package com.ternence.security.auth;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.ExceptionTranslationFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * create by Ternence at 2017/10/14 22:43
 *
 * @version 1.0
 * @email taojianghang@xinzhentech.com
 * @description 提供给 {@link ExceptionTranslationFilter} 开始认证模式
 */
public class UnAuthEntryPoint implements AuthenticationEntryPoint {
    /**
     * 开始认证
     *
     * @param request       http 请求对象
     * @param response      http响应对象
     * @param authException 认证异常
     * @throws IOException      可能抛出的异常
     * @throws ServletException 可能抛出的异常
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        //未认证的情况下直接发送一个错误就好了
        response.sendError(
                HttpServletResponse.SC_UNAUTHORIZED,
                "Unauthorized: Authentication token was either missing or invalid.");
    }
}
