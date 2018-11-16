package com.ternence.spring.skills.design.command.Invoker;

import com.ternence.spring.skills.design.command.command.Command;

import java.util.Arrays;
import java.util.Objects;

/**
 * @author TAOJIANGHANG
 * @since 2018/11/16 15:35
 */
public class FinancialLeader implements Invoker {
    private final static String name = "左总";

    private Command command;

    @Override
    public void isExecutingCommand(Command command) {
        this.command = command;
    }

    @Override
    public void execute(Object... someParameters) {
        System.out.println("The leader of named [" + name + "] send " +
                "command with params [" + Arrays.toString(someParameters) + "]");

        Objects.requireNonNull(command, "执行的命令不能为null");

        command.action(someParameters);
    }
}
