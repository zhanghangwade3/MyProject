package com.all.app.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.all.app.R;
import com.all.app.base.AdapterBase;
import com.all.app.bean.OfferListBean;
import com.all.app.ui.activity.ConfirmActivity;
import com.all.app.utils.XGlide;

import java.util.List;

/**
 * Created by Administrator on 2017/3/31 0031.
 * 接单的Adapter
 */

public class QuoteListAdpter extends AdapterBase<OfferListBean> {
    public static final int CLICK_QUTA = 0x131;

    public QuoteListAdpter(Context mContext, List<OfferListBean> mDatas) {
        super(mContext, mDatas, R.layout.item_quaot);
    }

    @Override
    public void Convert(final int position, View convertView) {
        TextView tv_name = Get(convertView, R.id.tv_name);
        TextView tv_time = Get(convertView, R.id.tv_time);
        TextView tv_price = Get(convertView, R.id.tv_price);
        TextView tv_addres = Get(convertView, R.id.tv_addres);
        ImageView iv_xiugai = Get(convertView, R.id.iv_xiugai);
        ImageView iv_pic = Get(convertView, R.id.iv_pic);
        TextView tv_zhong = Get(convertView, R.id.tv_zhong);
        TextView tv_news1 = Get(convertView, R.id.tv_news1);
        TextView tv_tong = Get(convertView, R.id.tv_tong);
        TextView tv_yan = Get(convertView, R.id.tv_yan);
       LinearLayout  ll_xiu = Get(convertView, R.id.ll_xiu);

        SetText(tv_name, getItem(position).getTask_title());
        SetText(tv_time, getItem(position).getBid_time());
        SetText(tv_price, "￥" + getItem(position).getQuote());
        SetText(tv_addres, "任务地址:" + getItem(position).getAddress());

        if ("2".equals(getItem(position).getTask_status())) {
            tv_zhong.setText("竞标中");
            tv_news1.setEnabled(true);
            tv_news1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (null != mListener)
                        mListener.onLazyAdpListener(CLICK_QUTA, position, getItem(position));
                }
            });
            iv_xiugai.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (null != mListener)
                        mListener.onLazyAdpListener(CLICK_QUTA, position, getItem(position));
                }
            });
            ll_xiu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (null != mListener)
                        mListener.onLazyAdpListener(CLICK_QUTA, position, getItem(position));
                }
            });

        } else if ("3".equals(getItem(position).getTask_status())) {
            if ("0".equals(getItem(position))) {
                tv_zhong.setText("竞标中");
                tv_news1.setEnabled(true);
                tv_news1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (null != mListener)
                            mListener.onLazyAdpListener(CLICK_QUTA, position, getItem(position));
                    }
                });
                iv_xiugai.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (null != mListener)
                            mListener.onLazyAdpListener(CLICK_QUTA, position, getItem(position));
                    }
                });
                ll_xiu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (null != mListener)
                            mListener.onLazyAdpListener(CLICK_QUTA, position, getItem(position));
                    }
                });


            } else if ("4".equals(getItem(position).getBid_status())) {
                tv_zhong.setText("已中标");
                tv_news1.setEnabled(false);
                iv_xiugai.setEnabled(false);
                ll_xiu.setEnabled(false);
            } else if ("7".equals(getItem(position).getBid_status())) {
                tv_zhong.setText("未中标");
                tv_news1.setEnabled(false);
                iv_xiugai.setEnabled(false);
                ll_xiu.setEnabled(false);
            }
        } else if ("4".equals(getItem(position).getTask_status())) {
            iv_xiugai.setVisibility(View.INVISIBLE);
            tv_news1.setVisibility(View.INVISIBLE);
            ll_xiu.setVisibility(View.INVISIBLE);
        }
        tv_tong.setVisibility(View.VISIBLE);
        tv_tong.setText("有" + getItem(position).getLynum());
        tv_yan.setVisibility(View.VISIBLE);

        if (TextUtils.isEmpty(getItem(position).getTask_pic())) {
            iv_pic.setImageResource(R.drawable.xiaotu);
        } else {
            XGlide.init(mContext).display(iv_pic, getItem(position).getTask_pic());
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
