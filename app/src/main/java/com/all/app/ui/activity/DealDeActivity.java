package com.all.app.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.all.app.R;
import com.all.app.app.AppData;
import com.all.app.base.BaseActivity;
import com.all.app.configs.Urls;
import com.all.app.http.OkHttpParam;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DealDeActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.re_title)
    RelativeLayout reTitle;
    @BindView(R.id.tv_news1)
    TextView tvNews1;
    @BindView(R.id.tv_number)
    TextView tvNumber;
    @BindView(R.id.tv_gao)
    TextView tvGao;
    @BindView(R.id.tv_gao2)
    TextView tvGao2;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.tv_news2)
    TextView tvNews2;
    @BindView(R.id.tv_number2)
    TextView tvNumber2;
    @BindView(R.id.tv_news3)
    TextView tvNews3;
    @BindView(R.id.tv_number3)
    TextView tvNumber3;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deal_de);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.iv_back, R.id.tv_title, R.id.re_title, R.id.tv_news1, R.id.tv_number})
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
            case R.id.tv_number:
                break;
        }
    }
}
