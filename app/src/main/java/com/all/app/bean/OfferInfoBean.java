package com.all.app.bean;

import com.all.app.base.BeanBase;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/4/28 0028.
 */

public class OfferInfoBean extends BeanBase {
    String task_id;
    String task_title;
    String only_id;

    String task_price;
    String sjaddress;
    ArrayList<String> task_pic;
    String task_desc;
    ArrayList<FileBean> task_file;
    String project_name;
    String iselevator;
    String khaddress;
    String is_pick;
    String pick_id;
    String pick_department;
    String pick_tel;
    String pick_name;
    String pick_number;
    String pick_address;
    String quote;
    String installation_price;
    String transportation_price;
    String handling_price;
    String other_price;
    String message;
    ArrayList<YanBean> lylist;

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

    public String getOnly_id() {
        return only_id;
    }

    public void setOnly_id(String only_id) {
        this.only_id = only_id;
    }

    public String getTask_price() {
        return task_price;
    }

    public void setTask_price(String task_price) {
        this.task_price = task_price;
    }

    public String getSjaddress() {
        return sjaddress;
    }

    public void setSjaddress(String sjaddress) {
        this.sjaddress = sjaddress;
    }

    public ArrayList<String> getTask_pic() {
        return task_pic;
    }

    public void setTask_pic(ArrayList<String> task_pic) {
        this.task_pic = task_pic;
    }

    public String getTask_desc() {
        return task_desc;
    }

    public void setTask_desc(String task_desc) {
        this.task_desc = task_desc;
    }

    public ArrayList<FileBean> getTask_file() {
        return task_file;
    }

    public void setTask_file(ArrayList<FileBean> task_file) {
        this.task_file = task_file;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public String getIselevator() {
        return iselevator;
    }

    public void setIselevator(String iselevator) {
        this.iselevator = iselevator;
    }

    public String getKhaddress() {
        return khaddress;
    }

    public void setKhaddress(String khaddress) {
        this.khaddress = khaddress;
    }

    public String getIs_pick() {
        return is_pick;
    }

    public void setIs_pick(String is_pick) {
        this.is_pick = is_pick;
    }

    public String getPick_id() {
        return pick_id;
    }

    public void setPick_id(String pick_id) {
        this.pick_id = pick_id;
    }

    public String getPick_department() {
        return pick_department;
    }

    public void setPick_department(String pick_department) {
        this.pick_department = pick_department;
    }

    public String getPick_tel() {
        return pick_tel;
    }

    public void setPick_tel(String pick_tel) {
        this.pick_tel = pick_tel;
    }

    public String getPick_name() {
        return pick_name;
    }

    public void setPick_name(String pick_name) {
        this.pick_name = pick_name;
    }

    public String getPick_number() {
        return pick_number;
    }

    public void setPick_number(String pick_number) {
        this.pick_number = pick_number;
    }

    public String getPick_address() {
        return pick_address;
    }

    public void setPick_address(String pick_address) {
        this.pick_address = pick_address;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public String getInstallation_price() {
        return installation_price;
    }

    public void setInstallation_price(String installation_price) {
        this.installation_price = installation_price;
    }

    public String getTransportation_price() {
        return transportation_price;
    }

    public void setTransportation_price(String transportation_price) {
        this.transportation_price = transportation_price;
    }

    public String getHandling_price() {
        return handling_price;
    }

    public void setHandling_price(String handling_price) {
        this.handling_price = handling_price;
    }

    public String getOther_price() {
        return other_price;
    }

    public void setOther_price(String other_price) {
        this.other_price = other_price;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<YanBean> getLylist() {
        return lylist;
    }

    public void setLylist(ArrayList<YanBean> lylist) {
        this.lylist = lylist;
    }

    @Override
    public String toString() {
        return "OfferInfoBean{" +
                "task_id='" + task_id + '\'' +
                ", task_title='" + task_title + '\'' +
                ", only_id='" + only_id + '\'' +
                ", task_price='" + task_price + '\'' +
                ", sjaddress='" + sjaddress + '\'' +
                ", task_pic=" + task_pic +
                ", task_desc='" + task_desc + '\'' +
                ", task_file=" + task_file +
                ", project_name='" + project_name + '\'' +
                ", iselevator='" + iselevator + '\'' +
                ", khaddress='" + khaddress + '\'' +
                ", is_pick='" + is_pick + '\'' +
                ", pick_id='" + pick_id + '\'' +
                ", pick_department='" + pick_department + '\'' +
                ", pick_tel='" + pick_tel + '\'' +
                ", pick_name='" + pick_name + '\'' +
                ", pick_number='" + pick_number + '\'' +
                ", pick_address='" + pick_address + '\'' +
                ", quote='" + quote + '\'' +
                ", installation_price='" + installation_price + '\'' +
                ", transportation_price='" + transportation_price + '\'' +
                ", handling_price='" + handling_price + '\'' +
                ", other_price='" + other_price + '\'' +
                ", message='" + message + '\'' +
                ", lylist=" + lylist +
                '}';
    }
}
