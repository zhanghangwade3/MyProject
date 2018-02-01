package com.all.app.ui.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.all.app.R;
import com.all.app.base.AdapterBase;
import com.all.app.bean.FinishBean;
import com.all.app.utils.XGlide;

import java.util.List;

/**
 * Created by Administrator on 2017/4/1 0032.
 * 发现的Adapter
 */

public class FinishAdpter extends AdapterBase<FinishBean> {

    public FinishAdpter(Context mContext, List<FinishBean> mDatas) {
        super(mContext, mDatas, R.layout.item_finish);
    }
    @Override
    public void Convert(final int position, View convertView) {
        TextView tv_number = Get(convertView, R.id.tv_number);
        TextView tv_num = Get(convertView, R.id.tv_num);
        TextView tv_date = Get(convertView, R.id.tv_date);
        TextView tv_ping = Get(convertView, R.id.tv_ping);
        TextView tv_con = Get(convertView, R.id.tv_con);
        TextView tv_content6 = Get(convertView, R.id.tv_content6);
        TextView tv_conten = Get(convertView, R.id.tv_conten);
        TextView tv_conte = Get(convertView, R.id.tv_conte);
        final RelativeLayout rl_service = Get(convertView, R.id.rl_service);
        final RelativeLayout rl_qulity = Get(convertView, R.id.rl_qulity);
        final RelativeLayout rl_taidu = Get(convertView, R.id.rl_taidu);
        final RelativeLayout rl_time = Get(convertView, R.id.rl_time);
        final RelativeLayout re_name = Get(convertView, R.id.re_name);

        ImageView iv_next = Get(convertView, R.id.iv_next);
        ImageView iv1 = Get(convertView, R.id.iv1);
        ImageView iv2 = Get(convertView, R.id.iv2);
        ImageView iv3 = Get(convertView, R.id.iv3);
        ImageView iv4 = Get(convertView, R.id.iv4);
        ImageView iv5 = Get(convertView, R.id.iv5);
        ImageView iv11 = Get(convertView, R.id.iv11);
        ImageView iv21 = Get(convertView, R.id.iv21);
        ImageView iv31 = Get(convertView, R.id.iv31);
        ImageView iv41 = Get(convertView, R.id.iv41);
        ImageView iv51 = Get(convertView, R.id.iv51);
        ImageView iv61 = Get(convertView, R.id.iv61);
        ImageView iv12 = Get(convertView, R.id.iv12);
        ImageView iv13 = Get(convertView, R.id.iv13);
        ImageView iv14 = Get(convertView, R.id.iv14);
        ImageView iv15 = Get(convertView, R.id.iv15);
        SetText(tv_number, getItem(position).getTask_title());
        SetText(tv_num, getItem(position).getTask_id());
        SetText(tv_date, getItem(position).getAssurance_time());
        if ("0".equals(getItem(position).getMark_status())) {
            tv_ping.setText("暂无评价");
        } else if ("1".equals(getItem(position).getMark_status())) {
            tv_ping.setText("好");
        } else if ("2".equals(getItem(position).getMark_status())) {
            tv_ping.setText("中");
        } else if ("3".equals(getItem(position).getMark_status())) {
            tv_ping.setText("差");
        }
        if (TextUtils.isEmpty(getItem(position).getMark_content())) {
            rl_time.setVisibility(View.GONE);
        } else {
            SetText(tv_con, getItem(position).getMark_content());
        }
        SetText(tv_content6, getItem(position).getStar1());
        if ("1".equals(getItem(position).getStar1())) {
            XGlide.init(mContext).display(iv1, R.drawable.ratingbar_icon_selected);
        } else if ("2".equals(getItem(position).getStar1())) {
            XGlide.init(mContext).display(iv1, R.drawable.ratingbar_icon_selected);
            XGlide.init(mContext).display(iv2, R.drawable.ratingbar_icon_selected);
        } else if ("3".equals(getItem(position).getStar1())) {
            XGlide.init(mContext).display(iv1, R.drawable.ratingbar_icon_selected);
            XGlide.init(mContext).display(iv2, R.drawable.ratingbar_icon_selected);
            XGlide.init(mContext).display(iv3, R.drawable.ratingbar_icon_selected);
        } else if ("4".equals(getItem(position).getStar1())) {
            XGlide.init(mContext).display(iv1, R.drawable.ratingbar_icon_selected);
            XGlide.init(mContext).display(iv2, R.drawable.ratingbar_icon_selected);
            XGlide.init(mContext).display(iv3, R.drawable.ratingbar_icon_selected);
            XGlide.init(mContext).display(iv4, R.drawable.ratingbar_icon_selected);
        } else if ("5".equals(getItem(position).getStar1())) {
            XGlide.init(mContext).display(iv1, R.drawable.ratingbar_icon_selected);
            XGlide.init(mContext).display(iv2, R.drawable.ratingbar_icon_selected);
            XGlide.init(mContext).display(iv3, R.drawable.ratingbar_icon_selected);
            XGlide.init(mContext).display(iv4, R.drawable.ratingbar_icon_selected);
            XGlide.init(mContext).display(iv5, R.drawable.ratingbar_icon_selected);
        }
        SetText(tv_conten, getItem(position).getStar2());
        if ("1".equals(getItem(position).getStar2())) {
            XGlide.init(mContext).display(iv11, R.drawable.ratingbar_icon_selected);
        } else if ("2".equals(getItem(position).getStar2())) {
            XGlide.init(mContext).display(iv11, R.drawable.ratingbar_icon_selected);
            XGlide.init(mContext).display(iv21, R.drawable.ratingbar_icon_selected);
        } else if ("3".equals(getItem(position).getStar2())) {
            XGlide.init(mContext).display(iv11, R.drawable.ratingbar_icon_selected);
            XGlide.init(mContext).display(iv21, R.drawable.ratingbar_icon_selected);
            XGlide.init(mContext).display(iv31, R.drawable.ratingbar_icon_selected);
        } else if ("4".equals(getItem(position).getStar2())) {
            XGlide.init(mContext).display(iv11, R.drawable.ratingbar_icon_selected);
            XGlide.init(mContext).display(iv21, R.drawable.ratingbar_icon_selected);
            XGlide.init(mContext).display(iv31, R.drawable.ratingbar_icon_selected);
            XGlide.init(mContext).display(iv41, R.drawable.ratingbar_icon_selected);
        } else if ("5".equals(getItem(position).getStar2())) {
            XGlide.init(mContext).display(iv11, R.drawable.ratingbar_icon_selected);
            XGlide.init(mContext).display(iv21, R.drawable.ratingbar_icon_selected);
            XGlide.init(mContext).display(iv31, R.drawable.ratingbar_icon_selected);
            XGlide.init(mContext).display(iv41, R.drawable.ratingbar_icon_selected);
            XGlide.init(mContext).display(iv51, R.drawable.ratingbar_icon_selected);
        }
        SetText(tv_conte, getItem(position).getStar3());
        if ("1".equals(getItem(position).getStar3())) {
            XGlide.init(mContext).display(iv61, R.drawable.ratingbar_icon_selected);
        } else if ("2".equals(getItem(position).getStar3())) {
            XGlide.init(mContext).display(iv61, R.drawable.ratingbar_icon_selected);
            XGlide.init(mContext).display(iv12, R.drawable.ratingbar_icon_selected);
        } else if ("3".equals(getItem(position).getStar3())) {
            XGlide.init(mContext).display(iv61, R.drawable.ratingbar_icon_selected);
            XGlide.init(mContext).display(iv12, R.drawable.ratingbar_icon_selected);
            XGlide.init(mContext).display(iv13, R.drawable.ratingbar_icon_selected);
        } else if ("4".equals(getItem(position).getStar3())) {
            XGlide.init(mContext).display(iv61, R.drawable.ratingbar_icon_selected);
            XGlide.init(mContext).display(iv12, R.drawable.ratingbar_icon_selected);
            XGlide.init(mContext).display(iv13, R.drawable.ratingbar_icon_selected);
            XGlide.init(mContext).display(iv14, R.drawable.ratingbar_icon_selected);
        } else if ("5".equals(getItem(position).getStar3())) {
            XGlide.init(mContext).display(iv61, R.drawable.ratingbar_icon_selected);
            XGlide.init(mContext).display(iv12, R.drawable.ratingbar_icon_selected);
            XGlide.init(mContext).display(iv13, R.drawable.ratingbar_icon_selected);
            XGlide.init(mContext).display(iv14, R.drawable.ratingbar_icon_selected);
            XGlide.init(mContext).display(iv15, R.drawable.ratingbar_icon_selected);
        }
        iv_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rl_service.setVisibility(View.VISIBLE);
                rl_qulity.setVisibility(View.VISIBLE);
                rl_taidu.setVisibility(View.VISIBLE);
                rl_time.setVisibility(View.VISIBLE);
            }
        });

        //item的监听事件
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rl_service.setVisibility(View.GONE);
                rl_qulity.setVisibility(View.GONE);
                rl_taidu.setVisibility(View.GONE);
                rl_time.setVisibility(View.GONE);
            }
        });
    }
}
