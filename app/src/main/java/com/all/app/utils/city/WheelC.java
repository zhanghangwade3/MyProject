package com.all.app.utils.city;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;
public class WheelC implements Serializable,Cloneable {

	/**
	 * @Fields serialVersionUID : TODO(描述: )
	 */
	private static final long serialVersionUID = 1L;
	private String city_id;
	private String city_name;
	private List<WheelA> areaData;
	/**
	 * @return the city_id
	 */
	public String getCity_id() {
		return city_id;
	}
	/**
	 * @param city_id the city_id to set
	 */
	public void setCity_id(String city_id) {
		this.city_id = city_id;
	}
	/**
	 * @return the city_name
	 */
	public String getCity_name() {
		return city_name;
	}
	/**
	 * @param city_name the city_name to set
	 */
	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}
	/**
	 * @return the areaData
	 */
	public List<WheelA> getAreaData() {
		return areaData;
	}
	/**
	 * @param areaData the areaData to set
	 */
	public void setAreaData(List<WheelA> areaData) {
		this.areaData = areaData;
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
