package com.all.app.ui.adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;

import com.all.app.R;
import com.all.app.ui.activity.FeedbackActivity;
import com.all.app.utils.XGlide;

import java.util.ArrayList;

public class GrAdapter extends BaseAdapter {
	private LayoutInflater inflater; // 视图容器
	private int selectedPosition = -1;// 选中的位置
	ArrayList<String> mSelectPath;
	Context mContext;

	/**
	 *
	 * @param mDataList
	 */
	public void setDataList(ArrayList<String> mSelectPath) {
		this.mSelectPath = mSelectPath;
		this.notifyDataSetChanged();
	}

	int W;

	public GrAdapter(Context context, ArrayList<String> mSelectPath) {
		inflater = LayoutInflater.from(context);
		this.mSelectPath = mSelectPath;
		mContext = context;

		WindowManager wm = (WindowManager) mContext
				.getSystemService(Context.WINDOW_SERVICE);
		W = (wm.getDefaultDisplay().getWidth() - 60) / 3;
	}

	public int getCount() {
		return (mSelectPath.size() + 1);
	}

	public Object getItem(int arg0) {

		return arg0;
	}

	public long getItemId(int arg0) {

		return arg0;
	}

	/**
	 * ListView Item设置
	 */
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.ted_item_published_grida,
					parent, false);
			holder = new ViewHolder();
			holder.image = (ImageView) convertView
					.findViewById(R.id.item_grida_image);
			holder.imgv_item_selectedImage_delete = (ImageView) convertView
					.findViewById(R.id.imgv_item_selectedImage_delete);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(W, W);
		holder.image.setLayoutParams(params);

		if (position == mSelectPath.size()) {
			holder.image.setScaleType(ScaleType.FIT_XY);
			holder.imgv_item_selectedImage_delete.setVisibility(View.GONE);
			XGlide.init(mContext).display(holder.image,
					R.drawable.release_xiangji);
			if (position == FeedbackActivity.MAX_PIC_NUM) {
				holder.image.setVisibility(View.GONE);
				holder.imgv_item_selectedImage_delete.setVisibility(View.GONE);
			}
		} else {
			holder.image.setScaleType(ScaleType.CENTER_CROP);
			XGlide.init(mContext).display(holder.image,
					mSelectPath.get(position));
		}

		return convertView;
	}

	public class ViewHolder {
		public ImageView image, imgv_item_selectedImage_delete;
	}

}
