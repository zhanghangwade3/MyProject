package com.all.app.ui.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddAlipayActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_shanchu)
    TextView tvShanchu;
    @BindView(R.id.re_title)
    RelativeLayout reTitle;
    @BindView(R.id.tv_ti)
    TextView tvTi;
    @BindView(R.id.bao_number)
    EditText baoNumber;
    @BindView(R.id.ll_number)
    LinearLayout llNumber;
    @BindView(R.id.tv_hang)
    TextView tvHang;
    @BindView(R.id.ll_hang)
    LinearLayout llHang;
    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.bao_number2)
    EditText baoNumber2;
    @BindView(R.id.et_ni_num)
    EditText et_ni_num;
    @BindView(R.id.hang_addres)
    RelativeLayout hangAddres;
    @BindView(R.id.et_zai_num)
    EditText etZaiNum;
    @BindView(R.id.bt_queren_tianjia)
    Button btQuerenTianjia;
     String account="";
     String account2="";
     String real_name="";
     String nickname="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_alipay);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.et_ni_num,R.id.iv_back, R.id.tv_title, R.id.tv_shanchu, R.id.re_title, R.id.tv_ti, R.id.bao_number, R.id.ll_number, R.id.tv_hang, R.id.ll_hang, R.id.tv, R.id.bao_number2, R.id.hang_addres, R.id.et_zai_num, R.id.bt_queren_tianjia})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_title:
                break;
            case R.id.et_ni_num:
                break;
            case R.id.tv_shanchu:
                break;
            case R.id.re_title:
                break;
            case R.id.tv_ti:
                break;
            case R.id.bao_number:
                break;
            case R.id.ll_number:
                break;
            case R.id.tv_hang:
                break;
            case R.id.ll_hang:
                break;
            case R.id.tv:
                break;
            case R.id.bao_number2:
                break;
            case R.id.hang_addres:
                break;
            case R.id.et_zai_num:
                break;
            case R.id.bt_queren_tianjia:
                submitAlipay();//添加支付宝
                break;
        }
    }

      private void submitAlipay() {
          account=baoNumber.getText().toString();
          account2=baoNumber2.getText().toString();
          real_name=etZaiNum.getText().toString();
          nickname=et_ni_num.getText().toString();
          OkHttpParam param = new OkHttpParam();
          if (TextUtils.isEmpty(AppData.Init().getLoginBean().getUid())) {
              Toast.makeText(AddAlipayActivity.this, "用户的ID不能为空", Toast.LENGTH_SHORT).show();
              return;
          }

          if (TextUtils.isEmpty(nickname)) {
              Toast.makeText(AddAlipayActivity.this, "请输入支付宝昵称", Toast.LENGTH_SHORT).show();
              return;
          }
          if (TextUtils.isEmpty(real_name)) {
              Toast.makeText(AddAlipayActivity.this, "请输入支付宝姓名", Toast.LENGTH_SHORT).show();
              return;
          }

          param.add("uid", AppData.Init().getLoginBean().getUid());
          param.add("account", account);
          param.add("nickname",nickname);
          param.add("real_name",real_name);
          _PostEntry(Urls.savealipayjs, param, Urls.ActionId.savealipayjs, true);
    }

    @Override
    protected void onNetSuccess(int actionId, ResponseEntry result) {
        super.onNetSuccess(actionId, result);
        switch (actionId){
            case Urls.ActionId.savealipayjs:
                if(result.isSuccess()){
                    Toast.makeText(this,"添加支付宝成功",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
