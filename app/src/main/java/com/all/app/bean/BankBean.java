package com.all.app.bean;

import com.all.app.base.BeanBase;

/**
 * Created by Administrator on 2017/4/28 0028.
 */

public class BankBean extends BeanBase {
    String bank_id;
    String icon_pic;
    String bank_name;
    String card_num;

    public String getBank_id() {
        return bank_id;
    }

    public void setBank_id(String bank_id) {
        this.bank_id = bank_id;
    }

    public String getIcon_pic() {
        return icon_pic;
    }

    public void setIcon_pic(String icon_pic) {
        this.icon_pic = icon_pic;
    }

    public String getBank_name() {
        return bank_name;
    }

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }

    public String getCard_num() {
        return card_num;
    }

    public void setCard_num(String card_num) {
        this.card_num = card_num;
    }

    @Override
    public String toString() {
        return "BankBean{" +
                "bank_id='" + bank_id + '\'' +
                ", icon_pic='" + icon_pic + '\'' +
                ", bank_name='" + bank_name + '\'' +
                ", card_num='" + card_num + '\'' +
                '}';
    }
}
