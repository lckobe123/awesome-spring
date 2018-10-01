package com.ternence.spring.test.bean;

import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;

/**
 * @author Ternence
 * @version 1.0
 * @since 2018/10/1 11:10
 */
public class Family {
    private Car car;
    /*
     *以下三种类型默认是不会被自动装配的
     */
    private BeanNameAware beanNameAware;
    private BeanFactoryAware beanFactoryAware;
    private BeanClassLoaderAware loaderAware;


    public void setCar(Car car) {
        this.car = car;
        System.out.println("Autowiring the car object by implicit");
    }

    public void setBeanNameAware(BeanNameAware beanNameAware) {
        this.beanNameAware = beanNameAware;
        System.out.println("Autowiring  the beanNameAware object by implicit");
    }

    public void setBeanFactoryAware(BeanFactoryAware beanFactoryAware) {
        this.beanFactoryAware = beanFactoryAware;
        System.out.println("Autowiring  the beanFactoryAware object by implicit");
    }

    public void setLoaderAware(BeanClassLoaderAware loaderAware) {
        this.loaderAware = loaderAware;
        System.out.println("Autowiring  the loaderAware object by implicit");
    }

    @Override
    public String toString() {
        return "Family{" +
                "car=" + car +
                ", beanNameAware=" + beanNameAware +
                ", beanFactoryAware=" + beanFactoryAware +
                ", loaderAware=" + loaderAware +
                '}';
    }
}
