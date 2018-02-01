package com.all.app.bean;

import com.all.app.base.BeanBase;

/**
 * Created by Administrator on 2017/4/28 0028.
 */

public class PayBean extends BeanBase {
    String alipayjs_a_id;
    String alipayjs_account;
    String real_name;
    String icon_pic;

    public String getAlipayjs_a_id() {
        return alipayjs_a_id;
    }

    public void setAlipayjs_a_id(String alipayjs_a_id) {
        this.alipayjs_a_id = alipayjs_a_id;
    }

    public String getAlipayjs_account() {
        return alipayjs_account;
    }

    public void setAlipayjs_account(String alipayjs_account) {
        this.alipayjs_account = alipayjs_account;
    }

    public String getReal_name() {
        return real_name;
    }

    public void setReal_name(String real_name) {
        this.real_name = real_name;
    }

    public String getIcon_pic() {
        return icon_pic;
    }

    public void setIcon_pic(String icon_pic) {
        this.icon_pic = icon_pic;
    }

    @Override
    public String toString() {
        return "PayBean{" +
                "alipayjs_a_id='" + alipayjs_a_id + '\'' +
                ", alipayjs_account='" + alipayjs_account + '\'' +
                ", real_name='" + real_name + '\'' +
                ", icon_pic='" + icon_pic + '\'' +
                '}';
    }
}
