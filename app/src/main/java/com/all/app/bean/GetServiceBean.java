package com.all.app.bean;

import com.all.app.base.BeanBase;

/**
 * Created by Administrator on 2017/4/28 0028.
 */

public class GetServiceBean extends BeanBase {
    String service_time;
    String uid;
    String id;
    String teamnum;
    String service_area;
    String service_type;
    String promise;
    String storage_size;
    String storage;

    public String getService_time() {
        return service_time;
    }

    public void setService_time(String service_time) {
        this.service_time = service_time;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTeamnum() {
        return teamnum;
    }

    public void setTeamnum(String teamnum) {
        this.teamnum = teamnum;
    }

    public String getService_area() {
        return service_area;
    }

    public void setService_area(String service_area) {
        this.service_area = service_area;
    }

    public String getService_type() {
        return service_type;
    }

    public void setService_type(String service_type) {
        this.service_type = service_type;
    }

    public String getPromise() {
        return promise;
    }

    public void setPromise(String promise) {
        this.promise = promise;
    }

    public String getStorage_size() {
        return storage_size;
    }

    public void setStorage_size(String storage_size) {
        this.storage_size = storage_size;
    }

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    @Override
    public String toString() {
        return "GetServiceBean{" +
                "service_time='" + service_time + '\'' +
                ", uid='" + uid + '\'' +
                ", id='" + id + '\'' +
                ", teamnum='" + teamnum + '\'' +
                ", service_area='" + service_area + '\'' +
                ", service_type='" + service_type + '\'' +
                ", promise='" + promise + '\'' +
                ", storage_size='" + storage_size + '\'' +
                ", storage='" + storage + '\'' +
                '}';
    }
}
