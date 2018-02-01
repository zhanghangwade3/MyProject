/*
 *******************************************
 * File: OkHttpCallBack.java
 * Author: Lee
 * Date: 2016年4月7日
 ********************************************/
package com.all.app.http;

import java.io.IOException;

import okhttp3.Call;

public interface OkHttpString {
	public void onResponse(Call call, String result);

	public void onFailure(Call call, IOException ex);
}
