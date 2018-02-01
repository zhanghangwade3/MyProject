package com.all.app.ui.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.all.app.R;
import com.all.app.base.AdapterBase;
import com.all.app.bean.FindBean;
import com.all.app.utils.XGlide;

import java.util.List;

/**
 * Created by Administrator on 2017/4/1 0032.
 *  发现的Adapter
 */

public class FindAdpter extends AdapterBase<FindBean> {

      public FindAdpter(Context mContext , List<FindBean>mDatas){
           super( mContext,mDatas, R.layout.item_find);
      }
    @Override
    public void Convert(final int position, View convertView) {
        TextView tv_title= Get(convertView, R.id.tv_title);
        ImageView iv_pic = Get(convertView, R.id.iv_pic);
        SetText(tv_title,getItem(position).getArt_title());
        XGlide.init(mContext).display(iv_pic,getItem(position).getArt_pic());
        //item的监听事件
    convertView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
         if (mListener!=null){
             mListener.onLazyAdpListener(CLICK_ITEM,position,getItem(position));
         }
        }
    });

    }


}
