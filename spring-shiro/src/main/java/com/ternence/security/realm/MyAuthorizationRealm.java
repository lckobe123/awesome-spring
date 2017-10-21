package com.ternence.security.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * @author 陶江航
 * @version 1.0
 * @date 2017/9/6
 * @description 授权信息获取的Realm，继承了认证的Realm，cache的Realm，可以通用几乎上
 * <p>
 * 如果在ini中配置了这个Realm之后就会调用这里定义的方式进行授权和权限匹配
 */
public class MyAuthorizationRealm extends AuthorizingRealm {
    @Override
    public String getName() {

        return "MyAuthorizationRealm";
    }

    /**
     * 授权，得到用户的身份信息之后，根据身份信息查询数据库得到相应的权限和角色的集合，然后返回这些信息
     * <p>
     * 之后使用hasRole isPermitted 等方法进行权限，角色的匹配，也就是真正的授权过程
     *
     * @param principals 身份信息的集合
     * @return 返回授权信息
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("开始授权");
        //查询授权信息，然后返回
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        /*给他一个创建的权限*/
        //为什么这样写可以看Permission类的解析规则
        authorizationInfo.addStringPermission("user:create");
        /*给他一个role_a角色*/
        authorizationInfo.addRole("role_a");
        return authorizationInfo;
    }

    /**
     * 授权的时候判断是否有这个角色，如果维护一个角色-权限的表，这就等于拥有了一组权限
     *
     * @param roleIdentifier
     * @param info
     * @return
     */
    @Override
    protected boolean hasRole(String roleIdentifier, AuthorizationInfo info) {
        System.out.println("匹配角色" + roleIdentifier);
        /*if (roleIdentifier != null && roleIdentifier.trim().length() > 0) {
            if ("role_a".equals(roleIdentifier))
                return true;
        }*/
        return super.hasRole(roleIdentifier, info);
    }

    /**
     * 授权的时候判断是否具有这个权限
     *
     * @param permission
     * @param info
     * @return
     */
    @Override
    protected boolean isPermitted(Permission permission, AuthorizationInfo info) {
        System.out.println("匹配权限" + permission);
        return super.isPermitted(permission, info);
    }

    @SuppressWarnings("Duplicates")
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("开始认证");
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
