package com.ternence.seckill.controller;

import com.ternence.seckill.SeckillApplicationStarter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 秒杀业务的Controller
 *
 * @author Ternence
 * @version 1.0
 * @since 1.0
 */
@RestController
public class SeckillController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SeckillApplicationStarter.class);

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping("/redis/config/{key}")
    public Object requestConfig(@PathVariable String key) {
        LOGGER.info("获取{}对应的值", key);
        Map<String, Object> data = new HashMap<>();
        data.put("name", stringRedisTemplate.opsForValue().get(key));
        return data;
    }

}
