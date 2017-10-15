package com.ternence.security.utils;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * create by Ternence at 2017/10/14 23:16
 *
 * @version 1.0
 * @email taojianghang@xinzhentech.com
 * @description 处理spring security的token的工具
 */
public class TokenUtils {

    /**
     * 创建token
     *
     * @param userDetails 用户信息对象
     * @return 创建的token字符串
     */
    public static String createToken(UserDetails userDetails) {

        return null;
    }

    /**
     * 计算签名
     *
     * @param userDetails 用户信息对象
     * @param expires     过期时间
     * @return 计算出来的签名字符串
     */
    public static String computeSignature(UserDetails userDetails, long expires) {

        return null;
    }

    /**
     * 从token中获取用户名称
     *
     * @param token token字符串
     * @return 用户名, 如果传入null就返回<code>null</code>
     */
    public static String getUserNameFromTOken(String token) {

        if (token == null) return null;

        return token.split(":")[0];
    }

    /**
     * 校验用户的token字符串
     *
     * @param token       字符串
     * @param userDetails 用户信息
     * @return 是否为有效的token
     */
    public static boolean validateToken(String token, UserDetails userDetails) {

        return false;
    }
}
