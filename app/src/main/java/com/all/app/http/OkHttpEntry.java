package com.all.app.http;
import okhttp3.Call;
public interface OkHttpEntry {
	public void onResponse(Call call, ResponseEntry result);

	public void onFailure(Call call, Exception ex);
}
