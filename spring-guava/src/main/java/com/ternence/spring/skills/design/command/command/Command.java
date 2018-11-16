package com.ternence.spring.skills.design.command.command;

/**
 * Encapsulate a request as a object
 * <p>
 * 可以使用这个对象参数化客户端的请求
 *
 * @author TAOJIANGHANG
 * @version 2.2.0
 * @since 2018/11/16 14:19
 */
public interface Command {

    void action(Object... someParameters);

}
