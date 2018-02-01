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

public class SendMesDelActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.re_title)
    RelativeLayout reTitle;
    @BindView(R.id.tv_news1)
    TextView tvNews1;
    @BindView(R.id.tv_news4)
    TextView tvNews4;
    @BindView(R.id.tv_number4)
    TextView tvNumber4;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_gao3)
    TextView tvGao3;
    @BindView(R.id.tv_gao4)
    TextView tvGao4;
    @BindView(R.id.tv_gao5)
    TextView tvGao5;
    @BindView(R.id.tv_gao6)
    TextView tvGao6;
    @BindView(R.id.tv_gao7)
    TextView tvGao7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_mes_del);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_back, R.id.tv_title, R.id.re_title, R.id.tv_news1, R.id.tv_news4, R.id.tv_number4, R.id.tv_time, R.id.tv_gao3, R.id.tv_gao4, R.id.tv_gao5})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_title:
                break;
            case R.id.re_title:
                break;
            case R.id.tv_news1:
                break;
            case R.id.tv_news4:
                break;
            case R.id.tv_number4:
                break;
            case R.id.tv_time:
                break;
            case R.id.tv_gao3:
                break;
            case R.id.tv_gao4:
                break;
            case R.id.tv_gao5:
                break;
        }
    }
}
