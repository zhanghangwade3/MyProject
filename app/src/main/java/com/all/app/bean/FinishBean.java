package com.all.app.bean;

import com.all.app.base.BeanBase;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/28 0028.
 */

public class FinishBean extends BeanBase {
    String task_id;
    String task_title;
    String assurance_time;
    String mark_status;
    String mark_content;
    String star1;
    String star2;
    String star3;

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

    public String getAssurance_time() {
        return assurance_time;
    }

    public void setAssurance_time(String assurance_time) {
        this.assurance_time = assurance_time;
    }

    public String getMark_status() {
        return mark_status;
    }

    public void setMark_status(String mark_status) {
        this.mark_status = mark_status;
    }

    public String getMark_content() {
        return mark_content;
    }

    public void setMark_content(String mark_content) {
        this.mark_content = mark_content;
    }

    public String getStar1() {
        return star1;
    }

    public void setStar1(String star1) {
        this.star1 = star1;
    }

    public String getStar2() {
        return star2;
    }

    public void setStar2(String star2) {
        this.star2 = star2;
    }

    public String getStar3() {
        return star3;
    }

    public void setStar3(String star3) {
        this.star3 = star3;
    }

    @Override
    public String toString() {
        return "FinishBean{" +
                "task_id='" + task_id + '\'' +
                ", task_title='" + task_title + '\'' +
                ", assurance_time='" + assurance_time + '\'' +
                ", mark_status='" + mark_status + '\'' +
                ", mark_content='" + mark_content + '\'' +
                ", star1='" + star1 + '\'' +
                ", star2='" + star2 + '\'' +
                ", star3='" + star3 + '\'' +
                '}';
    }
}
