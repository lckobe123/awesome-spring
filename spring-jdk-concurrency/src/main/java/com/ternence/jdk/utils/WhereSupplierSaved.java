package com.ternence.jdk.utils;

import java.util.function.Supplier;

/**
 * @author TAOJIANGHANG
 * @since 2018/11/9 13:53
 */
public class WhereSupplierSaved {

    public static Supplier<String> getSthSupplier() {
        return () -> {
            System.out.println("因为值不存在,所以做了某些操作");
            throw new RuntimeException("值不存在");
        };
    }

    public static Supplier<Throwable> getSthThrowSupplier() {

        return () -> new NullPointerException("result.get()返回值为null");
    }
}
