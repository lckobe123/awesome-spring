package com.ternence.spring.disconf;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * @author Ternence
 * @version 1.0
 * @since 2018/5/24 21:23
 */
public class Main {

    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext applicationContext = new  ClassPathXmlApplicationContext("" +
             "classpath:spring-disconf.xml");

        applicationContext.start();

        System.in.read();
    }
}
