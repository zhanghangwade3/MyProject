package com.all.app.ui.activity;

import android.content.Intent;
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
import com.all.app.utils.city.CityChoose2;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//编辑银行卡页面
public class EditBankActivity extends BaseActivity {
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
    @BindView(R.id.et_card_number)
    EditText etCardNumber;
    @BindView(R.id.ll_number)
    LinearLayout llNumber;
    @BindView(R.id.tv_hang)
    TextView tvHang;
    @BindView(R.id.ll_hang)
    LinearLayout llHang;
    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.hang_name)
    TextView hangName;
    @BindView(R.id.iv_right)
    ImageView ivRight;
    @BindView(R.id.hang_addres)
    RelativeLayout hangAddres;
    @BindView(R.id.adress)
    TextView adress;
    @BindView(R.id.et_kaihuhang)
    TextView etKaihuhang;
    @BindView(R.id.iv_right2)
    ImageView ivRight2;
    @BindView(R.id.ll_name)
    RelativeLayout llName;
    @BindView(R.id.zhi_name)
    EditText zhiName;
    @BindView(R.id.ll_zhi)
    LinearLayout llZhi;
    @BindView(R.id.hang_num)
    EditText hangNum;
    @BindView(R.id.hang_number)
    LinearLayout hangNumber;
    @BindView(R.id.et_zai_num)
    EditText etZaiNum;
    @BindView(R.id.bt_queren_tianjia)
    Button btQuerenTianjia;
    String text_name = "";
    String bank_name = "";
    String bank_full_name = "";
    String card_num = "";
    String bankaddress = "";
    String name = "";
    String province = "";
    String city = "";
    String realname = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_bank);
        ButterKnife.bind(this);
        name = getIntent().getStringExtra("name");
        bank_name = getIntent().getStringExtra("bank_name");
        realname = getIntent().getStringExtra("realname");
        hangName.setText(name);
        etCardNumber.setText(realname);
    }

    @OnClick({R.id.iv_back, R.id.tv_title, R.id.tv_shanchu, R.id.re_title, R.id.tv_ti, R.id.et_card_number, R.id.ll_number, R.id.tv_hang, R.id.ll_hang, R.id.tv, R.id.hang_name, R.id.iv_right, R.id.hang_addres, R.id.adress, R.id.et_kaihuhang, R.id.iv_right2, R.id.ll_name, R.id.zhi_name, R.id.ll_zhi, R.id.hang_num, R.id.hang_number, R.id.et_zai_num, R.id.bt_queren_tianjia})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_title:
                break;
            case R.id.tv_shanchu:
                break;
            case R.id.re_title:
                break;
            case R.id.tv_ti:
                break;
            case R.id.et_card_number:
                break;
            case R.id.ll_number:
                break;
            case R.id.tv_hang:
                break;
            case R.id.ll_hang:
                break;
            case R.id.tv:
                break;
            case R.id.hang_name:
                realname=etCardNumber.getText().toString();
                Intent intent=new Intent(EditBankActivity.this, BankCarListActivity.class);
                intent.putExtra("realname",realname);
                startActivity(intent);
                break;
            case R.id.iv_right:
                break;
            case R.id.hang_addres:
                break;
            case R.id.adress:
                break;
            case R.id.et_kaihuhang:
                CityChoose2 cityChoose2 = new CityChoose2(this);
                cityChoose2.showDialog();
                cityChoose2.setChooseCityListener(new CityChoose2.IChooseCityListener() {

                    @Override
                    public void onFinished(String provinceId, String provinceName,
                                           String cityId, String cityName) {
                        // TODO Auto-generated method stub
                        province = provinceName;
                        city = cityName;
                        etKaihuhang.setText(provinceName + cityName);
                        bankaddress = province + "," + city;
                    }
                });
                break;
            case R.id.iv_right2:
                break;
            case R.id.ll_name:
                break;
            case R.id.zhi_name:
                break;
            case R.id.ll_zhi:
                break;
            case R.id.hang_num:
                break;
            case R.id.hang_number:
                break;
            case R.id.et_zai_num:
                break;
            case R.id.bt_queren_tianjia:
                submitData();//提交银行卡数据
                break;
        }
    }

    private void submitData() {
        text_name = etCardNumber.getText().toString();
        bank_full_name = zhiName.getText().toString();
        card_num = hangNum.getText().toString();
        OkHttpParam param = new OkHttpParam();
        if (TextUtils.isEmpty(AppData.Init().getLoginBean().getUid())) {
            Toast.makeText(EditBankActivity.this, "用户的ID不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(text_name)) {
            Toast.makeText(EditBankActivity.this, "请选择开户行", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(bank_name)) {
            Toast.makeText(EditBankActivity.this, "请输入开户行", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(bank_full_name)) {
            Toast.makeText(EditBankActivity.this, "请输入开户行支行名称", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(card_num)) {
            Toast.makeText(EditBankActivity.this, "请输入卡号", Toast.LENGTH_SHORT).show();
            return;
        }
        if (card_num.length() < 15) {
            showTips("请输入正确的银行卡号", null);
            return;
        }
        if (TextUtils.isEmpty(bankaddress)) {
            Toast.makeText(EditBankActivity.this, "请输入开户行所在地址", Toast.LENGTH_SHORT).show();
            return;
        }
        param.add("uid", AppData.Init().getLoginBean().getUid());
        param.add("text_name", text_name);
        param.add("bank_name", bank_name);
        param.add("bank_full_name", bank_full_name);
        param.add("card_num", card_num);
        param.add("bankaddress", bankaddress);
        _PostEntry(Urls.savebankcard, param, Urls.ActionId.savebankcard, true);
    }

    @Override
    protected void onNetSuccess(int actionId, ResponseEntry result) {
        super.onNetSuccess(actionId, result);
        switch (actionId) {
            case Urls.ActionId.savebankcard:
                if (result.isSuccess()) {
                    Toast.makeText(this, "添加银行卡成功", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(EditBankActivity.this, BankTypeActivity.class));
                }
                break;
        }
    }
}
