package com.all.app.ui.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.all.app.R;
import com.all.app.base.AdapterBase;

import java.util.List;

/**
 * Created by Administrator on 2017/3/31 0031.
 *  接单的Adapter
 */

public class AccepAdpter extends AdapterBase<String> {

      public AccepAdpter(Context mContext , List<String>mDatas){
           super( mContext,mDatas, R.layout.item_accept);
      }
    @Override
    public void Convert( final int position, View convertView) {
        TextView tv_name = Get(convertView, R.id.tv_name);
        TextView tv_name22 = Get(convertView, R.id.tv_name22);
        TextView tv_name2 = Get(convertView, R.id.tv_name2);
        TextView tv_iv = Get(convertView, R.id.tv_iv);

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
