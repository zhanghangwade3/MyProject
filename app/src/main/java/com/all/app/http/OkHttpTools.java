package com.all.app.http;
import com.all.app.utils.FJson;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Request.Builder;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttpTools {
	private static final String TAG = "DeBug模式-OkHttpTools";
	private static OkHttpTools okHttp;
	private OkHttpClient mOkHttpClient;
	private long timeout = 60 * 1000;
	/** DeBug模式 */
	private boolean isDebug = true;

	private boolean isHttps = false;
	private boolean isHostnameVerifier = false;
	private SSLSocketFactory sslSocketFactory;

	public static OkHttpTools getOkHttp() {
		if (null == okHttp)
			synchronized (OkHttpTools.class) {
				if (null == okHttp) {
					okHttp = new OkHttpTools();
				}
			}
		return okHttp;
	}

	public void Init() {
		// TODO Auto-generated method stub
		OkHttpClient.Builder builder = new OkHttpClient.Builder().connectTimeout(timeout, TimeUnit.MILLISECONDS).writeTimeout(timeout, TimeUnit.MILLISECONDS).readTimeout(timeout, TimeUnit.MILLISECONDS);
		if (isHttps) {
			builder.sslSocketFactory(sslSocketFactory);
			builder.hostnameVerifier(new HostnameVerifier() {

				@Override
				public boolean verify(String hostname, SSLSession session) {
					// TODO Auto-generated method stub
					return isHostnameVerifier;
				}
			});
		}
		mOkHttpClient = builder.build();
	}
	/** 是否忽略HostName的验证 true-忽略 false-非忽略 */
	public OkHttpTools setHostnameVerifier(boolean isHostnameVerifier) {
		this.isHostnameVerifier = isHostnameVerifier;
		return this;
	}
	
	public OkHttpTools setCertificate(InputStream[] certificates, InputStream bksFile, String password) {
		isHttps = true;
		sslSocketFactory = SslSocketUtils.getSslSocketFactory(certificates, null, null);
		return this;
	}
	public OkHttpTools setTimeOut(long timeOut){
		this.timeout = timeOut;
		return this;
	}
	

	public void _Get(String url, OkHttpParam param, final OkHttpString callback) {
		if (null != param)
			url = url + param.getUrl();
		if (isDebug && url != null)
			System.err.println(TAG + "-->[url]" + url);
		Request request = RequestBuilder(param).get().url(url).build();
		mOkHttpClient.newCall(request).enqueue(new Callback() {

			@Override
			public void onResponse(Call call, Response resp) throws IOException {
				// TODO Auto-generated method stub
				String result = inputStream2Str(resp.body().byteStream());
				if (isDebug)
					System.err.println(TAG + "-->[Response]" + result);
				callback.onResponse(call, result);
			}

			@Override
			public void onFailure(Call call, IOException ex) {
				// TODO Auto-generated method stub
				if (isDebug)
					System.err.println(TAG + "-->[Err]" + ex.getMessage());
				callback.onFailure(call, ex);
			}
		});
	}

	public void _Post(String url, OkHttpParam param, final OkHttpString callback) {
		if (isDebug && url != null)
			System.err.println(TAG + "-->[url]" + url);
		Request request = RequestBuilder(param).post(param.getRequsetBody()).url(url).build();
		mOkHttpClient.newCall(request).enqueue(new Callback() {

			@Override
			public void onResponse(Call call, Response resp) throws IOException {
				// TODO Auto-generated method stub
				String result = inputStream2Str(resp.body().byteStream());
				if (isDebug)
					System.err.println(TAG + "-->[Response]" + result);
				callback.onResponse(call, result);
			}

			@Override
			public void onFailure(Call call, IOException ex) {
				// TODO Auto-generated method stub
				if (isDebug)
					System.err.println(TAG + "-->[Err]" + ex.getMessage());
				callback.onFailure(call, ex);
			}

		});
	}

	public void _Get(String url, OkHttpParam param, final OkHttpEntry callback) {
		if (null != param)
			url = url + param.getUrl();
		if (isDebug && url != null)
			System.err.println(TAG + "-->[url]" + url);
		Request request = RequestBuilder(param).get().url(url).build();
		mOkHttpClient.newCall(request).enqueue(new Callback() {

			@Override
			public void onResponse(Call call, Response resp) throws IOException {
				// TODO Auto-generated method stub
				String result = inputStream2Str(resp.body().byteStream());
				if (isDebug)
					System.err.println(TAG + "-->[Response]" + result);
				try {
					ResponseEntry entry = FJson.getObject(result, ResponseEntry.class);
					callback.onResponse(call, entry);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					if (isDebug)
						System.err.println(TAG + "-->[Err]" + e.getMessage());
					callback.onFailure(call, e);
				}
			}

			@Override
			public void onFailure(Call call, IOException ex) {
				// TODO Auto-generated method stub
				if (isDebug)
					System.err.println(TAG + "-->[Err]" + ex.getMessage());
				callback.onFailure(call, ex);
			}
		});
	}

	public void _Post(String url, OkHttpParam param, final OkHttpEntry callback) {
		if (isDebug && url != null)
			System.err.println(TAG + "-->[url]" + url);
		Request request = RequestBuilder(param).post(param.getRequsetBody()).url(url).build();
		mOkHttpClient.newCall(request).enqueue(new Callback() {

			@Override
			public void onResponse(Call call, Response resp) throws IOException {
				// TODO Auto-generated method stub
				String result = inputStream2Str(resp.body().byteStream());
				if (isDebug)
					System.err.println(TAG + "-->[Response]" + result);
				try {
					ResponseEntry entry = FJson.getObject(result, ResponseEntry.class);
					callback.onResponse(call, entry);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					if (isDebug)
						System.err.println(TAG + "-->[Err]" + e.getMessage());
					callback.onFailure(call, e);
				}
			}

			@Override
			public void onFailure(Call call, IOException ex) {
				// TODO Auto-generated method stub
				if (isDebug)
					System.err.println(TAG + "-->[Err]" + ex.getMessage());
				callback.onFailure(call, ex);
			}
		});
	}

	MediaType JSON = MediaType.parse("application/json; charset=utf-8");

	public void _PostJson(String url, String jsonStr, final OkHttpEntry callback) {
		if (isDebug && url != null)
			System.err.println(TAG + "-->[url]" + url + "-[jsonStr]-->" + jsonStr);
		RequestBody requestBody = RequestBody.create(JSON, jsonStr);
		Request request = RequestBuilder(null).url(url).post(requestBody).build();
		mOkHttpClient.newCall(request).enqueue(new Callback() {

			@Override
			public void onResponse(Call call, Response resp) throws IOException {
				// TODO Auto-generated method stub
				String result = inputStream2Str(resp.body().byteStream());
				if (isDebug)
					System.err.println(TAG + "-->[Response]" + result);
				try {
					ResponseEntry entry = FJson.getObject(result, ResponseEntry.class);
					callback.onResponse(call, entry);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					callback.onFailure(call, e);
				}
			}

			@Override
			public void onFailure(Call call, IOException ex) {
				// TODO Auto-generated method stub
				callback.onFailure(call, ex);
			}
		});
	}

	private Builder RequestBuilder(OkHttpParam param) {
		Builder builder = new Request.Builder();
		if (null != param && null != param.getHeaders() && param.getHeaders().size() > 0) {
			HashMap map = param.getHeaders();
			Iterator iter = map.entrySet().iterator();
			while (iter.hasNext()) {
				Map.Entry entry = (Map.Entry) iter.next();
				Object key = entry.getKey();
				Object val = entry.getValue();
				builder.addHeader(String.valueOf(key), String.valueOf(val));
			}
		}
		return builder;
	}

	public void cancelTag(Object tag) {
		for (Call call : mOkHttpClient.dispatcher().queuedCalls()) {
			if (tag.equals(call.request().tag())) {
				call.cancel();
			}
		}
		for (Call call : mOkHttpClient.dispatcher().runningCalls()) {
			if (tag.equals(call.request().tag())) {
				call.cancel();
			}
		}
	}

	private static String inputStream2Str(InputStream is) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int i = -1;
		while ((i = is.read()) != -1) {
			baos.write(i);
		}
		return baos.toString();
	}
}
