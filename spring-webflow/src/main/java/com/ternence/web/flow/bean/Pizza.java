package com.ternence.web.flow.bean;

import java.io.Serializable;

/**
 * create by 陶江航 at 2017/10/30 22:12
 *
 * @version 1.0
 * @email taojianghang@xinzhentech.com
 * @description 披萨信息
 */
public class Pizza implements Serializable {

    private String size;
    private String color;

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Pizza{" +
                "size='" + size + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
