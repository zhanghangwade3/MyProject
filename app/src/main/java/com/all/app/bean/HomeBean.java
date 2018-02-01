package com.all.app.bean;

import com.all.app.base.BeanBase;

import java.util.List;

/**
 * Created by Administrator on 2017/4/28 0028.
 */

public class HomeBean extends BeanBase {
    String ordercount;
    String xxnum;
    String endtimenum;
    String task_id;
    String endtimestr;

    public String getOrdercount() {
        return ordercount;
    }

    public void setOrdercount(String ordercount) {
        this.ordercount = ordercount;
    }

    public String getXxnum() {
        return xxnum;
    }

    public void setXxnum(String xxnum) {
        this.xxnum = xxnum;
    }

    public String getEndtimenum() {
        return endtimenum;
    }

    public void setEndtimenum(String endtimenum) {
        this.endtimenum = endtimenum;
    }

    public String getTask_id() {
        return task_id;
    }

    public void setTask_id(String task_id) {
        this.task_id = task_id;
    }

    public String getEndtimestr() {
        return endtimestr;
    }

    public void setEndtimestr(String endtimestr) {
        this.endtimestr = endtimestr;
    }

    @Override
    public String toString() {
        return "HomeBean{" +
                "ordercount='" + ordercount + '\'' +
                ", xxnum='" + xxnum + '\'' +
                ", endtimenum='" + endtimenum + '\'' +
                ", task_id='" + task_id + '\'' +
                ", endtimestr='" + endtimestr + '\'' +
                '}';
    }
}
