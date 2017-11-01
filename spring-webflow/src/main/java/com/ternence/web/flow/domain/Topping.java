package com.ternence.web.flow.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * create by 陶江航 at 2017/11/1 23:21
 *
 * @version 1.0
 * @email taojianghang@xinzhentech.com
 * @description 制作pizza的配料
 */
public class Topping {

    /**
     * 构建一个配料列表给试图使用
     *
     * @return 配料清单列表
     */
    public static List<Topping> asList() {

        /*构建一个配料列表*/
        return new ArrayList<>();
    }
}
