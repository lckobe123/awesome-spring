package com.ternence.spring.redis.cluster.op;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Ternence
 * @version 1.0
 * @since 2018/8/21 20:42
 */
@ContextConfiguration({"classpath:spring-jedis-cluster.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class RedisOperationsTest {
    private final static Logger LOGGER = LoggerFactory.getLogger(RedisOperationsTest.class);
    @Resource
    private JedisCluster jedisCluster;

    @Test
    public void divideScan() {
        Map<String, JedisPool> jedisPoolMap = jedisCluster.getClusterNodes();
        List<String> scanFinalResult = new ArrayList<>();
        jedisPoolMap.keySet().forEach(key -> {
            JedisPool jedisPool = jedisPoolMap.get(key);
            LOGGER.info("扫描节点{}", key);
            Jedis jedis = jedisPool.getResource();
            String cursor = "0";
            do {
                ScanParams params = new ScanParams();
                params.match("linked*");
                ScanResult<String> scanResult = jedis.scan(cursor, params);
                cursor = scanResult.getStringCursor();
                scanResult.getResult().forEach(item -> {
                    if (!item.startsWith("PV")) {
                        LOGGER.info("key : {} 不是以PV开头的数据", item);
                        scanFinalResult.add(item);
                    }
                });
                LOGGER.info("游标:{}", cursor);
            } while (!"0".equals(cursor));
        });
        LOGGER.info("扫描结果为:size={},result={}", scanFinalResult.size(), scanFinalResult);
    }

    /**
     * 但对于RedisCluster来说，是不可以对所有键进行scan操作的，但可以针对其他数据类型，比如hash, zset，
     * 进行一系列hscan，zscan操作。其实可以从jedisCluster的实现可以看出，如果要对所有key进行scan，
     * 需要实现MultiKeyCommands，但RedisCluster是不支持这类型操作的，同理pipeline，mget，mset等操作。
     */
    @Test
    public void scan() {

        //set zset hash list
        /*jedisCluster.sscan("", "0");
        jedisCluster.zscan("", "0");
        jedisCluster.hscan("", "0");*/


        ScanParams params = new ScanParams();
        params.match("linked*");
        String cursor = "0";
        do {
            ScanResult<String> scanResult = jedisCluster.scan(cursor, params);
            cursor = scanResult.getStringCursor();
            LOGGER.info("扫描结果为:{}", scanResult.getResult());
        } while (!"0".equals(cursor));
    }
}
