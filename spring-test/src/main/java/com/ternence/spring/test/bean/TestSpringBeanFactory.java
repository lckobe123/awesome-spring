package com.ternence.spring.test.bean;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;

/**
 * @author Ternence
 * @version 1.0
 * @since 2018/9/27 22:30
 */
public class TestSpringBeanFactory {

    public static void main(String[] args) {

        //不推荐使用了
        //BeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("spring-beans.xml"));

        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions(new ClassPathResource("spring-beans.xml"));

        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("car");
        System.out.println(beanDefinition);

        //MyClass myClass = factory.getBean(MyClass.class);
        //System.out.println(myClass);

        System.out.println(beanFactory.getBean("&car"));
        System.out.println(beanFactory.getBean(Car.class));
        System.out.println(beanFactory.getBean("car"));

        /*for (String alia : factory.getAliases("car3")) {
            System.out.println(alia);
        }*/
    }
}
