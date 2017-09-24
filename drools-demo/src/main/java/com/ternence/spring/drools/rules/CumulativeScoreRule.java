package com.ternence.spring.drools.rules;

import com.ternence.spring.drools.domain.CumulativeScoreDomain;

/**
 * create by 陶江航 at 2017/9/24 11:02
 *
 * @version 1.0
 * @email taojianghang@xinzhentech.com
 * @description 定义积分规则接口
 */
public interface CumulativeScoreRule {
    /**
     * 初始化规则引擎
     */
    void initEngine();

    /**
     * 刷新引擎中定义的规则
     */
    void refreshEngineRules();

    /**
     * 执行引擎中定义的规则
     *
     * @param scoreDomain 积分计算域
     */
    void executeEngineRules(CumulativeScoreDomain scoreDomain);
}
