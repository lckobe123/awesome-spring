package com.ternence.security.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.Arrays;

/**
 * create by Ternence at 2017/10/14 22:50
 *
 * @version 1.0
 * @email taojianghang@xinzhentech.com
 * @description 提供用户信息的类
 */
public class ApplicationUserDetailsService implements UserDetailsService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SimpleGrantedAuthority authorityAdmin = new SimpleGrantedAuthority("admin");
        SimpleGrantedAuthority authorityUser = new SimpleGrantedAuthority("user");
        User user = new User("Ternence", "a123456",
                Arrays.asList(authorityAdmin, authorityUser));
        logger.info("创建用户:{}", user);
        return user;
    }
}
