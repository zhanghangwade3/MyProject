package com.all.app.utils.city;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.all.app.R;
import com.all.app.app.AppData;
import com.all.app.utils.FJson;
import com.all.app.utils.views.CancelOnClickListener;
import com.all.app.utils.views.OnWheelChangedListener;
import com.all.app.utils.views.OnWheelScrollListener;
import com.all.app.utils.views.PopBottomBuilder;
import com.all.app.utils.views.WheelView;
import com.all.app.utils.views.adapters.AbstractWheelTextAdapter;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class CityChoose2 {
	View dialogView;
	Button btn_cancel;
	Button btn_enter;
	PopBottomBuilder dialogBuilder;

	private WheelView wvProvince;
	private WheelView wvCitys;
	private WheelView wvAreas;
	Activity mAty;

	private ProvinceTextAdapter provinceAdapter;
	private CityTextAdapter cityAdapter;
	private AreaTextAdapter areaAdapter;

	private int maxsize = 18;
	private int minsize = 14;

	public static List<WheelP> mDatas;
	private boolean isChangeDataSource;




	public CityChoose2(Activity aty) {
		mAty = aty;
	}

	public void isChangeDataSource(){
		isChangeDataSource = true;
	}

	public interface IChooseCityListener {
		public void onFinished(String provinceId, String provinceName, String cityId, String cityName);
	}

	private IChooseCityListener chooseCityListener;

	public void setChooseCityListener(IChooseCityListener chooseCityListener) {
		this.chooseCityListener = chooseCityListener;
	}

	public void showDialog() {
		dialogBuilder = new PopBottomBuilder();
		dialogView = dialogBuilder.setLayout(R.layout.popu_choose_cityz).setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)).commit(mAty);
		dialogView.setOnClickListener(new CancelOnClickListener(dialogView));
		wvProvince = (WheelView) dialogView.findViewById(R.id.wv_address_province);
		wvCitys = (WheelView) dialogView.findViewById(R.id.wv_address_city);
		btn_cancel = (Button) dialogView.findViewById(R.id.btn_cancel);
		btn_enter = (Button) dialogView.findViewById(R.id.btn_enter);
		btn_cancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dialogBuilder.Hide(dialogView);
			}
		});
		btn_enter.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dialogBuilder.Hide(dialogView);
				if (chooseCityListener == null)
					return;
				String provinceName = wheelPs.get(wvProvince.getCurrentItem()).getProvince_name();
				String provinceId = wheelPs.get(wvProvince.getCurrentItem()).getProvince_id();
				String cityName = wheelPs.get(wvProvince.getCurrentItem()).getCityData().get(wvCitys.getCurrentItem()).getCity_name();
				String cityId = wheelPs.get(wvProvince.getCurrentItem()).getCityData().get(wvCitys.getCurrentItem()).getCity_id();
				chooseCityListener.onFinished(provinceId, provinceName, cityId, cityName);

			}
		});

		if(isChangeDataSource){
			wheelPs = AppData.changeWheelPs;
		}else{
			wheelPs = AppData.wheelPs;
		}

		provinceAdapter = new ProvinceTextAdapter(mAty, wheelPs, 0, maxsize, minsize);
		wvProvince.setVisibleItems(5);
		wvProvince.setViewAdapter(provinceAdapter);
		wvProvince.setCurrentItem(0);

		cityAdapter = new CityTextAdapter(mAty, wheelPs.get(wvProvince.getCurrentItem()).getCityData(), 0, maxsize, minsize);
		wvCitys.setVisibleItems(5);
		wvCitys.setViewAdapter(cityAdapter);
		wvCitys.setCurrentItem(0);
		wvProvince.addChangingListener(new OnWheelChangedListener() {

			@Override
			public void onChanged(WheelView wheel, int oldValue, int newValue) {
				// TODO Auto-generated method stub
				String currentText = (String) provinceAdapter.getItemText(wheel.getCurrentItem());
				setTextviewSize(currentText, provinceAdapter);
				cityAdapter = new CityTextAdapter(mAty, wheelPs.get(wheel.getCurrentItem()).getCityData(), 0, maxsize, minsize);
				wvCitys.setVisibleItems(5);
				wvCitys.setViewAdapter(cityAdapter);
				wvCitys.setCurrentItem(0);
			}
		});

		wvProvince.addScrollingListener(new OnWheelScrollListener() {

			@Override
			public void onScrollingStarted(WheelView wheel) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onScrollingFinished(WheelView wheel) {
				// TODO Auto-generated method stub
				String currentText = (String) provinceAdapter.getItemText(wheel.getCurrentItem());
				setTextviewSize(currentText, provinceAdapter);
			}
		});

		wvCitys.addChangingListener(new OnWheelChangedListener() {

			@Override
			public void onChanged(WheelView wheel, int oldValue, int newValue) {
				// TODO Auto-generated method stub
				String currentText = (String) cityAdapter.getItemText(wheel.getCurrentItem());
				setTextviewSize(currentText, cityAdapter);

			}
		});

		wvCitys.addScrollingListener(new OnWheelScrollListener() {

			@Override
			public void onScrollingStarted(WheelView wheel) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onScrollingFinished(WheelView wheel) {
				// TODO Auto-generated method stub
				String currentText = (String) cityAdapter.getItemText(wheel.getCurrentItem());
				setTextviewSize(currentText, cityAdapter);
			}
		});

	}

	private class ProvinceTextAdapter extends AbstractWheelTextAdapter {
		List<WheelP> list;

		protected ProvinceTextAdapter(Context context, List<WheelP> list, int currentItem, int maxsize, int minsize) {
			super(context, R.layout.popu_item, NO_RESOURCE, currentItem, maxsize, minsize);
			this.list = list;
			setItemTextResource(R.id.tempValue);
		}

		@Override
		public View getItem(int index, View cachedView, ViewGroup parent) {
			View view = super.getItem(index, cachedView, parent);
			TextView text = (TextView) view.findViewById(R.id.tempValue);
			if (index == 0) {
				text.setTextColor(Color.parseColor("#454545"));
			} else {
				text.setTextColor(Color.parseColor("#b6b6b6"));
			}
			return view;
		}

		@Override
		public int getItemsCount() {
			return list.size();
		}

		@Override
		protected CharSequence getItemText(int index) {
			return list.get(index).getProvince_name() + "";
		}
	}

	private class CityTextAdapter extends AbstractWheelTextAdapter {
		List<WheelC> list;

		protected CityTextAdapter(Context context, List<WheelC> list, int currentItem, int maxsize, int minsize) {
			super(context, R.layout.popu_item, NO_RESOURCE, currentItem, maxsize, minsize);
			this.list = list;
			setItemTextResource(R.id.tempValue);
		}

		@Override
		public View getItem(int index, View cachedView, ViewGroup parent) {
			View view = super.getItem(index, cachedView, parent);
			TextView text = (TextView) view.findViewById(R.id.tempValue);
			if (index == 0) {
				text.setTextColor(Color.parseColor("#454545"));
			} else {
				text.setTextColor(Color.parseColor("#b6b6b6"));
			}
			return view;
		}

		@Override
		public int getItemsCount() {
			return list.size();
		}

		@Override
		protected CharSequence getItemText(int index) {
			return list.get(index).getCity_name() ;
		}
	}

	private class AreaTextAdapter extends AbstractWheelTextAdapter {
		List<WheelA> list;

		protected AreaTextAdapter(Context context, List<WheelA> list, int currentItem, int maxsize, int minsize) {
			super(context, R.layout.popu_item, NO_RESOURCE, currentItem, maxsize, minsize);
			this.list = list;
			setItemTextResource(R.id.tempValue);
		}

		@Override
		public View getItem(int index, View cachedView, ViewGroup parent) {
			View view = super.getItem(index, cachedView, parent);
			TextView text = (TextView) view.findViewById(R.id.tempValue);
			if (index == 0) {
				text.setTextColor(Color.parseColor("#454545"));
			} else {
				text.setTextColor(Color.parseColor("#b6b6b6"));
			}
			return view;
		}

		@Override
		public int getItemsCount() {
			return list.size();
		}

		@Override
		protected CharSequence getItemText(int index) {
			return list.get(index).getArea_name() + "";
		}
	}

	/**
	 * 设置字体大小
	 * 
	 * @param curriteItemText
	 * @param adapter
	 */
	public void setTextviewSize(String curriteItemText, ProvinceTextAdapter adapter) {
		ArrayList<View> arrayList = adapter.getTestViews();
		int size = arrayList.size();
		String currentText;
		for (int i = 0; i < size; i++) {
			TextView textvew = (TextView) arrayList.get(i);
			currentText = textvew.getText().toString();
			if (curriteItemText.equals(currentText)) {
				if(currentText.length() > 10){
					textvew.setTextSize(12);
				}else if(currentText.length() > 6){
					textvew.setTextSize(14);
				}else if(currentText.length() > 4){
					textvew.setTextSize(16);
				}else{
					textvew.setTextSize(18);
				}
				textvew.setTextColor(Color.parseColor("#454545"));
			} else {
				textvew.setTextSize(14);
				textvew.setTextColor(Color.parseColor("#b6b6b6"));
			}
		}
	}

	/**
	 * 设置字体大小
	 * 
	 * @param curriteItemText
	 * @param adapter
	 */
	public void setTextviewSize(String curriteItemText, CityTextAdapter adapter) {
		ArrayList<View> arrayList = adapter.getTestViews();
		int size = arrayList.size();
		String currentText;
		for (int i = 0; i < size; i++) {
			TextView textvew = (TextView) arrayList.get(i);
			currentText = textvew.getText().toString();
			if (curriteItemText.equals(currentText)) {
				if(currentText.length() > 10){
					textvew.setTextSize(12);
				}else if(currentText.length() > 6){
					textvew.setTextSize(14);
				}else if(currentText.length() > 4){
					textvew.setTextSize(16);
				}else{
					textvew.setTextSize(18);
				}
				textvew.setTextColor(Color.parseColor("#454545"));
			} else {
				textvew.setTextSize(14);
				textvew.setTextColor(Color.parseColor("#b6b6b6"));
			}
		}
	}

	/**
	 * 设置字体大小
	 * 
	 * @param curriteItemText
	 * @param adapter
	 */
	public void setTextviewSize(String curriteItemText, AreaTextAdapter adapter) {
		ArrayList<View> arrayList = adapter.getTestViews();
		int size = arrayList.size();
		String currentText;
		for (int i = 0; i < size; i++) {
			TextView textvew = (TextView) arrayList.get(i);
			currentText = textvew.getText().toString();
			if (curriteItemText.equals(currentText)) {
				if(currentText.length() > 10){
					textvew.setTextSize(12);
				}else if(currentText.length() > 6){
					textvew.setTextSize(14);
				}else if(currentText.length() > 4){
					textvew.setTextSize(16);
				}else{
					textvew.setTextSize(18);
				}
				textvew.setTextColor(Color.parseColor("#454545"));
			} else {
				textvew.setTextSize(14);
				textvew.setTextColor(Color.parseColor("#b6b6b6"));
			}
		}
	}

	List<WheelP> wheelPs;

	/**
	 * 从文件中读取地址数据
	 */
	private void initJsonData() {
		try {
			StringBuffer sb = new StringBuffer();
			InputStream is = mAty.getAssets().open("city");
			int len = -1;
			byte[] buf = new byte[1024];
			while ((len = is.read(buf)) != -1) {
				sb.append(new String(buf, 0, len, "utf-8"));
			}
			is.close();
			wheelPs = FJson.getObjects(sb.toString(), WheelP.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// /**
	// * 初始化省会
	// */
	// public void initProvinces() {
	// int length = wheelPs.size();
	// for (int i = 0; i < length; i++) {
	// arrProvinces.add(wheelPs.get(i).getName());
	// }
	// }

	// /**
	// * 根据省会，生成该省会的所有城市
	// *
	// * @param citys
	// */
	// public void initCitys(String[] citys) {
	// if (citys != null) {
	// arrCitys.clear();
	// int length = citys.length;
	// for (int i = 0; i < length; i++) {
	// arrCitys.add(citys[i]);
	// }
	// } else {
	// String[] city = mCitisDatasMap.get("四川");
	// arrCitys.clear();
	// int length = city.length;
	// for (int i = 0; i < length; i++) {
	// arrCitys.add(city[i]);
	// }
	// }
	// if (arrCitys != null && arrCitys.size() > 0 &&
	// !arrCitys.contains(strCity)) {
	// strCity = arrCitys.get(0);
	// }
	// }

	// /**
	// * 初始化地点
	// *
	// * @param province
	// * @param city
	// */
	// public void setAddress(String province, String city) {
	// if (province != null && province.length() > 0) {
	// this.strProvince = province;
	// }
	// if (city != null && city.length() > 0) {
	// this.strCity = city;
	// }
	// }

	// /**
	// * 返回省会索引，没有就返回默认“四川”
	// *
	// * @param province
	// * @return
	// */
	// public int getProvinceItem(String province) {
	// int size = arrProvinces.size();
	// int provinceIndex = 0;
	// boolean noprovince = true;
	// for (int i = 0; i < size; i++) {
	// if (province.equals(arrProvinces.get(i))) {
	// noprovince = false;
	// return provinceIndex;
	// } else {
	// provinceIndex++;
	// }
	// }
	// if (noprovince) {
	// strProvince = "四川";
	// return 22;
	// }
	// return provinceIndex;
	// }

	// /**
	// * 得到城市索引，没有返回默认“成都”
	// *
	// * @param city
	// * @return
	// */
	// public int getCityItem(String city) {
	// int size = arrCitys.size();
	// int cityIndex = 0;
	// boolean nocity = true;
	// for (int i = 0; i < size; i++) {
	// System.out.println(arrCitys.get(i));
	// if (city.equals(arrCitys.get(i))) {
	// nocity = false;
	// return cityIndex;
	// } else {
	// cityIndex++;
	// }
	// }
	// if (nocity) {
	// strCity = "成都";
	// return 0;
	// }
	// return cityIndex;
	// }

}
