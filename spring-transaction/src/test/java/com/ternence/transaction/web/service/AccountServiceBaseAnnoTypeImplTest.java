package com.ternence.transaction.web.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * 基于注解的事务管理测试
 *
 * @author Ternence
 * @version 1.0
 * @since 2018/4/22 15:11
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/springmvc.xml", "classpath:spring/spring-declare-anno.xml"})
public class AccountServiceBaseAnnoTypeImplTest {

    @Resource
    private AccountService accountServiceBaseAnnoTypeImpl;

    @Test
    public void transfer() throws Exception {

        accountServiceBaseAnnoTypeImpl.transfer("ternence", "charles", new BigDecimal(1000D));
    }

}