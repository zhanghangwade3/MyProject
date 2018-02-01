/*
 *******************************************
 * File: BirthdayChoose.java
 * Author: Lee
 * Date: 2016年6月29日
 ********************************************/
package com.all.app.utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.all.app.R;
import com.all.app.utils.views.CancelOnClickListener;
import com.all.app.utils.views.OnWheelChangedListener;
import com.all.app.utils.views.OnWheelScrollListener;
import com.all.app.utils.views.PopBottomBuilder;
import com.all.app.utils.views.WheelView;
import com.all.app.utils.views.adapters.AbstractWheelTextAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;


/***
 * 
 * @ClassName: BirthdayChoose
 * @Description: TODO(描述: 生日选择)
 * @author Lee
 * @date 2016年6月29日 下午1:44:29
 * @version V1.0
 */
public class BirthdayChoose {
	View dialogView;
	Button btn_cancel;
	Button btn_enter;
	PopBottomBuilder dialogBuilder;
	private int maxsize = 20;
	private int minsize = 12;
	Activity mAty;
	private WheelView wvYear;
	private WheelView wvMonth;
	private WheelView wvDay;

	private ArrayList<String> arry_years = new ArrayList<String>();
	private ArrayList<String> arry_months = new ArrayList<String>();
	private ArrayList<String> arry_days = new ArrayList<String>();
	private CalendarTextAdapter mYearAdapter;
	private CalendarTextAdapter mMonthAdapter;
	private CalendarTextAdapter mDaydapter;

	private int month;
	private int day;

	private int currentYear = getYear();
	private int currentMonth = 1;
	private int currentDay = 1;

	private boolean issetdata = false;

	private String selectYear;
	private String selectMonth;
	private String selectDay;

	public BirthdayChoose(Activity aty) {
		// TODO Auto-generated constructor stub
		this.mAty = aty;
	}

