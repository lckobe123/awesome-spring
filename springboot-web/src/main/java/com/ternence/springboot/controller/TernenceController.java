package com.ternence.springboot.controller;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * create by 陶江航 at 2017/11/8 20:37
 *
 * @version 1.0
 * @email taojianghang@xinzhentech.com
 * @description 测试controller
 */
@RestController
public class TernenceController {

    @Resource
    private JdbcTemplate jdbcTemplate;

    @RequestMapping("/")
    public Object test() {

        return  jdbcTemplate.queryForList("SELECT * FROM user");
    }
}
