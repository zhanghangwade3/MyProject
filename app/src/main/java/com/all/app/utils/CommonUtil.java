package com.all.app.utils;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.TextUtils;

import com.all.app.app.AppData;
import com.all.app.configs.Urls;
import com.all.app.http.OkHttpEntry;
import com.all.app.http.OkHttpParam;
import com.all.app.http.OkHttpTools;
import com.all.app.http.ResponseEntry;

import okhttp3.Call;

/**
 *
 * @author jst
 */
public class CommonUtil {

	/**
	 * Try to return the absolute file path from the given Uri  兼容了file:///开头的 和 content://开头的情况
	 *
	 * @param context
	 * @param uri
	 * @return the file path or null
	 */
	public static String getRealFilePathFromUri(final Context context, final Uri uri ) {
		if ( null == uri ) return null;
		final String scheme = uri.getScheme();
		String data = null;
		if ( scheme == null )
			data = uri.getPath();
		else if ( ContentResolver.SCHEME_FILE.equals( scheme ) ) {
			data = uri.getPath();
		} else if ( ContentResolver.SCHEME_CONTENT.equals( scheme ) ) {
			Cursor cursor = context.getContentResolver().query( uri, new String[] { MediaStore.Images.ImageColumns.DATA }, null, null, null );
			if ( null != cursor ) {
				if ( cursor.moveToFirst() ) {
					int index = cursor.getColumnIndex( MediaStore.Images.ImageColumns.DATA );
					if ( index > -1 ) {
						data = cursor.getString( index );
					}
				}
				cursor.close();
			}
		}
		return data;
	}

	/*获取经纬度信息*/
	/*public static void getLocationInfo(final Context context, final String opType) {
		BDLoacat bdLoacat = new BDLoacat(context, 0);
		bdLoacat.setBDReceivedListener(new BDLoacat.BDReceiveListener() {

			@Override
			public void onReceiveLocation(BDLocatInfo location) {
				// TODO Auto-generated method stub
				String Latitude = String.valueOf(location.getLatitude());
				String Longitude = String.valueOf(location.getLongitude());
				String locationInfo = location.getAddress();
				if(Latitude.equals("4.9E-324")){
					Latitude = "";
				}
				if(Longitude.equals("4.9E-324")){
					Longitude = "";
				}
				if(TextUtils.isEmpty(locationInfo)){
					locationInfo = "";
				}
				recordUserInfo(context,opType, Latitude, Longitude, locationInfo);
			}

		});
		bdLoacat.start();
	}*/

	/*记录用户信息*/
	/*public static void recordUserInfo(Context context, String opType, String Latitude, String Longitude, String locationInfo) {
		String userId = "";
		UserInfo info = AppData.Init().getUserInfo();
		if (info != null) {
			userId = info.getAppuser_id();
		} else {
			userId = "";
		}
		OkHttpParam param = new OkHttpParam();
		param.add("userId", userId);
		param.add("opType", opType);
		param.add("phoneOsType", "1");
		param.add("appVersion", Tools.getVersionName(context));
		param.add("lat", Latitude);
		param.add("lng", Longitude);
		param.add("location", locationInfo);
		param.add("phoneImei", Tools.getIMEI(context));
		param.add("phoneIp", Tools.getLocalIpAddress());
		param.add("phoneMac", Tools.getLocalMac());
		param.add("phoneBrand", Tools.getPhoneName());
		param.add("phoneModel", Tools.getPhoneModel());
		param.add("phoneSDKVersion", Tools.getSdkVersion());
		_PostEntry(Urls.recordUserPhoneRelated, param, Urls.ActionId.recordUserPhoneRelated, false);
	}
*/
	protected static void _PostEntry(String url, OkHttpParam param, final int actionId, boolean isShow) {
		OkHttpTools.getOkHttp()._Post(Urls._URL + url, param, new OkHttpEntry() {

			@Override
			public void onResponse(Call call, final ResponseEntry result) {
			}

			@Override
			public void onFailure(Call call, Exception ex) {

			}
		});
	}



}
