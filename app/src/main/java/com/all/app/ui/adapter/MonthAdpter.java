package com.all.app.ui.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.all.app.R;
import com.all.app.base.AdapterBase;

import java.util.List;

/**
 * Created by Administrator on 2017/3/31 0031.
 *  接单的Adapter
 */

public class MonthAdpter extends AdapterBase<String> {

      public MonthAdpter(Context mContext , List<String>mDatas){
           super( mContext,mDatas, R.layout.item_month);
      }
    @Override
    public void Convert( final int position, View convertView) {
        TextView tv_number = Get(convertView, R.id.tv_number);


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
