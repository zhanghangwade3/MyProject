package com.all.app.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.all.app.R;
import com.all.app.app.AppData;
import com.all.app.base.BaseActivity;
import com.all.app.configs.Urls;
import com.all.app.http.OkHttpParam;
import com.all.app.http.ResponseEntry;
import com.all.app.utils.ContainsEmojiEditText;
import com.all.app.utils.PwdEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PayActivity extends BaseActivity {

    @BindView(R.id.pay_title)
    TextView payTitle;
    @BindView(R.id.pet_pwd)
    EditText petPwd;
    @BindView(R.id.pay_cancel)
    TextView payCancel;
    @BindView(R.id.pay_sure)
    TextView paySure;
    @BindView(R.id.pay_input)
    LinearLayout payInput;
    String zfpwd = "";
    String pay_type = "";
    String payid = "";
    String withdrawcash = "";
    String margin = "";
    String type = "";
    String recharge = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        ButterKnife.bind(this);
        type = getIntent().getStringExtra("type");
        pay_type = getIntent().getStringExtra("pay_type");
        payid = getIntent().getStringExtra("payid");
        withdrawcash = getIntent().getStringExtra("withdrawcash");
        margin = getIntent().getStringExtra("margin");
        recharge = getIntent().getStringExtra("recharge");
    }

    @OnClick({R.id.pay_title, R.id.pet_pwd, R.id.pay_cancel, R.id.pay_sure, R.id.pay_input})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.pay_title:
                break;
            case R.id.pet_pwd:
                petPwd.setCursorVisible(true);
             /*   petPwd.setOnInputFinishListener(new PwdEditText.OnInputFinishListener() {

                    @Override
                    public void onInputFinish(String password) {

                        Toast.makeText(PayActivity.this, password, Toast.LENGTH_SHORT).show();
                    }
                });
                           */
                break;
            case R.id.pay_cancel:
                finish();
                break;
            case R.id.pay_sure:
                zfpwd = petPwd.getText().toString();
                OkHttpParam param = new OkHttpParam();
                if (TextUtils.isEmpty(AppData.Init().getLoginBean().getUid())) {
                    Toast.makeText(PayActivity.this, "用户的ID不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(zfpwd)) {
                    Toast.makeText(PayActivity.this, "请输入支付密码", Toast.LENGTH_SHORT).show();
                    return;
                }
                param.add("uid", AppData.Init().getLoginBean().getUid());
                param.add("zfpwd", zfpwd);
                _PostEntry(Urls.checkpwd, param, Urls.ActionId.checkpwd, true);
                break;
            case R.id.pay_input:
                break;
        }
    }

    @Override
    protected void onNetSuccess(int actionId, ResponseEntry result) {
        super.onNetSuccess(actionId, result);
        switch (actionId) {
            case Urls.ActionId.checkpwd:
                if (result.isSuccess()) {
                    System.out.println("000000000000000000000000000000000000000000");
                    submitData();
                } else {
                    Toast.makeText(this, result.getMsg(), Toast.LENGTH_SHORT).show();
                }
                break;
            case Urls.ActionId.withdrawapply:
                if (result.isSuccess()) {
                    startActivity(new Intent(PayActivity.this, MoneyActivity.class));
                }
                break;
            case Urls.ActionId.surrender:
                if (result.isSuccess()) {
                    startActivity(new Intent(PayActivity.this, MoneyActivity.class));
                }
                break;
            case Urls.ActionId.saveuserbond:
                if (result.isSuccess()) {
                    startActivity(new Intent(PayActivity.this, MoneyActivity.class));
                }
                break;
        }
    }

    private void submitData() {
        OkHttpParam param = new OkHttpParam();
        if (TextUtils.isEmpty(AppData.Init().getLoginBean().getUid())) {
            Toast.makeText(PayActivity.this, "用户的ID不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(zfpwd)) {
            Toast.makeText(PayActivity.this, "请输入支付密码", Toast.LENGTH_SHORT).show();
            return;
        }
        param.add("uid", AppData.Init().getLoginBean().getUid());
        param.add("zfpwd", zfpwd);
        if ("1".equals(type)) {
            if (TextUtils.isEmpty(payid)) {
                Toast.makeText(PayActivity.this, "支付方式的id不能为空", Toast.LENGTH_SHORT).show();
                return;
            }
            if (TextUtils.isEmpty(withdrawcash)) {
                Toast.makeText(PayActivity.this, "请输入提现金额", Toast.LENGTH_SHORT).show();
                return;
            }
            if (TextUtils.isEmpty(pay_type)) {
                Toast.makeText(PayActivity.this, "请选择支付方式", Toast.LENGTH_SHORT).show();
                return;
            }
            param.add("withdrawcash", withdrawcash);
            param.add("pay_type", pay_type);
            param.add("payid", payid);
            _PostEntry(Urls.withdrawapply, param, Urls.ActionId.withdrawapply, true);
        } else if ("2".equals(type)) {
            if (TextUtils.isEmpty(margin)) {
                Toast.makeText(PayActivity.this, "退款金额不能为空", Toast.LENGTH_SHORT).show();
                return;
            }
            param.add("margin", margin);
            _PostEntry(Urls.surrender, param, Urls.ActionId.surrender, true);
        } else if ("3".equals(type)) {
            if (TextUtils.isEmpty(recharge)) {
                Toast.makeText(PayActivity.this, "保证金金额不能为空", Toast.LENGTH_SHORT).show();
                return;
            }
            param.add("recharge", recharge);
            _PostEntry(Urls.saveuserbond, param, Urls.ActionId.saveuserbond, true);

        }
    }
}