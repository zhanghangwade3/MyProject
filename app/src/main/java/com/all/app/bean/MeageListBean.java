package com.all.app.bean;

import com.all.app.base.BeanBase;

/**
 * Created by Administrator on 2017/4/28 0028.
 */

public class MeageListBean extends BeanBase {
    String msg_id;
    String title;
    String on_time;
    String msgurl;
    String view_status;

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

    public String getOn_time() {
        return on_time;
    }

    public void setOn_time(String on_time) {
        this.on_time = on_time;
    }

    public String getMsgurl() {
        return msgurl;
    }

    public void setMsgurl(String msgurl) {
        this.msgurl = msgurl;
    }

    public String getView_status() {
        return view_status;
    }

    public void setView_status(String view_status) {
        this.view_status = view_status;
    }

    @Override
    public String toString() {
        return "MeageListBean{" +
                "msg_id='" + msg_id + '\'' +
                ", title='" + title + '\'' +
                ", on_time='" + on_time + '\'' +
                ", msgurl='" + msgurl + '\'' +
                ", view_status='" + view_status + '\'' +
                '}';
    }
}
