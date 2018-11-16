package com.ternence.spring.skills.design.command.receiver;

import java.util.Arrays;

/**
 * 自动化测试
 *
 * @author TAOJIANGHANG
 * @since 2018/11/16 15:28
 */
public class AutomationTester implements TeamMember {

    private final static String name = "魏露(Lucy)";

    @Override
    public void doAction(Object... someParameters) {
        System.out.println("The automation tester of named [" + name + "] execute " +
                "command with params [" + Arrays.toString(someParameters) + "]");
    }
}
