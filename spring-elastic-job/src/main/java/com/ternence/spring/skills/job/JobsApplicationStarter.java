package com.ternence.spring.skills.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 启动spring容器
 *
 * @author Ternence
 * @version 1.0
 * @since 2018/6/16 11:25
 */
public class JobsApplicationStarter {
    private final static Logger LOGGER = LoggerFactory.getLogger(JobsApplicationStarter.class);

    public static void main(String[] args) {
        LOGGER.info("start application");

        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("spring-job.xml");

        context.start();
    }
}
