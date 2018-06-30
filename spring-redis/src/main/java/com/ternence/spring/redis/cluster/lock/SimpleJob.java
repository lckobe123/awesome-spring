package com.ternence.spring.redis.cluster.lock;

/**
 * @author Ternence
 * @version 1.0
 * @since 2018/6/30 22:57
 */
public interface SimpleJob {

    /**
     * 执行一个可以中断的任务
     *
     * @throws InterruptedException 中断的时候抛出异常
     */
    void exec() throws InterruptedException;

}
