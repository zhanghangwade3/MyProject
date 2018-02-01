package com.all.app.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.all.app.R;
import com.all.app.base.AdapterBase;
import com.all.app.bean.ProBean;
import com.all.app.ui.activity.ProOrderActivity;
import com.all.app.utils.AlertDialog;
import com.all.app.utils.XGlide;

import java.util.List;

/**
 * Created by Administrator on 2017/3/31 0031.
 *  接单的Adapter
 */

public class ProOrderAdpter extends AdapterBase<ProBean> {

    public static final int CLICK_PHONE= 123;

    public ProOrderAdpter(Context mContext , List<ProBean>mDatas){
           super( mContext,mDatas, R.layout.item_proder);
      }
    @Override
    public void Convert( final int position, View convertView) {
        TextView tv_name = Get(convertView, R.id.tv_name);
        TextView tv_tu = Get(convertView, R.id.tv_tu);
        TextView tv_jiaju = Get(convertView, R.id.tv_jiaju);
        TextView tv_type = Get(convertView, R.id.tv_type);
        TextView tv_addre = Get(convertView, R.id.tv_addre);
        ImageView iv_pic = Get(convertView, R.id.iv_pic);
        SetText(tv_name,getItem(position).getTask_title());
        if(TextUtils.isEmpty(getItem(position).getOpinion())){
            tv_tu.setText("未反馈");
            tv_tu.setBackgroundColor(mContext.getResources().getColor(R.color.time_gray));
        }else {
            tv_tu.setText("已反馈");
            tv_tu.setBackgroundColor(mContext.getResources().getColor(R.color.APP_EC801B));
        }
        SetText(tv_jiaju,getItem(position).getIndus_pid());
        if("0".equals(getItem(position).getIs_pick())){
            tv_type.setText("安装");
        }else if("1".equals(getItem(position).getIs_pick())){
            tv_type.setText("配送安装");
        }else if("2".equals(getItem(position).getIs_pick())) {
            tv_type.setText("送货到家");
        } else if("3".equals(getItem(position).getIs_pick())) {
            tv_type.setText("送货到楼下");
        }else if("4".equals(getItem(position).getIs_pick())) {
            tv_type.setText("维修");
        }else if("5".equals(getItem(position).getIs_pick())) {
            tv_type.setText("量尺寸");
        }
        if("0".equals(getItem(position).getIs_pick())||"4".equals(getItem(position).getIs_pick())||"5".equals(getItem(position).getIs_pick())){
            tv_addre.setText("施工地址："+getItem(position).getAddress());
        }else if("1".equals(getItem(position).getIs_pick())||"2".equals(getItem(position).getIs_pick())
                ||"3".equals(getItem(position).getIs_pick())){
            tv_addre.setText("配送地址："+getItem(position).getAddress());
        }

        if(TextUtils.isEmpty(getItem(position).getTask_pic())){
            XGlide.init(mContext).display(iv_pic,R.drawable.xiaotu);
        }else {
            XGlide.init(mContext).display(iv_pic,getItem(position).getTask_pic());
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
