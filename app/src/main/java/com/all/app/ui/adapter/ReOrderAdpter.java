package com.all.app.ui.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.all.app.R;
import com.all.app.base.AdapterBase;
import com.all.app.bean.OrderAccptBean;
import com.all.app.ui.fragment.ReceiveFragment;
import com.all.app.utils.XGlide;

import java.util.List;

/**
 * Created by Administrator on 2017/3/31 0031.
 *  接单的Adapter
 */

public class ReOrderAdpter extends AdapterBase<OrderAccptBean> {

      public ReOrderAdpter(Context mContext , List<OrderAccptBean>mDatas){
           super( mContext,mDatas, R.layout.item_receorder);
      }
    @Override
    public void Convert( final int position, View convertView) {
        TextView tv_name = Get(convertView, R.id.tv_name);
        TextView tv_time = Get(convertView, R.id.tv_time);
        TextView tv_type = Get(convertView, R.id.tv_type);
        TextView tv_address = Get(convertView, R.id.tv_address);
        ImageView iv_pic = Get(convertView, R.id.iv_pic);
        SetText(tv_name, getItem(position).getTask_title());
        SetText(tv_time, getItem(position).getSub_time());
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
        SetText(tv_address, "施工地址："+getItem(position).getAddress());
        XGlide.init(mContext).display(iv_pic,getItem(position).getTask_pic());

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
