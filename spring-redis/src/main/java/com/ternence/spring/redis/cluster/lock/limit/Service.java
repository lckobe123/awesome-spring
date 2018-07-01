package com.ternence.spring.redis.cluster.lock.limit;

/**
 * 分布式系统提供的服务
 *
 * @author Ternence
 * @version 1.0
 * @since 2018/7/1 17:02
 */
public interface Service<T> {

    T service() throws CannotServiceException;
}
