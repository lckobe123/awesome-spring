package com.ternence.spring.rabbitmq.producer.impl;

import com.ternence.spring.rabbitmq.exception.SendRefuseException;
import com.ternence.spring.rabbitmq.producer.EventTemplate;
import org.omg.IOP.CodecFactory;

/**
 * 生产者的默认实现
 *
 * @author Ternence
 * @version 1.0
 */
public class DefaultEventTemplate implements EventTemplate {

    @Override
    public <T> void send(String queueName, String exchangeName, T eventContent)
            throws SendRefuseException {

    }

    @Override
    public <T> void send(String queueName, String exchangeName, T eventContent, CodecFactory codecFactory)
            throws SendRefuseException {

    }
}
