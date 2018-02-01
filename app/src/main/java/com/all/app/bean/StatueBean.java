package com.all.app.bean;

import com.all.app.base.BeanBase;

/**
 * Created by Administrator on 2017/4/28 0028.
 */

public class StatueBean extends BeanBase {
    String realname_status;//1.代表已实名认证，2.代表未实名认证,0.已上传资料


    public String getRealname_status() {
        return realname_status;
    }

    public void setRealname_status(String realname_status) {
        this.realname_status = realname_status;
    }
}
