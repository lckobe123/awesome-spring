package com.ternence.transaction.web.condition;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * 编程式事务实现的AccountService的注入条件
 *
 * @author Ternence
 * @version 1.0
 * @since 2018/4/22 0:15
 */
public class ProgramingTypeInjectCondition implements Condition {
    private final static Logger LOGGER = LoggerFactory.getLogger(ProgramingTypeInjectCondition.class);

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        try {
            //如果存在这个bean则注入目标bean，否则就不会注入
            context.getRegistry().getBeanDefinition("transactionTemplate");
            return true;
        } catch (NoSuchBeanDefinitionException e) {
            LOGGER.error(e.getMessage());
            return false;
        }
    }
}
