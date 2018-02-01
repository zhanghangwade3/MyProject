package com.all.app.base;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.LinearLayout;
import com.all.app.R;
import com.all.app.app.AppManager;
import com.all.app.configs.Urls;
import com.all.app.http.OkHttpEntry;
import com.all.app.http.OkHttpParam;
import com.all.app.http.OkHttpTools;
import com.all.app.http.ResponseEntry;
import com.all.app.utils.ActionSheetDialog;
import com.all.app.utils.AlertDialog;
import com.all.app.utils.Tools;
import com.all.app.utils.XDialog;
import com.all.app.utils.image_selector.MultiImageSelectorActivity;

import java.io.File;
import java.util.ArrayList;

import okhttp3.Call;
/**
 *
 * @ClassName: BaseActivity
 * @Description: TODO(描述:Activity 基类)
 * @author Lee
 * @date 2017年3月28日 下午2:41:31
 * @version V1.0
 */
@SuppressLint("NewApi")
public class BaseActivity extends Activity {
	IntentFilter intentFilter;
	BroadcastReceiver logoutReceiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			new AlertDialog(context).builder().setCancelable(false)
					.setTitle("提示").setMsg("您的账号已在别的设备上登录，当前设备已下线")
					.setPositiveButton("确定", new View.OnClickListener() {

						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							//AppData.Init().LogOut();
							/*startActivity(new Intent(BaseActivity.this,
									LoginActivity.class).putExtra("isLogout",
									true).putExtra("type", "1"));*/
						}
					}).show();

		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		AppManager.Manager().onCreate(this);//Activity管理的初始化
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		//registerReceiver(logoutReceiver, intentFilter);
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		//unregisterReceiver(logoutReceiver);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		AppManager.Manager().onFinish(this);
	}

	private Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			showTips("当前网络不通，请检查网络", null);

		}

	};

    //使用okhttp请求框架
	protected void _PostEntry(String url, OkHttpParam param,
							  final int actionId, boolean isShow) {
		if (!Tools.isNetworkAvailable(this)) {
			handler.sendEmptyMessage(0);
			return;
		}
		if (isShow) {
			showDialog(this, "");
		}
		OkHttpTools.getOkHttp()._Post(Urls._URL + url, param,
				new OkHttpEntry() {

					@Override
					public void onResponse(Call call, final ResponseEntry result) {
						// TODO Auto-generated method stub
						runOnUiThread(new Runnable() {
							public void run() {
								dismissDialog();
								if (!result.isSuccess()
										&& TextUtils.isEmpty(result.getMsg())) {
									result.setMsg(result.getMsg().toString());
								}
								onNetSuccess(actionId, result);
							}
						});
					}

					@Override
					public void onFailure(final Call call, final Exception ex) {
						// TODO Auto-generated method stub
						runOnUiThread(new Runnable() {
							public void run() {
								dismissDialog();
								onNetFailure(call, ex);
								showTips("请求信息不完整", null);

							}
						});
					}
				});
	}

	protected void onNetFailure(Call call, Exception ex) {
	}

	protected void onNetSuccess(int actionId, String result) {

	}

	protected void onNetSuccess(int actionId, ResponseEntry result) {
	}

	/** 提示 Toast */
	//private XToast tipsToast;

	public void showTips(String msg, OnClickListener confirmListener,
                         OnClickListener cancleListener) {
		if (!isDestroyed())
			new AlertDialog(this).builder().setTitle("提示").setMsg(msg).setCancelable(false)
					.setPositiveButton("确定", confirmListener)
					.setNegativeButton("取消", cancleListener).show();
	}

	protected void showTips(String msg, OnClickListener ll) {
		if (!isDestroyed())
			new AlertDialog(this).builder().setTitle("提示").setMsg(msg).setCancelable(false)
					.setPositiveButton("确定", ll).show();
	}

	protected void showTips(String title, String msg, OnClickListener ll) {
		if (!isDestroyed())
			new AlertDialog(this).builder().setTitle(title).setMsg(msg).setCancelable(false)
					.setPositiveButton("确定", ll).show();
	}

	// //////点击空白隐藏软键盘//////////////////////////////////////////////////////
	public boolean dispatchTouchEvent(MotionEvent ev) {
		if (ev.getAction() == MotionEvent.ACTION_DOWN) {
			// 获得当前得到焦点的View，一般情况下就是EditText（特殊情况就是轨迹求或者实体案件会移动焦点）
			View v = getCurrentFocus();
			if (isShouldHideInput(v, ev)) {
				hideSoftInput(v.getWindowToken());
			}
		}
		return super.dispatchTouchEvent(ev);
	}

	/**
	 * 根据EditText所在坐标和用户点击的坐标相对比，来判断是否隐藏键盘，因为当用户点击EditText时没必要隐藏
	 *
	 * @param v
	 * @param event
	 * @return
	 */
	private boolean isShouldHideInput(View v, MotionEvent event) {
		if (v != null && (v instanceof EditText)) {
			int[] l = { 0, 0 };
			v.getLocationInWindow(l);
			int left = l[0], top = l[1], bottom = top + v.getHeight(), right = left
					+ v.getWidth();
			if (event.getX() > left && event.getX() < right
					&& event.getY() > top && event.getY() < bottom) {
				// 点击EditText的事件，忽略它。
				return false;
			} else {
				return true;
			}
		}
		// 如果焦点不是EditText则忽略，这个发生在视图刚绘制完，第一个焦点不在EditView上，和用户用轨迹球选择其他的焦点
		return false;
	}

	/**
	 * 多种隐藏软件盘方法的其中一种
	 *
	 * @param token
	 */
	protected void hideSoftInput(IBinder token) {
		if (token != null) {
			InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
			im.hideSoftInputFromWindow(token,
					InputMethodManager.HIDE_NOT_ALWAYS);
		}
	}

	LinearLayout ll_wx;
	LinearLayout ll_friend;
	LinearLayout ll_weibo;
	LinearLayout ll_qq;
	LinearLayout ll_qzone;
	ActionSheetDialog shareDialog;

	protected void showShare() {
		View shareView = getLayoutInflater().inflate(R.layout.view_share, null);
		ll_wx = (LinearLayout) shareView.findViewById(R.id.ll_wx);
		ll_friend = (LinearLayout) shareView.findViewById(R.id.ll_friend);
		ll_weibo = (LinearLayout) shareView.findViewById(R.id.ll_weibo);
		ll_qq = (LinearLayout) shareView.findViewById(R.id.ll_qq);
		ll_qzone = (LinearLayout) shareView.findViewById(R.id.ll_qzone);
		ll_wx.setOnClickListener(onShareListener);
		ll_friend.setOnClickListener(onShareListener);
		ll_weibo.setOnClickListener(onShareListener);
		ll_qq.setOnClickListener(onShareListener);
		ll_qzone.setOnClickListener(onShareListener);
		shareDialog = new ActionSheetDialog(this);
		shareDialog.builder().setContentView(shareView).setCancelable(true)
				.setCanceledOnTouchOutside(true).show();
	}

	public OnClickListener onShareListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			if (shareDialog != null)
				shareDialog.dismiss();
			ClickShared(v);
		}
	};

	/**
	 * 加载网页
	 *
	 * @param webview
	 * @param url
	 */
	private static final String APP_CACAHE_DIRNAME = "/webcache";

	protected void loadUrl(WebView webview, String url) {
		if (!Tools.isNetworkAvailable(this)) {
			showTips("当前网络不通，请检查网络", null);
			return;
		}
		showDialog(this, "");
		webview.loadUrl(url);
		// 覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
		webview.setWebViewClient(new WebViewClient() {
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				/*System.out.println("=======================deeeeee==========="+url);
				//网站有可能不对
				if (url=="https://mobile.abchina.com/mpay/KCodePaymentInitBAct.do?TOKEN=14773013863495799170"){
					finish();//让页面关闭
				}*/
				view.loadUrl(url);
				return true;
			}
		});
		WebSettings settings = webview.getSettings();
		// 不使用缓存：
		settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
		settings.setAppCacheEnabled(false);
		settings.setJavaScriptEnabled(true);// 支持js
		settings.setDefaultTextEncodingName("GBK");// 设置字符编码
		webview.setWebChromeClient(new WebChromeClient() {
			@Override
			public void onProgressChanged(WebView view, int position) {
				System.out.println("--------------position=" + position);
				if (position == 100) {
					dismissDialog();
				}
				super.onProgressChanged(view, position);
			}
		});
	}
	protected void ClickShared(View v) {
		// showTips("分享成功", null);
	}
	protected void loadData(final WebView webview, String url) {
		if (!Tools.isNetworkAvailable(this)) {
			handler.sendEmptyMessage(0);
			return;
		}
		showDialog(this, "");
		webview.loadData(url, "text/html; charset=UTF-8", null);// 这种写法可以正确解码
		// 覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开

		webview.setWebViewClient(new WebViewClient() {
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				System.out.println("99999999999999999999999999999999"+url);
				if(url.endsWith("callBackb2c.do")){

				}
				// 返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
				view.loadUrl(url);
				return true;
			}
		});

		WebSettings settings = webview.getSettings();
		settings.setCacheMode(WebSettings.LOAD_NO_CACHE);// 不使用缓存：
		settings.setAppCacheEnabled(false);
		settings.setJavaScriptEnabled(true);// 支持js
		settings.setDefaultTextEncodingName("GBK");// 设置字符编码
		webview.setWebChromeClient(new WebChromeClient() {

			@Override
			public void onProgressChanged(WebView view, int position) {

				System.out.println("--------------position=" + position);
				if (position == 100) {
					dismissDialog();
				}
				super.onProgressChanged(view, position);

			}

		});
	}


	/**
	 * 清除WebView缓存
	 */
	public void clearWebViewCache() {

		// 清理Webview缓存数据库
		try {
			deleteDatabase("webview.db");
			deleteDatabase("webviewCache.db");
		} catch (Exception e) {
			e.printStackTrace();
		}

		// WebView 缓存文件
		File appCacheDir = new File(getFilesDir().getAbsolutePath()
				+ APP_CACAHE_DIRNAME);
		Log.e(TAG, "appCacheDir path=" + appCacheDir.getAbsolutePath());

		File webviewCacheDir = new File(getCacheDir().getAbsolutePath()
				+ "/webviewCache");
		Log.e(TAG, "webviewCacheDir path=" + webviewCacheDir.getAbsolutePath());

		// 删除webview 缓存目录
		if (webviewCacheDir.exists()) {
			deleteFile(webviewCacheDir);
		}
		// 删除webview 缓存 缓存目录
		if (appCacheDir.exists()) {
			deleteFile(appCacheDir);
		}
	}

	/**
	 * 递归删除 文件/文件夹
	 * @param file
	 */

	/**
	 * TAG
	 */
	public String TAG = this.getClass().getSimpleName();

	public void deleteFile(File file) {

		Log.i(TAG, "delete file path=" + file.getAbsolutePath());

		if (file.exists()) {
			if (file.isFile()) {
				file.delete();
			} else if (file.isDirectory()) {
				File files[] = file.listFiles();
				for (int i = 0; i < files.length; i++) {
					deleteFile(files[i]);
				}
			}
			file.delete();
		} else {
			Log.e(TAG, "delete file no exists " + file.getAbsolutePath());
		}
	}

	/**
	 * 显示dialog
	 *
	 * @param mContext
	 *            上下文
	 * @param msg
	 *            提示语
	 */
	protected XDialog loadingDialog;

	public void showDialog(Context mContext, String msg) {
		if (null != loadingDialog) {
			loadingDialog.dismiss();
		}
		loadingDialog = new XDialog(mContext, R.style.Dialog_image, msg);
		loadingDialog.show();
	}

	/**
	 * 让dialog消失
	 */
	public void dismissDialog() {
		if (null != loadingDialog) {
			loadingDialog.dismiss();
		}
	}


	public static void setPricePoint(final EditText editText) {
		editText.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
				if (s.toString().contains(".")) {
					if (s.length() - 1 - s.toString().indexOf(".") > 2) {
						s = s.toString().subSequence(0,
								s.toString().indexOf(".") + 3);
						editText.setText(s);
						editText.setSelection(s.length());
					}
				}
				if (s.toString().trim().substring(0).equals(".")) {
					s = "0" + s;
					editText.setText(s);
					editText.setSelection(2);
				}

				if (s.toString().startsWith("0")
						&& s.toString().trim().length() > 1) {
					if (!s.toString().substring(1, 2).equals(".")) {
						editText.setText(s.subSequence(0, 1));
						editText.setSelection(1);
						return;
					}
				}
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub

			}

		});
	}

	protected int computeScrollDeltaToGetChildRectOnScreen(Rect rect) {
		return 0;
	}
	protected void setAlias(String alias) {
		// 调用 Handler 来异步设置别名
		mHandler.sendMessage(mHandler.obtainMessage(MSG_SET_ALIAS, alias));
	}
	private static final int MSG_SET_ALIAS = 1001;
	private final Handler mHandler = new Handler() {
		@Override
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case MSG_SET_ALIAS:
				Log.d(TAG, "Set alias in handler.");
				// 调用 JPush 接口来设置别名。
				//JPushInterface.setAliasAndTags(getApplicationContext(), (String) msg.obj, null, mAliasCallback);
				break;
			default:
				Log.i(TAG, "Unhandled msg - " + msg.what);
			}
		}
	};
	/**
	 * 打开图片选择器
	 *
	 * @param maxNum
	 *            最大支持几张
	 * @param showCamera
	 *            是否需要照相机
	 * @param isMultiselect
	 *            是否支持多选
	 */

	protected ArrayList<String> mSelectPath = new ArrayList<String>();
	protected ArrayList<String> mSelectPath2 = new ArrayList<String>();
	protected ArrayList<String> mSelectPath3= new ArrayList<String>();
	protected static final int REQUEST_IMAGE = 1;
	public void openChosePic(int maxNum, boolean showCamera,
							 boolean isMultiselect) {
		int selectedMode = MultiImageSelectorActivity.MODE_MULTI;

		if (isMultiselect) {
			selectedMode = MultiImageSelectorActivity.MODE_MULTI;
		} else {
			selectedMode = MultiImageSelectorActivity.MODE_SINGLE;

		}

		Intent intent = new Intent(this, MultiImageSelectorActivity.class);
		// 是否显示拍摄图片
		intent.putExtra(MultiImageSelectorActivity.EXTRA_SHOW_CAMERA,
				showCamera);
		// 最大可选择图片数量
		intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_COUNT, maxNum);
		// 选择模式
		intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_MODE,
				selectedMode);
		// 默认选择


			if (mSelectPath != null && mSelectPath.size() > 0) {
				intent.putExtra(
						MultiImageSelectorActivity.EXTRA_DEFAULT_SELECTED_LIST,
						mSelectPath);
			}

		startActivityForResult(intent, REQUEST_IMAGE);
	}


	public void openChosePic2(int maxNum, boolean showCamera,
							 boolean isMultiselect) {
		int selectedMode = MultiImageSelectorActivity.MODE_MULTI;

		if (isMultiselect) {
			selectedMode = MultiImageSelectorActivity.MODE_MULTI;
		} else {
			selectedMode = MultiImageSelectorActivity.MODE_SINGLE;

		}

		Intent intent = new Intent(this, MultiImageSelectorActivity.class);
		// 是否显示拍摄图片
		intent.putExtra(MultiImageSelectorActivity.EXTRA_SHOW_CAMERA,
				showCamera);
		// 最大可选择图片数量
		intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_COUNT, maxNum);
		// 选择模式
		intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_MODE,
				selectedMode);
		// 默认选择


		if (mSelectPath2 != null && mSelectPath2.size() > 0) {
			intent.putExtra(
					MultiImageSelectorActivity.EXTRA_DEFAULT_SELECTED_LIST,
					mSelectPath2);
		}

		startActivityForResult(intent, REQUEST_IMAGE);
	}
	public void openChosePic3(int maxNum, boolean showCamera,
							 boolean isMultiselect) {
		int selectedMode = MultiImageSelectorActivity.MODE_MULTI;

		if (isMultiselect) {
			selectedMode = MultiImageSelectorActivity.MODE_MULTI;
		} else {
			selectedMode = MultiImageSelectorActivity.MODE_SINGLE;

		}

		Intent intent = new Intent(this, MultiImageSelectorActivity.class);
		// 是否显示拍摄图片
		intent.putExtra(MultiImageSelectorActivity.EXTRA_SHOW_CAMERA,
				showCamera);
		// 最大可选择图片数量
		intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_COUNT, maxNum);
		// 选择模式
		intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_MODE,
				selectedMode);
		// 默认选择


		if (mSelectPath3 != null && mSelectPath3.size() > 0) {
			intent.putExtra(
					MultiImageSelectorActivity.EXTRA_DEFAULT_SELECTED_LIST,
					mSelectPath3);
		}

		startActivityForResult(intent, REQUEST_IMAGE);
	}


	protected ArrayList<String> mImgPath = new ArrayList<String>();
	protected static final int REQUEST_IMG = 2;
	public void openChoseImg(int maxNum, boolean showCamera,
							 boolean isMultiselect) {
		int selectedMode = MultiImageSelectorActivity.MODE_MULTI;

		if (isMultiselect) {
			selectedMode = MultiImageSelectorActivity.MODE_MULTI;
		} else {
			selectedMode = MultiImageSelectorActivity.MODE_SINGLE;

		}

		Intent intent = new Intent(this, MultiImageSelectorActivity.class);
		// 是否显示拍摄图片
		intent.putExtra(MultiImageSelectorActivity.EXTRA_SHOW_CAMERA,
				showCamera);
		// 最大可选择图片数量
		intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_COUNT, maxNum);
		// 选择模式
		intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_MODE,
				selectedMode);
		// 默认选择
		if (mImgPath != null && mImgPath.size() > 0) {
			intent.putExtra(
					MultiImageSelectorActivity.EXTRA_DEFAULT_SELECTED_LIST,
					mImgPath);
		}
		startActivityForResult(intent, REQUEST_IMG);
	}


}
