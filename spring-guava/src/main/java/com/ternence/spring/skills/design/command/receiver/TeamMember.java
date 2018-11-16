package com.ternence.spring.skills.design.command.receiver;

/**
 * 团队成员(执行命令对应的动作的地方)
 *
 * @author TAOJIANGHANG
 * @since 2018/11/16 15:27
 */
public interface TeamMember {

    void doAction(Object... someParameters);

}
