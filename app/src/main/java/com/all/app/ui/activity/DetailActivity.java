package com.all.app.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.all.app.R;
import com.all.app.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_comment)
    TextView tvComment;
    @BindView(R.id.web_view)
    WebView webView;
    @BindView(R.id.sv_text)
    ScrollView svText;
    String title = "";
    String art_url = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        title=getIntent().getStringExtra("art_title");
        art_url=getIntent().getStringExtra("art_url");
        tvComment.setText(title);
         loadData();

    }

    private void loadData() {
        webView.getSettings().setJavaScriptEnabled(true);//启用js
        webView.getSettings().setBlockNetworkImage(false);//解决图片不显示
        webView.getSettings().setUseWideViewPort(true);// 这个很关键
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.loadUrl(art_url);
        webView.setWebViewClient(new WebViewClient() {//只在当前页加载数据，不做跳转
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // 返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                showDialog(DetailActivity.this, "");
                view.loadUrl(url);
                return true;
            }
        });
        webView.setWebChromeClient(new WebChromeClient() {//显示加载数据的请求框

            @Override
            public void onProgressChanged(WebView view, int position) {

                if (position == 100) {
                    dismissDialog();
                }
                super.onProgressChanged(view, position);

            }

        });
    }



    @OnClick({R.id.iv_back, R.id.tv_comment, R.id.web_view, R.id.sv_text})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_comment:
                break;
            case R.id.web_view:
                break;
            case R.id.sv_text:
                break;
        }
    }
}
