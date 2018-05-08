package com.ternence.transaction.web.service;

import org.junit.Test;

/**
 * 事务的并发测试案例
 *
 * @author Ternence
 * @version 1.0
 * @since 2018/4/28 11:30
 */
public class TransactionConcurrencyTestCase {


    /**
     * 启动很多读写线程去操作数据库,使用线程池的思想不断的提交任务
     */
    @Test
    public void testConcurrency() {

    }
}
