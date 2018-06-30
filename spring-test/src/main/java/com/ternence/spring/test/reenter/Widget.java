package com.ternence.spring.test.reenter;

/**
 * @author Ternence
 * @version 1.0
 * @since 2018/6/23 12:03
 */
public class Widget {

    public synchronized void doSomething() {

        System.out.println("super's doSomething print this is :" + this);
    }
}
