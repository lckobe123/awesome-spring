package com.ternence.transaction.dist.twopc;

import com.ternence.transaction.dist.ParticipationOne;
import com.ternence.transaction.dist.ParticipationTwo;

/**
 * 分布式事务中两阶段提交的基本模拟实现
 *
 * @author Ternence
 * @version 1.0
 * @since 1.0
 */
public class TwoPhaseCommit implements Coordinator {

    private ParticipationOne participationOne = new ParticipationOne();

    private ParticipationTwo participationTwo = new ParticipationTwo();

    @Override
    public boolean prepare() {

        if (participationOne.prepare("ready") && participationTwo.prepare("ready")) {
            System.out.println("准备成功,可以提交");
            return true;
        } else {
            System.out.println("某一方准备失败");
            return false;
        }

    }

    @Override
    public boolean commit() {
        if (participationOne.commit("success") && participationTwo.commit("success")) {
            System.out.println("提交成功,事务完成");
            return true;
        }
        System.out.println("有一方提交失败");
        return false;
    }

    @Override
    public void rollback() {

        System.out.println("混滚事务");
    }

    public static void main(String[] args) {
        TwoPhaseCommit phaseCommit = new TwoPhaseCommit();
        if (phaseCommit.prepare()) {
            System.out.println("准备成功，开始提交");
            if (phaseCommit.commit()) {
                System.out.println("提交成功，事务完成");
            } else {
                phaseCommit.rollback();
                System.out.println("回滚成功,事务完成");
            }
            //可能还需要补偿
        } else {
            System.out.println("准备失败");
        }
    }
}
