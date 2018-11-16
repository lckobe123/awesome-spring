package com.ternence.spring.skills.design.command.Invoker;

import com.ternence.spring.skills.design.command.command.Command;

/**
 * 发起命令的对象,执行者不需要知道他将会让谁执行命令，命令发送出去后睡会执行，他只需要
 * 让命令执行动作即可，这样就让调用者和执行者解耦了，剩下的事情交给客户端程序员来决定你想想让谁做什么事情吧,
 * 但是也要防止命令千变万化的，造成类爆炸
 * <p>
 * 因为他不知道睡会执行命令，所以客户端就可以使用这个类随意的向任何人发出命令。而且在扩展出来新的命令和接收者的时候也同样
 * 使用，这就是解耦的作用
 *
 * @author TAOJIANGHANG
 * @since 2018/11/16 15:34
 */
public interface Invoker {

    void execute(Object... someParameters);

    void isExecutingCommand(Command command);

}
