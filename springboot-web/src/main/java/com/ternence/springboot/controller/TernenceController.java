package com.ternence.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * create by 陶江航 at 2017/11/8 20:37
 *
 * @version 1.0
 * @email taojianghang@xinzhentech.com
 * @description 测试controller
 */
@Controller
public class TernenceController {

    @RequestMapping("/test")
    public Object test() {

        return "this is a word";
    }
}
