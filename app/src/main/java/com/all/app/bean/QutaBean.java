package com.all.app.bean;

import com.all.app.base.BeanBase;

/**
 * Created by Administrator on 2017/4/28 0028.
 */

public class QutaBean extends BeanBase {

    String installation_price;
    String transportation_price;
    String handling_price;
    String other_price;

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

    @Override
    public String toString() {
        return "QutaBean{" +
                "installation_price='" + installation_price + '\'' +
                ", transportation_price='" + transportation_price + '\'' +
                ", handling_price='" + handling_price + '\'' +
                ", other_price='" + other_price + '\'' +
                '}';
    }
}
