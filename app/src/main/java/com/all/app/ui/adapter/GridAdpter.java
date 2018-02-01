package com.all.app.ui.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.all.app.R;
import com.all.app.base.AdapterBase;
import com.all.app.bean.ProBean;
import com.all.app.utils.XGlide;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/31 0031.
 *  接单的Adapter
 */

public class GridAdpter extends AdapterBase<String> {
    ArrayList<String> pictures;
      public GridAdpter(Context mContext , ArrayList<String> pictures){
           super( mContext,pictures, R.layout.item_gride);
              this.pictures=pictures;
      }
    @Override
    public void Convert( final int position, View convertView) {
        ImageView iv_gride = Get(convertView, R.id.iv_gride);
        XGlide.init(mContext).display(iv_gride ,pictures.get(position));
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
