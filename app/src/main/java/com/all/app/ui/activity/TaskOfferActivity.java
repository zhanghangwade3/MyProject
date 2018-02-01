package com.all.app.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.all.app.R;
import com.all.app.app.AppData;
import com.all.app.base.BaseActivity;
import com.all.app.configs.Urls;
import com.all.app.http.OkHttpParam;
import com.all.app.http.ResponseEntry;
import com.all.app.utils.ContainsEmojiEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//任务报价页面
public class TaskOfferActivity extends BaseActivity implements TextWatcher {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.re_title)
    RelativeLayout reTitle;
    @BindView(R.id.v1)
    View v1;
    @BindView(R.id.tv_news1)
    TextView tvNews1;
    @BindView(R.id.re_price)
    RelativeLayout rePrice;
    @BindView(R.id.v2)
    View v2;
    @BindView(R.id.et_time)
    ContainsEmojiEditText et_time;
    @BindView(R.id.et_an)
    ContainsEmojiEditText etAn;
    @BindView(R.id.et_yun)
    ContainsEmojiEditText etYun;
    @BindView(R.id.et_yun2)
    ContainsEmojiEditText etYun2;
    @BindView(R.id.et_other)
    ContainsEmojiEditText etOther;
    @BindView(R.id.shuru)
    LinearLayout shuru;
    @BindView(R.id.tv_anp)
    TextView tvAnp;
    @BindView(R.id.tv_yunp)
    TextView tvYunp;
    @BindView(R.id.tv_yun2p)
    TextView tvYun2p;
    @BindView(R.id.otherp)
    TextView otherp;
    @BindView(R.id.he)
    LinearLayout he;
    @BindView(R.id.tv_zhong)
    TextView tvZhong;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.tv_danwei)
    TextView tvDanwei;
    @BindView(R.id.re_he)
    RelativeLayout reHe;
    @BindView(R.id.tv_miaoshu)
    TextView tvMiaoshu;
    @BindView(R.id.et_content)
    ContainsEmojiEditText etContent;
    @BindView(R.id.tv_submit)
    TextView tv_submit;
    String cycle = "";
    String installation_price = "";
    String transportation_price = "";
    String handling_price = "";
    String other_price = "";
    String message = "";
    String address = "陕西省,西安市,高新区";
    String taskid = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_offer);
        ButterKnife.bind(this);
        taskid = getIntent().getStringExtra("taskid");
        etAn.addTextChangedListener(this);
        etYun.addTextChangedListener(this);
        etYun2.addTextChangedListener(this);
        etOther.addTextChangedListener(this);
    }

    @OnClick({R.id.et_content, R.id.et_time, R.id.tv_submit, R.id.iv_back, R.id.tv_title, R.id.re_title, R.id.v1, R.id.tv_news1, R.id.re_price, R.id.v2, R.id.et_an, R.id.et_yun, R.id.et_yun2, R.id.et_other, R.id.shuru, R.id.tv_anp, R.id.tv_yunp, R.id.tv_yun2p, R.id.otherp, R.id.he, R.id.tv_zhong})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_submit:
                submintPrice();
                break;
            case R.id.et_time:
                et_time.setCursorVisible(true);
                et_time.setHint("");
                break;
            case R.id.et_content:
                etContent.setCursorVisible(true);
                etContent.setHint("");
                break;
            case R.id.tv_title:
                break;
            case R.id.re_title:
                break;
            case R.id.v1:
                break;
            case R.id.tv_news1:
                break;
            case R.id.re_price:
                break;
            case R.id.v2:
                break;
            case R.id.et_an:
                etAn.setCursorVisible(true);
                etAn.setSelection(etAn.getText().length());//
                break;
            case R.id.et_yun:
                etYun.setCursorVisible(true);
                //etYun.setSelection(etAn.getText().length());
                break;
            case R.id.et_yun2:
                etYun2.setCursorVisible(true);
                //etYun2.setSelection(etAn.getText().length());
                break;
            case R.id.et_other:
                etOther.setCursorVisible(true);
                //etOther.setSelection(etAn.getText().length());
                break;
            case R.id.shuru:
                break;
            case R.id.tv_anp:
                break;
            case R.id.tv_yunp:
                break;
            case R.id.tv_yun2p:
                break;
            case R.id.otherp:
                break;
            case R.id.he:
                break;
            case R.id.tv_zhong:
                break;
        }
    }

    //提交任务报价
    private void submintPrice() {
        cycle = et_time.getText().toString();
        installation_price = etAn.getText().toString();
        transportation_price = etYun.getText().toString();
        handling_price = etYun2.getText().toString();
        other_price = etOther.getText().toString();
        if("".equals(installation_price)){
            installation_price="0";
        }
        if("".equals(transportation_price )){
            transportation_price ="0";
        }
        if("".equals(handling_price )){
            handling_price ="0";
        }
        if("".equals(other_price)){
            other_price ="0";
        }
        message = etContent.getText().toString();
        OkHttpParam param = new OkHttpParam();
        if (TextUtils.isEmpty(AppData.Init().getLoginBean().getUid())) {
            Toast.makeText(this, "用户id不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(taskid)) {
            Toast.makeText(this, "订单id不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(cycle)) {
            Toast.makeText(this, "请输入安装周期", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(installation_price)) {
            Toast.makeText(this, "请输入报价", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(address)) {
            Toast.makeText(this, "请输入当前地址", Toast.LENGTH_SHORT).show();
            return;
        }
        param.add("uid", AppData.Init().getLoginBean().getUid());
        param.add("taskid", taskid);
        param.add("cycle", cycle);
        param.add("installation_price", installation_price);
        param.add("transportation_price", transportation_price);
        param.add("handling_price", handling_price);
        param.add("other_price", other_price);
        param.add("message", message);
        param.add("address", address);
        _PostEntry(Urls.orderprice, param, Urls.ActionId.orderprice, true);
    }


    @Override
    protected void onNetSuccess(int actionId, ResponseEntry result) {
        super.onNetSuccess(actionId, result);
        switch (actionId) {
            case Urls.ActionId.orderprice:
                if (result.isSuccess()) {
                    Toast.makeText(this, "任务报价成功", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(TaskOfferActivity.this, OrderDetailsActivity.class);
                    intent.putExtra("data-id", taskid);
                    startActivity(intent);
                }
                break;

        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        installation_price = etAn.getText().toString();
        transportation_price = etYun.getText().toString();
        handling_price = etYun2.getText().toString();
        other_price = etOther.getText().toString();
        if (TextUtils.isEmpty(installation_price)) {
            installation_price = "0";
        }
        if (TextUtils.isEmpty(transportation_price)) {
            transportation_price = "0";
        }
        if (TextUtils.isEmpty(handling_price)) {
            handling_price = "0";
        }
        if (TextUtils.isEmpty(other_price)) {
            other_price = "0";
        }

        tvPrice.setText(String.valueOf(Integer.parseInt(installation_price)
                + Integer.parseInt(transportation_price)
                + Integer.parseInt(handling_price)
                + Integer.parseInt(other_price)));
    }


    @Override
    public void afterTextChanged(Editable s) {

    }
}
