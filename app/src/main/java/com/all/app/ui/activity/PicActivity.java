package com.all.app.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.all.app.R;
import com.all.app.base.BaseActivity;
import com.all.app.utils.XGlide;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PicActivity extends BaseActivity {
    String pic = "";
    @BindView(R.id.iv_back)
    ImageView ivBack;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pic);
        ButterKnife.bind(this);
        pic = getIntent().getStringExtra("pic");
     /*   webView = (WebView) findViewById(R.id.web_view);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        //支持javascript
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(pic);*/
        XGlide.init(this).display(ivBack,pic);

    }


    @OnClick({R.id.iv_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
               // finish();
                break;
        }
    }
}
