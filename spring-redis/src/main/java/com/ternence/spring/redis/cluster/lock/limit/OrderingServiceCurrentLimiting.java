package com.ternence.spring.redis.cluster.lock.limit;

import com.ternence.spring.redis.cluster.utils.RedissonUtils;
import org.redisson.api.RSemaphore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 使用分布式信号量对订单服务限流
 *
 * @author Ternence
 * @version 1.0
 * @since 2018/7/1 17:02
 */
@Component
public class OrderingServiceCurrentLimiting {
    private final static Logger LOGGER = LoggerFactory.getLogger(OrderingServiceCurrentLimiting.class);
    private RSemaphore semaphore;
    private int permits;

    public OrderingServiceCurrentLimiting() {
        this(10);
    }

    public OrderingServiceCurrentLimiting(int permits) {
        this.permits = permits;
    }

    public boolean canAccess() throws InterruptedException {
        ensureHoldSemaphore();
        return semaphore.availablePermits() > 0 && semaphore.tryAcquire();
    }

    public void serviceComplete() {
        try {
            ensureHoldSemaphore();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        semaphore.release();
    }

    public int getPermits() {

        return semaphore.availablePermits();
    }


    private synchronized void ensureHoldSemaphore() throws InterruptedException {
        if (semaphore == null) {
            semaphore = RedissonUtils.getSemaphore("my-semaphore");
            if (semaphore.isExists()) {
                semaphore.delete();
            }
            LOGGER.info("semaphore的许可证数量{}", semaphore.availablePermits());
            if (semaphore.trySetPermits(permits)) {
                LOGGER.info("semaphore的许可证数量{}", semaphore.availablePermits());
            } else {
                LOGGER.info("设置semaphore的许可证数量失败");
            }

        }
    }
}
