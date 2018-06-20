package com.ternence.spring.skills.tag;

import com.ternence.spring.skills.tag.pojo.Tao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Ternence
 * @version 1.0
 * @since 2018/6/21 0:20
 */
public class ApplicationTest {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-application.xml");

        Tao tao = (Tao) context.getBean("ternence");

        System.out.println(tao);
    }
}
