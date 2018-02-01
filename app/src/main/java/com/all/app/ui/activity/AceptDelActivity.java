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
import com.all.app.bean.AcceptBean;
import com.all.app.configs.Urls;
import com.all.app.http.OkHttpParam;
import com.all.app.http.ResponseEntry;
import com.all.app.utils.FJson;
import com.all.app.utils.LeeTools;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AceptDelActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.re_title)
    RelativeLayout reTitle;
    @BindView(R.id.tv_news1)
    TextView tvNews1;
    @BindView(R.id.tv_news4)
    TextView tvNews4;
    @BindView(R.id.tv_number4)
    TextView tvNumber4;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_gao3)
    TextView tvGao3;
    @BindView(R.id.tv_gao5)
    TextView tvGao5;
    @BindView(R.id.tv_gao6)
    TextView tvGao6;
    @BindView(R.id.tv_gao7)
    TextView tvGao7;
    @BindView(R.id.tv_gao8)
    TextView tvGao8;
    String msg_id = "";
    AcceptBean acceptBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acept_del);
        ButterKnife.bind(this);
        msg_id = getIntent().getStringExtra("msg_id");
        loadData();
    }

    private void loadData() {
        OkHttpParam param = new OkHttpParam();
        if (TextUtils.isEmpty(AppData.Init().getLoginBean().getUid())) {
            Toast.makeText(AceptDelActivity.this, "用户的ID不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(msg_id)) {
            Toast.makeText(AceptDelActivity.this, "消息的ID不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        param.add("uid", AppData.Init().getLoginBean().getUid());
        param.add("msgid", msg_id);
        param.add("source", "app");
        _PostEntry(Urls.msginfo, param, Urls.ActionId.msginfo, true);

    }

    @OnClick({R.id.iv_back, R.id.tv_title, R.id.re_title, R.id.tv_news1, R.id.tv_news4, R.id.tv_number4, R.id.tv_time, R.id.tv_gao3, R.id.tv_gao5, R.id.tv_gao6, R.id.tv_gao7, R.id.tv_gao8})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_title:
                break;
            case R.id.re_title:
                break;
            case R.id.tv_news1:
                break;
            case R.id.tv_news4:
                break;
            case R.id.tv_number4:
                break;
            case R.id.tv_time:
                break;
            case R.id.tv_gao3:
                break;
            case R.id.tv_gao5:
                break;
            case R.id.tv_gao6:
                break;
            case R.id.tv_gao7:
                break;
            case R.id.tv_gao8:
                startActivity(new Intent(AceptDelActivity.this, SendMesgActivity.class));
                break;
        }
    }

    @Override
    protected void onNetSuccess(int actionId, ResponseEntry result) {
        super.onNetSuccess(actionId, result);
        switch (actionId) {
            case Urls.ActionId.msginfo:
                if (result.isSuccess()) {
                    System.out.println("7777777777777777777777777777777777777"+result);
                    acceptBean = FJson.getObject(result.getData().toString(), AcceptBean.class);
                    if (acceptBean != null) {
                        initView();
                    }
                }
                break;
        }
    }

    private void initView() {
        LeeTools.setText(tvNews1, acceptBean.getContent());
        LeeTools.setText(tvNumber4, acceptBean.getTo_username());
        LeeTools.setText(tvGao3, acceptBean.getOn_time());
        LeeTools.setText(tvGao6, acceptBean.getSource());
        LeeTools.setText(tvNews1, acceptBean.getContent());
    }
}
