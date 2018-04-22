package com.ternence.transaction.web.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * 使用aop实现的事务管理功能
 *
 * @author Ternence
 * @version 1.0
 * @since 2018/4/22 14:44
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-declare-aop.xml", "classpath:spring/springmvc.xml"})
public class AccountServiceDeclareAopTypeImplTest {

    @Resource
    private AccountService accountServiceDeclareAopTypeImpl;

    @Test
    public void transfer() throws Exception {

        accountServiceDeclareAopTypeImpl.transfer("philip",
                "ternence", new BigDecimal(1000D));
    }

}