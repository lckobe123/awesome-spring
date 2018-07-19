package com.ternence.spring.redis.cluster.lock.artifact;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisCluster;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * 使用Redis来自己实现一个加锁和释放锁
 *
 * @author Ternence
 * @version 1.0
 * @since 2018/7/19 21:40
 */
@Service
public class LockService {
    private final static Logger LOGGER = LoggerFactory.getLogger(LockService.class);
    @Resource
    private JedisCluster jedisCluster;

    /**
     * 这种简易的锁，一旦客户端崩溃基本就会导致系统不可用了
     *
     * @param lock 锁的名称
     * @return 是否获取成功锁
     */
    @Deprecated
    public boolean tryLock(String lock) {
        //默认的锁
        String value = UUID.randomUUID().toString()
                .replace("-", "");
        return jedisCluster.setnx(lock, value) == 1;
    }

    /**
     * 这个锁虽然能在有限的时间内返回，但还是无法应对客户端
     * 挂掉的情况，挂掉了还没有释放锁，那么接下来获取锁的行为都将发生死锁
     *
     * @param waitTime 在指定时间内获取锁，如果没有获取到锁就不断重试，
     *                 如果在这个时间内之后没有获取到锁就返回,以s为单位
     * @return 是否获取锁成功
     */
    public boolean tryLock(String lock, long waitTime) {
        //默认的锁
        String value = UUID.randomUUID().toString()
                .replace("-", "");
        long endTime = System.currentTimeMillis() + (waitTime * 1000);

        while (System.currentTimeMillis() < endTime) {
            if (jedisCluster.setnx(lock, value) == 1) {
                return true;
            }
            try {
                Thread.sleep(1);//告诉CPU我不参加时间片分配，重新分配时间片给别人吧
                //这样不会出现有哪个线程一致获得CPU时间片，一直在不断的尝试获取锁
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return false;//指定时间段内没有获取到锁
    }

    /**
     * 这个基本是一个正确，高可用的锁
     */
    public boolean tryLock(String lock, long waitTime, int lockTime) {
        //默认的锁
        String value = UUID.randomUUID().toString()
                .replace("-", "");
        long endTime = System.currentTimeMillis() + (waitTime * 1000);

        while (System.currentTimeMillis() < endTime) {
            if (jedisCluster.setnx(lock, value) == 1) {
                //获取成功之后加一个超时时间，这样就算持有锁的客户端挂掉也无所谓
                //时间到了自动会释放这个锁,但是要考虑的是设置合适的超时时间
                //避免锁被释放了，可任务还在执行
                jedisCluster.expire(lock, lockTime);
                return true;
            } else if (jedisCluster.ttl(lock) == -1) {
                LOGGER.info("这个锁{}没有设置超时时间", lock);
                //没有获取锁成功的线程需要检测锁有没有被设置超时时间，避免死锁
                jedisCluster.expire(lock, lockTime);
            }
            try {
                Thread.sleep(1);//告诉CPU我不参加时间片分配，重新分配时间片给别人吧
                //这样不会出现有哪个线程一致获得CPU时间片，一直在不断的尝试获取锁
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return false;//指定时间段内没有获取到锁
    }


    /**
     * 这样释放锁的方法很有可能不正确的释放其他线程持有的锁，这就很尴尬了
     */
    public void release(String lock) {
        jedisCluster.del(lock);
        LOGGER.info("线程{}释放锁成功", Thread.currentThread().getName());
    }

    /**
     * 释放锁也是需要很小心的操作,redis集群不支持事务操作，可以用lua脚本实现事务
     * <p>
     * 在释放之前检查锁的值有没有被修改，这样可以避免释放不属于自己的锁
     */
    public void releaseWithCheck(String lock, String identifier) {
        if (identifier.equals(jedisCluster.get(lock))) {
            jedisCluster.del(lock);
            return;
        } else {
            throw new RuntimeException("释放不属于自己持有的锁" + lock + ":" + identifier);
        }
    }
}
