package com.ternence.spring.drools.domain;

/**
 * create by 陶江航 at 2017/9/24 12:05
 *
 * @version 1.0
 * @email taojianghang@xinzhentech.com
 * @description 产品类领域对象
 */
public class ProductDomain {
    public static final String DIAMOND = "0";
    public static final String GOLD = "1";
    private String type;

    private int discount;

    public ProductDomain() {
    }

    public ProductDomain(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "ProductDomain{" +
                "type='" + type + '\'' +
                ", discount=" + discount +
                '}';
    }
}
