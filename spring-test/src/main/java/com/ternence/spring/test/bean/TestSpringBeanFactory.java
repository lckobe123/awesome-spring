package com.ternence.spring.test.bean;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

/**
 * @author Ternence
 * @version 1.0
 * @since 2018/9/27 22:30
 */
public class TestSpringBeanFactory {

    public static void main(String[] args) {
        BeanFactory factory = new XmlBeanFactory(new ClassPathResource("spring-beans.xml"));


        //MyClass myClass = factory.getBean(MyClass.class);
        //System.out.println(myClass);

        System.out.println(factory.getBean("&car"));
        System.out.println(factory.getBean(Car.class));
        System.out.println(factory.getBean("car"));

        /*for (String alia : factory.getAliases("car3")) {
            System.out.println(alia);
        }*/
    }
}
