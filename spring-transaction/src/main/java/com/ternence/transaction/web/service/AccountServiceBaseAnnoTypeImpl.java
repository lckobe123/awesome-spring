package com.ternence.transaction.web.service;

import com.ternence.transaction.web.mapper.AccountMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * 账户服务业务的实现类,基于注解的事务实现
 * rollback-for默认只会回滚运行时异常和Error
 * 可以设置{@code noRollbackFor = {ArithmeticException.class}}测试不会滚的情况
 *
 * @author Ternence
 * @version 1.0
 * @since 2018/4/21 21:41
 */
@Service("accountServiceBaseAnnoTypeImpl")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class AccountServiceBaseAnnoTypeImpl implements AccountService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AccountServiceBaseAnnoTypeImpl.class);
    @Resource
    private AccountMapper accountMapper;

    @Override
    public void transfer(String out, String in, BigDecimal money) throws Exception {
        accountMapper.out(out, money);
        try {
            //noinspection NumericOverflow
            System.out.println(1 / 0);//出现异常会回滚
        } catch (ArithmeticException e) {
            e.printStackTrace();
            //1如果进行了try-catch操作，那么程序还是可以继续向下执行的，事务也不会回滚
            //2:运行结果表明如果抛出非运行异常或Error,那么事务是不会回滚的，他的回滚只会针对运行时异常
            throw new Exception("unchecked exception has been throw");
        }
        accountMapper.in(in, money);
        LOGGER.info(out + "转账" + money + "元给" + in + "成功");
    }

}
