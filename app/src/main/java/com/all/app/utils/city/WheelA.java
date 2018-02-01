package com.all.app.utils.city;
import java.io.Serializable;
public class WheelA implements Serializable,Cloneable {

	/**
	 * @Fields serialVersionUID : TODO(描述: )
	 */
	private static final long serialVersionUID = -6695370812011213738L;
	private String area_id;
	private String area_name;
	/**
	 * @return the area_id
	 */
	public String getArea_id() {
		return area_id;
	}
	/**
	 * @param area_id the area_id to set
	 */
	public void setArea_id(String area_id) {
		this.area_id = area_id;
	}
	/**
	 * @return the area_name
	 */
	public String getArea_name() {
		return area_name;
	}
	/**
	 * @param area_name the area_name to set
	 */
	public void setArea_name(String area_name) {
		this.area_name = area_name;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
