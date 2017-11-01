package com.ternence.web.flow.ex;

/**
 * create by 陶江航 at 2017/11/1 21:07
 *
 * @version 1.0
 * @email taojianghang@xinzhentech.com
 * @description 账户未发现异常
 */
public class CustomerNotFoundException extends Exception {
    public CustomerNotFoundException() {
    }

    public CustomerNotFoundException(String message) {
        super(message);
    }
}
