package com.all.app.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.all.app.R;
import com.all.app.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WebFindActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_comment)
    TextView tvComment;
    @BindView(R.id.web_view)
    WebView webView;
    String art_url = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_find);
        ButterKnife.bind(this);
        art_url = getIntent().getStringExtra("art_url");
        webView = (WebView) findViewById(R.id.web_view);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        //支持javascript
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(art_url);

    }


    @OnClick({R.id.iv_back, R.id.tv_comment, R.id.web_view})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_comment:
                break;
            case R.id.web_view:
                break;
        }
    }
}
