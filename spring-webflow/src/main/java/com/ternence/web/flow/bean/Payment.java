package com.ternence.web.flow.bean;

import java.io.Serializable;

/**
 * create by 陶江航 at 2017/10/30 22:12
 *
 * @version 1.0
 * @email taojianghang@xinzhentech.com
 * @description 支付信息
 */
public class Payment implements Serializable {
    //支付金额
    private float amount;
    //支付订单
    private Order order;

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "amount=" + amount +
                ", order=" + order +
                '}';
    }
}
