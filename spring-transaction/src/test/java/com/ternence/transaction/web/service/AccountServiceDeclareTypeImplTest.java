package com.ternence.transaction.web.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * 申明式事务测试
 *
 * @author Ternence
 * @version 1.0
 * @since 2018/4/21 23:44
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:/spring/spring-declare.xml",
        "classpath:spring/springmvc.xml"})
public class AccountServiceDeclareTypeImplTest {
    @Resource
    private AccountService declareAccountServiceProxy;

    @Test
    public void transfer() throws Exception {
        declareAccountServiceProxy.transfer("philip", "ternence", new BigDecimal(1000));
    }

}