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
import com.all.app.ui.adapter.MonthAdpter;
import com.all.app.utils.pullrefresh.PullToRefreshListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MonthActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.re_title)
    RelativeLayout reTitle;
    @BindView(R.id.lv_content)
    PullToRefreshListView lvContent;
    MonthAdpter monthAdpter;
    //模拟的数据
    List<String> mDatas = new ArrayList<String>(Arrays.asList("Hello",
            "World", "Welcome", "Hello",
            "World", "Welcome", "World", "Welcome", "Hello", "World", "Welcome", "Hello"));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_month);
        ButterKnife.bind(this);
        monthAdpter=new MonthAdpter(this,mDatas);
        monthAdpter.setAdpListener(new AdapterBase.IAdpBaseListener() {
            @Override
            public void onLazyAdpListener(int key, int position, Object data) {
                if(key==MonthAdpter.CLICK_ITEM){
                    startActivity(new Intent(MonthActivity.this,MonthDeActivity.class));
                }
            }
        });
        lvContent.setAdapter(monthAdpter);
    }

    @OnClick({R.id.iv_back, R.id.tv_title, R.id.re_title, R.id.lv_content})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_title:
                break;
            case R.id.re_title:
                break;
            case R.id.lv_content:
                break;
        }
    }
}
