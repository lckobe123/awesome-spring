package com.ternence.transaction.web.service;

import java.math.BigDecimal;

/**
 * 账户操作业务
 *
 * @author Ternence
 * @version 1.0
 * @since 2018/4/21 21:40
 */
public interface AccountService {

    /**
     * 转账mapper
     *
     * @param out   转入账户名称
     * @param in    转出账户名称
     * @param money 流转的金额
     */
    void transfer(String out, String in, BigDecimal money) throws Exception;
}
