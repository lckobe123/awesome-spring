package com.ternence.spring.skills.job;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Ternence
 * @version 1.0
 * @since 2018/6/16 12:19
 */
public class JobsApplicationStarterTest {
    private final static Logger LOGGER = LoggerFactory.getLogger(JobsApplicationStarter.class);

    @Test
    public void startJob() {
        LOGGER.info("start application");

        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("spring-job.xml");

        context.start();

        try {
            JobsApplicationStarter.class.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
