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

public class TeamAdpter extends AdapterBase<String> {

      public TeamAdpter(Context mContext , List<String>mDatas){
           super( mContext,mDatas, R.layout.item_team);
      }
    @Override
    public void Convert(final int position, View convertView) {
        TextView tv_name= Get(convertView, R.id.tv_name);
        TextView tv_time = Get(convertView, R.id.tv_time);
        TextView tv_cont = Get(convertView, R.id.tv_cont);
        ImageView iv_pic = Get(convertView, R.id.iv_pic);
        //item的监听事件
    convertView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          if(null!=mListener){
              mListener.onLazyAdpListener(CLICK_ITEM,position,getItem(position));
          }
        }
    });

    }


}
