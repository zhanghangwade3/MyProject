package com.all.app.ui.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.all.app.R;
import com.all.app.base.AdapterBase;
import com.all.app.utils.XGridView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2017/4/1 0032.
 *  案例展示的Adapter
 */

public class ExampleAdpter extends AdapterBase<String> {
    List<String> mDatap = new ArrayList<String>(Arrays.asList("Hello",
            "World", "Welcome", "Hello",
            "World"));
      public ExampleAdpter(Context mContext , List<String>mDatas){
           super( mContext,mDatas, R.layout.item_example);
      }
    @Override
    public void Convert(int position, View convertView) {
        TextView tv_name= Get(convertView, R.id.tv_name);
        XGridView view_grid = Get(convertView, R.id.view_grid);
        GridAdpter  gridAdpter=new GridAdpter(mContext, (ArrayList<String>) mDatap);
        view_grid.setAdapter(gridAdpter);

        //item的监听事件
    convertView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    });
    }
}
