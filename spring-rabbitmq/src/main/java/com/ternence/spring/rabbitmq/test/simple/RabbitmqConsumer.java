package com.ternence.spring.rabbitmq.test.simple;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;
import com.ternence.spring.rabbitmq.utils.ConnectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Rabbitmq的消费者
 *
 * @author Ternence
 * @version 1.0
 */
public class RabbitmqConsumer {
    private final static Logger LOGGER = LoggerFactory.getLogger(RabbitmqConsumer.class);
    private static final String QUEUE_NAME = "test_queue";

    public static void main(String[] args) throws IOException, InterruptedException {
        Connection connection = ConnectionUtils.getConnection();

        Channel channel = connection.createChannel();

        QueueingConsumer consumer = new QueueingConsumer(channel);

        //true表示有消息就自动应答
        channel.basicConsume(QUEUE_NAME, true, consumer);

        //这种方式很简单,就算生产者挂了,他也能收到消息
        while (true) {

            QueueingConsumer.Delivery delivery = consumer.nextDelivery();

            LOGGER.info(new String(delivery.getBody()));
        }
    }
}
