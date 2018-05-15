package com.ternence.spring.disconf;

import com.baidu.disconf.client.common.annotations.DisconfFile;
import com.baidu.disconf.client.common.annotations.DisconfFileItem;
import com.baidu.disconf.client.common.annotations.DisconfUpdateService;
import com.baidu.disconf.client.common.update.IDisconfUpdate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * redis配置更新的监听
 *
 * @author Ternence
 * @since 2018/05/15
 */
@Service
@Scope("singleton")
@DisconfFile(filename = "redis.properties")
@DisconfUpdateService(classes = {RedisConfigWatcher.class})
public class RedisConfigWatcher implements IDisconfUpdate {
    private static final Logger LOGGER = LoggerFactory.getLogger(RedisConfigWatcher.class);

    private String host;
    private int port;

    @DisconfFileItem(name = "redis.host", associateField = "host")
    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    @DisconfFileItem(name = "redis.port", associateField = "port")
    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    /**
     * 每次配置被重新加载的时候这个方法会被调用,修改disconf控制台的redis.properties，将host改为127.0.0.1，观察IDEA控制台日志输出
     * <p>
     * 会发现配置确实会被更新并且是实时的
     *
     * @throws Exception 可能会抛出的异常
     */
    @Override
    public void reload() throws Exception {
        LOGGER.info("redis config be reloaded by disconf ...");
    }
}
