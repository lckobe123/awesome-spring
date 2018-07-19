package com.ternence.spring.redis.cluster.utils;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.config.AbstractFactoryBean;
import org.springframework.core.io.Resource;
import org.springframework.util.StringUtils;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.io.IOException;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

/**
 * 创建{@link JedisCluster}的工厂,默认是singleton的对象
 *
 * @author Ternence
 * @version 1.0
 * @since 2018/7/19 21:45
 */
public class JedisClusterFactory extends AbstractFactoryBean<JedisCluster> {
    private Resource configLocation;
    private String addressPrefix;
    private GenericObjectPoolConfig poolConfig;

    /**
     * 负责创建你想要的对象，如果{@link super#isSingleton()}返回true，则这个方法只会被调用一次，否则
     * 每次在调用{@link super#getObject()}的时候这个方法都会被调用
     */
    @Override
    protected JedisCluster createInstance() throws Exception {

        return new JedisCluster(internalParseConfigs(), poolConfig);
    }

    private Set<HostAndPort> internalParseConfigs() throws IOException {
        if (StringUtils.isEmpty(addressPrefix)) {
            throw new IllegalArgumentException("请指定识别集群地址的前缀字符串,并在properties config中以此字符串为前缀命名");
        }
        if (configLocation == null) {
            throw new IllegalArgumentException("请指定配置文件的位置");
        }
        Set<HostAndPort> hostAndPorts = new HashSet<>();
        Properties configProperties = new Properties();
        configProperties.load(configLocation.getInputStream());
        configProperties.keySet().forEach(item -> {
            String key = (String) item;
            if (key.startsWith(addressPrefix)) {
                String address = configProperties.getProperty(key);
                String ip = address.split(":")[0];
                String port = address.split(":")[1];
                HostAndPort hostAndPort = new HostAndPort(ip, Integer.parseInt(port));
                hostAndPorts.add(hostAndPort);
            }
        });
        return hostAndPorts;
    }

    @Override
    public Class<?> getObjectType() {
        return JedisCluster.class;
    }

    public void setConfigLocation(Resource configLocation) {
        this.configLocation = configLocation;
    }

    public void setAddressPrefix(String addressPrefix) {
        this.addressPrefix = addressPrefix;
    }

    public void setPoolConfig(GenericObjectPoolConfig poolConfig) {
        this.poolConfig = poolConfig;
    }
}
