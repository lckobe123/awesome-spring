package com.ternence.boot.mvc.eureka.service.imps;

import com.ternence.boot.mvc.eureka.service.EurekaService;
import com.ternence.boot.mvc.eureka.vo.RegistryConfig;
import org.springframework.stereotype.Service;

/**
 * Eureka服务的实现
 *
 * @author Ternence
 * @version 1.0
 */
@Service
public class DefaultEurekaServiceImps implements EurekaService {

    @Override
    public RegistryConfig obtainServerRegistryConfig(Servers server) {

        return null;
    }

}
