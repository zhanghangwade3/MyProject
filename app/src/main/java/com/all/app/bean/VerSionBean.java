package com.all.app.bean;

import com.all.app.base.BeanBase;

/**
 * Created by Administrator on 2017/4/28 0028.
 */

public class VerSionBean extends BeanBase {
    String versionCode;
    String downlandUrl;
    String versionName;

    public String getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
    }

    public String getDownlandUrl() {
        return downlandUrl;
    }

    public void setDownlandUrl(String downlandUrl) {
        this.downlandUrl = downlandUrl;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    @Override
    public String toString() {
        return "VerSionBean{" +
                "versionCode='" + versionCode + '\'' +
                ", downlandUrl='" + downlandUrl + '\'' +
                ", versionName='" + versionName + '\'' +
                '}';
    }
}
