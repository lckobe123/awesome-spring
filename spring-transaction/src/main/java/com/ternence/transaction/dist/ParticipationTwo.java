package com.ternence.transaction.dist;

/**
 * 事务参与者二
 *
 * @author Ternence
 * @version 1.0
 * @since 1.0
 */
public class ParticipationTwo {

    /**
     * 准备阶段
     *
     * @param param
     * @return
     */
    public boolean prepare(String param) {

        return "ready".equals(param);
    }

    /**
     * 提交阶段
     *
     * @param param
     * @return
     */
    public boolean commit(String param) {

        return "success".equals(param);
    }

    /**
     * 回滚阶段
     */
    public void roolback() {
        System.out.println("回滚");
    }
}
