package com.all.app.bean;

import com.all.app.base.BeanBase;

/**
 * Created by Administrator on 2017/4/28 0028.
 */

public class BidBean extends BeanBase {
    String task_id;
    String task_title;
    String task_pic;
    String address;
    String sub_time;
    String uid;
    String indus_pid;
    String service_type;
    String order_time;
    String kftel;
    String is_pick;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSub_time() {
        return sub_time;
    }

    public void setSub_time(String sub_time) {
        this.sub_time = sub_time;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getIndus_pid() {
        return indus_pid;
    }

    public void setIndus_pid(String indus_pid) {
        this.indus_pid = indus_pid;
    }

    public String getService_type() {
        return service_type;
    }

    public void setService_type(String service_type) {
        this.service_type = service_type;
    }

    public String getOrder_time() {
        return order_time;
    }

    public void setOrder_time(String order_time) {
        this.order_time = order_time;
    }

    public String getKftel() {
        return kftel;
    }

    public void setKftel(String kftel) {
        this.kftel = kftel;
    }

    public String getIs_pick() {
        return is_pick;
    }

    public void setIs_pick(String is_pick) {
        this.is_pick = is_pick;
    }

    @Override
    public String toString() {
        return "BidBean{" +
                "task_id='" + task_id + '\'' +
                ", task_title='" + task_title + '\'' +
                ", task_pic='" + task_pic + '\'' +
                ", address='" + address + '\'' +
                ", sub_time='" + sub_time + '\'' +
                ", uid='" + uid + '\'' +
                ", indus_pid='" + indus_pid + '\'' +
                ", service_type='" + service_type + '\'' +
                ", order_time='" + order_time + '\'' +
                ", kftel='" + kftel + '\'' +
                ", is_pick='" + is_pick + '\'' +
                '}';
    }
}
