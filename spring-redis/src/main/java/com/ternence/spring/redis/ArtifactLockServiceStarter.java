package com.ternence.spring.redis;

import com.ternence.spring.redis.cluster.lock.artifact.LockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 锁服务测试
 *
 * @author Ternence
 * @version 1.0
 * @since 2018/7/19 22:34
 */
@SuppressWarnings("Duplicates")
public class ArtifactLockServiceStarter {
    private final static Logger LOGGER = LoggerFactory.getLogger(ArtifactLockServiceStarter.class);
    private static LockService service;

    public static void main(String[] args) throws InterruptedException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "spring-jedis-cluster.xml");
        context.start();
        LOGGER.info("The application has been started !");
        service = context.getBean(LockService.class);
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            executorService.submit(new Thread(() -> {
                String lock = "test-lock";
                while (true) {
                    if (service.tryLock(lock, 3, 15)) {
                        try {
                            LOGGER.info("线程{}获取锁成功", Thread.currentThread().getName());
                            for (int j = 0; j < 5; j++) {
                                LOGGER.info("{}:j={}", Thread.currentThread().getName(), j);
                                Thread.sleep(1000);
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } finally {
                            service.release(lock);
                        }
                    } else {
                        LOGGER.info("{}三秒内没有获取到锁", Thread.currentThread().getName());
                    }
                }
            }));
        }
        Thread.sleep(Integer.MAX_VALUE);
    }

    public void demoTryWithWaitTime() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            executorService.submit(new Thread(() -> {
                String lock = "test-lock";
                while (true) {
                    if (service.tryLock(lock, 3)) {
                        try {
                            LOGGER.info("线程{}获取锁成功", Thread.currentThread().getName());
                            for (int j = 0; j < 5; j++) {
                                LOGGER.info("{}:j={}", Thread.currentThread().getName(), j);
                                Thread.sleep(1000);
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } finally {
                            service.release(lock);
                        }
                    } else {
                        LOGGER.info("{}三秒内没有获取到锁", Thread.currentThread().getName());
                    }
                }
            }));
        }
    }

    public void 演示不正确的释放锁的姿势() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            executorService.submit(new Thread(() -> {
                String lock = "test-lock";
                while (true) {
                    try {
                        if (service.tryLock(lock)) {
                            LOGGER.info("线程{}获取锁成功", Thread.currentThread().getName());
                            for (int i1 = 0; i1 < 3; i1++) {
                                LOGGER.info("i=" + i1);
                                Thread.sleep(1000);
                            }
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {//这里会导致无论锁获取成功还是失败都会释放锁，这就不正确了
                        service.release(lock);
                    }
                }
            }));
        }
    }

}
