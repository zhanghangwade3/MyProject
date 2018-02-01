package com.all.app.bean;

import com.all.app.base.BeanBase;

/**
 * Created by Administrator on 2017/4/28 0028.
 */

public class BaoBean extends BeanBase {
    String balance;
    String margin;
    String charge;

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getMargin() {
        return margin;
    }

    public void setMargin(String margin) {
        this.margin = margin;
    }

    public String getCharge() {
        return charge;
    }

    public void setCharge(String charge) {
        this.charge = charge;
    }

    @Override
    public String toString() {
        return "BaoBean{" +
                "balance='" + balance + '\'' +
                ", margin='" + margin + '\'' +
                ", charge='" + charge + '\'' +
                '}';
    }
}
