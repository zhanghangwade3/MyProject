/*
 *******************************************
 * File: LeeTools.java
 * Author: Lee
 * Date: 2015年9月29日
 ********************************************/
package com.all.app.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ImageSpan;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @ClassName: LeeTools
 * @Description: TODO(描述: )
 * @author Lee
 * @date 2015年11月24日 上午9:38:25
 * @version V1.0
 */
public class LeeTools {
	
	
	private static DateFormat format = new SimpleDateFormat("yyyy年MM月dd日");

	public static String getNowStr() {
		return format.format(new Date(System.currentTimeMillis()));
	}

	/**
	 * 
	 * @param str
	 * @return true-小于或等于 false-大于
	 */
	public static boolean datecompare(String str) {
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		String nowStr = getNowStr();
		try {
			c1.setTime(format.parse(str));
			c2.setTime(format.parse(nowStr));
		} catch (java.text.ParseException e) {
			return true;
		}
		int result = c1.compareTo(c2);
		if (result == 0)
			return true;
		else if (result < 0)
			return true;
		else
			return false;
	}

	//版本名
	public static String getVersionName(Context context) {
	    return getPackageInfo(context).versionName;
	}
	 
	//版本号
	public static int getVersionCode(Context context) {
	    return getPackageInfo(context).versionCode;
	}
	 
