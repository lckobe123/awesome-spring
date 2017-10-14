package com.ternence.security.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * create by Ternence at 2017/10/14 22:50
 *
 * @version 1.0
 * @email taojianghang@xinzhentech.com
 * @description 提供用户信息的类
 */
public class ApplicationUserDetailsService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
