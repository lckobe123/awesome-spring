package com.ternence.transaction.web.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * 账户业务测试类
 *
 * @author Ternence
 * @version 1.0
 * @since 2018/4/21 22:49
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:/spring/spring-programing.xml",
        "classpath:spring/springmvc.xml"})
public class AccountServiceTest {
    @Resource
    private AccountService accountServiceImpl;

    @Test
    public void transfer() {
        accountServiceImpl.transfer("charles", "ternence", new BigDecimal(100D));
    }
}
