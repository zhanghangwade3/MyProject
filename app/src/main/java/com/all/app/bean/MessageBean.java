package com.all.app.bean;

import com.all.app.base.BeanBase;

/**
 * Created by Administrator on 2017/4/28 0028.
 */

public class MessageBean extends BeanBase {
    String jynum;
    String jyedittime;
    String xtnum;
    String xtedittime;
    String sjxnum;
    String sjedittime;
    String fjxnum;

    public String getJynum() {
        return jynum;
    }

    public void setJynum(String jynum) {
        this.jynum = jynum;
    }

    public String getJyedittime() {
        return jyedittime;
    }

    public void setJyedittime(String jyedittime) {
        this.jyedittime = jyedittime;
    }

    public String getXtnum() {
        return xtnum;
    }

    public void setXtnum(String xtnum) {
        this.xtnum = xtnum;
    }

    public String getXtedittime() {
        return xtedittime;
    }

    public void setXtedittime(String xtedittime) {
        this.xtedittime = xtedittime;
    }

    public String getSjxnum() {
        return sjxnum;
    }

    public void setSjxnum(String sjxnum) {
        this.sjxnum = sjxnum;
    }

    public String getSjedittime() {
        return sjedittime;
    }

    public void setSjedittime(String sjedittime) {
        this.sjedittime = sjedittime;
    }

    public String getFjxnum() {
        return fjxnum;
    }

    public void setFjxnum(String fjxnum) {
        this.fjxnum = fjxnum;
    }

    @Override
    public String toString() {
        return "MessageBean{" +
                "jynum='" + jynum + '\'' +
                ", jyedittime='" + jyedittime + '\'' +
                ", xtnum='" + xtnum + '\'' +
                ", xtedittime='" + xtedittime + '\'' +
                ", sjxnum='" + sjxnum + '\'' +
                ", sjedittime='" + sjedittime + '\'' +
                ", fjxnum='" + fjxnum + '\'' +
                '}';
    }
}
