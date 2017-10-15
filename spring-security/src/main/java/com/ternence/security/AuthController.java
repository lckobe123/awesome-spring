package com.ternence.security;

import com.ternence.security.bean.UserBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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


    @RequestMapping("/login")
    @ResponseBody
    public Object login(@RequestBody UserBean userBean) {
        //执行登录认证

        return null;
    }
}
