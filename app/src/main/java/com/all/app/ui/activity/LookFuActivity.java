package com.all.app.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.net.http.SslError;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.all.app.R;
import com.all.app.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LookFuActivity extends BaseActivity {
    WebView webView;
    ImageView iv_back;
    TextView tv_title;
    String single="";
    String info="";
    String info2="";
    String info3="";
    String info4="";
    String info5="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_look_fu);
         getData();
        webView = (WebView) findViewById(R.id.web_view);
        ButterKnife.bind(this);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setDatabaseEnabled(true);
        webView.getSettings().setAppCacheEnabled(true);
        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setSavePassword(true);
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        //支持javascript
        webView.getSettings().setTextSize(WebSettings.TextSize.LARGEST);
        webView.setDownloadListener(new MyWebViewDownLoadListener());
        webView.clearCache(true);
        webView.clearHistory();
        webView.setWebViewClient(new webClient());
        if("1".equals(single)){
            webView.loadUrl(info);
        }else if("2".equals(single)){
            webView.loadUrl(info2);
        }else if("3".equals(single)){
            webView.loadUrl(info3);
        }else if("4".equals(single)){
            webView.loadUrl(info4);
        }else if("5".equals(single)){
            webView.loadUrl(info5);
        }

    }

    private void getData() {
        single=getIntent().getStringExtra("single");
        info=getIntent().getStringExtra("info");
        info2=getIntent().getStringExtra("info2");
        info3=getIntent().getStringExtra("info3");
        info4=getIntent().getStringExtra("info4");
        info5=getIntent().getStringExtra("info5");
    }

    private class MyWebViewDownLoadListener implements DownloadListener {
        @Override
        public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
            Uri uri = Uri.parse(url);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }
    }


    private class webClient extends WebViewClient {
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

    }
}
