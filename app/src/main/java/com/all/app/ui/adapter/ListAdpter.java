package com.all.app.ui.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.all.app.R;
import com.all.app.base.AdapterBase;
import com.all.app.bean.YanBean;

import java.util.List;

/**
 * Created by Administrator on 2017/4/1 0032.
 *  发现的Adapter
 */

public class ListAdpter extends AdapterBase<YanBean> {

      public ListAdpter(Context mContext , List<YanBean>mDatas){
           super( mContext,mDatas, R.layout.item_list);
      }
    @Override
    public void Convert(final int position, View convertView) {
        TextView tv_content= Get(convertView, R.id.tv_content);
        TextView tv_time = Get(convertView, R.id.tv_time);
        SetText(tv_content,getItem(position).getContent());
        SetText(tv_time,getItem(position).getOn_time());
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
