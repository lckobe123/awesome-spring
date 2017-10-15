package com.ternence.security.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * create by Ternence at 2017/10/15 15:45
 *
 * @version 1.0
 * @email taojianghang@xinzhentech.com
 * @description 对security抛出的各种异常进行处理
 */
public class ApplicationAuthenticationEntryPoint extends LoginUrlAuthenticationEntryPoint {
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * @param loginFormUrl URL where the login page can be found. Should either be
     *                     relative to the web-app context path (include a leading {@code /}) or an absolute
     *                     URL.
     */
    public ApplicationAuthenticationEntryPoint(String loginFormUrl) {
        super(loginFormUrl);
    }

    /**
     * 开始处理异常
     *
     * @param request       请求对象
     * @param response      响应对象，处理完毕之后可以写到响应给用户
     * @param authException 认证异常
     * @throws IOException      IO异常
     * @throws ServletException servlet异常
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        super.commence(request, response, authException);
        logger.info("会话过期或者没有登录" + authException);
    }
}
