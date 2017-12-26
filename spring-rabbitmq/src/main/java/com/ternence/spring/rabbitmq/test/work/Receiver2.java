package com.ternence.spring.rabbitmq.test.work;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;
import com.ternence.spring.rabbitmq.utils.ConnectionUtils;

import java.io.IOException;

/**
 * work模型的MQ消息的接收者
 *
 * @author Ternence
 * @version 1.0
 */
public class Receiver2 {
    private static final String QUEUE_NAME = "QUEUE_WORK";

    @SuppressWarnings("Duplicates")
    public static void main(String[] args) throws IOException, InterruptedException {

        Connection connection = ConnectionUtils.getConnection();

        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        channel.basicQos(1);

        QueueingConsumer consumer = new QueueingConsumer(channel);

        channel.basicConsume(QUEUE_NAME,consumer);
        while (true) {
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();

            String message = new String(delivery.getBody());

            System.out.println("收到消息:" + message);

            Thread.sleep(200);

            channel.basicAck(delivery.getEnvelope().getDeliveryTag(),false);
        }

    }
}
