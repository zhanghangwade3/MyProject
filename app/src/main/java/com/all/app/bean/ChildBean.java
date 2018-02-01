package com.all.app.bean;

import com.all.app.base.BeanBase;

import java.util.List;

/**
 * Created by Administrator on 2017/4/28 0028.
 */

public class ChildBean extends BeanBase {
    String id;
    String name;
    boolean isChecked;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    @Override
    public String toString() {
        return "ChildBean{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", isChecked=" + isChecked +
                '}';
    }
}
