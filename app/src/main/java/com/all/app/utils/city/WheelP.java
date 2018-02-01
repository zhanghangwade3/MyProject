package com.all.app.utils.city;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;

public class WheelP implements Serializable, Cloneable {
    /**
     * @Fields serialVersionUID : TODO(描述: )
     */
    private static final long serialVersionUID = 1L;
    private String province_name;
    private String province_id;
    private List<WheelC> cityData;

    /**
     * @return the province_name
     */
    public String getProvince_name() {
        return province_name;
    }

    /**
     * @param province_name the province_name to set
     */
    public void setProvince_name(String province_name) {
        this.province_name = province_name;
    }

    /**
     * @return the province_id
     */
    public String getProvince_id() {
        return province_id;
    }

    /**
     * @param province_id the province_id to set
     */
    public void setProvince_id(String province_id) {
        this.province_id = province_id;
    }

    /**
     * @return the cityData
     */
    public List<WheelC> getCityData() {
        return cityData;
    }

    /**
     * @param cityData the cityData to set
     */
    public void setCityData(List<WheelC> cityData) {
        this.cityData = cityData;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        //将对象写到流里
        try {
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            ObjectOutputStream oo = new ObjectOutputStream(bo);
            oo.writeObject(this);//从流里读出来
            ByteArrayInputStream bi = new ByteArrayInputStream(bo.toByteArray());
            ObjectInputStream oi = new ObjectInputStream(bi);
            return (oi.readObject());
        }catch (Exception e){

        }
        return super.clone();
    }
}
