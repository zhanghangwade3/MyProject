package com.all.app.ui.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.all.app.R;
import com.all.app.base.AdapterBase;
import com.all.app.bean.MoneyListBean;

import java.util.List;

/**
 * Created by Administrator on 2017/3/31 0031.
 * 接单的Adapter
 */

public class PayAdpter extends AdapterBase<MoneyListBean> {
    String type1 = "in";
    String type2 = "out";
    public PayAdpter(Context mContext, List<MoneyListBean> mDatas) {
        super(mContext, mDatas, R.layout.item_pay);
    }

    @Override
    public void Convert(final int position, View convertView) {
        TextView tv_title = Get(convertView, R.id.tv_title);
        TextView tv_time = Get(convertView, R.id.tv_time);
        TextView tv_number = Get(convertView, R.id.tv_number);
        TextView tv_money = Get(convertView, R.id.tv_money);
        SetText(tv_title, getItem(position).getFina_action());
        SetText(tv_time, getItem(position).getFina_time());
        SetText(tv_number, "余额：" + getItem(position).getUser_balance());
        if (getItem(position).getFina_type().equals(type1)){
            SetText(tv_money, "+"+getItem(position).getFina_cash());
        }else if(getItem(position).getFina_type().equals(type2)){
            SetText(tv_money, "-"+getItem(position).getFina_cash());
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
