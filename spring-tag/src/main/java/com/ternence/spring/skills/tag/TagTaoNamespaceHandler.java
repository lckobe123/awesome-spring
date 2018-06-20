package com.ternence.spring.skills.tag;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * 自定义标签的解析器
 *
 * @author Ternence
 * @version 1.0
 * @since 2018/6/20 23:22
 */
public class TagTaoNamespaceHandler extends NamespaceHandlerSupport {

    @Override
    public void init() {
        registerBeanDefinitionParser("instance", new TagTaoBeanDefinitionParser());
    }
}
