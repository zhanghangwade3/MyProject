package com.all.app.bean;

import com.all.app.base.BeanBase;

/**
 * Created by Administrator on 2017/4/28 0028.
 */

public class ProBean extends BeanBase {
    String task_title;
    String task_pic;
    String indus_pid;
    String is_pick;
    String Address;
    String que_status;
    String  quid;
    String  opinion;

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

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getQue_status() {
        return que_status;
    }

    public void setQue_status(String que_status) {
        this.que_status = que_status;
    }

    public String getQuid() {
        return quid;
    }

    public void setQuid(String quid) {
        this.quid = quid;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    @Override
    public String toString() {
        return "ProBean{" +
                "task_title='" + task_title + '\'' +
                ", task_pic='" + task_pic + '\'' +
                ", indus_pid='" + indus_pid + '\'' +
                ", is_pick='" + is_pick + '\'' +
                ", Address='" + Address + '\'' +
                ", que_status='" + que_status + '\'' +
                ", quid='" + quid + '\'' +
                ", opinion='" + opinion + '\'' +
                '}';
    }
}
