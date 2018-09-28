package com.ternence.spring.test.bean;

/**
 * @author Ternence
 * @version 1.0
 * @since 2018/9/28 22:06
 */
public class Car {

    private String color;

    private String price;

    private String brand;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public String toString() {
        return "Car{" +
                "color='" + color + '\'' +
                ", price='" + price + '\'' +
                ", brand='" + brand + '\'' +
                '}';
    }
}
