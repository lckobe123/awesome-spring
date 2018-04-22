package com.ternence.transaction.web.service;

import com.ternence.transaction.web.condition.ProgramingTypeInjectCondition;
import com.ternence.transaction.web.mapper.AccountMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * 账户服务业务的实现类,使用编程式的事务
 *
 * @author Ternence
 * @version 1.0
 * @since 2018/4/21 21:41
 */
@Service("accountServiceProgramingTypeImpl")
@Conditional({ProgramingTypeInjectCondition.class})
public class AccountServiceProgramingTypeImpl implements AccountService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AccountServiceProgramingTypeImpl.class);
    @Resource
    private AccountMapper accountMapper;
    @Resource
    private TransactionTemplate transactionTemplate;

    @Override
    public void transfer(String out, String in, BigDecimal money) {
        programingTrans(out, in, money);
    }

    private void programingTrans(String out, String in, BigDecimal money) {
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                accountMapper.out(out, money);
                //noinspection NumericOverflow
                System.out.println(1 / 0);//出现异常会回滚
                accountMapper.in(in, money);
                LOGGER.info(out + "转账" + money + "元给" + in + "成功");
            }
        });
    }
}
