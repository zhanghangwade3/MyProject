package com.all.app.bean;

import com.all.app.base.BeanBase;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/4/28 0028.
 */

public class BankListBean extends BeanBase {
    ArrayList<BankBean> bank;
    ArrayList<PayBean> alipay;

    public ArrayList<BankBean> getBank() {
        return bank;
    }

    public void setBank(ArrayList<BankBean> bank) {
        this.bank = bank;
    }

    public ArrayList<PayBean> getAlipay() {
        return alipay;
    }

    public void setAlipay(ArrayList<PayBean> alipay) {
        this.alipay = alipay;
    }

    @Override
    public String toString() {
        return "BankListBean{" +
                "bank=" + bank +
                ", alipay=" + alipay +
                '}';
    }
}
