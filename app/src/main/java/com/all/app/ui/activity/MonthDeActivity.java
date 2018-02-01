package com.all.app.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.all.app.R;
import com.all.app.base.BaseActivity;
import com.all.app.ui.adapter.MonthdelAdpter;
import com.all.app.utils.pullrefresh.PullToRefreshListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MonthDeActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.re_title)
    RelativeLayout reTitle;
    @BindView(R.id.tv_title2)
    TextView tvTitle2;
    @BindView(R.id.tv_title3)
    TextView tvTitle3;
    @BindView(R.id.tv_title4)
    TextView tvTitle4;
    @BindView(R.id.tv_title5)
    TextView tvTitle5;
    @BindView(R.id.lv_content)
    PullToRefreshListView lvContent;
    //模拟的数据
    List<String> mDatas = new ArrayList<String>(Arrays.asList("Hello",
            "World", "Welcome", "Hello",
            "World", "Welcome", "World", "Welcome", "Hello", "World", "Welcome", "Hello"));

    MonthdelAdpter monthdelAdpter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_month_de);
        ButterKnife.bind(this);
        monthdelAdpter=new MonthdelAdpter(this,mDatas);
        lvContent.setAdapter(monthdelAdpter);
    }

    @OnClick({R.id.iv_back, R.id.tv_title, R.id.re_title, R.id.tv_title2, R.id.tv_title3, R.id.tv_title4, R.id.tv_title5, R.id.lv_content})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_title:
                break;
            case R.id.re_title:
                break;
            case R.id.tv_title2:
                break;
            case R.id.tv_title3:
                break;
            case R.id.tv_title4:
                break;
            case R.id.tv_title5:
                break;
            case R.id.lv_content:
                break;
        }
    }
}
