package com.ternence.web.flow.bean;

import java.io.Serializable;

/**
 * create by 陶江航 at 2017/10/30 22:11
 *
 * @version 1.0
 * @email taojianghang@xinzhentech.com
 * @description 顾客信息
 */
public class Customer implements Serializable {
    //电话号码
    private String phoneNumber;
    //区域码
    private String zipCode;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "phoneNumber='" + phoneNumber + '\'' +
                "zipCode='" + zipCode + '\'' +
                '}';
    }
}
