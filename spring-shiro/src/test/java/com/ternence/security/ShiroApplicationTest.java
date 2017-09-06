package com.ternence.security;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.env.DefaultEnvironment;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author 陶江航
 * @version 1.0
 * @date 2017/9/6
 * @description 测试shiro的功能
 */
public class ShiroApplicationTest {

    @Test
    public void testShiroLogin() {
        /*DefaultEnvironment实现了线程安全，这里可以好好学习一下是如何实现线程安全的*/
        //默认会去找classpath:shiro.ini
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-realm.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();//模拟一个用户
        //模拟用户登陆UsernamePasswordToken是账户密码，也就是身份信息
        UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            e.printStackTrace();
            System.err.println("登录失败");
        }
        Assert.assertEquals(true, subject.isAuthenticated());
        System.out.println("登陆成功");
        //登出系统，就是清除session而已
        subject.logout();
    }

    /**
     * 测试shiro的授权
     */
    @Test
    public void testAuthorization() {
        Factory<SecurityManager> factory = new IniSecurityManagerFactory(
                "classpath:shiro-roles.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();//模拟一个用户
        //模拟用户登陆UsernamePasswordToken是账户密码，也就是身份信息
        UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            e.printStackTrace();
            System.err.println("登录失败");
        }
        System.out.println("登陆成功");
        Assert.assertEquals(true, subject.hasRole("role_a"));
        System.out.println("有角色role_a");
        Assert.assertEquals(true, subject.isPermitted("user:create"));
        System.out.println("有权限user:create");
    }

}