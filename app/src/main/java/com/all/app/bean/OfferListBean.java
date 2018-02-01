package com.all.app.bean;

import com.all.app.base.BeanBase;

/**
 * Created by Administrator on 2017/4/28 0028.
 */

public class OfferListBean extends BeanBase {
    String task_title;
    String bid_time;
    String quote;
    String address;
    String task_pic;
    String task_status;
    String bid_status;
    String lynum ;
    String bid_id ;
    String task_id ;

    public String getTask_title() {
        return task_title;
    }

    public void setTask_title(String task_title) {
        this.task_title = task_title;
    }

    public String getBid_time() {
        return bid_time;
    }

    public void setBid_time(String bid_time) {
        this.bid_time = bid_time;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTask_pic() {
        return task_pic;
    }

    public void setTask_pic(String task_pic) {
        this.task_pic = task_pic;
    }

    public String getTask_status() {
        return task_status;
    }

    public void setTask_status(String task_status) {
        this.task_status = task_status;
    }

    public String getBid_status() {
        return bid_status;
    }

    public void setBid_status(String bid_status) {
        this.bid_status = bid_status;
    }

    public String getLynum() {
        return lynum;
    }

    public void setLynum(String lynum) {
        this.lynum = lynum;
    }

    public String getBid_id() {
        return bid_id;
    }

    public void setBid_id(String bid_id) {
        this.bid_id = bid_id;
    }

    public String getTask_id() {
        return task_id;
    }

    public void setTask_id(String task_id) {
        this.task_id = task_id;
    }

    @Override
    public String toString() {
        return "OfferListBean{" +
                "task_title='" + task_title + '\'' +
                ", bid_time='" + bid_time + '\'' +
                ", quote='" + quote + '\'' +
                ", address='" + address + '\'' +
                ", task_pic='" + task_pic + '\'' +
                ", task_status='" + task_status + '\'' +
                ", bid_status='" + bid_status + '\'' +
                ", lynum='" + lynum + '\'' +
                ", bid_id='" + bid_id + '\'' +
                ", task_id='" + task_id + '\'' +
                '}';
    }
}
