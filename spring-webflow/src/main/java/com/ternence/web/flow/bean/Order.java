package com.ternence.web.flow.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * create by 陶江航 at 2017/10/30 22:10
 *
 * @version 1.0
 * @email taojianghang@xinzhentech.com
 * @description 订单信息类
 */
public class Order implements Serializable {
    private Customer customer;
    private List<Pizza> pizzas;
    private Payment payment;

    public Order() {
        pizzas = new ArrayList<>();
        customer = new Customer();
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Pizza> getPizzas() {
        return pizzas;
    }

    public void setPizzas(List<Pizza> pizzas) {
        this.pizzas = pizzas;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    @Override
    public String toString() {
        return "Order{" +
                "customer=" + customer +
                ", pizzas=" + pizzas +
                ", payment=" + payment +
                '}';
    }
}
