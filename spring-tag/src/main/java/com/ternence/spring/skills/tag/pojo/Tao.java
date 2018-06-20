package com.ternence.spring.skills.tag.pojo;

/**
 * tag对应的配置文件
 *
 * @author Ternence
 * @version 1.0
 * @since 2018/6/20 23:25
 */
public class Tao {
    private String name;
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Tao{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
