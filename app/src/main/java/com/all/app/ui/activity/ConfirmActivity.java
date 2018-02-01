package com.all.app.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.Selection;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.all.app.R;
import com.all.app.app.AppData;
import com.all.app.base.BaseActivity;
import com.all.app.bean.GetBean;
import com.all.app.bean.GetBidBean;
import com.all.app.bean.QutaBean;
import com.all.app.configs.Urls;
import com.all.app.http.OkHttpParam;
import com.all.app.http.ResponseEntry;
import com.all.app.utils.ContainsEmojiEditText;
import com.all.app.utils.FJson;
import com.all.app.utils.LeeTools;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//确认中标价页面
public class ConfirmActivity extends BaseActivity implements TextWatcher {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.re_title)
    RelativeLayout reTitle;
    @BindView(R.id.tv_titl)
    TextView tvTitl;
    @BindView(R.id.tv_news1)
    TextView tvNews1;
    @BindView(R.id.iv_xiugai)
    ImageView ivXiugai;
    @BindView(R.id.tv_number)
    TextView tvNumber;
    @BindView(R.id.et_an)
    ContainsEmojiEditText etAn;
    @BindView(R.id.et_yun)
    ContainsEmojiEditText etYun;
    @BindView(R.id.et_yun2)
    ContainsEmojiEditText etYun2;
    @BindView(R.id.et_other)
    ContainsEmojiEditText etOther;
    @BindView(R.id.tv_anp)
    TextView tvAnp;
    @BindView(R.id.tv_yunp)
    TextView tvYunp;
    @BindView(R.id.tv_yun2p)
    TextView tvYun2p;
    @BindView(R.id.otherp)
    TextView otherp;
    @BindView(R.id.tv_zhong)
    TextView tvZhong;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.tv_danwei)
    TextView tvDanwei;
    @BindView(R.id.other_cancle)
    TextView otherCancle;
    @BindView(R.id.other_price)
    TextView otherPrice;
    @BindView(R.id.re_cancle)
    RelativeLayout reCancle;
    @BindView(R.id.re_xiu)
    RelativeLayout re_xiu;
    String task_id ="";
    String bid_id ="";
    String pricetype="";
    String installation_price="";
    String transportation_price="";
    String handling_price="";
    String other_price="";
    String quote="";
     String type2="";
    GetBidBean getBidBean;
    QutaBean qutaBean;
    String type="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);
         ButterKnife.bind(this);
        bid_id=getIntent().getStringExtra("bid_id");
        type2=getIntent().getStringExtra("type");
        task_id=getIntent().getStringExtra("task_id");
        loadData();
        pricetype="1";
        etAn.addTextChangedListener(this);
        etYun.addTextChangedListener(this);
        etYun2.addTextChangedListener(this);
        etOther.addTextChangedListener(this);
    }



    private void loadData() {
        OkHttpParam param = new OkHttpParam();
        param.add("uid", AppData.Init().getLoginBean().getUid());
        param.add("bidid", bid_id);
        _PostEntry(Urls.getbidprice, param, Urls.ActionId.getbidprice, true);

    }

    @OnClick({R.id.re_xiu,R.id.iv_back, R.id.tv_title, R.id.re_title, R.id.tv_titl, R.id.tv_news1, R.id.iv_xiugai, R.id.tv_number, R.id.et_an, R.id.et_yun, R.id.et_yun2, R.id.et_other, R.id.tv_anp, R.id.tv_yunp, R.id.tv_yun2p, R.id.otherp, R.id.tv_zhong, R.id.tv_price, R.id.tv_danwei, R.id.other_cancle, R.id.other_price, R.id.re_cancle})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
              finish();
                break;
            case R.id.re_xiu:
                etAn.setCursorVisible(true);
                otherCancle.setVisibility(View.VISIBLE);
                etAn.setFocusable(true);
                etAn.setFocusableInTouchMode(true);
                etAn.requestFocus();
                InputMethodManager inputManager =
                                 (InputMethodManager)etAn.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                        inputManager.showSoftInput(etAn, 0);

                etAn.setEnabled(true);
                etYun.setEnabled(true);
                etYun2.setEnabled(true);
                etOther.setEnabled(true);
                pricetype="2";
                otherPrice.setText("修改中标价");
                break;
            case R.id.tv_title:
                break;
            case R.id.re_title:
                break;
            case R.id.tv_titl:
                break;
            case R.id.tv_news1:
                break;
            case R.id.iv_xiugai:
                break;
            case R.id.tv_number:
                etAn.setCursorVisible(true);
                etAn.setEnabled(true);
                otherCancle.setVisibility(View.VISIBLE);
                etAn.setFocusable(true);
                etAn.setFocusableInTouchMode(true);
                etAn.requestFocus();
                InputMethodManager inputManager2 =
                        (InputMethodManager)etAn.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager2.showSoftInput(etAn, 0);
                etYun.setEnabled(true);
                etYun2.setEnabled(true);
                etOther.setEnabled(true);
                pricetype="2";
                otherPrice.setText("修改中标价");
                break;
            case R.id.et_an:
                etAn.setCursorVisible(true);
                etAn.setSelection(etAn.getText().length());//
                break;
            case R.id.et_yun:
                etYun.setCursorVisible(true);
                etYun.setSelection(etAn.getText().length());
                break;
            case R.id.et_yun2:
                etYun2.setCursorVisible(true);
                etYun2.setSelection(etAn.getText().length());
                break;
            case R.id.et_other:
                etOther.setCursorVisible(true);
                etOther.setSelection(etAn.getText().length());
                break;
            case R.id.tv_anp:
                break;
            case R.id.tv_yunp:
                break;
            case R.id.tv_yun2p:
                break;
            case R.id.otherp:
                break;
            case R.id.tv_zhong:
                break;
            case R.id.tv_price:
                break;
            case R.id.tv_danwei:
                break;
            case R.id.other_cancle:
                etAn.setCursorVisible(false);
                etOther.setCursorVisible(false);
                etYun.setCursorVisible(false);
                etYun2.setCursorVisible(false);
                otherCancle.setVisibility(View.GONE);
                otherPrice.setText("确认中标价");
                break;
            case R.id.other_price:
                if("1".equals(type2)){

                }else{
                    submitData();//提交确认中标价数据
                }
                break;
            case R.id.re_cancle:

                break;
        }
    }
    private void submitData() {
        installation_price=etAn.getText().toString();
        transportation_price=etYun.getText().toString();
        handling_price=etYun2.getText().toString();
        other_price=etOther.getText().toString();
        OkHttpParam param = new OkHttpParam();
        if (TextUtils.isEmpty(AppData.Init().getLoginBean().getUid())) {
            Toast.makeText(ConfirmActivity.this, "用户的ID不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(task_id)) {
            Toast.makeText(ConfirmActivity.this, "任务的ID不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(bid_id)) {
            Toast.makeText(ConfirmActivity.this, "中标任务的ID不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(installation_price)) {
            Toast.makeText(ConfirmActivity.this, "安装费不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(transportation_price)) {
            Toast.makeText(ConfirmActivity.this, "运输费不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(handling_price)) {
            Toast.makeText(ConfirmActivity.this, "搬运费不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(other_price)) {
            Toast.makeText(ConfirmActivity.this, "其它费用不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        param.add("uid", AppData.Init().getLoginBean().getUid());
        param.add("taskid", task_id);
        param.add("bidid", bid_id);
        if(otherPrice.getText().toString().equals("确认中标价")){
            param.add("pricetype","1");
        }else if(otherPrice.getText().toString().equals("修改中标价")){
            param.add("pricetype","2");
        }
        param.add("installation_price",installation_price);
        param.add("transportation_price",transportation_price);
        param.add("handling_price", handling_price);
        param.add("other_price", other_price);
        _PostEntry(Urls.updateorderprice, param, Urls.ActionId.updateorderprice, true);
    }

    @Override
    protected void onNetSuccess(int actionId, ResponseEntry result) {
        super.onNetSuccess(actionId, result);
      switch (actionId){
          case Urls.ActionId.updateorderprice:
                 if(result.isSuccess()){
                     Toast.makeText(this ,"任务报价确认成功",Toast.LENGTH_SHORT).show();
                      startActivity(new Intent(ConfirmActivity.this,AcceptActivity.class));
                 }
              break;
          case Urls.ActionId.getbidprice:
              if(result.isSuccess()){
                  getBidBean= FJson.getObject(result.getData().toString(), GetBidBean.class);
                  if(null!=getBidBean){
                      notifyView();
                  }
              }
              break;
      }
    }
    private void notifyView() {
        bid_id=getBidBean.getBid_id();
        task_id=getBidBean.getTask_id();
        LeeTools.setText(etAn,getBidBean.getInstallation_price());
        LeeTools.setText(etYun,getBidBean.getTransportation_price());
        LeeTools.setText(etYun2,getBidBean.getHandling_price());
        LeeTools.setText(etOther,getBidBean.getOther_price());
        LeeTools.setText(tvPrice, getBidBean.getQuote());
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        installation_price=etAn.getText().toString();
        transportation_price=etYun.getText().toString();
        handling_price=etYun2.getText().toString();
        other_price=etOther.getText().toString();
        if(TextUtils.isEmpty( installation_price)){
            installation_price = "0";
        }
        if(TextUtils.isEmpty(transportation_price)){
            transportation_price = "0";
        }
        if(TextUtils.isEmpty(handling_price)){
            handling_price = "0";
        }
        if(TextUtils.isEmpty(other_price)){
            other_price = "0";
        }

        tvPrice.setText(String.valueOf(Integer.parseInt(installation_price)
                    +Integer.parseInt(transportation_price)
                    +Integer.parseInt(handling_price)
                    +Integer.parseInt(other_price)));
        }

    @Override
    public void afterTextChanged(Editable s) {

    }


}
