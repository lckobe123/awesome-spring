package com.ternence.transaction.web.service;

import com.ternence.transaction.web.mapper.AccountMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * 账户服务业务的实现类,使用申明式的事务
 *
 * @author Ternence
 * @version 1.0
 * @since 2018/4/21 21:41
 */
public class AccountServiceDeclareTypeImpl implements AccountService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AccountServiceDeclareTypeImpl.class);
    @Resource
    private AccountMapper accountMapper;

    @Override
    public void transfer(String out, String in, BigDecimal money) {
        accountMapper.out(out, money);
        //noinspection NumericOverflow
        System.out.println(1 / 0);//出现异常会回滚
        accountMapper.in(in, money);
        LOGGER.info(out + "转账" + money + "元给" + in + "成功");
    }

}
