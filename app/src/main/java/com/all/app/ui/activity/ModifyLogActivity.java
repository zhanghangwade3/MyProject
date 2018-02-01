package com.all.app.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ModifyLogActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.re_title)
    RelativeLayout reTitle;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.imagec)
    ImageView imagec;
    @BindView(R.id.rl_username_del)
    RelativeLayout rlUsernameDel;
    @BindView(R.id.re_phone)
    RelativeLayout rePhone;
    @BindView(R.id.et_ma)
    EditText etMa;
    @BindView(R.id.re_ma)
    RelativeLayout reMa;
    @BindView(R.id.et_zheng)
    EditText etZheng;
    @BindView(R.id.re_sh)
    RelativeLayout reSh;
    @BindView(R.id.tv_tijiao)
    TextView tvTijiao;
    String oldPassWord="";
    String newPassWord="";
    String newPassWorda="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_log);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_back, R.id.tv_title, R.id.re_title, R.id.et_password, R.id.imagec, R.id.rl_username_del, R.id.re_phone, R.id.et_ma, R.id.re_ma, R.id.et_zheng, R.id.re_sh, R.id.tv_tijiao})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_title:
                break;
            case R.id.re_title:
                break;
            case R.id.et_password:
                break;
            case R.id.imagec:
                break;
            case R.id.rl_username_del:
                break;
            case R.id.re_phone:
                break;
            case R.id.et_ma:
                break;
            case R.id.re_ma:
                break;
            case R.id.et_zheng:
                break;
            case R.id.re_sh:
                break;
            case R.id.tv_tijiao:
                submitPassWord();//提交密码
                break;
        }
    }

    private void submitPassWord() {
        oldPassWord=etPassword.getText().toString();
        newPassWord=etMa.getText().toString();
        newPassWorda=etZheng.getText().toString();
        OkHttpParam param=new OkHttpParam();
        if (TextUtils.isEmpty(AppData.Init().getLoginBean().getUid())) {
            Toast.makeText(this, "用户id不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(oldPassWord)) {
            Toast.makeText(this, "请输入旧密码", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(newPassWord)) {
            Toast.makeText(this, "请输入新密码", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!newPassWord.equals(newPassWorda)) {
            showTips("您两次输入的密码不一致，请重新输入", null);
            etZheng.setText("");
            return;
        }

        param.add("uid", AppData.Init().getLoginBean().getUid());
        param.add("taskid", oldPassWord);
        param.add("cycle", newPassWord);
        _PostEntry(Urls.updatepwd, param, Urls.ActionId.updatepwd, true);

    }

    @Override
    protected void onNetSuccess(int actionId, ResponseEntry result) {
        super.onNetSuccess(actionId, result);
         switch (actionId){
             case Urls.ActionId.updatepwd:
                  if(result.isSuccess()){
                      Toast.makeText(this,"修改密码成功",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(ModifyLogActivity.this,LoginActivity.class));
                  }
                 break;
         }
    }
}
