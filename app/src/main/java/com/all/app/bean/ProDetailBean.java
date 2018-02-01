package com.all.app.bean;

import com.all.app.base.BeanBase;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/4/28 0028.
 */

public class ProDetailBean extends BeanBase {
    String task_title;
    String task_id;
    String question;
    ArrayList<String> qpic;
    String opinion;
    String que_status;
    String kftel;
    String only_id;

    public String getTask_title() {
        return task_title;
    }

    public void setTask_title(String task_title) {
        this.task_title = task_title;
    }

    public String getTask_id() {
        return task_id;
    }

    public void setTask_id(String task_id) {
        this.task_id = task_id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public ArrayList<String> getQpic() {
        return qpic;
    }

    public void setQpic(ArrayList<String> qpic) {
        this.qpic = qpic;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public String getQue_status() {
        return que_status;
    }

    public void setQue_status(String que_status) {
        this.que_status = que_status;
    }

    public String getKftel() {
        return kftel;
    }

    public void setKftel(String kftel) {
        this.kftel = kftel;
    }

    public String getOnly_id() {
        return only_id;
    }

    public void setOnly_id(String only_id) {
        this.only_id = only_id;
    }

    @Override
    public String toString() {
        return "ProDetailBean{" +
                "task_title='" + task_title + '\'' +
                ", task_id='" + task_id + '\'' +
                ", question='" + question + '\'' +
                ", qpic=" + qpic +
                ", opinion='" + opinion + '\'' +
                ", que_status='" + que_status + '\'' +
                ", kftel='" + kftel + '\'' +
                ", only_id='" + only_id + '\'' +
                '}';
    }
}
