package com.ternence.spring.redis.cluster.utils;

import com.ternence.spring.redis.utils.SpringContextHolder;
import org.redisson.api.*;
import org.redisson.client.codec.Codec;
import org.redisson.client.codec.StringCodec;

/**
 * 由Redisson提供的一些实用工具集
 *
 * @author Ternence
 * @version 1.0
 * @since 2018/6/30 22:23
 */
public class RedissonUtils {

    private static RedissonClient internalGetClient() {

        return SpringContextHolder.getBean(RedissonClient.class);
    }

    public static RLock getRLock(String key) {

        return internalGetClient().getLock(key);
    }

    public static RBuckets getBuckets() {

        return internalGetClient().getBuckets();
    }

    public static RBuckets getBuckets(Codec codec) {

        return internalGetClient().getBuckets(codec);
    }

    public static RBucket<String> getStringBucket(String key) {

        return internalGetClient().getBucket(key, StringCodec.INSTANCE);
    }

    public static RSemaphore getSemaphore(String key) {

        return internalGetClient().getSemaphore(key);
    }

    public static void release() {
        internalGetClient().shutdown();
    }
}
