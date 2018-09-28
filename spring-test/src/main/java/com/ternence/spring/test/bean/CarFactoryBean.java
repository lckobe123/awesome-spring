package com.ternence.spring.test.bean;

import org.springframework.beans.factory.FactoryBean;

/**
 * @author Ternence
 * @version 1.0
 * @since 2018/9/28 22:08
 */
public class CarFactoryBean implements FactoryBean<Car> {
    private String brand;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public Car getObject() throws Exception {
        Car car = new Car();
        car.setBrand(brand);
        car.setColor("Red");
        car.setPrice("100,000,000");
        return car;
    }

    @Override
    public Class<?> getObjectType() {
        return Car.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
