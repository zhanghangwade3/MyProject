package com.all.app.ui.adapter;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.all.app.R;
import com.all.app.base.AdapterBase;
import com.all.app.ui.activity.PhotoActivity;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class PhotoAdpter extends AdapterBase<String> {
    public static final int CLICK_IA = 0x102;
    public static final int CLICK_IB = 123;
    public static final int CLICK_IC = 124;
    public static final int CLICK_ID = 125;
    public static final int CLICK_IE = 126;
    public PhotoAdpter(Context mContext, List<String> mDatas) {
        super(mContext, mDatas, R.layout.item_photo);
    }

    @Override
    public void Convert(final int position, View convertView) {
        TextView tv_name = Get(convertView, R.id.tv_name);
        final ImageView iv1 = Get(convertView, R.id.iv1);
        final ImageView iv2 = Get(convertView, R.id.iv2);
        final ImageView iv3 = Get(convertView, R.id.iv3);
        final ImageView iv4 = Get(convertView, R.id.iv4);
        final ImageView iv5 = Get(convertView, R.id.iv5);

        iv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(null!=mListener){
                    mListener.onLazyAdpListener(CLICK_IA,position,getItem(position));
                }
            }
        });
        iv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(null!=mListener){
                    mListener.onLazyAdpListener(CLICK_IB,position,getItem(position));
                }
            }
        });
        iv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(null!=mListener){
                    mListener.onLazyAdpListener(CLICK_IC,position,getItem(position));
                }
            }
        });
        iv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(null!=mListener){
                    mListener.onLazyAdpListener(CLICK_ID,position,getItem(position));
                }
            }
        });
        iv5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(null!=mListener){
                    mListener.onLazyAdpListener(CLICK_IE,position,getItem(position));
                }
            }
        });

    }
}