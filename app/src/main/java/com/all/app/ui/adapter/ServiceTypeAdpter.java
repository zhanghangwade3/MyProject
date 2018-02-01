package com.all.app.ui.adapter;


import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.all.app.R;
import com.all.app.base.AdapterBase;
import com.all.app.bean.CheckState;
import com.all.app.bean.ServiceBean;
import com.all.app.utils.XGridView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2017/3/31 0031.
 * 接单的Adapter
 */

public class ServiceTypeAdpter extends AdapterBase<ServiceBean> {
    private CheckState state;
    private List<CheckState> states = new ArrayList<CheckState>();
    public ServiceTypeAdpter(Context mContext, List<ServiceBean> mDatas) {
        super(mContext, mDatas, R.layout.item_guige);
        for (int i = 0; i < mDatas.size(); i++) {
            CheckState state = new CheckState();
            state.setCheck(false);
            states.add(state);
        }
    }

    @Override
    public void Convert(final int position, final View convertView) {
        TextView tv_name = Get(convertView, R.id.tv_name);
        XGridView gv = Get(convertView, R.id.gv);
        GridTextAdpter2 g2 = new GridTextAdpter2(mContext, getItem(position).getChild(), this, position);
        tv_name.setText(getItem(position).getName());

        //当states中未保存内部gridview的状态时，初始化内部gridview状态
        if (states.size() > 0 && states.get(position).getmGvSelects() == null) {
            ArrayList<Boolean> gvCelects = new ArrayList<Boolean>() {
            };
            for (int i = 0; i < getItem(position).getChild().size(); i++) {
                gvCelects.add(false);
            }
            states.get(position).setmGvSelects(gvCelects);
        }
        g2.setSelectState(states.get(position).getmGvSelects());
        gv.setAdapter(g2);


        //item的监听事件
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (states.get(position).isCheck()) {
                    states.get(position).setCheck(false);//保存外部check状态
                } else {
                    states.get(position).setCheck(true);//保存外部check状态
                }

            }
        });
    }

    //设置外部check全选
    public void setAllCheck(boolean tag, int lv_position) {
        if (tag) {//将所有check状态改为true
            states.get(lv_position).setCheck(true);
        } else {//将所有check状态改为false
            states.get(lv_position).setCheck(false);
        }
        notifyDataSetChanged();
    }

    //获取用户选择的服务类型集合
    public List<CheckState> getDataList() {
        return states;
    }
    public void setCheckDataList(List<CheckState>states){
           this.states=states;
         notifyDataSetChanged();
    }
}
