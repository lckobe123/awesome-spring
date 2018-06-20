package com.ternence.spring.skills.tag;

import com.ternence.spring.skills.tag.pojo.Tao;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

/**
 * 解析xsd和bean的定义
 *
 * @author Ternence
 * @version 1.0
 * @since 2018/6/20 23:24
 */
public class TagTaoBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {

    @Override
    protected Class<?> getBeanClass(Element element) {
        return Tao.class;
    }

    @Override
    protected void doParse(Element element, BeanDefinitionBuilder builder) {
        String name = element.getAttribute("user-name");
        String email = element.getAttribute("email");
        if (StringUtils.hasText(name)) {
            builder.addPropertyValue("name", name);
        }
        if (StringUtils.hasText(email)) {
            builder.addPropertyValue("email", name);
        }
    }
}
