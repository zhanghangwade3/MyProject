package com.all.app.ui.adapter;
import android.content.Context;
import android.view.View;
import android.widget.CheckBox;

import com.all.app.R;
import com.all.app.base.AdapterBase;
import com.all.app.bean.AreaBean;

import java.util.ArrayList;
import java.util.List;


public class GridTextAdpter extends AdapterBase<AreaBean> {
    private ArrayList<Boolean>  mSelects=new ArrayList<Boolean>(){};
    public GridTextAdpter(Context mContext , List<AreaBean>mDatas){
        super( mContext,mDatas, R.layout.item_text);
        //初始化mSelects
        for (int i = 0; i < mDatas.size(); i++) {
            mSelects.add(false);
        }
    }
    @Override
    public void Convert( final int position, View convertView) {
        final CheckBox  ck_value=Get(convertView, R.id.ck_value);
        ck_value.setText(getItem(position).getName());
        ck_value.setChecked(mSelects.get(position));
        //item的监听事件
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (position < mSelects.size()) {
                    boolean stste=  mSelects.get(position);
                    mSelects.set(position,!stste);
                }
                notifyDataSetChanged();
            }
        });
    }
    public void setSelectState(ArrayList<Boolean> selects) {
        mSelects=selects;
    }
    //获取用户选择的服务类型集合
    public ArrayList<Boolean> getSelectState() {
        return mSelects;
    }
}
