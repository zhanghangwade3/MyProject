package com.all.app.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.all.app.R;
import com.all.app.app.AppData;
import com.all.app.base.BaseActivity;
import com.all.app.configs.Urls;
import com.all.app.http.OkHttpParam;
import com.all.app.http.ResponseEntry;
import com.all.app.utils.IDCard;
import com.all.app.utils.StringUtil;
import com.all.app.utils.StringUtils;
import com.all.app.utils.Timer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ModifyPwActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.re_title)
    RelativeLayout reTitle;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.imagec)
    ImageView imagec;
    @BindView(R.id.rl_username_del)
    RelativeLayout rlUsernameDel;
    @BindView(R.id.re_phone)
    RelativeLayout rePhone;
    @BindView(R.id.tv_ma)
    TextView tvMa;
    @BindView(R.id.et_ma)
    EditText etMa;
    @BindView(R.id.tv_tixian)
    TextView tvTixian;
    @BindView(R.id.re_ma)
    RelativeLayout reMa;
    @BindView(R.id.tv_zheng)
    TextView tvZheng;
    @BindView(R.id.et_zheng)
    EditText etZheng;
    @BindView(R.id.re_sh)
    RelativeLayout reSh;
    @BindView(R.id.tv_newPw)
    TextView tvNewPw;
    @BindView(R.id.et_pw)
    EditText etPw;
    @BindView(R.id.re_new)
    RelativeLayout reNew;
    @BindView(R.id.tv_tijiao)
    TextView tvTijiao;
    String phone = "";
    String idcard = "";
    String newcode = "";
    String type="pwdyz";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_pw);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_back, R.id.tv_title, R.id.re_title, R.id.tv_phone, R.id.et_password, R.id.imagec, R.id.rl_username_del, R.id.re_phone, R.id.tv_ma, R.id.et_ma, R.id.tv_tixian, R.id.re_ma, R.id.tv_zheng, R.id.et_zheng, R.id.re_sh, R.id.tv_newPw, R.id.et_pw, R.id.re_new, R.id.tv_tijiao})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_title:
                break;
            case R.id.re_title:
                break;
            case R.id.tv_phone:
                break;
            case R.id.et_password:
                break;
            case R.id.imagec:
                break;
            case R.id.rl_username_del:
                break;
            case R.id.re_phone:
                break;
            case R.id.tv_ma:
                break;
            case R.id.et_ma:
                break;
            case R.id.tv_tixian:
                getVerifCode();
                break;
            case R.id.re_ma:
                break;
            case R.id.tv_zheng:
                break;
            case R.id.et_zheng:
                break;
            case R.id.re_sh:
                break;
            case R.id.tv_newPw:
                break;
            case R.id.et_pw:
                break;
            case R.id.re_new:
                break;
            case R.id.tv_tijiao:
                submit();
                break;
        }
    }

    private void getVerifCode() {
        phone =etPassword.getText().toString().trim();
        OkHttpParam param = new OkHttpParam();
        if (StringUtil.isEmpty( phone)) {
            Toast.makeText(this, "请输入电话号码", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!StringUtils.isPhone(phone)) {
            Toast.makeText(this, "请输入正确的电话号码", Toast.LENGTH_SHORT).show();
            return;
        }

        param.add("uphone", phone);
        _PostEntry(Urls.sendregistermsg, param, Urls.ActionId.sendregistermsg, true);
    }

    private void submit() {
        phone = etPassword.getText().toString();
        idcard = etZheng.getText().toString();
        newcode = etPw.getText().toString();
        OkHttpParam param=new OkHttpParam();
        if (TextUtils.isEmpty(AppData.Init().getLoginBean().getUid())) {
            Toast.makeText(ModifyPwActivity.this, "用户的ID不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(ModifyPwActivity.this, "请输入手机号码", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(idcard)) {
            Toast.makeText(ModifyPwActivity.this, "请输入身份证号码", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!IDCard.IDCardValidate(idcard.toUpperCase()).equals("1")) {
            showTips(IDCard.IDCardValidate(idcard.toUpperCase()), null);
            return;
        }
        if (TextUtils.isEmpty(newcode)) {
            Toast.makeText(ModifyPwActivity.this, "请输入新密码", Toast.LENGTH_SHORT).show();
            return;
        }
        param.add("uid", AppData.Init().getLoginBean().getUid());
        param.add("phone", phone);
        param.add("idcard", idcard);
        param.add("newcode",newcode);
        param.add("type", type);
        _PostEntry(Urls.updpaypwd, param, Urls.ActionId.updpaypwd, true);
    }

    @Override
    protected void onNetSuccess(int actionId, ResponseEntry result) {
        super.onNetSuccess(actionId, result);
        switch (actionId) {
            case Urls.ActionId.sendregistermsg:
                sendSmsFinish(result);
                break;
            case Urls.ActionId.updpaypwd:
                if (result.isSuccess()) {
                    System.out.println("1111111111111111111111111111" + result);
                   startActivity(new Intent(ModifyPwActivity.this,MoneyActivity.class));
                }
        }

    }
    //发送验证码
    boolean isSendSms = false;
    Timer.OnTimeFinishListener listener = new Timer.OnTimeFinishListener() {

        @Override
        public void onTimeFinished() {
            // 倒计时结束后 手机号码设置可以更改
            etPassword.setEnabled(true);
        }
    };
    private void sendSmsFinish(ResponseEntry result) {
        isSendSms = true;
        if (result.isSuccess()) {
            Log.e(getClass().getSimpleName() + "TAG", "request successful!");
            showTips(result.getMsg(), null);
            // 倒计时结束前 手机号码设置不可更改
            etPassword.setEnabled(false);
            // 开启倒计时
            Timer timer = new Timer(60000, 1000, this, tvTixian);
            timer.setOnTimeFinishListener(listener);
            timer.start();
        } else {
            Log.e(getClass().getSimpleName() + "TAG", "request failure!");
            showTips(result.getMsg(), null);
        }

    }
}