	private static PackageInfo getPackageInfo(Context context) {
	    PackageInfo pi = null;
	 
	    try {
	        PackageManager pm = context.getPackageManager();
	        pi = pm.getPackageInfo(context.getPackageName(),
	                PackageManager.GET_CONFIGURATIONS);
	 
	        return pi;
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	 
	    return pi;
	}
	
	/**
	 * 是否有某个的权限
	 * 
	 * @param ctx
	 * @param permissionStr
	 *            如:android.permission.INTERNET
	 * @return true-有 false-无
	 */
	public static boolean isPermission(Context ctx, String permissionStr) {
		PackageManager pm = ctx.getPackageManager();
		boolean permission = (PackageManager.PERMISSION_GRANTED == pm
				.checkPermission(permissionStr, ctx.getPackageName()));
		if (permission) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 是否有访问网络状态的权限
	 * 
	 * @return @return true-有 false-无
	 */
	public static boolean isNetStatePermission(Context ctx) {
		if (null == ctx)
			return false;
		PackageManager pm = ctx.getPackageManager();
		boolean permission = (PackageManager.PERMISSION_GRANTED == pm
				.checkPermission("android.permission.ACCESS_NETWORK_STATE",
						ctx.getPackageName()));
		if (permission) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 是否有访问网络的权限
	 * 
	 * @return true-有 false-无
	 */
	public static boolean isNetPermission(Context ctx) {
		if (null == ctx)
			return false;
		PackageManager pm = ctx.getPackageManager();
		boolean permission = (PackageManager.PERMISSION_GRANTED == pm
				.checkPermission("android.permission.INTERNET",
						ctx.getPackageName()));
		if (permission) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 是否有网络连接
	 * 
	 * @return true-有网络连接 false-无网络连接
	 */
	public static boolean isNetworkConnected(Context ctx) {
		if (null != ctx) {
			ConnectivityManager mConnectivityManager = (ConnectivityManager) ctx
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo mNetworkInfo = mConnectivityManager
					.getActiveNetworkInfo();
			if (mNetworkInfo != null) {
				return mNetworkInfo.isAvailable();
			}
		}
		return false;
	}

	public static String getText(View v) {
		if (null == v)
			return "";
		if (v instanceof TextView) {// TextView
			return ((TextView) v).getText().toString() == null ? ""
					: ((TextView) v).getText().toString();
		}
		if (v instanceof Button) {// Button
			return ((Button) v).getText().toString() == null ? ""
					: ((Button) v).getText().toString();
		}
		if (v instanceof CheckBox) {// CheckBox
			return ((CheckBox) v).getText().toString() == null ? ""
					: ((CheckBox) v).getText().toString();
		}
		if (v instanceof RadioButton) {// RadioButton
			return ((RadioButton) v).getText().toString() == null ? ""
					: ((RadioButton) v).getText().toString();
		}
		if (v instanceof EditText) {// EditText
			return ((EditText) v).getText().toString() == null ? ""
					: ((EditText) v).getText().toString();
		}
		return "";
	}

	/**
	 * @Title: setText
	 * @Description: TODO 设置TextView的text 为空时默认显示 --
	 * @param tv
	 * @param text
	 * @return void
	 * @throws
	 */
	public static void setText(View v, String text) {
		setText(v, text, "");
	}

	/**
	 * @Title: setText
	 * @Description: TODO 设置TextView的text
	 * @param tv
	 * @param text
	 * @param defaultText
	 *            默认显示
	 * @return void
	 * @throws
	 */
	public static void setText(View v, String text, String defaultText) {
		if (null == v)
			return;
		if (TextUtils.isEmpty(text))
			text = defaultText;
		if (v instanceof TextView) {// TextView
			((TextView) v).setText(text);
			return;
		}
		if (v instanceof Button) {// Button
			((Button) v).setText(text);
			return;
		}
		if (v instanceof CheckBox) {// CheckBox
			((CheckBox) v).setText(text);
			return;
		}
		if (v instanceof RadioButton) {// RadioButton
			((RadioButton) v).setText(text);
			return;
		}
		if (v instanceof EditText) {// EditText
			((EditText) v).setText(text);
			return;
		}

	}

	/**
	 * @Title: setText
	 * @Description: TODO String资源文件的format方法
	 * @param @param tv
	 * @param @param context
	 * @param @param text
	 * @param @param resId
	 * @return void
	 * @throws
	 */
	public static void setText(View v, Context context, String text, int resId) {
		String format = context.getResources().getString(resId);
		setText(v, String.format(format, text));
	}

	/**
	 * @Title: setText
	 * @Description: TODO String资源文件的format方法
	 * @param tv
	 * @param context
	 * @param i
	 * @param resId
	 * @return void
	 * @throws
	 */
	public static void setText(View v, Context context, int i, int resId) {
		String format = context.getResources().getString(resId);
		setText(v, String.format(format, i));
	}

	/**
	 * @Title: setText
	 * @Description: TODO String资源文件的format方法
	 * @param @param tv
	 * @param @param context
	 * @param @param text1
	 * @param @param text2
	 * @param @param resId
	 * @return void
	 * @throws
	 */
	public static void setText(View v, Context context, String text1,
                               String text2, int resId) {
		String format = context.getResources().getString(resId);
		setText(v, String.format(format, text1, text2));
	}

	/**
	 * @Title: setText
	 * @Description: TODO String资源文件的format方法
	 * @param @param tv
	 * @param @param context
	 * @param @param i1
	 * @param @param i2
	 * @param @param resId
	 * @return void
	 * @throws
	 */
	public static void setText(View v, Context context, int i1, int i2,
                               int resId) {
		String format = context.getResources().getString(resId);
		setText(v, String.format(format, i1, i2));
	}

	/**
	 * @Title: setTextViewStyle
	 * @Description: TODO 优惠价格
	 * @param tv
	 * @param text
	 * @return void
	 * @throws
	 */
	public static void setTextViewStyle(TextView tv) {
		tv.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
	}

	/**
	 * @Title: setUnderline
	 * @Description: TODO 下划线
	 * @param tv
	 * @param text
	 * @return void
	 * @throws
	 */
	public static void setUnderline(View v) {
		if (v instanceof TextView) {// TextView
			((TextView) v).getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
			((TextView) v).getPaint().setAntiAlias(true);
			return;
		}
		if (v instanceof Button) {// Button
			((Button) v).getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
			((Button) v).getPaint().setAntiAlias(true);
			return;
		}
		if (v instanceof CheckBox) {// CheckBox
			((CheckBox) v).getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
			((CheckBox) v).getPaint().setAntiAlias(true);
			return;
		}
		if (v instanceof RadioButton) {// RadioButton
			((RadioButton) v).getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
			((RadioButton) v).getPaint().setAntiAlias(true);
			return;
		}
		if (v instanceof EditText) {// EditText
			((EditText) v).getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
			((EditText) v).getPaint().setAntiAlias(true);
			return;
		}

	}

	/**
	 * @Title: setDelline
	 * @Description: TODO 删除线
	 * @param tv
	 * @param text
	 * @return void
	 * @throws
	 */
	public static void setDeleteline(View v) {
		if (v instanceof TextView) {// TextView
			((TextView) v).getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
			((TextView) v).getPaint().setAntiAlias(true);
			return;
		}
		if (v instanceof Button) {// Button
			((Button) v).getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
			((Button) v).getPaint().setAntiAlias(true);
			return;
		}
		if (v instanceof CheckBox) {// CheckBox
			((CheckBox) v).getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
			((CheckBox) v).getPaint().setAntiAlias(true);
			return;
		}
		if (v instanceof RadioButton) {// RadioButton
			((RadioButton) v).getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
			((RadioButton) v).getPaint().setAntiAlias(true);
			return;
		}
		if (v instanceof EditText) {// EditText
			((EditText) v).getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
			((EditText) v).getPaint().setAntiAlias(true);
			return;
		}
	}

	/**
	 * @TODO ViewHodler简写模式
	 * @param view
	 * @param id
	 * @return
	 */
	public <T extends View> T get(View view, int id) {
		SparseArray<View> viewHolder = (SparseArray<View>) view.getTag();
		if (viewHolder == null) {
			viewHolder = new SparseArray<View>();
			view.setTag(viewHolder);
		}
		View childView = viewHolder.get(id);
		if (childView == null) {
			childView = view.findViewById(id);
			viewHolder.put(id, childView);
		}
		return (T) childView;
	}

	/**
	 * 
	 * @param context
	 * @param v
	 * @param drawableId
	 * @param text
	 */
	public static void spanText(Context context, View v, int drawableId,
                                String text) {
		Bitmap b = BitmapFactory.decodeResource(context.getResources(),
				drawableId);
		ImageSpan imgSpan = new ImageSpan(context, b);
		SpannableString spanString = new SpannableString("ic");
		spanString.setSpan(imgSpan, 0, 2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		if (v instanceof TextView) {// TextView
			((TextView) v).setText(spanString);
			((TextView) v).append(text);
			return;
		}
		if (v instanceof Button) {// Button
			((Button) v).setText(spanString);
			((Button) v).append(text);
			return;
		}
		if (v instanceof CheckBox) {// CheckBox
			((CheckBox) v).setText(spanString);
			((CheckBox) v).append(text);
			return;
		}
		if (v instanceof RadioButton) {// RadioButton
			((RadioButton) v).setText(spanString);
			((RadioButton) v).append(text);
			return;
		}
		if (v instanceof EditText) {// EditText
			((EditText) v).setText(spanString);
			((EditText) v).append(text);
			return;
		}
	}

	/**
	 * @该方法用来调用程序拨号界面,但不拨出
	 * 
	 * @param context
	 *            为调用activity的上下文
	 * @param phoneNum
	 *            为要拨出的号码
	 * */
	public static void callDial(Context context, String phoneNum) {
		Intent intentPhone = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"
				+ phoneNum));// 调用拨号界面
		context.startActivity(intentPhone);
	}

	/**
	 * 正则验证身份证
	 * 
	 * @param idNum
	 * @return true-对 false-错
	 */
	public static boolean matcherIdCard(String idNum) {
		Pattern idNumPattern = Pattern
				.compile("(\\d{14}[0-9a-zA-Z])|(\\d{17}[0-9a-zA-Z])");
		// 通过Pattern获得Matcher
		Matcher idNumMatcher = idNumPattern.matcher(idNum);
		return idNumMatcher.matches();
	}

	public String getTimer(String startDate, int day) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return "";
	}

	/**
	 * 拨打电话
	 * 
	 * @param context
	 * @param phone
	 */
	public static void callPhone(Context context, String phone) {
		Intent intent = new Intent(Intent.ACTION_CALL);
		Uri data = Uri.parse("tel:" + phone);
		intent.setData(data);
		context.startActivity(intent);
	}

}
