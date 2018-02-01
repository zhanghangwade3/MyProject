package com.all.app.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Typeface;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.telephony.TelephonyManager;
import android.text.format.DateFormat;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tools {
	public static final boolean IS_DEVELOPMODE = true;
	public static final int REQUSEST_CODE_TAKE_PHOTO = 1000;
	public static final int REQUSEST_CODE_SELECT_PIC_IN_ALBUM = 1001;
	public final static String TAG = "Qee";
	public static final String APP_NAME = "CIU";
	public final static String SDCARD_PATH = Environment.getExternalStorageDirectory() + "/";
	public final static String ROOT_PATH = SDCARD_PATH + APP_NAME + "/";
	public final static String FOLDER_NAME_CAMERA = "camera/";
	public final static String FOLDER_NAME_RECORDER = "recorder/";
	public final static String CAMERA_PATH = ROOT_PATH + FOLDER_NAME_CAMERA;
	public final static String RECORDER_PATH = ROOT_PATH + FOLDER_NAME_RECORDER;
	public final static String FILE_NAME_PHOTO = "MessageImage.jpg";
	public final static String FILE_NAME_AUDIO = System.currentTimeMillis() + ".amr";
	public final static String PATH_FILE_PHOTO = CAMERA_PATH + FILE_NAME_PHOTO;
	public final static String PATH_FILE_AUDIO = RECORDER_PATH + FILE_NAME_AUDIO;
	private static Toast toast = null;
	public static Location location;

	public static class DisplayUtil {

		/**
		 * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
		 */
		public static int dip2px(Context context, float dpValue) {
			final float scale = context.getResources().getDisplayMetrics().density;
			Log.i("dip2px", "scale=" + scale);
			return (int) (dpValue * scale + 0.5f);
		}

		/**
		 * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
		 */
		public static int px2dip(Context context, float pxValue) {
			final float scale = context.getResources().getDisplayMetrics().density;
			Log.i("dip2px", "scale=" + scale);
			return (int) (pxValue / scale + 0.5f);
		}

		public static int getScreenWidthPixels(Context context) {
			return context.getResources().getDisplayMetrics().widthPixels;
		}

		public static int getScreenHeightPixels(Context context) {
			return context.getResources().getDisplayMetrics().heightPixels;
		}
	}

	public static class CodingUtil {
		private static final String UTF_8 = "utf-8";

		public static String utf8ToUrl(String utf8Str) {
			String URLStr = "";
			try {
				URLStr = URLEncoder.encode(utf8Str, UTF_8);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			return URLStr;
		}
	}

	public static class FileUtil {
		/**
		 * <p>
		 * 将文件转成base64 字符串
		 * </p>
		 * 
		 * @param path
		 *            文件路径
		 * @return
		 * @throws Exception
		 */
		public static String FileToBase64Str(String path) throws Exception {
			File file = new File(path);
			BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
			byte[] buffer = new byte[(int) file.length()];
			in.read(buffer);
			in.close();
			return Base64.encodeToString(buffer, Base64.DEFAULT);
		}

		/**
		 * <p>
		 * 将base64字符保存文本文件
		 * </p>
		 * @param
		 * @param targetPath
		 * @throws Exception
		 */
		public static void base64Str2File(String base64Str, String targetPath) throws Exception {
			byte[] bytes = Base64.decode(base64Str, Base64.DEFAULT);
			File newFile = new File(targetPath);
			BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(newFile));
			out.write(bytes);
			out.flush();
			out.close();
		}
	}

	public static class ImageUtil {
		/**
		 * 将图片等比缩成高为150像素的新图
		 * 
		 * @author Larry
		 */
		public static Bitmap imageZoomOut(Bitmap bitmap) {
			if (bitmap != null) {
				float width = bitmap.getWidth();
				float height = bitmap.getHeight();
				final float newHeight = 150;
				float newWidth = newHeight * (width / height);
				// 取得想要缩放的matrix参数
				Matrix matrix = new Matrix();
				matrix.postScale(1f, 1f);
				// 得到新的图片后压缩进流中
				Bitmap bit = Bitmap.createBitmap(bitmap, 0, 0, Math.round(width), Math.round(height), matrix, true);
				Log.i("Qee", "Zooming out image,newWidth = " + newWidth + "  newHeight=" + newHeight);
				return Bitmap.createScaledBitmap(bit, Math.round(newWidth), Math.round(newHeight), false);
			}
			return null;
		}

		/**
		 * bitmap转为base64
		 * 
		 * @param bitmap
		 * @return
		 */
		public static String bitmapToBase64(Bitmap bitmap) {

			String result = null;
			ByteArrayOutputStream baos = null;
			try {
				if (bitmap != null) {
					baos = new ByteArrayOutputStream();

					// 获得图片的宽高
					int width = bitmap.getWidth();
					int height = bitmap.getHeight();
					float scaleWidth = 0.5f;
					float scaleHeight = 0.5f;
					if (height >= width) {
						if (height >= 3000) {
							scaleWidth = 0.3f;
							scaleHeight = 0.3f;
						} else if (3000 > height && height > 2000) {
							scaleWidth = 0.5f;
							scaleHeight = 0.5f;
						} else if (2000 >= height && height >= 1000) {
							scaleWidth = 0.7f;
							scaleHeight = 0.7f;
						}
					} else {
						if (width >= 3000) {
							scaleWidth = 0.3f;
							scaleHeight = 0.3f;
						} else if (3000 > width && width > 2000) {
							scaleWidth = 0.5f;
							scaleHeight = 0.5f;
						} else if (2000 >= width && width >= 1000) {
							scaleWidth = 0.7f;
							scaleHeight = 0.7f;
						}
					}
					// 取得想要缩放的matrix参数
					Matrix matrix = new Matrix();
					matrix.postScale(scaleWidth, scaleHeight);
					// 得到新的图片后压缩进流中
					Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true).compress(Bitmap.CompressFormat.JPEG,
							50, baos);
					// bitmap.compress(Bitmap.CompressFormat.JPEG, 50, baos);
					baos.flush();
					baos.close();
					byte[] bitmapBytes = baos.toByteArray();
					result = Base64.encodeToString(bitmapBytes, Base64.DEFAULT);
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (baos != null) {
						baos.flush();
						baos.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return result;
		}

		/**
		 * base64转为bitmap
		 * 
		 * @param base64Data
		 * @return
		 */
		public static Bitmap base64ToBitmap(String base64Data) {
			byte[] bytes = Base64.decode(base64Data, Base64.DEFAULT);
			return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
		}
	}

	/**
	 * get IMEI to identify user
	 * 
	 * @author Larry 1 moblie 1 IMEI
	 */
	public static String getIMEI(Context context) {
		return ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
	}

	/*获取APP versionCode*/
	public static int getVersionCode(Context context) {
		try {
			return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return -1;
	}

	/*获取手机品牌*/
	public static String getPhoneName(){
		return Build.MANUFACTURER;
	}

	/*获取手机型号*/
	public static String getPhoneModel(){
		return Build.MODEL;
	}

	/*获取SDK版本或固件版本*/
	public static String getSdkVersion(){
		return Build.VERSION.RELEASE;
	}

	/*获取APP versionName*/
	public static String getVersionName(Context context) {
		try {
			return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return "";
	}

	/*获取wifi状态下手机mac*/
	public static String getLocalMac() {
		String mac="";
		String str = "";
		try
		{
			Process pp = Runtime.getRuntime().exec("cat /sys/class/net/wlan0/address ");
			InputStreamReader ir = new InputStreamReader(pp.getInputStream());
			LineNumberReader input = new LineNumberReader(ir);
			for (; null != str;)
			{
				str = input.readLine();
				if (str != null)
				{
					mac = str.trim();// 去空格
					break;
				}
			}
		} catch (IOException ex) {
			// 赋予默认值
			ex.printStackTrace();
		}
		return mac;

	}

	public static String formatIpAddresss(int ipAdress) {
		return (ipAdress & 0xFF) + "." + ((ipAdress >> 8) & 0xFF) + "." + ((ipAdress >> 16) & 0xFF) + "." + ((ipAdress >> 24) & 0xFF);
	}



	public static int getDpi(Activity context) {
		DisplayMetrics metric = new DisplayMetrics();
		context.getWindowManager().getDefaultDisplay().getMetrics(metric);
		Log.i("DPI", metric.densityDpi + "");
		return metric.densityDpi; // 屏幕密度DPI（120 / 160 / 240 /320）
	}

	public static void installApp(Context context, String downloadedApkPath) {

		Uri uri = Uri.fromFile(new File(downloadedApkPath));

		Intent intent = new Intent(Intent.ACTION_VIEW);

		intent.setDataAndType(uri, "application/vnd.android.package-archive");

		context.startActivity(intent);
	}

	public static String getFormatedYear(long longTime) {
		return DateFormat.format("yyyy-MM-dd", longTime * 1000).toString();
	}

	public static String getFormatedDate(long longTime) {
		return DateFormat.format("MM月dd日", longTime * 1000).toString();
	}

	public static String getFormatedTime(long longTime) {
		return DateFormat.format("kk:mm", longTime * 1000).toString();
	}

	public static String getFormatedMiao(long longTime) {
		return DateFormat.format("kk:mm:ss", longTime * 1000).toString();
	}

	public static String getFormatedMsgTime(long longTime) {
		String date = getFormatedDate(longTime);
		String time = getFormatedTime(longTime);
		return date + "\n" + time;
	}

	public static String getFormateDongTaiTime(long longTime) {
		String year = getFormatedYear(longTime);
		String time = getFormatedMiao(longTime);
		return year + " " + time;
	}

	public static String getFormateServiceTime(long longTime) {
		String year = getFormatedYear(longTime);
		return year;
	}

	public static AlertDialog.Builder getExitAlertDialog(final Context context) {
		return new AlertDialog.Builder(context).setTitle("是否退出？").setIcon(android.R.drawable.ic_dialog_info)
				.setPositiveButton("确定", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// 点击“确认”后退出
						// AppManager.getAppManager().AppExit(context);
					}
				}).setNegativeButton("返回", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// 点击“返回”后的操作,这里不设置没有任何操作
					}
				});
	}

	//判断是否有网络连接
	public static boolean isNetworkAvailable(Context context) {
		if (context != null) {
			ConnectivityManager mConnectivityManager = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
			if (mNetworkInfo != null) {
				return mNetworkInfo.isAvailable();
			}
		}
		return false;
	}

	//判断WIFI网络是否可用
	public static boolean isWifiConnected(Context context) {
		if (context != null) {
			ConnectivityManager mConnectivityManager = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo mWiFiNetworkInfo = mConnectivityManager
					.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
			if (mWiFiNetworkInfo != null) {
				return mWiFiNetworkInfo.isAvailable();
			}
		}
		return false;
	}

	//判断MOBILE网络是否可用
	public static boolean isMobileConnected(Context context) {
		if (context != null) {
			ConnectivityManager mConnectivityManager = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo mMobileNetworkInfo = mConnectivityManager
					.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
			if (mMobileNetworkInfo != null) {
				return mMobileNetworkInfo.isAvailable();
			}
		}
		return false;
	}

	/**
	 * 弹出网络设置对话框
	 */
	public static void showNetworkSettingDialog(final Activity context) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context);

		builder.setTitle("无网络").setMessage("没有检测到网络,是否进行网络设置?");

		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Intent intent = null;
				try {
					int sdkVersion = android.os.Build.VERSION.SDK_INT;
					if (sdkVersion > 10) {
						intent = new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS);
					} else {
						intent = new Intent();
						ComponentName comp = new ComponentName("com.android.settings",
								"com.android.settings.WirelessSettings");
						intent.setComponent(comp);
						intent.setAction("android.intent.action.VIEW");
					}
					context.startActivityForResult(intent, 100);
				} catch (Exception e) {
					Log.w(TAG, "open network settings failed, please check...");
					e.printStackTrace();
				}
			}
		}).setNegativeButton("取消", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
				context.finish();
			}
		}).show();
	}

	public static void showToast(Context context, String text) {
		toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.show();
	}


	/**
	 * 自定义字体
	 * 
	 * @return
	 */
	public static Typeface TextType(Context context) {
		Typeface type_chinese = Typeface.createFromAsset(context.getAssets(), "font/youyuan.ttf");
		return type_chinese;
	}

	/**
	 * 判断输入的数据是否合法
	 * 
	 * @param text
	 * @return true 合法 ||false 不合法
	 */
	public static boolean isDouble(String text) {
		try {
			Double.parseDouble(text);
			return true;
		} catch (NumberFormatException ex) {
			return false;
		}
	}

	/**
	 * 
	 * @param text
	 *            文字或字符的组合
	 * @param min
	 *            最小长度
	 * @param max
	 *            最大长度
	 * @return true 合法 ||false 不合法
	 */
	public static boolean isLegalLength(String text, int min, int max) {
		int sum = 0;
		String regCh = "[\u4e00-\u9fa5]";
		Pattern pattern = Pattern.compile(regCh);
		char[] chr = text.toCharArray();
		for (int i = 0; i < chr.length; i++) {
			Matcher match = pattern.matcher(chr[i] + "");
			if (match.matches()) {
				sum += 2;
			} else {
				sum += 1;
			}
		}
		if (min <= sum && sum <= max) {
			return true;
		}
		return false;
	}

	/**
	 * 启动另一个app
	 * 
	 * @param mContext
	 *            上下文
	 * @param packageStr
	 *            包名
	 */
	public static void openOtherApp(Context mContext, String packageStr) {
		try {
			PackageManager packageManager = mContext.getPackageManager();
			Intent intent = new Intent();
			intent = packageManager.getLaunchIntentForPackage(packageStr);
			mContext.startActivity(intent);
		} catch (Exception e) {
			e.printStackTrace();
			Intent intent = new Intent();
			intent.setAction("android.intent.action.VIEW");
			Uri content_url = Uri.parse("http://weixin.qq.com/");
			intent.setData(content_url);
			mContext.startActivity(intent);

		}
	}

}
