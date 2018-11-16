package com.ternence.spring.skills.design.command;

import com.ternence.spring.skills.design.command.Invoker.FinancialLeader;
import com.ternence.spring.skills.design.command.Invoker.Invoker;
import com.ternence.spring.skills.design.command.command.Command;
import com.ternence.spring.skills.design.command.command.ConcreteDevelopCommand;
import com.ternence.spring.skills.design.command.command.ConcreteTestCommand;
import com.ternence.spring.skills.design.command.receiver.AutomationTester;
import com.ternence.spring.skills.design.command.receiver.JavaDeveloper;
import com.ternence.spring.skills.design.command.receiver.TeamMember;

import java.util.Arrays;

/**
 * 使用封装好的这些Api的客户端程序
 * <p>
 * 在发起动作和执行动作的对象之间建立一层抽象机制,这样更灵活，更好扩展
 *
 * @author TAOJIANGHANG
 * @since 2018/11/16 15:39
 */
public class Client {

    public static void main(String[] args) {
        //leader 通过命令让人执行他想要的动作
        Invoker invoker = new FinancialLeader();

        invoker.setExecutingCommand(new ConcreteDevelopCommand(new JavaDeveloper()));
        invoker.execute("1：完成2.2.0的开发", "2：完成产品上线");

        System.out.println("---------------------------------------------");

        invoker.setExecutingCommand(new ConcreteTestCommand(new AutomationTester()));
        invoker.execute("1：完成这个测试", "2：完成那个测试");

        invoker.setExecutingCommand(new ConcreteTestCommand(new TeamMember() {
            @Override
            public void doAction(Object... someParameters) {
                System.out.println("匿名执行命令的内容:" + Arrays.deepToString(someParameters));
            }
        }));

        System.out.println("---------------------------------------------");
        invoker.execute("1：完成这个测试", "2：完成那个测试");

        invoker.setExecutingCommand(new Command() {
            private TeamMember teamMember;

            public Command setTeamMember(TeamMember teamMember) {
                this.teamMember = teamMember;
                return this;
            }

            @Override
            public void action(Object... someParameters) {
                teamMember.doAction("匿名执行:" + Arrays.asList(someParameters));
            }
        }.setTeamMember(new TeamMember() {
            @Override
            public void doAction(Object... someParameters) {

                System.out.println(Arrays.toString(someParameters));

            }
        }));

        invoker.execute("-------------");
    }
}
