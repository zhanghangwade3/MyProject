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

public class StuAdpter extends AdapterBase<String> {

      public StuAdpter(Context mContext , List<String>mDatas){
           super( mContext,mDatas, R.layout.item_zige);
      }
    @Override
    public void Convert(final int position, View convertView) {
        TextView tv_number2= Get(convertView, R.id.tv_number2);
        TextView tv_number3 = Get(convertView, R.id.tv_number3);
        TextView tv_number4 = Get(convertView, R.id.tv_number4);

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
