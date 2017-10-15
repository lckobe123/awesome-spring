package com.ternence.security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * create by Ternence at 2017/10/14 18:40
 *
 * @version 1.0
 * @email taojianghang@xinzhentech.com
 * @description 认证相关的controller
 */
@Controller
@RequestMapping("/auth")
public class AuthController {


    @RequestMapping("/index")
    public String loginSuccess() {
        //执行登录认证

        return "index";
    }

    @RequestMapping("/failure")
    public String loginFailure() {

        return "failure";
    }
}
