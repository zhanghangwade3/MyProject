package com.all.app.ui.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.all.app.R;
import com.all.app.base.AdapterBase;
import java.util.List;


public class CategroyAdpter extends AdapterBase<String> {

      public CategroyAdpter(Context mContext , List<String>mDatas){
           super( mContext,mDatas, R.layout.item_guige);
      }
    @Override
    public void Convert( final int position, View convertView) {
        TextView tv_name = Get(convertView, R.id.tv_name);


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
