package com.ternence.spring.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

/**
 * 测试使用{@link org.springframework.context.ApplicationContext}作为容器，而不是使用
 * BeanFactory作为容器
 *
 * @author Ternence
 * @version 1.0
 * @since 2018/10/5 13:40
 */
public class TestApplicationContext {
    private final static Logger logger = LoggerFactory.getLogger(TestApplicationContext.class);

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                new String[]{"applicationContext-beans.xml"}, true);
        context.start();

        String[] names = context.getBeanNamesForAnnotation(Service.class);

        for (String name : names) {
            logger.info("扫描到名为{}的service", name);
        }
        if (!context.containsBean("testService")) {
            logger.info("不包含名为testService的bean");
        }

        logger.info("刷新容器---------------------------------------------------------");

        context.refresh();
    }
}
