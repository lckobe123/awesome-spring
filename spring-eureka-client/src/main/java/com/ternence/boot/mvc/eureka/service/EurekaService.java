package com.ternence.boot.mvc.eureka.service;

import com.ternence.boot.mvc.eureka.vo.RegistryConfig;

/**
 * 使用eureka client编写的，获取eureka注册信息的服务
 *
 * @author Ternence
 * @version 1.0
 */
public interface EurekaService {

    /**
     * 获取注册节点的信息
     *
     * @param server 注册的节点的所属服务
     * @return 节点的相关信息
     */
    RegistryConfig obtainServerRegistryConfig(Servers server);


    enum Servers {

    }

}
