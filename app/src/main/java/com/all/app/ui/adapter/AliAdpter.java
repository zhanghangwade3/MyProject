package com.all.app.ui.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.all.app.R;
import com.all.app.base.AdapterBase;
import com.all.app.bean.BankBean;
import com.all.app.bean.PayBean;
import com.all.app.utils.XGlide;

import java.util.List;


public class AliAdpter extends AdapterBase<PayBean> {
    public static final int CLICK_SET=133;
    public AliAdpter(Context mContext, List<PayBean> mDatas) {
        super(mContext, mDatas, R.layout.item_ali);
    }
    @Override
    public void Convert(final int position, View convertView) {
        TextView tv_name = Get(convertView, R.id.tv_name);
        TextView tv_name2 = Get(convertView, R.id.tv_name2);
        TextView tv_set = Get(convertView, R.id.tv_set);
        ImageView iv_pic = Get(convertView, R.id.iv_pic);
        SetText(tv_name, getItem(position).getReal_name());
        SetText(tv_name2, getItem(position).getAlipayjs_account());
        XGlide.init(mContext).display(iv_pic, getItem(position).getIcon_pic());

        tv_set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener)
                    mListener.onLazyAdpListener(CLICK_SET, position, getItem(position));
            }
        });
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
