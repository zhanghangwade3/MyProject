package com.all.app.bean;

import com.all.app.base.BeanBase;

/**
 * Created by Administrator on 2017/4/28 0028.
 */

public class GetBidBean extends BeanBase {
    String bid_id;
    String task_id;
    String installation_price;
    String transportation_price;
    String handling_price;
    String other_price;
    String quote;

    public String getBid_id() {
        return bid_id;
    }

    public void setBid_id(String bid_id) {
        this.bid_id = bid_id;
    }

    public String getTask_id() {
        return task_id;
    }

    public void setTask_id(String task_id) {
        this.task_id = task_id;
    }

    public String getInstallation_price() {
        return installation_price;
    }

    public void setInstallation_price(String installation_price) {
        this.installation_price = installation_price;
    }

    public String getTransportation_price() {
        return transportation_price;
    }

    public void setTransportation_price(String transportation_price) {
        this.transportation_price = transportation_price;
    }

    public String getHandling_price() {
        return handling_price;
    }

    public void setHandling_price(String handling_price) {
        this.handling_price = handling_price;
    }

    public String getOther_price() {
        return other_price;
    }

    public void setOther_price(String other_price) {
        this.other_price = other_price;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    @Override
    public String toString() {
        return "GetBidBean{" +
                "bid_id='" + bid_id + '\'' +
                ", task_id='" + task_id + '\'' +
                ", installation_price='" + installation_price + '\'' +
                ", transportation_price='" + transportation_price + '\'' +
                ", handling_price='" + handling_price + '\'' +
                ", other_price='" + other_price + '\'' +
                ", quote='" + quote + '\'' +
                '}';
    }
}
