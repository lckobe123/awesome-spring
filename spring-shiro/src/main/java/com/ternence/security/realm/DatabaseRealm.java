package com.ternence.security.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author 陶江航
 * @version 1.0
 * @date 2017/9/6
 * @description 从数据库获取数据
 */
public class DatabaseRealm implements Realm {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public String getName() {

        return "DatabaseRealm";
    }

    /**
     * 是否支持此类Token的验证
     *
     * @param token
     * @return
     * @see ModularRealmAuthenticator#doSingleRealmAuthentication(org.apache.shiro.realm.Realm, org.apache.shiro.authc.AuthenticationToken)
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        logger.info("判断是否支持" + token.getClass() + "的校验");
        return token instanceof UsernamePasswordToken;
    }

    /**
     * 返回认证信息给shiro,以便于完成接下来的验证，如果没有返回数据则抛出异常
     *
     * @param token 用户传入的token
     * @return 返回账户信息
     * @throws AuthenticationException 认证发生异常，直接抛出异常,抛出的异常会被
     *                                 {@link DefaultSecurityManager#login(Subject, AuthenticationToken)}方法拦截
     * @see ModularRealmAuthenticator#doSingleRealmAuthentication(Realm, AuthenticationToken)
     */
    @SuppressWarnings("Duplicates")
    @Override
    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {
        String username = (String) token.getPrincipal();
        //密码使用字符数组存储的，因为String的不可变性不安全
        String password = new String((char[]) token.getCredentials());
        if (!"zhang".equals(username)) {
            throw new UnknownAccountException("未知的账户 " + username);
        }
        if (!"123".equals(password)) {
            throw new IncorrectCredentialsException("密码错误 " + password);
        }
        return new SimpleAuthenticationInfo(username, password, getName());
    }

}
