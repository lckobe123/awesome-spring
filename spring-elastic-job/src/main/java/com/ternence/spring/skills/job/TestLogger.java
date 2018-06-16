package com.ternence.spring.skills.job;

import java.net.URL;

/**
 * 看看classpath下面是否有这个class
 *
 * @author Ternence
 * @version 1.0
 * @since 2018/6/16 16:26
 */
public class TestLogger {

    public static void main(String[] args) {
        URL url = ClassLoader.getSystemResource("org/slf4j/impl/StaticLoggerBinder.class");
        System.out.println(url);
    }
}
