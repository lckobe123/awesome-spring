package com.ternence.transaction.web.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

/**
 * 账户的dao接口定义
 *
 * @author 陶江航
 * @version 1.0
 */
@Mapper
public interface AccountMapper {

    /**
     * 转出操作
     *
     * @param accountName 账户名称
     * @param money       转账金额
     */
    void out(@Param("accountName") String accountName, @Param("money") BigDecimal money);

    /**
     * 转入操作
     *
     * @param accountName 账户名称
     * @param money       转账金额
     */
    void in(@Param("accountName") String accountName, @Param("money") BigDecimal money);
}
