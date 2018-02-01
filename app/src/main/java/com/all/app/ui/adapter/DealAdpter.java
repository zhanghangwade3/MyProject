package com.all.app.ui.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.all.app.R;
import com.all.app.base.AdapterBase;
import com.all.app.bean.MeageListBean;

import java.util.List;

/**
 * Created by Administrator on 2017/3/31 0031.
 * 接单的Adapter
 */

public class DealAdpter extends AdapterBase<MeageListBean> {

    public DealAdpter(Context mContext, List<MeageListBean> mDatas) {
        super(mContext, mDatas, R.layout.item_deal);
    }

    @Override
    public void Convert(final int position, View convertView) {
        TextView tv_name = Get(convertView, R.id.tv_name);
        TextView tv_time = Get(convertView, R.id.tv_time);
        TextView tv_iv = Get(convertView, R.id.tv_iv);
        SetText(tv_name, getItem(position).getTitle());
        SetText(tv_time, getItem(position).getOn_time());
        if ("0".equals(getItem(position).getView_status())) {
            tv_iv.setVisibility(View.VISIBLE);
        } else {
            tv_iv.setVisibility(View.GONE);
        }
        //item的监听事件
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener)
                    mListener.onLazyAdpListener(CLICK_ITEM, position, getItem(position));
            }
        });

    }


}
