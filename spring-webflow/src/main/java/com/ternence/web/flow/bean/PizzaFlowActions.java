package com.ternence.web.flow.bean;

import com.ternence.web.flow.ex.CustomerNotFoundException;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * create by 陶江航 at 2017/10/30 22:54
 *
 * @version 1.0
 * @email taojianghang@xinzhentech.com
 * @description Pizza流程的各种行为
 */
public class PizzaFlowActions {
    private Map<String, Customer> customerMap;
    private List<String> areaScopes;
    private List<Order> orders;

    private void init() {
        customerMap = new HashMap<>();
        Customer customer = new Customer();
        customer.setPhoneNumber("18798624470");
        customerMap.put("18798624470", customer);

        areaScopes = new ArrayList<>();
        areaScopes.add("BeiJin");
        areaScopes.add("ShangHai");
        areaScopes.add("ShenZhen");

        orders = new ArrayList<>();
    }

    /**
     * 根据手机号码查询是否存在用户
     *
     * @param phone 手机号码
     * @return 找到的用户, 如果没有找到则抛出 {@link CustomerNotFoundException}
     * @throws CustomerNotFoundException 没有找到对应的用户
     */
    public Customer lookupCustomer(String phone) throws CustomerNotFoundException {
        if (customerMap == null) init();
        if (phone == null) {
            throw new NullPointerException("请输入电话号码");
        }
        if (customerMap.containsKey(phone)) {

            return customerMap.get(phone);
        } else {

            throw new CustomerNotFoundException("没有找到用户");
        }
    }

    /**
     * 检查某个区域是否在配送范围内,如果不在的话需要到店自取
     *
     * @param areaName 区域码
     * @return 是否可以配送
     */
    public boolean checkDeliveryArea(String areaName) {
        if (areaScopes == null) init();
        if (!StringUtils.hasText(areaName)) {
            throw new NullPointerException("区域不能为空");
        }
        return areaScopes.contains(areaName);
    }

    /**
     * 添加顾客到用户列表中
     *
     * @param customer 顾客对象
     */
    public void addCustomer(Customer customer) {
        if (customer == null || !StringUtils.hasText(customer.getPhoneNumber())) {

            throw new NullPointerException("保存的用户或用户的手机号码不能为null");
        }
        System.out.println("保存顾客" + customer);
        customerMap.put(customer.getPhoneNumber(), customer);
    }

    public void saveOrder(Order order) {
        if (order == null) {
            throw new NullPointerException("不能保存空的订单");
        }
        if (orders == null) {
            init();
        }
        orders.add(order);
        System.out.println("保存订单" + order + "成功");
    }
}
