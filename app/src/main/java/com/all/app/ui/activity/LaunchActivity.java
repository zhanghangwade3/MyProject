package com.all.app.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.all.app.R;
import com.all.app.app.AppData;
import com.all.app.base.BaseActivity;
import java.util.Timer;
import java.util.TimerTask;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;

public class LaunchActivity extends BaseActivity {

    @BindView(R.id.iv)
    ImageView iv;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_times)
    TextView tvTimes;
    @BindView(R.id.bt_tiao)
    ImageButton btTiao;
    @BindView(R.id.tv_appname)
    TextView tvAppname;
    @BindView(R.id.tv_appver)
    TextView tvAppver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        ButterKnife.bind(this);
        delayTime();//延时跳转
    }

    private void delayTime() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                //实现页面跳转
                jumpNextPage();
            }
        };
        //表示延时两秒进行任务的执行
        timer.schedule(task, 2 * 1000);


    }
     //判断页面的跳转
    private void jumpNextPage() {
        // 判断之前有没有显示过新手引导
        SharedPreferences sp = getSharedPreferences("config", MODE_PRIVATE);
        boolean userGuide = sp.getBoolean("is_user_guide_showed",
                false);
        if (!userGuide) {
            // 跳转到新手引导页
            startActivity(new Intent(LaunchActivity.this, GuideActivity.class));
        } else {
               if(TextUtils.isEmpty(AppData.Init().getLoginBean().getUid())){
                   startActivity(new Intent(LaunchActivity.this,LoginActivity.class));
               }else{
                   if("1".equals(AppData.Init().getLoginBean().getRealname_status())){
                       startActivity(new Intent(LaunchActivity.this,MainActivity.class));
                   }else{
                       startActivity(new Intent(LaunchActivity.this,RenActivity.class));
                   }

               }
            }
         finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        JPushInterface.onResume(getApplicationContext());
    }

    @Override
    protected void onPause() {
        JPushInterface.onPause(getApplicationContext());
        super.onPause();
    }

    @OnClick({R.id.iv, R.id.tv_time, R.id.tv_times, R.id.bt_tiao, R.id.tv_appname, R.id.tv_appver})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv:
                break;
            case R.id.tv_time:
                break;
            case R.id.tv_times:
                break;
            case R.id.bt_tiao:
                break;
            case R.id.tv_appname:
                break;
            case R.id.tv_appver:
                break;
        }
    }
}
