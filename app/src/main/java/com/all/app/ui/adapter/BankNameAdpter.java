package com.all.app.ui.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.all.app.R;
import com.all.app.base.AdapterBase;
import com.all.app.bean.BankBean;
import com.all.app.bean.BankNameBean;
import com.all.app.utils.XGlide;

import java.util.List;


public class BankNameAdpter extends AdapterBase<BankNameBean> {
    public BankNameAdpter(Context mContext, List<BankNameBean> mDatas) {
        super(mContext, mDatas, R.layout.item_text3);
    }
    @Override
    public void Convert(final int position, View convertView) {
        TextView tv_name = Get(convertView, R.id.tv_name);
        SetText(tv_name, getItem(position).getName());

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
