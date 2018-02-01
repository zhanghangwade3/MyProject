package com.all.app.http;


import com.all.app.base.BeanBase;

/**
 *
 * @ClassName: ResponseEntry
 * @Description: TODO(描述: 响应实体)
 * @author Lee
 * @date 2016年4月8日 上午11:07:58
 * @version V1.0
 */
public class ResponseEntry extends BeanBase {
	private int code;
	private String msg;
	private Object data;

	public ResponseEntry() {
		// TODO Auto-generated constructor stub
	}

	/** true 成功 false 失败 */
	public boolean isSuccess() {
		if (code == 1)
			return true;
		return false;
	}

	public int getCode(){
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}

	/**
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * @param msg
	 *            the msg to set
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}

	/**
	 * @return the data
	 */
	public Object getData() {
		return data;
	}

	/**
	 * @param data
	 *            the data to set
	 */
	public void setData(Object data) {
		this.data = data;
	}

	public ResponseEntry(int code, String msg, Object data) {
		super();
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	@Override
	public String toString() {
		return "ResponseEntry [code=" + code + ", msg=" + msg + ", data="
				+ data.toString() + "]";
	}

}
