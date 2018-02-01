package com.all.app.bean;

import com.all.app.base.BeanBase;

/**
 * Created by Administrator on 2017/4/28 0028.
 */

public class BankNameBean extends BeanBase {
    String name;
    String bank_name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBank_name() {
        return bank_name;
    }

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }

    @Override
    public String toString() {
        return "BankNameBean{" +
                "name='" + name + '\'' +
                ", bank_name='" + bank_name + '\'' +
                '}';
    }
}
