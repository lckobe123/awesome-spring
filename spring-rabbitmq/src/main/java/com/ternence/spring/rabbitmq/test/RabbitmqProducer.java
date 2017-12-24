package com.ternence.spring.rabbitmq.test;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.ternence.spring.rabbitmq.utils.ConnectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author Ternence
 * @version 1.0
 */
public class RabbitmqProducer {
    private final static Logger LOGGER = LoggerFactory.getLogger(RabbitmqProducer.class);
    private static final String QUEUE_NAME = "test_queue";

    public static void main(String[] args) {
        RabbitmqProducer starter = new RabbitmqProducer();
        starter.testSendMessage();
    }


    private void testSendMessage() {
        Connection connection = null;
        Channel channel = null;
        try {
            connection = ConnectionUtils.getConnection();
            if (connection == null) throw new NullPointerException("获取连接失败");
            //创建信道
            channel = connection.createChannel();
            //申明队列,不存在则创建
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            String message = "Hello World for RabbitMQ";
            //发送一条基本的信息
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());

        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.error("发送消息的过程发生失败");
        } finally {
            //关闭信道
            try {
                if (channel != null) channel.close();
            } catch (TimeoutException | IOException e) {
                e.printStackTrace();
                //处理关闭连接失败的情况
                LOGGER.error("关闭信道失败");
            }
            try {
                if (connection != null) connection.close();
            } catch (IOException e) {
                e.printStackTrace();
                LOGGER.error("关闭连接失败");
            }
        }
    }
}
