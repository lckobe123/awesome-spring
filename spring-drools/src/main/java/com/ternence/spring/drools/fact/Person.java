package com.ternence.spring.drools.fact;

/**
 * create by Ternence at 2017/10/5 15:09
 *
 * @version 1.0
 * @email ternence.tao@foxmail.com
 * @description Person Fact Object
 */
public class Person {
    private String name;

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                '}';
    }
}


