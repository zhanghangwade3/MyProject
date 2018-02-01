package com.all.app.ui.activity;

import android.content.Intent;
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

public class ChooseTypeActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.re_title)
    RelativeLayout reTitle;
    @BindView(R.id.iv_secret)
    ImageView ivSecret;
    @BindView(R.id.iv_right4)
    ImageView ivRight4;
    @BindView(R.id.re_service)
    RelativeLayout reService;
    @BindView(R.id.iv_bao)
    ImageView ivBao;
    @BindView(R.id.iv_right2)
    ImageView ivRight2;
    @BindView(R.id.re_bao)
    RelativeLayout reBao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_type);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_back, R.id.tv_title, R.id.re_title, R.id.iv_secret, R.id.iv_right4, R.id.re_service, R.id.iv_bao, R.id.iv_right2, R.id.re_bao})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_title:
                break;
            case R.id.re_title:
                break;
            case R.id.iv_secret:
                break;
            case R.id.iv_right4:
                break;
            case R.id.re_service:
                startActivity(new Intent(ChooseTypeActivity.this,EditBankActivity.class));
                break;
            case R.id.iv_bao:
                startActivity(new Intent(ChooseTypeActivity.this,AddAlipayActivity.class));
                break;
            case R.id.iv_right2:
                break;
            case R.id.re_bao:
                startActivity(new Intent(ChooseTypeActivity.this,AddAlipayActivity.class));
                break;
        }
    }
}
