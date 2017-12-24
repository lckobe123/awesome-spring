package com.ternence.spring.rabbitmq.exception;

/**
 * RabbitMQ拒绝发送消息的异常
 *
 * @author Ternence
 * @version 1.0
 */
public class SendRefuseException extends Exception {
    public SendRefuseException() {
        super();
    }

    public SendRefuseException(String message) {
        super(message);
    }
}
