package com.ternence.spring.redis.cluster.lock.limit;

import com.ternence.spring.redis.cluster.utils.RedissonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

/**
 * mock的订单服务
 *
 * @author Ternence
 * @version 1.0
 * @since 2018/7/1 17:03
 */
@org.springframework.stereotype.Service
public class OrderingServiceImpl implements Service<String> {
    private final static Logger LOGGER = LoggerFactory.getLogger(OrderingServiceImpl.class);
    @Resource
    private OrderingServiceCurrentLimiting limiting;
    private ThreadLocal<Integer> threadLocal = new ThreadLocal<>();

    @Override
    public String service() throws CannotServiceException {
        try {
            if (limiting.canAccess()) {
                LOGGER.info("成功获取许可,访问服务");
                threadLocal.set(threadLocal.get() + 1);
                RedissonUtils.getStringBucket("PV:" + Thread.currentThread().getName())
                        .set(String.valueOf(threadLocal.get()));
                RedissonUtils.release();
                Thread.sleep(1000 * 3);
                return "下单成功";
            } else {
                throw new CannotServiceException("服务繁忙,清稍后重试");
            }
        } catch (InterruptedException e) {
            LOGGER.error("请求范文服务发生异常:{}", e);
            throw new CannotServiceException("访问当前不可用");
        } finally {
            LOGGER.info("服务完成,释放许可,还有:{}个许可", limiting.getPermits());
            limiting.serviceComplete();
        }
    }
}
