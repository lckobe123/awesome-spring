package com.ternence.pdf.entity;

/**
 * create by 陶江航 at 2017/9/18 22:53
 *
 * @version 1.0
 * @description 基本信息实体类
 */
public class BasicMessageEntity {
    private String uuid;
    private String telphone;
    private String address;
    private String email;
    //信用代码
    private String creditCode;

    public BasicMessageEntity() {
    }

    public BasicMessageEntity(String uuid, String telphone, String address, String email, String creditCode) {
        this.uuid = uuid;
        this.telphone = telphone;
        this.address = address;
        this.email = email;
        this.creditCode = creditCode;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCreditCode() {
        return creditCode;
    }

    public void setCreditCode(String creditCode) {
        this.creditCode = creditCode;
    }

    @Override
    public String toString() {
        return "BasicMessageEntity{" +
                "uuid='" + uuid + '\'' +
                ", telphone='" + telphone + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", creditCode='" + creditCode + '\'' +
                '}';
    }
}
