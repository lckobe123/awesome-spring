package com.ternence.spring.redis;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

/**
 * 使用redisson实现的分布式锁 测试效果
 *
 * @author Ternence
 * @version 1.0
 */
public class RedisDisLockDemo {
    private final static String SERVER_ADDRESS = "http://192.168.72.128:6379";

    public static void main(String[] args) {
        Config config = new Config();
        config.useSingleServer().setAddress(SERVER_ADDRESS);
        RedissonClient client = Redisson.create(config);
        //他的锁是用一个hash值作为锁的
        RLock rLock = client.getLock("lock:bond");

        rLock.lock();//上锁，这是一个阻塞操作,会一直阻塞程序的执行

        try {
            System.out.println("lock:bond");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            rLock.unlock();//释放锁
        }
        client.shutdown();//关闭Redisson实例,释放资源
    }
}
