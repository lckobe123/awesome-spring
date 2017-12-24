package com.ternence.spring.rabbitmq.producer;

import com.ternence.spring.rabbitmq.exception.SendRefuseException;
import org.omg.IOP.CodecFactory;

/**
 * RabbitMQ 生产者接口
 *
 * @author Ternence
 * @version 1.0
 */
public interface EventTemplate {
    /**
     * 发送消息
     *
     * @param queueName    队列名称
     * @param exchangeName 交换器名称
     * @param eventContent 需要发送的消息的内容
     * @param <T>          需要发送的消息的内容类型
     * @throws SendRefuseException 发送被RabbitMQ拒绝时抛出的异常
     */
    <T> void send(String queueName, String exchangeName, T eventContent) throws SendRefuseException;

    /**
     * 发送消息
     *
     * @param queueName    队列名称
     * @param exchangeName 交换器名称
     * @param eventContent 需要发送的消息的内容
     * @param codecFactory ?
     * @param <T>          需要发送的消息的内容类型
     * @throws SendRefuseException 发送被RabbitMQ拒绝时抛出的异常
     */
    <T> void send(String queueName, String exchangeName, T eventContent, CodecFactory codecFactory) throws SendRefuseException;
}
