package com.all.app.ui.activity;

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
import com.all.app.bean.MoneyDetailBean;
import com.all.app.configs.Urls;
import com.all.app.http.OkHttpParam;
import com.all.app.http.ResponseEntry;
import com.all.app.utils.FJson;
import com.all.app.utils.LeeTools;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//明细详情页面
public class MoneyDetailActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.re_title)
    RelativeLayout reTitle;
    @BindView(R.id.tv_jiaju)
    TextView tvJiaju;
    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.tv_yue)
    TextView tvYue;
    @BindView(R.id.tv_numder)
    TextView tvNumder;
    @BindView(R.id.tv_yu)
    TextView tvYu;
    String fina_id="";
    MoneyDetailBean moneyDetailBean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money_detail);
        ButterKnife.bind(this);
        fina_id=getIntent().getStringExtra("fina_id");
        loadData();
    }

    private void loadData() {
        OkHttpParam param = new OkHttpParam();
        if (TextUtils.isEmpty(AppData.Init().getLoginBean().getUid())) {
            Toast.makeText(MoneyDetailActivity.this, "用户的ID不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(fina_id)) {
            Toast.makeText(MoneyDetailActivity.this, "钱包详情ID不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        param.add("uid", AppData.Init().getLoginBean().getUid());
        param.add("fina_id", fina_id);
        _PostEntry(Urls.getdetailedinfo, param, Urls.ActionId.  getdetailedinfo, true);

    }

    @OnClick({R.id.iv_back, R.id.tv_title, R.id.re_title, R.id.tv_jiaju, R.id.tv_type, R.id.tv_yue})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_title:
                break;
            case R.id.re_title:
                break;
            case R.id.tv_jiaju:
                break;
            case R.id.tv_type:
                break;
            case R.id.tv_yue:
                break;
        }
    }

    @Override
    protected void onNetSuccess(int actionId, ResponseEntry result) {
        super.onNetSuccess(actionId, result);
        switch (actionId){
            case Urls.ActionId.getdetailedinfo:
                if(result.isSuccess()){
                    moneyDetailBean= FJson.getObject(result.getData().toString(),MoneyDetailBean.class);
                  if(moneyDetailBean!=null){
                      initView();
                  }
                }
                break;
        }
    }

    private void initView() {
        LeeTools.setText(tvJiaju,moneyDetailBean.getFina_cash());
        if(moneyDetailBean.getFina_type().equals("in")){
            LeeTools.setText(tvType,"收入");
        }else if(moneyDetailBean.getFina_type().equals("out")){
            LeeTools.setText(tvType,"支出");
        }
        LeeTools.setText(tvYue,moneyDetailBean.getFina_time());
        LeeTools.setText(tvNumder,moneyDetailBean.getFina_id());
        LeeTools.setText(tvYu,moneyDetailBean.getUser_balance());
    }
}
