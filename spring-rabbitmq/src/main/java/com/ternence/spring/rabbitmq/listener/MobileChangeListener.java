package com.ternence.spring.rabbitmq.listener;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Component;

/**
 * @author Ternence
 * @version 1.0
 * @since 2018/7/5 0:14
 */
@Component
public class MobileChangeListener implements MessageListener {

    @Override
    public void onMessage(Message message) {
        System.out.println(new String(message.getBody()));
    }
}
