package com.ternence.spring.rabbitmq.test.fanout;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.ternence.spring.rabbitmq.utils.ConnectionUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * fanout模型的mq消息发送者，这种类型的交换器有助于消息的发送者和接收者解耦
 *
 * @author Ternence
 * @version 1.0
 */
public class Sender {
    private static final String EXCHANGE_NAME = "EXCHANGE_FANOUT";

    @SuppressWarnings("Duplicates")
    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        Connection connection = ConnectionUtils.getConnection();

        Channel channel = connection.createChannel();

        //创建一个exchange
        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");

        for (int i = 0; i < 5000; i++) {
            String content = "第" + i + "条消息";
            System.out.println(content);
            //exchange为空白字符串,表示默认的交换器(direct类型),队列名称作为routing key
            //direct类型的交换器是以队列名称作为routing key来查找队列的
            channel.basicPublish(EXCHANGE_NAME, "", null, content.getBytes());
            //一秒发送一个消息
            Thread.sleep(1000);
        }
        channel.close();

        connection.close();
    }
}
