package com.all.app.bean;

import com.all.app.base.BeanBase;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/4/28 0028.
 */

public class CheckState extends BeanBase {
    private boolean isCheck;//listview 外部check选中状态保存
    private ArrayList<Boolean> mGvSelects;//gridview 内部check选中状态保存

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public ArrayList<Boolean> getmGvSelects() {
        return mGvSelects;
    }

    public void setmGvSelects(ArrayList<Boolean> mGvSelects) {
        this.mGvSelects = mGvSelects;
    }

    @Override
    public String toString() {
        return "CheckState{" +
                "isCheck=" + isCheck +
                ", mGvSelects=" + mGvSelects.size() +
                '}';
    }
}
