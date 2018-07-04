package com.ternence.spring.rabbitmq;

import com.ternence.spring.rabbitmq.producer.ChangeMobileProducer;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * @author Ternence
 * @version 1.0
 * @since 2018/7/4 23:00
 */
public class SpringRabbitMqStarter {

    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "classpath:spring-application.xml"
        );

        context.start();

        ChangeMobileProducer producer = context.getBean(ChangeMobileProducer.class);

        producer.send();

        System.in.read();
    }
}