	public void showDialog() {
		dialogBuilder = new PopBottomBuilder();
		dialogView = dialogBuilder
				.setLayout(R.layout.popu_choose_time)
				.setLayoutParams(
						new RelativeLayout.LayoutParams(
								ViewGroup.LayoutParams.MATCH_PARENT,
								ViewGroup.LayoutParams.WRAP_CONTENT))
				.commit(mAty);
		dialogView.setOnClickListener(new CancelOnClickListener(dialogView));
		wvYear = (WheelView) dialogView.findViewById(R.id.wv_birth_year);
		wvMonth = (WheelView) dialogView.findViewById(R.id.wv_birth_month);
		wvDay = (WheelView) dialogView.findViewById(R.id.wv_birth_day);

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
				if (selectedListener == null)
					return;
				String Y = arry_years.get(wvYear.getCurrentItem());
				String M = arry_months.get(wvMonth.getCurrentItem());
				String D = arry_days.get(wvDay.getCurrentItem());
				int currentDay = getDay();
				int selectedDay = Integer.parseInt(D);
				if (selectedDay > currentDay) {
					selectedListener.onTimeSelected(Y, M, currentDay + "");
					Toast.makeText(mAty, "不能选择未来的日期。", Toast.LENGTH_SHORT)
							.show();
				} else {
					selectedListener.onTimeSelected(Y, M, D);
				}
			}
		});
		initData();
		initYears();
		mYearAdapter = new CalendarTextAdapter(mAty, arry_years,
				setYear(currentYear), maxsize, minsize);
		wvYear.setVisibleItems(5);
		wvYear.setViewAdapter(mYearAdapter);
		wvYear.setCurrentItem(setYear(currentYear));

		initMonths(month);
		mMonthAdapter = new CalendarTextAdapter(mAty, arry_months,
				setMonth(currentMonth), maxsize, minsize);
		wvMonth.setVisibleItems(5);
		wvMonth.setViewAdapter(mMonthAdapter);
		wvMonth.setCurrentItem(setMonth(currentMonth));

		initDays(day);
		mDaydapter = new CalendarTextAdapter(mAty, arry_days, getDay() - 1,
				maxsize, minsize);
		wvDay.setVisibleItems(5);
		wvDay.setViewAdapter(mDaydapter);
		wvDay.setCurrentItem(getDay() - 1);

		wvYear.addChangingListener(new OnWheelChangedListener() {

			@Override
			public void onChanged(WheelView wheel, int oldValue, int newValue) {
				// TODO Auto-generated method stub
				String currentText = (String) mYearAdapter.getItemText(wheel
						.getCurrentItem());
				selectYear = currentText;
				setTextviewSize(currentText, mYearAdapter);
				currentYear = Integer.parseInt(currentText);
				setYear(currentYear);
				initMonths(month);
				mMonthAdapter = new CalendarTextAdapter(mAty, arry_months, 0,
						maxsize, minsize);
				wvMonth.setVisibleItems(5);
				wvMonth.setViewAdapter(mMonthAdapter);
				wvMonth.setCurrentItem(0);

				initDays(day);
				mDaydapter = new CalendarTextAdapter(mAty, arry_days, 0,
						maxsize, minsize);
				wvDay.setVisibleItems(5);
				wvDay.setViewAdapter(mDaydapter);
				wvDay.setCurrentItem(0);
			}
		});

		wvYear.addScrollingListener(new OnWheelScrollListener() {

			@Override
			public void onScrollingStarted(WheelView wheel) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onScrollingFinished(WheelView wheel) {
				// TODO Auto-generated method stub
				String currentText = (String) mYearAdapter.getItemText(wheel
						.getCurrentItem());
				setTextviewSize(currentText, mYearAdapter);
			}
		});

		wvMonth.addChangingListener(new OnWheelChangedListener() {

			@Override
			public void onChanged(WheelView wheel, int oldValue, int newValue) {
				// TODO Auto-generated method stub
				String currentText = (String) mMonthAdapter.getItemText(wheel
						.getCurrentItem());
				selectMonth = currentText;
				setTextviewSize(currentText, mMonthAdapter);
				setMonth(Integer.parseInt(currentText));
				initDays(day);
				mDaydapter = new CalendarTextAdapter(mAty, arry_days, 0,
						maxsize, minsize);
				wvDay.setVisibleItems(5);
				wvDay.setViewAdapter(mDaydapter);
				wvDay.setCurrentItem(0);
			}
		});

		wvMonth.addScrollingListener(new OnWheelScrollListener() {

			@Override
			public void onScrollingStarted(WheelView wheel) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onScrollingFinished(WheelView wheel) {
				// TODO Auto-generated method stub
				String currentText = (String) mMonthAdapter.getItemText(wheel
						.getCurrentItem());
				setTextviewSize(currentText, mMonthAdapter);
			}
		});

		wvDay.addChangingListener(new OnWheelChangedListener() {

			@Override
			public void onChanged(WheelView wheel, int oldValue, int newValue) {
				// TODO Auto-generated method stub
				String currentText = (String) mDaydapter.getItemText(wheel
						.getCurrentItem());
				setTextviewSize(currentText, mDaydapter);
				selectDay = currentText;
			}
		});

		wvDay.addScrollingListener(new OnWheelScrollListener() {

			@Override
			public void onScrollingStarted(WheelView wheel) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onScrollingFinished(WheelView wheel) {
				// TODO Auto-generated method stub
				String currentText = (String) mDaydapter.getItemText(wheel
						.getCurrentItem());
				setTextviewSize(currentText, mDaydapter);
			}
		});
	}

	public void initYears() {
		for (int i = getYear(); i > 1950; i--) {
			arry_years.add(i + "");
		}
	}

	public void initMonths(int months) {
		arry_months.clear();
		for (int i = 1; i <= months; i++) {
			arry_months.add(i + "");
		}
	}

	public void initDays(int days) {
		arry_days.clear();
		for (int i = 1; i <= days; i++) {
			arry_days.add(i + "");
		}
	}

	public int getYear() {
		Calendar c = Calendar.getInstance();
		c.setTimeZone(TimeZone.getTimeZone("GMT"));
		return c.get(Calendar.YEAR);
	}

	public int getMonth() {
		Calendar c = Calendar.getInstance();
		c.setTimeZone(TimeZone.getTimeZone("GMT"));
		return c.get(Calendar.MONTH) + 1;
	}

	public int getDay() {
		Calendar c = Calendar.getInstance();
		c.setTimeZone(TimeZone.getTimeZone("GMT"));
		return c.get(Calendar.DATE);
	}

	public void initData() {
		setDate(getYear(), getMonth(), getDay());
	}

	/**
	 * 设置年月日
	 * 
	 * @param year
	 * @param month
	 * @param day
	 */
	public void setDate(int year, int month, int day) {
		selectYear = year + "";
		selectMonth = month + "";
		selectDay = day + "";
		issetdata = true;
		this.currentYear = year;
		this.currentMonth = month;
		this.currentDay = day;
		if (year == getYear()) {
			this.month = getMonth();
		} else {
			this.month = 12;
		}
		calDays(year, month);
	}

	/**
	 * 设置年份
	 * 
	 * @param year
	 */
	public int setYear(int year) {
		int yearIndex = 0;
		if (year != getYear()) {
			this.month = 12;
		} else {
			this.month = getMonth();
		}
		for (int i = getYear(); i > 1950; i--) {
			if (i == year) {
				return yearIndex;
			}
			yearIndex++;
		}
		return yearIndex;
	}

	/**
	 * 设置月份
	 * 
	 * @param
	 * @param month
	 * @return
	 */
	public int setMonth(int month) {
		int monthIndex = 0;
		calDays(currentYear, month);
		for (int i = 1; i < this.month; i++) {
			// if (month == i) {
			// return monthIndex;
			// } else {
			monthIndex++;
			// }
		}
		return monthIndex;
	}

	/**
	 * 计算每月多少天
	 * 
	 * @param month
	 * @param
	 */
	public void calDays(int year, int month) {
		boolean leayyear = false;
		if (year % 4 == 0 && year % 100 != 0) {
			leayyear = true;
		} else {
			leayyear = false;
		}
		for (int i = 1; i <= 12; i++) {
			switch (month) {
			case 1:
			case 3:
			case 5:
			case 7:
			case 8:
			case 10:
			case 12:
				this.day = 31;
				break;
			case 2:
				if (leayyear) {
					this.day = 29;
				} else {
					this.day = 28;
				}
				break;
			case 4:
			case 6:
			case 9:
			case 11:
				this.day = 30;
				break;
			}
		}
		// if (year == getYear() && month == getMonth()) {
		// this.day = getDay();
		// }
	}

	private class CalendarTextAdapter extends AbstractWheelTextAdapter {
		ArrayList<String> list;

		protected CalendarTextAdapter(Context context, ArrayList<String> list,
                                      int currentItem, int maxsize, int minsize) {
			super(context, R.layout.popu_item, NO_RESOURCE, currentItem,
					maxsize, minsize);
			this.list = list;
			setItemTextResource(R.id.tempValue);
		}

		@Override
		public View getItem(int index, View cachedView, ViewGroup parent) {
			View view = super.getItem(index, cachedView, parent);
			return view;
		}

		@Override
		public int getItemsCount() {
			return list.size();
		}

		@Override
		protected CharSequence getItemText(int index) {
			return list.get(index) + "";
		}
	}

	/**
	 * 设置字体大小
	 * 
	 * @param curriteItemText
	 * @param adapter
	 */
	public void setTextviewSize(String curriteItemText,
			CalendarTextAdapter adapter) {
		ArrayList<View> arrayList = adapter.getTestViews();
		int size = arrayList.size();
		String currentText;
		for (int i = 0; i < size; i++) {
			TextView textvew = (TextView) arrayList.get(i);
			currentText = textvew.getText().toString();
			if (curriteItemText.equals(currentText)) {
				textvew.setTextSize(maxsize);
			} else {
				textvew.setTextSize(minsize);
			}
		}
	}

	private TimeSelectedListener selectedListener;

	public void setTimeSelectedListener(TimeSelectedListener ll) {
		this.selectedListener = ll;
	}

	public interface TimeSelectedListener {
		public void onTimeSelected(String Y, String M, String D);
	}

}
