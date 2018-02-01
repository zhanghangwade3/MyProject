package com.all.app.ui.activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.all.app.R;
import com.all.app.base.BaseActivity;
import com.all.app.bean.AddressBean;
import com.all.app.configs.Urls;
import com.all.app.http.OkHttpParam;
import com.all.app.http.ResponseEntry;
import com.all.app.utils.FJson;
import com.all.app.utils.StringUtil;
import com.all.app.utils.StringUtils;
import com.all.app.utils.Timer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BackActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.re_title)
    RelativeLayout reTitle;
    @BindView(R.id.et_shouji)
    EditText etShouji;
    @BindView(R.id.et_yan)
    EditText etYan;
    @BindView(R.id.bt_yan)
    Button btYan;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.imagec)
    ImageView imagec;
    @BindView(R.id.rl_username_del)
    RelativeLayout rlUsernameDel;
    @BindView(R.id.bt_sign)
    Button btSign;
    @BindView(R.id.ll_content)
    LinearLayout llContent;
    String uphone = "";
    String ucode = "";
    String upwd;//新密码
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_back);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_back, R.id.tv_title, R.id.re_title, R.id.et_shouji, R.id.et_yan, R.id.bt_yan, R.id.et_password, R.id.imagec, R.id.rl_username_del, R.id.bt_sign, R.id.ll_content})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_title:
                break;
            case R.id.re_title:
                break;
            case R.id.et_shouji:
                break;
            case R.id.et_yan:
                break;
            case R.id.bt_yan:
                getVerifCode();
                break;
            case R.id.et_password:
                break;
            case R.id.imagec:
                break;
            case R.id.rl_username_del:
                break;
            case R.id.bt_sign:
                 submint();
                break;
            case R.id.ll_content:
                break;
        }
    }

    private void getVerifCode() {
        uphone = etShouji.getText().toString().trim();
        if (StringUtil.isEmpty(uphone)) {
            Toast.makeText(this, "请输入电话号码", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!StringUtils.isPhone(uphone)) {
            Toast.makeText(this, "请输入正确的电话号码", Toast.LENGTH_SHORT).show();
            return;
        }
        OkHttpParam param = new OkHttpParam();
        param.add("uphone", uphone);
        _PostEntry(Urls.sendregister, param, Urls.ActionId.sendregister, true);
    }
    //发送验证码
    boolean isSendSms = false;
    Timer.OnTimeFinishListener listener = new Timer.OnTimeFinishListener() {

        @Override
        public void onTimeFinished() {
            // 倒计时结束后 手机号码设置可以更改
            etShouji.setEnabled(true);
        }
    };

    //确认新密码提交
    private void submint() {
        uphone = etShouji.getText().toString().trim();
        ucode = etYan.getText().toString().trim();
        upwd= etPassword.getText().toString().trim();
        if (StringUtil.isEmpty(uphone)) {
            Toast.makeText(this, "请输入手机号码", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!StringUtils.isPhone(uphone)) {
            Toast.makeText(this, "请输入正确的手机号码", Toast.LENGTH_SHORT).show();
        }
        if (StringUtil.isEmpty(ucode)) {
            Toast.makeText(this, "请输入验证码", Toast.LENGTH_SHORT).show();
            return;
        }
        if (StringUtil.isEmpty(upwd)) {
            Toast.makeText(this, "请输入新密码", Toast.LENGTH_SHORT).show();
            return;
        }
        OkHttpParam param = new OkHttpParam();
        param.add("uphone", uphone);
        param.add("ucode",  ucode);
        param.add("upwd",  upwd);
         _PostEntry(Urls.retrieve, param, Urls.ActionId.retrieve, true);

    }

    @Override
    protected void onNetSuccess(int actionId, ResponseEntry result) {
        super.onNetSuccess(actionId, result);

        switch (actionId) {
            case Urls.ActionId.sendregister:
                sendSmsFinish(result);
                break;
            case Urls.ActionId.retrieve:
                 if(result.isSuccess()){
                     startActivity(new Intent(BackActivity.this,LoginActivity.class));
                 }else{
                     showTips(result.getMsg(),null);
                 }
                break;


        }

    }

    private void sendSmsFinish(ResponseEntry result) {
        isSendSms = true;
        if (result.isSuccess()) {
            Log.e(getClass().getSimpleName() + "TAG", "request successful!");
            showTips(result.getMsg(), null);
            // 倒计时结束前 手机号码设置不可更改
            etShouji.setEnabled(false);
            // 开启倒计时
            Timer timer = new Timer(60000, 1000, this, btYan);
            timer.setOnTimeFinishListener(listener);
            timer.start();
        } else {
            Log.e(getClass().getSimpleName() + "TAG", "request failure!");
            showTips(result.getMsg(), null);
        }

    }
}
