package com.all.app.bean;

import com.all.app.base.BeanBase;

/**
 * Created by Administrator on 2017/4/28 0028.
 */

public class AccBean extends BeanBase {
    String task_id;
    String task_title;
    String task_pic;
    String uid;
    String address;
    String indus_pid;
    String timestr;
    String is_pick;
    String confirm_success;
    String task_status;
    String price_show;
    String mobile;
    String bid_id;

    public String getTask_id() {
        return task_id;
    }

    public void setTask_id(String task_id) {
        this.task_id = task_id;
    }

    public String getTask_title() {
        return task_title;
    }

    public void setTask_title(String task_title) {
        this.task_title = task_title;
    }

    public String getTask_pic() {
        return task_pic;
    }

    public void setTask_pic(String task_pic) {
        this.task_pic = task_pic;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIndus_pid() {
        return indus_pid;
    }

    public void setIndus_pid(String indus_pid) {
        this.indus_pid = indus_pid;
    }

    public String getTimestr() {
        return timestr;
    }

    public void setTimestr(String timestr) {
        this.timestr = timestr;
    }

    public String getIs_pick() {
        return is_pick;
    }

    public void setIs_pick(String is_pick) {
        this.is_pick = is_pick;
    }

    public String getConfirm_success() {
        return confirm_success;
    }

    public void setConfirm_success(String confirm_success) {
        this.confirm_success = confirm_success;
    }

    public String getTask_status() {
        return task_status;
    }

    public void setTask_status(String task_status) {
        this.task_status = task_status;
    }

    public String getPrice_show() {
        return price_show;
    }

    public void setPrice_show(String price_show) {
        this.price_show = price_show;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getBid_id() {
        return bid_id;
    }

    public void setBid_id(String bid_id) {
        this.bid_id = bid_id;
    }

    @Override
    public String toString() {
        return "AccBean{" +
                "task_id='" + task_id + '\'' +
                ", task_title='" + task_title + '\'' +
                ", task_pic='" + task_pic + '\'' +
                ", uid='" + uid + '\'' +
                ", address='" + address + '\'' +
                ", indus_pid='" + indus_pid + '\'' +
                ", timestr='" + timestr + '\'' +
                ", is_pick='" + is_pick + '\'' +
                ", confirm_success='" + confirm_success + '\'' +
                ", task_status='" + task_status + '\'' +
                ", price_show='" + price_show + '\'' +
                ", mobile='" + mobile + '\'' +
                ", bid_id='" + bid_id + '\'' +
                '}';
    }
}
