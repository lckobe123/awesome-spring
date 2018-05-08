package com.ternence.spring.dubbo.consumer;

import com.ternence.spring.dubbo.api.ServiceApi;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 服务消费方
 *
 * @author Ternence
 * @version 1.0
 * @since 2018/5/8 16:31
 */
@RestController
public class ServiceConsumerController {

    @Resource
    private ServiceApi serviceApiImpl;


    @GetMapping("/exec/service")
    public Object exec(String input) {

        return serviceApiImpl.service(input);
    }

}
