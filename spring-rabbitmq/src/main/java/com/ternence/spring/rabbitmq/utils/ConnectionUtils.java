package com.ternence.spring.rabbitmq.utils;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author Ternence
 * @version 1.0
 */
public class ConnectionUtils {

    public static Connection getConnection() {
        ConnectionFactory factory = new ConnectionFactory();
        try {
            factory.setHost("192.168.72.128");
            factory.setUsername("admin");
            factory.setPassword("a123456");
            factory.setPort(5672);
            factory.setVirtualHost("/");
            return factory.newConnection();
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
            return null;
        }

    }
}
