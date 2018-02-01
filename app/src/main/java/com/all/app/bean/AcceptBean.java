package com.all.app.bean;

import com.all.app.base.BeanBase;

/**
 * Created by Administrator on 2017/4/28 0028.
 */

public class AcceptBean extends BeanBase {
    String msg_id;
    String title;
    String to_username;
    String on_time;
    String content;
    String source;

    public String getMsg_id() {
        return msg_id;
    }

    public void setMsg_id(String msg_id) {
        this.msg_id = msg_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTo_username() {
        return to_username;
    }

    public void setTo_username(String to_username) {
        this.to_username = to_username;
    }

    public String getOn_time() {
        return on_time;
    }

    public void setOn_time(String on_time) {
        this.on_time = on_time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Override
    public String toString() {
        return "AcceptBean{" +
                "msg_id='" + msg_id + '\'' +
                ", title='" + title + '\'' +
                ", to_username='" + to_username + '\'' +
                ", on_time='" + on_time + '\'' +
                ", content='" + content + '\'' +
                ", source='" + source + '\'' +
                '}';
    }
}
