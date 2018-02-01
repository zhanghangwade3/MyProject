package com.all.app.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import com.all.app.R;
import com.all.app.base.BaseActivity;

public class MessageActivity extends BaseActivity {
     WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        webView= (WebView) findViewById(R.id.web_view);
    }
}
