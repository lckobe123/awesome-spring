package com.ternence.spring.rabbitmq.test.fanout;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;
import com.ternence.spring.rabbitmq.utils.ConnectionUtils;

import java.io.IOException;

/**
 * fanout模型的MQ消息的接收者
 *
 * @author Ternence
 * @version 1.0
 */
public class Receiver {
    private static final String QUEUE_NAME = "QUEUE_FANOUT_1";
    private static final String EXCHANGE_NAME = "EXCHANGE_FANOUT";

    @SuppressWarnings("Duplicates")
    public static void main(String[] args) throws IOException, InterruptedException {
        Connection connection = ConnectionUtils.getConnection();

        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        //绑定队列到交换机. 绑定也可在rabbitMQ的管理界面进行
        //routing key 是发送时候的routing key
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "");

        channel.basicQos(1);
        QueueingConsumer consumer = new QueueingConsumer(channel);
        channel.basicConsume(QUEUE_NAME, false, consumer);

        while (true) {
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            System.out.println("获取:" + new String(delivery.getBody()));
            Thread.sleep(100);
            //手动应答
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
        }
    }
}
