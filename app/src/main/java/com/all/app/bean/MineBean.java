package com.all.app.bean;

import com.all.app.base.BeanBase;

/**
 * Created by Administrator on 2017/4/28 0028.
 */

public class MineBean extends BeanBase {
    String only_id;
    String truename;
    String realname_status;
    String title;
    String level;
    String user_pic;
    String kftel;

    public String getOnly_id() {
        return only_id;
    }

    public void setOnly_id(String only_id) {
        this.only_id = only_id;
    }

    public String getTruename() {
        return truename;
    }

    public void setTruename(String truename) {
        this.truename = truename;
    }

    public String getRealname_status() {
        return realname_status;
    }

    public void setRealname_status(String realname_status) {
        this.realname_status = realname_status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getUser_pic() {
        return user_pic;
    }

    public void setUser_pic(String user_pic) {
        this.user_pic = user_pic;
    }

    public String getKftel() {
        return kftel;
    }

    public void setKftel(String kftel) {
        this.kftel = kftel;
    }

    @Override
    public String toString() {
        return "MineBean{" +
                "only_id='" + only_id + '\'' +
                ", truename='" + truename + '\'' +
                ", realname_status='" + realname_status + '\'' +
                ", title='" + title + '\'' +
                ", level='" + level + '\'' +
                ", user_pic='" + user_pic + '\'' +
                ", kftel='" + kftel + '\'' +
                '}';
    }
}
