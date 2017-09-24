package com.ternence.spring.drools.domain;

/**
 * create by 陶江航 at 2017/9/24 10:43
 *
 * @version 1.0
 * @email taojianghang@xinzhentech.com
 * @description 积分计算对象(按照领域模型驱动设计分包和命名)
 */
public class CumulativeScoreDomain {
    //用户名
    private String username;
    //当天是否是用户的生日
    private Boolean isBirthDayAtNow;
    //增加的积分数目
    private Integer plusPoint;
    //当月的购物次数
    private Integer timesOfShopping;
    //当月购物总金额
    private Float amountOfShopping;
    //当月退货总金额
    private Float amountOfReturnGoods;
    //当月信用卡还款次数
    private Integer timesOfBackCredit;

    public CumulativeScoreDomain() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getBirthDayAtNow() {
        return isBirthDayAtNow;
    }

    public void setBirthDayAtNow(Boolean birthDayAtNow) {
        isBirthDayAtNow = birthDayAtNow;
    }

    public Integer getPlusPoint() {
        return plusPoint;
    }

    public void setPlusPoint(Integer plusPoint) {
        this.plusPoint = plusPoint;
    }

    public Integer getTimesOfShopping() {
        return timesOfShopping;
    }

    public void setTimesOfShopping(Integer timesOfShopping) {
        this.timesOfShopping = timesOfShopping;
    }

    public Float getAmountOfShopping() {
        return amountOfShopping;
    }

    public void setAmountOfShopping(Float amountOfShopping) {
        this.amountOfShopping = amountOfShopping;
    }

    public Float getAmountOfReturnGoods() {
        return amountOfReturnGoods;
    }

    public void setAmountOfReturnGoods(Float amountOfReturnGoods) {
        this.amountOfReturnGoods = amountOfReturnGoods;
    }

    public Integer getTimesOfBackCredit() {
        return timesOfBackCredit;
    }

    public void setTimesOfBackCredit(Integer timesOfBackCredit) {
        this.timesOfBackCredit = timesOfBackCredit;
    }

    @Override
    public String toString() {
        return "CumulativeScoreDomain{" +
                "username='" + username + '\'' +
                ", isBirthDayAtNow=" + isBirthDayAtNow +
                ", plusPoint=" + plusPoint +
                ", timesOfShopping=" + timesOfShopping +
                ", amountOfShopping=" + amountOfShopping +
                ", amountOfReturnGoods=" + amountOfReturnGoods +
                ", timesOfBackCredit=" + timesOfBackCredit +
                '}';
    }
}
