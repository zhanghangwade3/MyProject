package com.all.app.bean;

import com.all.app.base.BeanBase;

import java.util.List;

/**
 * Created by Administrator on 2017/4/28 0028.
 */

public class ServiceBean extends BeanBase {
    String id;
    String name;
    List<ChildBean>child;

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

    public List<ChildBean> getChild() {
        return child;
    }

    public void setChild(List<ChildBean> child) {
        this.child = child;
    }

    @Override
    public String toString() {
        return "ServiceBean{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", child=" + child +
                '}';
    }
}
