package com.ternence.spring.dubbo.service;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * @author Ternence
 * @version 1.0
 * @since 2018/7/4 20:55
 */
public class DubboProviderApplicationStarter {

    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "classpath:spring-application.xml");
        context.start();

        Runtime.getRuntime().addShutdownHook(new Thread(context::destroy));

        System.in.read();
    }
}
