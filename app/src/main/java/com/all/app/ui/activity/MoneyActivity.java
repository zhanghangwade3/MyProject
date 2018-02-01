package com.all.app.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.all.app.R;
import com.all.app.app.AppData;
import com.all.app.base.BaseActivity;
import com.all.app.bean.MoneyHomeBean;
import com.all.app.configs.Urls;
import com.all.app.http.OkHttpParam;
import com.all.app.http.ResponseEntry;
import com.all.app.utils.FJson;
import com.all.app.utils.LeeTools;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//我的钱包页面
public class MoneyActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.re_title)
    RelativeLayout reTitle;
    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.tv_biao)
    TextView tvBiao;
    @BindView(R.id.tv_mnumber)
    TextView tvMnumber;
    @BindView(R.id.tv_danwei)
    TextView tvDanwei;
    @BindView(R.id.tv_detail)
    TextView tvDetail;
    @BindView(R.id.tv_total)
    TextView tvTotal;
    @BindView(R.id.tv_number)
    TextView tvNumber;
    @BindView(R.id.tv_ti)
    TextView tvTi;
    @BindView(R.id.tv_tim)
    TextView tvTim;
    @BindView(R.id.tv_tixian)
    TextView tvTixian;
    @BindView(R.id.tv_pay)
    TextView tv_pay;
    @BindView(R.id.tv_ji)
    TextView tv_ji;
    @BindView(R.id.iv_money)
    ImageView ivMoney;
    @BindView(R.id.textView2)
    TextView textView2;
    @BindView(R.id.iv_right2)
    ImageView ivRight2;
    @BindView(R.id.re_money)
    RelativeLayout reMoney;
    @BindView(R.id.re_moneyde)
    RelativeLayout re_moneyde;
    @BindView(R.id.iv_service)
    ImageView ivService;
    @BindView(R.id.iv_right3)
    ImageView ivRight3;
    @BindView(R.id.re_renzheng)
    RelativeLayout reRenzheng;
    @BindView(R.id.iv_secret)
    ImageView ivSecret;
    @BindView(R.id.re_service)
    RelativeLayout reService;
    @BindView(R.id.iv_parter)
    ImageView ivParter;
    @BindView(R.id.re_pater)
    RelativeLayout rePater;
    MoneyHomeBean moneyHomeBean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money);
        ButterKnife.bind(this);
         loadData();

    }

    private void loadData() {
        OkHttpParam param = new OkHttpParam();
        if (TextUtils.isEmpty(AppData.Init().getLoginBean().getUid())) {
            Toast.makeText(MoneyActivity.this, "用户的ID不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        param.add("uid", AppData.Init().getLoginBean().getUid());
        _PostEntry(Urls.mypurse, param, Urls.ActionId.mypurse, true);

    }

    @OnClick({R.id.re_moneyde,R.id.re_service,R.id.re_renzheng,R.id.iv_right3,R.id.tv_pay,R.id.iv_back, R.id.tv_tixian, R.id.re_money, R.id.tv_money, R.id.tv_biao, R.id.tv_mnumber, R.id.tv_danwei, R.id.tv_detail, R.id.tv_total, R.id.tv_number, R.id.tv_ti, R.id.tv_tim})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_pay:
                 startActivity(new Intent(MoneyActivity.this, ModifyPwActivity.class));
                break;
            case R.id.re_moneyde:
                startActivity(new Intent(this,DetailMoneyActivity.class));
                break;
            case R.id.iv_back:
                Intent intent=new Intent(MoneyActivity.this,MainActivity.class);
                String type="1";
                intent.putExtra("type",type);
                startActivity(intent);
                break;
            case R.id.re_renzheng:
                startActivity(new Intent(MoneyActivity.this, ModifyPwActivity.class));
                break;
            case R.id.iv_right3:
                startActivity(new Intent(MoneyActivity.this, ModifyPwActivity.class));
                break;
            case R.id.tv_tixian:
                startActivity(new Intent(MoneyActivity.this,WithdrawActivity.class));
                break;
            case R.id.re_money:
                startActivity(new Intent(MoneyActivity.this,BankTypeActivity.class));
                break;
            case R.id.tv_money:
                break;
            case R.id.re_service:
                startActivity(new Intent(MoneyActivity.this,SafeActivity.class));
                break;
            case R.id.tv_biao:
                break;
            case R.id.tv_mnumber:
                break;
            case R.id.tv_danwei:
                break;
            case R.id.tv_detail:
                startActivity(new Intent(this,DetailMoneyActivity.class));
                break;
            case R.id.tv_total:
                break;
            case R.id.tv_number:
                break;
            case R.id.tv_ti:
                break;
            case R.id.tv_tim:
                break;
        }
    }

    @Override
    protected void onNetSuccess(int actionId, ResponseEntry result) {
        super.onNetSuccess(actionId, result);
        switch (actionId){
            case Urls.ActionId.mypurse:
                if(result.isSuccess()){
                    moneyHomeBean= FJson.getObject(result.getData().toString(),MoneyHomeBean.class);
                   if( moneyHomeBean!=null){
                        initView();
                   }

                }

                break;
        }
    }

    private void initView() {
        LeeTools.setText(tvMnumber, moneyHomeBean.getBalance());
        LeeTools.setText(tvNumber, moneyHomeBean.getTotal()+"元");
        LeeTools.setText(tvTim, moneyHomeBean.getTxprice()+"元");
         if("1".equals(moneyHomeBean.getPwdtatus())){
             tv_pay.setText("支付密码和登录密码一致");
         }
         if("0".equals(moneyHomeBean.getMargin())){
             tv_ji.setText("暂未缴纳");
         }else{
             LeeTools.setText(tv_ji, moneyHomeBean.getMargin());
        System.out.println("99999999999999999999999999999999999999999"+moneyHomeBean.getMargin());

         }
    }
}
