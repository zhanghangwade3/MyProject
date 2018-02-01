package com.all.app.ui.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.all.app.R;
import com.all.app.base.AdapterBase;
import com.all.app.bean.BankBean;
import com.all.app.bean.BankListBean;
import com.all.app.utils.XGlide;

import java.util.List;


public class BankAdpter extends AdapterBase<BankBean> {
    public static final int CLICK_DELETE=132;
    public BankAdpter(Context mContext, List<BankBean> bankBeen) {
        super(mContext,bankBeen, R.layout.item_bank);
    }
    @Override
    public void Convert(final int position, View convertView) {
        TextView tv_name = Get(convertView, R.id.tv_name);
        TextView tv_name2 = Get(convertView, R.id.tv_name2);
        TextView  tv_delete = Get(convertView, R.id.tv_delete);
        ImageView iv_pic = Get(convertView, R.id.iv_pic);
        SetText(tv_name, getItem(position).getBank_name());
        SetText(tv_name2, getItem(position).getCard_num());
        XGlide.init(mContext).display(iv_pic, getItem(position).getIcon_pic());

        tv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener)
                    mListener.onLazyAdpListener(CLICK_DELETE, position, getItem(position));
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
