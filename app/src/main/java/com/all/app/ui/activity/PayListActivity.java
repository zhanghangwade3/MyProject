package com.all.app.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.all.app.R;
import com.all.app.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PayListActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.re_title)
    RelativeLayout reTitle;
    @BindView(R.id.iv_icon)
    ImageView ivIcon;
    @BindView(R.id.tv_jing)
    TextView tvJing;
    @BindView(R.id.iv_next)
    ImageView ivNext;
    @BindView(R.id.re_title2)
    RelativeLayout reTitle2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_list);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_back, R.id.tv_title, R.id.re_title, R.id.iv_icon, R.id.tv_jing, R.id.iv_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_title:
                break;
            case R.id.re_title:
                break;
            case R.id.iv_icon:
                break;
            case R.id.tv_jing:
                break;
            case R.id.iv_next:
                break;
        }
    }
}
