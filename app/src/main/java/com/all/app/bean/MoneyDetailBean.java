package com.all.app.bean;

import com.all.app.base.BeanBase;

/**
 * Created by Administrator on 2017/4/28 0028.
 */

public class MoneyDetailBean extends BeanBase {
    String fina_action;
    String user_balance;
    String fina_type;
    String fina_time;
    String fina_cash;
    String fina_id;

    public String getFina_action() {
        return fina_action;
    }

    public void setFina_action(String fina_action) {
        this.fina_action = fina_action;
    }

    public String getUser_balance() {
        return user_balance;
    }

    public void setUser_balance(String user_balance) {
        this.user_balance = user_balance;
    }

    public String getFina_type() {
        return fina_type;
    }

    public void setFina_type(String fina_type) {
        this.fina_type = fina_type;
    }

    public String getFina_time() {
        return fina_time;
    }

    public void setFina_time(String fina_time) {
        this.fina_time = fina_time;
    }

    public String getFina_cash() {
        return fina_cash;
    }

    public void setFina_cash(String fina_cash) {
        this.fina_cash = fina_cash;
    }

    public String getFina_id() {
        return fina_id;
    }

    public void setFina_id(String fina_id) {
        this.fina_id = fina_id;
    }

    @Override
    public String toString() {
        return "MoneyDetailBean{" +
                "fina_action='" + fina_action + '\'' +
                ", user_balance='" + user_balance + '\'' +
                ", fina_type='" + fina_type + '\'' +
                ", fina_time='" + fina_time + '\'' +
                ", fina_cash='" + fina_cash + '\'' +
                ", fina_id='" + fina_id + '\'' +
                '}';
    }
}
