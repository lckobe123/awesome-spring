package com.ternence.spring.test;

/**
 * 测试的被类加载器加载的类
 *
 * @author Ternence
 * @version 1.0
 * @since 2018/6/10 13:56
 */
public class MyClass {

    private String p;

    public MyClass(String s) {
    }

    void my(){}

    public void setP(String p) {
        this.p = p;
    }

    public String getP() {
        return p;
    }
}
