package com.ternence.security.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;

/**
 * @author 陶江航
 * @version 1.0
 * @date 2017/9/6
 * @description 从数据库获取数据
 */
public class DatabaseRealm implements Realm {
    @Override
    public String getName() {

        return "DatabaseRealm";
    }

    /**
     * 是否支持此类Token的验证
     *
     * @param token
     * @return
     */
    @Override
    public boolean supports(AuthenticationToken token) {

        return token instanceof UsernamePasswordToken;
    }

    /**
     * 返回认证信息给shiro,以便于完成接下来的验证，如果没有返回数据则抛出异常
     *
     * @param token
     * @return
     * @throws AuthenticationException
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
