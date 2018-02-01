package com.all.app.ui.activity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import com.all.app.R;
import com.all.app.base.BaseActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WebViewActivity extends BaseActivity {
    WebView webView;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    String single = "";
    String info = "";
    String art_url = "";
    @BindView(R.id.tv_comment)
    TextView tv_comment;
    String msgurl="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        single = getIntent().getStringExtra("single");
        info = getIntent().getStringExtra("info");
        art_url = getIntent().getStringExtra("art_url");
        msgurl=getIntent().getStringExtra("msgurl");
        webView = (WebView) findViewById(R.id.web_view);
        ButterKnife.bind(this);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        //支持javascript
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setTextSize(WebSettings.TextSize.LARGEST);
        if ("1".equals(single)) {
            webView.loadUrl("file:///android_asset/agreement.htm");
        }
    }
    @OnClick(R.id.iv_back)
    public void onViewClicked() {
          finish();
    }
}

