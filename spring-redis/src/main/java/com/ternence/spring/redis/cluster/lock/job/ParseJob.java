package com.ternence.spring.redis.cluster.lock.job;

import com.ternence.spring.redis.cluster.utils.RedissonUtils;
import org.redisson.api.RLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 模拟一个分布式的job，很多定时任务在定时的执行这个job，
 * 这个操作要能够控制并发的访问，如果上一个任务还没有完成，那么需要放弃本次任务的执行
 *
 * @author Ternence
 * @version 1.0
 * @since 2018/6/30 22:56
 */
public class ParseJob implements SimpleJob {
    private final static Logger LOGGER = LoggerFactory.getLogger(ParseJob.class);
    private final static String JOB_LOCK_NAME = "spring-redis:lock:job-parse";

    /**
     * 模拟一个耗时操作
     * <p>
     * RLock.tryLock()方法是立即返回的，如果redis中已经存在这个锁,那么立即返回false
     */
    @Override
    public void exec() throws InterruptedException {
        RLock jobLock = RedissonUtils.getRLock(JOB_LOCK_NAME);
        if (jobLock.tryLock()) {//这个方法是立即返回的
            try {
                LOGGER.info("{}获得锁,开始执行解析任务 ...", Thread.currentThread().getName());
                Thread.sleep(1000 * 60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                LOGGER.info("{}释放锁,执行解析任务完成 ...", Thread.currentThread().getName());
                jobLock.forceUnlock();//强制解除锁
            }
        } else {
            // Thread.interrupted(); don't inverse the thread interrupt state ! just throw a ex.
            throw new InterruptedException("The task can't acquire lock.");
        }

    }
}
