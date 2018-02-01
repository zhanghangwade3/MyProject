package com.all.app.ui.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.all.app.R;
import com.all.app.base.AdapterBase;

import java.util.List;

/**
 * Created by Administrator on 2017/4/1 0032.
 *  发现的Adapter
 */

public class ServiceAdpter extends AdapterBase<String> {

      public ServiceAdpter(Context mContext , List<String>mDatas){
           super( mContext,mDatas, R.layout.item_pingjia);
      }
    @Override
    public void Convert(int position, View convertView) {
        TextView tv_number= Get(convertView, R.id.tv_number);
        TextView tv_ping = Get(convertView, R.id.tv_ping);
        TextView tv_point = Get(convertView, R.id.tv_point);
        TextView tv_time = Get(convertView, R.id.tv_time);
        TextView tv_type = Get(convertView, R.id.tv_type);
        TextView tv_conten = Get(convertView, R.id.tv_conten);
        TextView tv_conte = Get(convertView, R.id.tv_conte);
        ImageView iv1= Get(convertView,R.id.iv1);
        ImageView iv2= Get(convertView,R.id.iv2);
        ImageView iv3= Get(convertView,R.id.iv3);
        ImageView iv4= Get(convertView,R.id.iv4);
        ImageView iv5= Get(convertView,R.id.iv5);
        ImageView iv11= Get(convertView,R.id.iv11);
        ImageView iv21= Get(convertView,R.id.iv21);
        ImageView iv31= Get(convertView,R.id.iv31);
        ImageView iv41= Get(convertView,R.id.iv41);
        ImageView iv51= Get(convertView,R.id.iv51);
        ImageView iv61= Get(convertView,R.id.iv61);
        ImageView iv12= Get(convertView,R.id.iv12);
        ImageView iv13= Get(convertView,R.id.iv13);
        ImageView iv14= Get(convertView,R.id.iv14);
        ImageView iv15= Get(convertView,R.id.iv15);
        //item的监听事件
    convertView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    });

    }


}
