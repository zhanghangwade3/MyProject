package com.all.app.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.all.app.R;
import com.all.app.base.AdapterBase;
import com.all.app.base.BaseActivity;
import com.all.app.ui.adapter.CarAdpter;
import com.all.app.utils.pullrefresh.PullToRefreshListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CarListActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.re_title)
    RelativeLayout reTitle;
    @BindView(R.id.iv1)
    View iv1;
    @BindView(R.id.lv_content)
    PullToRefreshListView lvContent;
    @BindView(R.id.iv_add)
    ImageView ivAdd;
    @BindView(R.id.tv_add)
    TextView tvAdd;
    @BindView(R.id.re_add)
    RelativeLayout reAdd;
    List<String> mDatas = new ArrayList<String>(Arrays.asList("Hello",
            "World", "Welcome", "Hello"));
     CarAdpter carAdpter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_list);
        ButterKnife.bind(this);
        carAdpter=new CarAdpter(this,mDatas);
        carAdpter.setAdpListener(new AdapterBase.IAdpBaseListener() {
            @Override
            public void onLazyAdpListener(int key, int position, Object data) {
                if(key==CarAdpter.CLICK_ITEM){
                  startActivity(new Intent(CarListActivity.this,CarActivity.class));
                }
            }
        });
        lvContent.setAdapter(carAdpter);
    }

    @OnClick({R.id.iv_back, R.id.tv_title, R.id.re_title, R.id.iv1, R.id.lv_content, R.id.iv_add, R.id.tv_add, R.id.re_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_title:
                break;
            case R.id.re_title:
                break;
            case R.id.iv1:
                break;
            case R.id.lv_content:
                break;
            case R.id.iv_add:
                break;
            case R.id.tv_add:
                break;
            case R.id.re_add:
                startActivity(new Intent(CarListActivity.this,CarActivity.class));
                break;
        }
    }
}
