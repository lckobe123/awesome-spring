package com.ternence.spring.redis.cluster.lock.limit;

/**
 * 流量暴增,导致不能服务的问题时候抛出这个异常
 *
 * @author Ternence
 * @version 1.0
 * @since 2018/7/1 17:21
 */
public class CannotServiceException extends Exception {
    public CannotServiceException() {
    }

    public CannotServiceException(String message) {
        super(message);
    }
}
