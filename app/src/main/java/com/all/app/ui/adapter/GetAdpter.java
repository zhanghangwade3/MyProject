package com.all.app.ui.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.all.app.R;
import com.all.app.base.AdapterBase;
import com.all.app.bean.GetBean;
import com.all.app.utils.XGlide;

import java.util.List;

/**
 * Created by Administrator on 2017/3/31 0031.
 *  接单的Adapter
 */

public class GetAdpter extends AdapterBase<GetBean> {

      public GetAdpter(Context mContext , List<GetBean>mDatas){
           super( mContext,mDatas, R.layout.item_get);
      }
    @Override
    public void Convert( final int position, View convertView) {

        TextView tv_number = Get(convertView, R.id.tv_number);
        TextView tv_name = Get(convertView, R.id.tv_name);
        TextView tv_jiaju = Get(convertView, R.id.tv_jiaju);
        TextView tv_type = Get(convertView, R.id.tv_type);
        TextView tv_addre = Get(convertView, R.id.tv_addre);
        TextView tv_time = Get(convertView, R.id.tv_time);
        ImageView iv_pic = Get(convertView, R.id.iv_pic);
        ImageView  iv_time = Get(convertView, R.id.iv_time);
        TextView tv_day = Get(convertView, R.id.tv_day);
        SetText(tv_name,getItem(position).getTask_title());
        SetText(tv_number,getItem(position).getTask_id());
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

        if("货未到".equals(getItem(position).getTimestr())){
            iv_time.setVisibility(View.INVISIBLE);
            tv_time.setVisibility(View.INVISIBLE);
            tv_day.setText("货未到");
        }else{
            if("办公家具".equals(getItem(position).getIndus_pid())){
                iv_time.setVisibility(View.GONE);
                tv_time.setVisibility(View.GONE);
                tv_day.setVisibility(View.GONE);
            }else{
                iv_time.setVisibility(View.VISIBLE);
                tv_time.setVisibility(View.VISIBLE);
                tv_day.setText(getItem(position).getTimestr());
           }
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
