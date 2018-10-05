package com.ternence.spring.test;

import com.ternence.spring.test.bean.Car;
import com.ternence.spring.test.bean.Family;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;

import java.util.Map;

/**
 * @author Ternence
 * @version 1.0
 * @since 2018/9/27 22:30
 */
public class TestSpringBeanFactory {
    private final static Logger logger = LoggerFactory.getLogger(TestSpringBeanFactory.class);

    public static void main(String[] args) {

        //不推荐使用了
        //BeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("spring-beans.xml"));
        //其实到现在我们明白了BeanFactory其实就一个Bean的管理工厂，是一个registry，也是一个BeanFactory，可以
        //注册BeanDefinition，创建bean，自动装配bean，还可以做其他很多的事情

        //applicationContext中其实也是使用这样的方式来使用容器的
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions(new ClassPathResource("spring-beans.xml"));
        logger.info("Pr-creating all the beans");
        beanFactory.preInstantiateSingletons();
        logger.info("Complete pre-creating all the beans");
        //BeanDefinition beanDefinition = beanFactory.getBeanDefinition("car");
        //System.out.println(beanDefinition);

        //MyClass myClass = factory.getBean(MyClass.class);
        //System.out.println(myClass);

        //System.out.println(beanFactory.getBean("&car"));
        //System.out.println(beanFactory.getBean(Car.class));
        //System.out.println(beanFactory.getBean("car"));

        /*for (String alia : factory.getAliases("car3")) {
            System.out.println(alia);
        }*/

        /*System.out.println(beanFactory.getBean("carTemplate"));
        System.out.println(beanFactory.getBean("benzCar"));
        BeanDefinition definition = beanFactory.getBeanDefinition("benzCar");
        System.out.println("benzCard of parent is : " + definition.getParentName());*/
        /*logger.error("----------------------------get bean start -----------------------------------");
        Family family = beanFactory.getBean(Family.class);
        logger.error("----------------------------be autowiring family is : {} ----------------------------", family);*/
    }
}
