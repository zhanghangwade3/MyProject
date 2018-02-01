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
import com.all.app.bean.AccBean;
import com.all.app.ui.activity.WorkingActivity;
import com.all.app.utils.AlertDialog;
import com.all.app.utils.XGlide;

import java.util.List;

/**
 * Created by Administrator on 2017/3/31 0031.
 *  接单的Adapter
 */

public class AcceptAdpter extends AdapterBase<AccBean> {
    public static final int CLICK_ZHONG =123;
    public static final int CLICK_PHON =124;
      public AcceptAdpter(Context mContext , List<AccBean>mDatas){
           super( mContext,mDatas, R.layout.item_accpte);
      }
    @Override
    public void Convert( final int position, View convertView) {
        TextView tv_yue2 = Get(convertView, R.id.tv_yue2);
        TextView tv_name = Get(convertView, R.id.tv_name);
        TextView tv_jiaju = Get(convertView, R.id.tv_jiaju);
        TextView tv_type = Get(convertView, R.id.tv_type);
        TextView tv_addre = Get(convertView, R.id.tv_addre);
        TextView phone_call= Get(convertView, R.id.phone_call);
        TextView zhong_price= Get(convertView, R.id.zhong_price);
        ImageView iv_pic = Get(convertView, R.id.iv_pic);
        if("6".equals(getItem(position).getTask_status())){
            if("0".equals(getItem(position).getConfirm_success())){
                if("1".equals(getItem(position).getPrice_show())){
                    tv_yue2.setText("请确认中标价");
                }else if("2".equals(getItem(position).getPrice_show())){
                    tv_yue2.setText("等待商家确认中标价");
                    zhong_price.setVisibility(View.INVISIBLE);
                }else if("3".equals(getItem(position).getPrice_show())){
                    tv_yue2.setText("等待商家验收");
                    zhong_price.setVisibility(View.INVISIBLE);
                }
            }else if("1".equals(getItem(position).getPrice_show())){
                tv_yue2.setText("等待商家验收");
                zhong_price.setVisibility(View.INVISIBLE);
            }
        }else if("7".equals(getItem(position).getTask_status())){
              if("0".equals(getItem(position).getConfirm_success())){
                  if("1".equals(getItem(position).getPrice_show())){
                      tv_yue2.setText("请确认中标价");
                  }else if("2".equals(getItem(position).getPrice_show())){
                      tv_yue2.setText("等待商家确认中标价");
                      zhong_price.setVisibility(View.INVISIBLE);
                  }else if("3".equals(getItem(position).getPrice_show())){
                       tv_yue2.setText("等待商家验收");
                      zhong_price.setVisibility(View.INVISIBLE);
                  }
              }else if("1".equals(getItem(position).getPrice_show())){
                  tv_yue2.setText("等待商家付款");
                  zhong_price.setVisibility(View.INVISIBLE);
              }
        }
        SetText(tv_name,getItem(position).getTask_title());
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
            tv_type.setText("维修");
        }
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

        phone_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog dialog1 = new AlertDialog(mContext);
                dialog1.builder();
                dialog1.setTitle("商家电话");
                dialog1.setMsg("是否拨打商家电话：\n" + getItem(position).getMobile());
                dialog1.setPositiveButton("拨打", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                        Uri uri = Uri.parse("tel:" + getItem(position).getMobile());
                        Intent intent = new Intent(Intent.ACTION_DIAL, uri);
                        mContext.startActivity(intent);
                    }
                });
                dialog1.setNegativeButton("取消", new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                    }
                });
                dialog1.show();



            }
        });

        zhong_price.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener)
                    mListener.onLazyAdpListener(CLICK_ZHONG, position, getItem(position));
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
