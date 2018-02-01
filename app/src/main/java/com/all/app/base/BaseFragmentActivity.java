package com.all.app.base;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
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
import okhttp3.Call;

/**
 * 
 * @ClassName: BaseActivity
 * @Description: TODO(描述: FragmentActivity 基类)
 * @author Lee
 * @date 2017年3月28日 下午4:41:31
 * @version V1.0
 */
public class BaseFragmentActivity extends FragmentActivity {
	protected static final String TAG = "JPUSH";

	IntentFilter intentFilter;
	BroadcastReceiver logoutReceiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			new AlertDialog(context).builder().setCancelable(false).setTitle("提示").setMsg("您的账号已在别的设备上登录，当前设备已下线")
					.setPositiveButton("确定", new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					//AppData.Init().LogOut();
					/*startActivity(new Intent(BaseFragmentActivity.this, LoginActivity.class).putExtra("isLogout", true)
							.putExtra("type", "1"));*/
				}
			}).show();
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		AppManager.Manager().onCreate(this);
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();

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
	protected void _PostEntry(String url, OkHttpParam param, final int actionId, boolean isShow) {
		if (!Tools.isNetworkAvailable(this)) {
			showTips("当前网络不通，请检查网络", null);
			return;
		}
		if (isShow) {
			showDialog(this, "");
		}
		OkHttpTools.getOkHttp()._Post(Urls._URL + url, param, new OkHttpEntry() {

			@Override
			public void onResponse(Call call, final ResponseEntry result) {
				// TODO Auto-generated method stub
				runOnUiThread(new Runnable() {
					public void run() {
						dismissDialog();
						if (!result.isSuccess() && TextUtils.isEmpty(result.getMsg())) {
							result.setMsg("操作失败");
						}
						onNetSuccess(actionId, result);
					}
				});
			}

			@Override
			public void onFailure(final Call call, final Exception ex) {
				runOnUiThread(new Runnable() {
					public void run() {
						dismissDialog();
						onNetFailure(actionId, call, ex);
					}
				});
			}
		});
	}

	protected void onNetFailure(int actionId, Call call, Exception ex) {
		showTips("操作失败", null);
	}

	protected void onNetSuccess(int actionId, String result) {

	}

	protected void onNetSuccess(int actionId, ResponseEntry result) {
	}


	/** 提示 Toast */
	//private XToast tipsToast;

	public void showTips(String msg, OnClickListener ll) {
		new AlertDialog(this).builder().setTitle("提示").setMsg(msg).setCancelable(false).setPositiveButton("确定", ll)
				.show();
	}

	public void showTips(String title, String msg, OnClickListener ll) {

		new AlertDialog(this).builder().setTitle(title).setMsg(msg).setCancelable(false).setPositiveButton("确定", ll)
				.show();
	}

	public void showTipNoncancelable(String msg, OnClickListener ll) {
		new AlertDialog(this).builder().setTitle("提示").setMsg(msg).setCancelable(false).setPositiveButton("确定", ll)
				.show();
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
			int left = l[0], top = l[1], bottom = top + v.getHeight(), right = left + v.getWidth();
			if (event.getX() > left && event.getX() < right && event.getY() > top && event.getY() < bottom) {
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
	private void hideSoftInput(IBinder token) {
		if (token != null) {
			InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
			im.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
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
		shareDialog.builder().setContentView(shareView).setCancelable(true).setCanceledOnTouchOutside(true).show();
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

	protected void ClickShared(View v) {
		// TODO Auto-generated method stub

	}

	/**
	 * 让dialog消失
	 */
	public void dismissDialog() {
		if (null != loadingDialog) {
			loadingDialog.dismiss();
		}

	}

	protected void loadData(WebView webview, String url) {
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
				// TODO Auto-generated method stub
				// 返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
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

	//////////////////// 设置别名 ///////////////////
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

}
