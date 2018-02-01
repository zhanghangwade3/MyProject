package com.all.app.utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.all.app.R;
import com.all.app.utils.views.CancelOnClickListener;
import com.all.app.utils.views.OnWheelChangedListener;
import com.all.app.utils.views.OnWheelScrollListener;
import com.all.app.utils.views.PopBottomBuilder;
import com.all.app.utils.views.WheelView;
import com.all.app.utils.views.adapters.AbstractWheelTextAdapter;

import java.util.ArrayList;
import java.util.List;
/**
 * 
 * @ClassName: UnitChoose
 * @Description: TODO(描述: 单位选择)
 * @author Lee
 * @date 2016年5月9日 下午1:12:37
 * @version V1.0
 */
public class UnitChoose<T> {
	View dialogView;
	Button btn_cancel;
	Button btn_enter;
	PopBottomBuilder dialogBuilder;

	private WheelView wvUnit;
	Activity mAty;
	private int maxsize = 24;
	private int minsize = 14;

	private List<T> arrUnits;

	TextAdapter textAdapter;

	public interface IChooseUnitListener<T> {
		public void onFinished(T p);
	}

	private IChooseUnitListener<T> chooseListener;

	public void setChooseListener(IChooseUnitListener<T> ll) {
		this.chooseListener = ll;
	}

	public UnitChoose(Activity aty, List<T> arr) {
		mAty = aty;
		this.arrUnits = arr;
	}

	public void showDialog() {
		dialogBuilder = new PopBottomBuilder();
		dialogView = dialogBuilder
				.setLayout(R.layout.popu_choose_unit)
				.setLayoutParams(
						new RelativeLayout.LayoutParams(
								ViewGroup.LayoutParams.MATCH_PARENT,
								ViewGroup.LayoutParams.WRAP_CONTENT))
				.commit(mAty);
		dialogView.setOnClickListener(new CancelOnClickListener(dialogView));
		wvUnit = (WheelView) dialogView.findViewById(R.id.wv_unit);
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
				if (chooseListener == null)
					return;
				chooseListener.onFinished(arrUnits.get(wvUnit.getCurrentItem()));
			}
		});
		textAdapter = new TextAdapter(mAty, arrUnits, 0, maxsize, minsize);
		wvUnit.setVisibleItems(5);
		wvUnit.setViewAdapter(textAdapter);
		wvUnit.setCurrentItem(0);

		wvUnit.addChangingListener(new OnWheelChangedListener() {

			@Override
			public void onChanged(WheelView wheel, int oldValue, int newValue) {
				// TODO Auto-generated method stub
				String currentText = (String) textAdapter.getItemText(wheel
						.getCurrentItem());
				setTextviewSize(currentText, textAdapter);
			}
		});

		wvUnit.addScrollingListener(new OnWheelScrollListener() {

			@Override
			public void onScrollingStarted(WheelView wheel) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onScrollingFinished(WheelView wheel) {
				// TODO Auto-generated method stub
				String currentText = (String) textAdapter.getItemText(wheel
						.getCurrentItem());
				setTextviewSize(currentText, textAdapter);
			}
		});
	}

	private class TextAdapter extends AbstractWheelTextAdapter {
		List<T> list;

		protected TextAdapter(Context context, List<T> list, int currentItem,
                              int maxsize, int minsize) {
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
			/*if (list.get(index) instanceof FindUnitBean)
				return ((FindUnitBean) list.get(index)).getPname() + "";
			if (list.get(index) instanceof PriceUnitBean)
				return ((PriceUnitBean) list.get(index)).getPname() + "";
			if (list.get(index) instanceof TypeBean)
				return ((TypeBean) list.get(index)).getCodeLabel() + "";
			//添加显示车型列表的字段
			if (list.get(index) instanceof LogisticsListInfo)
				return ((LogisticsListInfo) list.get(index)).getVehicleInfo() + "";
*/
			if (list.get(index) instanceof String)
				return list.get(index) + "";
			return "";
		}
	}

	/**
	 * 设置字体大小
	 * 
	 * @param curriteItemText
	 * @param adapter
	 */
	public void setTextviewSize(String curriteItemText, TextAdapter adapter) {
		ArrayList<View> arrayList = adapter.getTestViews();
		int size = arrayList.size();
		String currentText;
		for (int i = 0; i < size; i++) {
			TextView textvew = (TextView) arrayList.get(i);
			currentText = textvew.getText().toString();
			if (curriteItemText.equals(currentText)) {
				textvew.setTextSize(20);
			} else {
				textvew.setTextSize(14);
			}
		}
	}

}
