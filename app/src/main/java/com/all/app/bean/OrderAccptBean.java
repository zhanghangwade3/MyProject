package com.all.app.bean;

import com.all.app.base.BeanBase;

/**
 * Created by Administrator on 2017/4/28 0028.
 */

public class OrderAccptBean extends BeanBase {
    String uid;
    String area;
    String task_pic;
    String address;
    String service_type;
    String task_id;
    String task_title;
    String sub_time;
    String indus_pid;
    String is_pick;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
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

    public String getService_type() {
        return service_type;
    }

    public void setService_type(String service_type) {
        this.service_type = service_type;
    }

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

    public String getSub_time() {
        return sub_time;
    }

    public void setSub_time(String sub_time) {
        this.sub_time = sub_time;
    }

    public String getIndus_pid() {
        return indus_pid;
    }

    public void setIndus_pid(String indus_pid) {
        this.indus_pid = indus_pid;
    }

    public String getIs_pick() {
        return is_pick;
    }

    public void setIs_pick(String is_pick) {
        this.is_pick = is_pick;
    }

    @Override
    public String toString() {
        return "OrderAccptBean{" +
                "uid='" + uid + '\'' +
                ", area='" + area + '\'' +
                ", task_pic='" + task_pic + '\'' +
                ", address='" + address + '\'' +
                ", service_type='" + service_type + '\'' +
                ", task_id='" + task_id + '\'' +
                ", task_title='" + task_title + '\'' +
                ", sub_time='" + sub_time + '\'' +
                ", indus_pid='" + indus_pid + '\'' +
                ", is_pick='" + is_pick + '\'' +
                '}';
    }
}
