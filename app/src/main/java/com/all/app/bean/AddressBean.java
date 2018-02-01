package com.all.app.bean;

import com.all.app.base.BeanBase;

import java.util.List;

/**
 * Created by Administrator on 2017/4/28 0028.
 */

public class AddressBean extends BeanBase {
    List<DataBean> data;
    String code;
    String msg;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
