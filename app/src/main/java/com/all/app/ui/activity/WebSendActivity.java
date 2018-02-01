package com.all.app.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.all.app.R;
import com.all.app.app.AppData;
import com.all.app.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WebSendActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_comment)
    TextView tvComment;
    @BindView(R.id.web_view)
    WebView webView;
    String msgurl="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_send);
        ButterKnife.bind(this);
        msgurl=getIntent().getStringExtra("msgurl");
        webView = (WebView) findViewById(R.id.web_view);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        //支持javascript
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(msgurl+"&uid="+ AppData.Init().getLoginBean().getUid());
    }

    @OnClick({R.id.iv_back, R.id.tv_comment, R.id.web_view})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                 startActivity(new Intent(WebSendActivity.this,SendActivity.class));
                break;
            case R.id.tv_comment:
                break;
            case R.id.web_view:
                break;
        }
    }
}
