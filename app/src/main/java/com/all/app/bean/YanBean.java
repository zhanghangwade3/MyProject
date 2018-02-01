package com.all.app.bean;

import com.all.app.base.BeanBase;

/**
 * Created by Administrator on 2017/4/28 0028.
 */

public class YanBean extends BeanBase {
    String comment_id;
    String content;
    String on_time;

    public String getComment_id() {
        return comment_id;
    }

    public void setComment_id(String comment_id) {
        this.comment_id = comment_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getOn_time() {
        return on_time;
    }

    public void setOn_time(String on_time) {
        this.on_time = on_time;
    }

    @Override
    public String toString() {
        return "YanBean{" +
                "comment_id='" + comment_id + '\'' +
                ", content='" + content + '\'' +
                ", on_time='" + on_time + '\'' +
                '}';
    }
}
