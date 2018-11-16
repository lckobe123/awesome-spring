package com.ternence.spring.skills.design.command.receiver;

import java.util.Arrays;

/**
 * Java开发
 *
 * @author TAOJIANGHANG
 * @since 2018/11/16 15:26
 */
public class JavaDeveloper implements TeamMember {

    private final static String name = "陶江航(Carl)";

    @Override
    public void doAction(Object... someParameters) {
        System.out.println("The developer of named [" + name + "] execute " +
                "command with params [" + Arrays.toString(someParameters) + "]");
    }
}
