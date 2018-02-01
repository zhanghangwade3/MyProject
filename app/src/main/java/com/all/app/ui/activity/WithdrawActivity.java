package com.all.app.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.all.app.R;
import com.all.app.app.AppData;
import com.all.app.base.BaseActivity;
import com.all.app.bean.NumberBean;
import com.all.app.configs.Urls;
import com.all.app.http.OkHttpParam;
import com.all.app.http.ResponseEntry;
import com.all.app.utils.ContainsEmojiEditText;
import com.all.app.utils.DialogWidget;
import com.all.app.utils.FJson;
import com.all.app.utils.PwdEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//提现页面
public class WithdrawActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.re_title)
    RelativeLayout reTitle;
    @BindView(R.id.textVi1)
    TextView textVi1;
    @BindView(R.id.iv_right2)
    ImageView ivRight2;
    @BindView(R.id.re_bank)
    RelativeLayout reBank;
    @BindView(R.id.text)
    TextView text;
    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.tv_ti)
    TextView tvTi;
    @BindView(R.id.re_money)
    RelativeLayout reMoney;
    @BindView(R.id.textV)
    TextView textV;
    @BindView(R.id.et_carNumber)
    ContainsEmojiEditText etCarNumber;
    @BindView(R.id.re_mon)
    RelativeLayout reMon;
    @BindView(R.id.iv_ti)
    ImageView ivTi;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.tv_get)
    TextView tv_get;

    @BindView(R.id.re_ti)
    RelativeLayout reTi;
    DialogWidget mDialogWidget;
    NumberBean numberBean;
    String balance = "";
    String bank_name = "";
    String account = "";
    String single = "";
    String pay_type="";
    String payid="";
    String withdrawcash="";
    String type="1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tixian);
        ButterKnife.bind(this);
        bank_name = getIntent().getStringExtra("bank_name");
        account = getIntent().getStringExtra("account");
        single=getIntent().getStringExtra("single");
        if("3".equals(single)){
            textVi1.setText(bank_name);
            pay_type="onlinebank";
            payid= getIntent().getStringExtra("bank_id");;
        }else if("2".equals(single)){
            textVi1.setText(account);
            pay_type="alipayjs";
            payid=getIntent().getStringExtra("alipayjs_a_id");;
        }
        loadMoneyNumber();
    }

    private void loadMoneyNumber() {
        OkHttpParam param = new OkHttpParam();
        param.add("uid", AppData.Init().getLoginBean().getUid());
        _PostEntry(Urls.getuserbalance, param, Urls.ActionId.getuserbalance, true);

    }

    @OnClick({R.id.tv_get, R.id.iv_back, R.id.tv_title, R.id.re_title, R.id.textVi1, R.id.iv_right2, R.id.re_bank, R.id.text, R.id.tv_money, R.id.tv_ti, R.id.re_money, R.id.textV, R.id.et_carNumber, R.id.re_mon, R.id.iv_ti, R.id.tv_content, R.id.re_ti})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_get:
                withdrawcash=etCarNumber.getText().toString();
                Intent intent=new Intent(WithdrawActivity.this, PayActivity.class);
                intent.putExtra("pay_type", pay_type);
                intent.putExtra("payid", payid);
                intent.putExtra("balance", balance);
                intent.putExtra("withdrawcash",withdrawcash);
                intent.putExtra("type", type);
                startActivity(intent);
                break;
            case R.id.tv_title:
                break;
            case R.id.re_title:
                break;
            case R.id.textVi1:
                break;
            case R.id.iv_right2:
                break;
            case R.id.re_bank:
                startActivity(new Intent(WithdrawActivity.this, BankTypeActivity.class));
                break;
            case R.id.text:
                break;
            case R.id.tv_money:
                break;
            case R.id.tv_ti:
                withdrawcash=tvMoney.getText().toString();
                Intent intent2=new Intent(WithdrawActivity.this, PayActivity.class);
                intent2.putExtra("pay_type", pay_type);
                intent2.putExtra("payid", payid);
                intent2.putExtra("balance", balance);
                intent2.putExtra("withdrawcash",withdrawcash);
                intent2.putExtra("type", type);
                startActivity(intent2);
                break;
            case R.id.re_money:
                break;
            case R.id.textV:
                break;
            case R.id.et_carNumber:
                break;
            case R.id.re_mon:
                break;
            case R.id.iv_ti:
                break;
            case R.id.tv_content:
                break;
            case R.id.re_ti:
                break;
        }
    }
    @Override
    protected void onNetSuccess(int actionId, ResponseEntry result) {
        super.onNetSuccess(actionId, result);
        switch (actionId) {
            case Urls.ActionId.getuserbalance:
                if (result.isSuccess()) {
                    numberBean = FJson.getObject(result.getData().toString(), NumberBean.class);
                    if (numberBean != null) {
                        balance = numberBean.getBalance();
                        tvMoney.setText(balance);
                    }
                }
                break;
        }
    }
}

