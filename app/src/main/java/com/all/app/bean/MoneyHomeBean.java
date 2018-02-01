package com.all.app.bean;

import com.all.app.base.BeanBase;

/**
 * Created by Administrator on 2017/4/28 0028.
 */

public class MoneyHomeBean extends BeanBase {
    String balance;
    String total;
    String txprice;
    String pwdtatus;
    String margin;

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getTxprice() {
        return txprice;
    }

    public void setTxprice(String txprice) {
        this.txprice = txprice;
    }

    public String getPwdtatus() {
        return pwdtatus;
    }

    public void setPwdtatus(String pwdtatus) {
        this.pwdtatus = pwdtatus;
    }

    public String getMargin() {
        return margin;
    }

    public void setMargin(String margin) {
        this.margin = margin;
    }

    @Override
    public String toString() {
        return "MoneyHomeBean{" +
                "balance='" + balance + '\'' +
                ", total='" + total + '\'' +
                ", txprice='" + txprice + '\'' +
                ", pwdtatus='" + pwdtatus + '\'' +
                ", margin='" + margin + '\'' +
                '}';
    }
}
