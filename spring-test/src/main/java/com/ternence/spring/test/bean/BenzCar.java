package com.ternence.spring.test.bean;

/**
 * @author Ternence
 * @version 1.0
 * @since 2018/10/1 0:02
 */
public class BenzCar {

    private String price;

    private String brand;

    private String owner;

    private String color;

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

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "BenzCar{" +
                "price='" + price + '\'' +
                ", brand='" + brand + '\'' +
                ", owner='" + owner + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
