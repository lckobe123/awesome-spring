package com.ternence.skills.feign.bean;

/**
 * 贡献者Java bean
 *
 * @author Ternence
 * @version 1.0
 * @since 2018/4/3 17:10
 */
public class Contributor {
    private String login;
    private int contributions;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getContributions() {
        return contributions;
    }

    public void setContributions(int contributions) {
        this.contributions = contributions;
    }

    @Override
    public String toString() {
        return "Contributor{" +
                "login='" + login + '\'' +
                ", contributions=" + contributions +
                '}';
    }
}
