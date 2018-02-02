package com.ternence.seckill;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * 秒杀应用启动器,servlet 3以后是不需要web.xml的了，去了解为什么
 *
 * @author Ternence
 * @version 1.0
 * @since 1.0
 */
@SpringBootApplication
public class SeckillApplicationStarter {

    public static void main(String[] args) {
        SpringApplicationBuilder builder = new SpringApplicationBuilder();
        builder.main(SeckillApplicationStarter.class);
        builder.sources(SeckillApplicationStarter.class);
        builder.run(args);
    }
}
