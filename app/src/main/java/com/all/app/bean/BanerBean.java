package com.all.app.bean;

import com.all.app.base.BeanBase;

/**
 * Created by Administrator on 2017/4/28 0028.
 */

public class BanerBean extends BeanBase {
    String art_id;
    String art_title;
    String art_pic;
    String art_url;

    public String getArt_id() {
        return art_id;
    }

    public void setArt_id(String art_id) {
        this.art_id = art_id;
    }

    public String getArt_title() {
        return art_title;
    }

    public void setArt_title(String art_title) {
        this.art_title = art_title;
    }

    public String getArt_pic() {
        return art_pic;
    }

    public void setArt_pic(String art_pic) {
        this.art_pic = art_pic;
    }

    public String getArt_url() {
        return art_url;
    }

    public void setArt_url(String art_url) {
        this.art_url = art_url;
    }

    @Override
    public String toString() {
        return "NewsBean{" +
                "art_id='" + art_id + '\'' +
                ", art_title='" + art_title + '\'' +
                ", art_pic='" + art_pic + '\'' +
                ", art_url='" + art_url + '\'' +
                '}';
    }
}
