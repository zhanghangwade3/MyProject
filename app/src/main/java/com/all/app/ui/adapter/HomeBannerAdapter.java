package com.all.app.ui.adapter;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import com.all.app.R;
import com.all.app.app.AppData;
import com.all.app.base.AdapterBase;
import com.all.app.bean.BanerBean;
import com.all.app.bean.NewsBean;
import com.all.app.utils.XGlide;

import java.util.List;

/**
 * 
 * @ClassName: HomeAdvertAdapter
 * @Description: TODO(描述: 首页Banner)
 * @author Lee
 * @date 2016年6月14日 下午3:48:54
 * @version V1.0
 */
public class HomeBannerAdapter extends AdapterBase<BanerBean> {

	public HomeBannerAdapter(Context ctx, List<BanerBean> mDatas) {
		super(ctx, mDatas, R.layout.item_banner);
	}

	@Override
	public void Convert(final int position, View convertView) {
		// TODO Auto-generated method stub
		ImageView iv = Get(convertView, R.id.iv);
		iv.setScaleType(ScaleType.FIT_XY);

		if (!TextUtils.isEmpty(getItem(position).getArt_pic())) {
			XGlide.init(mContext).display(iv, getItem(position).getArt_pic());
		}

	convertView.setOnClickListener(new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			AppData.Init().openAdvert(mContext,getItem(position));
		}
	});

	}

}
