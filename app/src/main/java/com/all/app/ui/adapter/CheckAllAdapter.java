package com.all.app.ui.adapter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.all.app.R;
import com.all.app.ui.activity.CheckModel;
import com.all.app.utils.UISwitchButton;

public class CheckAllAdapter extends BaseAdapter {

	final String TAG = "adapter======>";
	Context context; // 提供数据
	List<Map<String, Object>> datas = new ArrayList<Map<String, Object>>();

	OncheckAllListener oncheckAllListener;
	
	public CheckAllAdapter(Context context, List<Map<String, Object>> datas) {
		super();
		this.context = context;
		this.datas = datas;
	}

	@Override
	public int getCount() {
		return datas.size();
	}

	@Override
	public Object getItem(int position) {
		return datas.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(
					R.layout.item_text, null);

			holder = new ViewHolder(convertView);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		CheckModel model = (CheckModel) datas.get(position).get("parentName");
		holder.ck_value.setChecked(model.isChecked);
		final boolean nowBeanChecked = model.isChecked;
		holder.ck_value.setText("" + model.name);
		holder.ck_value.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				setupOneParentAllChildChecked(!nowBeanChecked, position);
				// 控制总checkedbox 接口
				oncheckAllListener
						.onCheckedBoxNeedChange(dealAllParentIsChecked());
			}
		});
		return convertView;
	}


	/**
	 * 供全选按钮调用
	 * @param isChecked
	 */
	public void checkAll(boolean isChecked) {
		for (int j = 0; j < datas.size(); j++) {
			CheckModel storeBean = (CheckModel) datas.get(j)
					.get("parentName");
			storeBean.isChecked = isChecked;
		}
		notifyDataSetChanged();
	}

	
	
	/**
	 * 处理全选选中的
	 * @return
	 */
	public boolean dealAllParentIsChecked() {
		Log.d(TAG, "dealAllParentIsChecked: ============");
		for (int i = 0; i < datas.size(); i++) {
			CheckModel storeBean = (CheckModel) datas.get(i)
					.get("parentName");
			if (!storeBean.isChecked) {
				return false;// 如果有一个没选择 就false
			}
		}
		return true;
	}
	
	private void setupOneParentAllChildChecked(boolean isChecked, int position) {
		Log.d(TAG, "setupOneParentAllChildChecked: ============");
		Log.d(TAG, "setupOneParentAllChildChecked: groupPosition:" + position);
		Log.d(TAG, "setupOneParentAllChildChecked: isChecked：" + isChecked);
		for (int j = 0; j < datas.size(); j++) {
			CheckModel storeBean = (CheckModel) datas.get(position)
					.get("parentName");
			storeBean.isChecked = isChecked;
		}
		notifyDataSetChanged();
	}

	

	public void delete() {
		for (int i = datas.size() - 1; i >= 0; i--) {// 倒过来遍历 remove
			CheckModel storeBean = (CheckModel) datas.get(i)
					.get("parentName");
			if (storeBean.isChecked) {
				datas.remove(i);
			}
		}
		// showList("begin###############");
		/**
		 * 1.不要边遍历边删除，容易出现数组越界的情况<br>
		 * 2.现将要删除的对象放进相应的列表容器中，待遍历完后，以removeAll的方式进行删除
		 */
		// 还是有问题
		/*
		 * List<Map<String, Object>> needRemoreParentMapList = new
		 * ArrayList<>();// 待删除的组元素列表 List<List<Map<String, Object>>>
		 * needRemoreChildMapList_List = new ArrayList<>();// 待删除的 最大的
		 * 
		 * for (int i = 0; i < parentMapList.size(); i++) { StoreBean storeBean
		 * = (StoreBean) parentMapList.get(i).get("parentName");
		 * 
		 * if(storeBean.isChecked()){
		 * needRemoreParentMapList.add(parentMapList.get(i));
		 * needRemoreChildMapList_List.add(childMapList_list.get(i));//！！！！
		 * 因为parentMapList和childMapList_list是pos关联的 得保持一致 } // List<Map<String,
		 * Object>> childMapList = childMapList_list.get(i);//最大的
		 * 
		 * List<Map<String, Object>> needRemoreChildMapList = new
		 * ArrayList<>();// 待删除的子元素列表
		 * 
		 * for (int j = 0; j < childMapList.size(); j++) { GoodsBean goodsBean =
		 * (GoodsBean) childMapList.get(j).get("childName"); if
		 * (goodsBean.isChecked()) {
		 * needRemoreChildMapList.add(childMapList.get(j)); } }
		 * 
		 * childMapList.removeAll(needRemoreChildMapList);//正式删除子元素
		 * 不是childMapList_list ！！！
		 * 
		 * } parentMapList.removeAll(needRemoreParentMapList);//正式删除父元素
		 * Log.d(TAG,
		 * "removeGoods: needRemoreChildMapList_List"+needRemoreChildMapList_List
		 * ); childMapList_list.remove(needRemoreChildMapList_List);//！！！！
		 * 因为parentMapList和childMapList_list是pos关联的 得保持一致
		 */
		// !!!!!!!!!!!!!!!删除完 状态需要重置 待思考 why？
		// resetViewAfterDelete();
//		if (datas != null && datas.size() > 0) {
//			onCheckHasGoodsListener.onCheckHasGoods(true);//
//		} else {
//			onCheckHasGoodsListener.onCheckHasGoods(false);//
//		}
		// showList("after@@@@@@@@@@@@@@@@@@@@@@@");
		notifyDataSetChanged();//
	}
	
	/**
	 * 提供子类选中关联全选操作
	 * @param oncheckAllListener
	 */
	public void setOncheckAllListener(
			OncheckAllListener oncheckAllListener) {
		this.oncheckAllListener = oncheckAllListener;
	}
	
	/**
	 * 全选接口，关联子类
	 * @author liao
	 *
	 */
	public interface OncheckAllListener {
		void onCheckedBoxNeedChange(boolean isChecked);
	}
	
	/**
	 * Item
	 * 
	 * @author liao
	 * 
	 */
	class ViewHolder {
		final CheckBox ck_value;
		final ImageView iv_pei;
		public ViewHolder(View v) {
			ck_value= (CheckBox) v.findViewById(R.id.ck_value);
			iv_pei= (ImageView) v.findViewById(R.id.iv_pei);
		}
	}
}

