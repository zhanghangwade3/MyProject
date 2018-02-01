package com.all.app.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.all.app.R;
import com.all.app.base.AdapterBase;
import com.all.app.bean.BidBean;
import com.all.app.ui.activity.BidActivity;
import com.all.app.utils.AlertDialog;
import com.all.app.utils.XGlide;

import java.util.List;

/**
 * Created by Administrator on 2017/3/31 0031.
 *  接单的Adapter
 */

public class BidAdpter extends AdapterBase<BidBean> {
    public static final int CLICK_OK=128;
      public BidAdpter(Context mContext , List<BidBean>mDatas){
           super( mContext,mDatas, R.layout.item_zhongbiao);
      }
    @Override
    public void Convert( final int position, View convertView) {
        TextView tv_name = Get(convertView, R.id.tv_name);
        TextView tv_number = Get(convertView, R.id.tv_number);
        TextView tv_jiaju = Get(convertView, R.id.tv_jiaju);
        TextView tv_type = Get(convertView, R.id.tv_type);
        TextView tv_addre = Get(convertView, R.id.tv_addre);
        TextView tv_tim = Get(convertView, R.id.tv_tim);
        TextView other_cancle = Get(convertView, R.id. other_cancle);
        TextView other_ok = Get(convertView, R.id.other_ok);
        ImageView iv_pic = Get(convertView, R.id.iv_pic);

        SetText(tv_name,getItem(position).getTask_title());
        SetText(tv_number,getItem(position).getTask_id());
        SetText(tv_jiaju,getItem(position).getIndus_pid());
        if("0".equals(getItem(position).getIs_pick())){
            tv_type.setText("安装");
        }else if("1".equals(getItem(position).getIs_pick())){
            tv_type.setText("配送安装");
        }else if("2".equals(getItem(position).getIs_pick())) {
            tv_type.setText("送货到家");
        } else if("3".equals(getItem(position).getIs_pick())) {
            tv_type.setText("送货到楼下");
        }else if("4".equals(getItem(position).getIs_pick())) {
            tv_type.setText("维修");
        }else if("5".equals(getItem(position).getIs_pick())) {
            tv_type.setText("量尺寸");
        }
        SetText(tv_tim,getItem(position).getOrder_time());
        if("0".equals(getItem(position).getIs_pick())||"4".equals(getItem(position).getIs_pick())||"5".equals(getItem(position).getIs_pick())){
            tv_addre.setText("施工地址："+getItem(position).getAddress());
        }else if("1".equals(getItem(position).getIs_pick())||"2".equals(getItem(position).getIs_pick())
                ||"3".equals(getItem(position).getIs_pick())){
            tv_addre.setText("配送地址："+getItem(position).getAddress());
        }

        if(TextUtils.isEmpty(getItem(position).getTask_pic())){
            XGlide.init(mContext).display(iv_pic,R.drawable.xiaotu);
        }else {
            XGlide.init(mContext).display(iv_pic,getItem(position).getTask_pic());
        }

        other_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog dialog = new AlertDialog(mContext);
                dialog.builder();
                dialog.setTitle("客服电话");
                dialog.setMsg("是否拨打客服电话：\n" + getItem(position).getKftel());

                dialog.setPositiveButton("拨打", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                        Uri uri = Uri.parse("tel:" + getItem(position).getKftel());
                        Intent intent = new Intent(Intent.ACTION_DIAL, uri);
                        mContext.startActivity(intent);
                    }
                });
                dialog.setNegativeButton("取消", new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                    }
                });
                dialog.show();
            }
        });

       other_ok.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if (null != mListener)
                   mListener.onLazyAdpListener(CLICK_OK, position, getItem(position));
           }
       });

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
