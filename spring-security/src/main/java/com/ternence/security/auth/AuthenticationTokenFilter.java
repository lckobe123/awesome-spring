package com.ternence.security.auth;

import com.ternence.security.utils.TokenUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * create by Ternence at 2017/10/14 23:03
 *
 * @version 1.0
 * @email taojianghang@xinzhentech.com
 * @description 处理用户登录时候的token信息的filter
 */
public class AuthenticationTokenFilter extends GenericFilterBean {
    private UserDetailsService userDetailsService;
    private Logger logger = LoggerFactory.getLogger(getClass());

    public AuthenticationTokenFilter(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    /**
     * 处理token
     *
     * @param request  请求对象
     * @param response 响应对象
     * @param chain    filter执行链条
     * @throws IOException      可能发生的异常
     * @throws ServletException 可能发生的异常
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        logger.info("AuthenticationTokenFilter doFilter");
        HttpServletRequest httpReq = (HttpServletRequest) request;
        //获取token
        String authToken = extractAuthTokenFromRequest(httpReq);
        String username = TokenUtils.getUserNameFromTOken(authToken);
        if (username != null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            //验证token是否有效
            if (TokenUtils.validateToken(authToken, userDetails)) {
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpReq));
                //把token放到上下文环境当中
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        chain.doFilter(request, response);
    }

    /**
     * 从请求对象中获取token header
     *
     * @param httpRequest 请求对象
     * @return token
     */
    private String extractAuthTokenFromRequest(HttpServletRequest httpRequest) {
        String authToken = httpRequest.getHeader("X-Auth-Token");
        if (authToken == null) {
            authToken = httpRequest.getParameter("token");
        }
        return authToken;
    }
}
