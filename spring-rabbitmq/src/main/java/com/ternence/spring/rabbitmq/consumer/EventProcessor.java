package com.ternence.spring.rabbitmq.consumer;

/**
 * RabbitMQ消息的消费者
 *
 * @author Ternence
 * @version 1.0
 */
public interface EventProcessor {
    /**
     * 处理RabbitMQ队列中的消息
     *
     * @param message 消息内容
     * @param <T>     消息内容的类型
     */
    <T> void process(T message);
}
