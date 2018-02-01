package com.all.app.ui.adapter;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.all.app.R;
import com.all.app.base.AdapterBase;
import com.all.app.bean.ChildBean;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2017/3/31 0031.
 *  接单的Adapter
 */

public class GridTextAdpter2 extends AdapterBase<ChildBean> {

    private ArrayList<Boolean>  mSelects=new ArrayList<Boolean>(){};
    private CheckBox ck;
    private ServiceTypeAdpter adpte;
    private int lv_position;
    public GridTextAdpter2(Context mContext , List<ChildBean> mDatas,ServiceTypeAdpter adpte,int lv_position){
        super( mContext,mDatas, R.layout.item_text);
        this.adpte=adpte;
        this.lv_position=lv_position;
    }
    @Override
    public void Convert( final int position, View convertView) {
        ck=Get(convertView, R.id.ck_value);
        ck.setText(getItem(position).getName());
        //item的监听事件
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position < mSelects.size()) {
                    boolean stste=  mSelects.get(position);
                    mSelects.set(position,!stste);
                }
                notifyDataSetChanged();
                //遍历集合，如果内部gridview全部选中，则外部check选中
                int trueTag=0;
                int falseTag=0;
                for(Boolean bool:mSelects){
                    if (bool){
                        trueTag++;
                    }else {
                        falseTag++;
                    }
                }
                if (mSelects.size()==trueTag){//全选
                    adpte.setAllCheck(true,lv_position);
                }else  {//全未选
                    adpte.setAllCheck(false,lv_position);
                }
            }
        });
        ck.setChecked(mSelects.get(position));
    }

    public void setSelectState(ArrayList<Boolean> selects) {
        mSelects=selects;
    }
}
