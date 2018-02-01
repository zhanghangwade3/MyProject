package com.all.app.bean;

import com.all.app.base.BeanBase;

/**
 * Created by Administrator on 2017/4/28 0028.
 */

public class OrderBean extends BeanBase {
    int zbnum;
    int thnum;
    int sgnum;
    int ysnum;
    int wcnum;
    int wtdnum;

    public int getZbnum() {
        return zbnum;
    }

    public void setZbnum(int zbnum) {
        this.zbnum = zbnum;
    }

    public int getThnum() {
        return thnum;
    }

    public void setThnum(int thnum) {
        this.thnum = thnum;
    }

    public int getSgnum() {
        return sgnum;
    }

    public void setSgnum(int sgnum) {
        this.sgnum = sgnum;
    }

    public int getYsnum() {
        return ysnum;
    }

    public void setYsnum(int ysnum) {
        this.ysnum = ysnum;
    }

    public int getWcnum() {
        return wcnum;
    }

    public void setWcnum(int wcnum) {
        this.wcnum = wcnum;
    }

    public int getWtdnum() {
        return wtdnum;
    }

    public void setWtdnum(int wtdnum) {
        this.wtdnum = wtdnum;
    }

    @Override
    public String toString() {
        return "OrderBean{" +
                "zbnum=" + zbnum +
                ", thnum=" + thnum +
                ", sgnum=" + sgnum +
                ", ysnum=" + ysnum +
                ", wcnum=" + wcnum +
                ", wtdnum=" + wtdnum +
                '}';
    }
}
