package com.all.app.ui.activity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.all.app.R;
import com.all.app.app.AppData;
import com.all.app.base.BaseActivity;
import com.all.app.utils.DataCleanManager;
import com.all.app.utils.UISwitchButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//设置页面
public class SettingActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_push)
    UISwitchButton tvPush;
    @BindView(R.id.rel_jpush)
    RelativeLayout relJpush;
    @BindView(R.id.tv_banben)
    TextView tvBanben;
    @BindView(R.id.rel_version)
    RelativeLayout relVersion;
    @BindView(R.id.tv_clear)
    TextView tvClear;
    @BindView(R.id.rel_clear)
    RelativeLayout relClear;
    @BindView(R.id.rel_rest_pwd)
    RelativeLayout relRestPwd;
    @BindView(R.id.rel_feedback)
    RelativeLayout relFeedback;
    @BindView(R.id.rel_about)
    RelativeLayout relAbout;
    @BindView(R.id.bt_logout)
    Button btLogout;
    PackageManager pm;
    PackageInfo pi;
    String single="2";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        try {
            tvClear.setText(DataCleanManager.getTotalCacheSize(this));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        pm = getPackageManager();
        try {
            pi = pm.getPackageInfo(getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        tvBanben.setText("V" + pi.versionName);
    }



    @OnClick({R.id.rel_clear,R.id.bt_logout,R.id.rel_about,R.id.rel_feedback,R.id.rel_rest_pwd,R.id.iv_back, R.id.tv_push})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.rel_clear:
                tvClear.setText("0 M");
                DataCleanManager.clearAllCache(this);
                showTips("缓存已清除", null);
                break;
            case R.id.rel_rest_pwd:
               startActivity(new Intent(SettingActivity.this, ModifyLogActivity.class));
                break;
            case R.id.rel_about:
                Intent intent2 =new Intent(SettingActivity.this,WebUsActivity.class);
                intent2.putExtra("single",single);
                startActivity(intent2);
                break;
            case R.id.tv_push:
                break;
            case R.id.rel_feedback:
                startActivity(new Intent(SettingActivity.this,AdviceActivity.class));
                break;
            case R.id.bt_logout:
                AppData.Init().LogOut();
                Intent intent = new Intent(this, LoginActivity.class);
                intent.putExtra("isLogout", true);
                intent.putExtra("type", "1");
                startActivity(intent);
                break;
        }
    }
}
