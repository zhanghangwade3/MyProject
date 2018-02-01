package com.all.app.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.all.app.R;
import com.all.app.app.AppData;
import com.all.app.base.BaseActivity;
import com.all.app.bean.BaoBean;
import com.all.app.configs.Urls;
import com.all.app.http.OkHttpParam;
import com.all.app.http.ResponseEntry;
import com.all.app.utils.FJson;
import com.all.app.utils.LeeTools;
import com.all.app.utils.XGlide;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SafeActivity extends BaseActivity {


    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.re_title)
    RelativeLayout reTitle;
    @BindView(R.id.iv_icon)
    ImageView ivIcon;
    @BindView(R.id.tv_jing)
    TextView tvJing;
    @BindView(R.id.tv_mony)
    TextView tvMony;
    @BindView(R.id.tv_detail)
    TextView tvDetail;
    @BindView(R.id.re_moneyde)
    RelativeLayout reMoneyde;
    @BindView(R.id.ck_1)
    CheckBox ck1;
    @BindView(R.id.iv2)
    ImageView iv2;
    @BindView(R.id.tv_yin1)
    TextView tvYin1;
    @BindView(R.id.tv_yin2)
    TextView tvYin2;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.re_1)
    RelativeLayout re1;
    @BindView(R.id.ck_2)
    CheckBox ck2;
    @BindView(R.id.iv3)
    ImageView iv3;
    @BindView(R.id.tv_yin3)
    TextView tvYin3;
    @BindView(R.id.tv_content2)
    TextView tvContent2;
    @BindView(R.id.re_2)
    RelativeLayout re2;
    @BindView(R.id.ck_3)
    CheckBox ck3;
    @BindView(R.id.iv4)
    ImageView iv4;
    @BindView(R.id.tv_yin5)
    TextView tvYin5;
    @BindView(R.id.tv_yin6)
    TextView tvYin6;
    @BindView(R.id.tv_content3)
    TextView tvContent3;
    @BindView(R.id.re_3)
    RelativeLayout re3;
    @BindView(R.id.ck_4)
    CheckBox ck4;
    @BindView(R.id.iv5)
    ImageView iv5;
    @BindView(R.id.tv_yin7)
    TextView tvYin7;
    @BindView(R.id.tv_yin8)
    TextView tvYin8;
    @BindView(R.id.tv_content4)
    TextView tvContent4;
    @BindView(R.id.re_4)
    RelativeLayout re4;
    @BindView(R.id.ck_5)
    CheckBox ck5;
    @BindView(R.id.iv6)
    ImageView iv6;
    @BindView(R.id.tv_yin9)
    TextView tvYin9;
    @BindView(R.id.tv_yin10)
    TextView tvYin10;
    @BindView(R.id.tv_content5)
    TextView tvContent5;
    @BindView(R.id.re_5)
    RelativeLayout re5;
    @BindView(R.id.re_title2)
    RelativeLayout re_title2;
    @BindView(R.id.iv_xie)
    ImageView ivXie;
    @BindView(R.id.tv_xie)
    TextView tvXie;
    @BindView(R.id.re_xie)
    RelativeLayout reXie;
    @BindView(R.id.tv_bao)
    TextView tvBao;
    @BindView(R.id.iv_jin)
    ImageView ivJin;
    @BindView(R.id.tv_jingc)
    TextView tvJingc;
    @BindView(R.id.tv_jingc2)
    TextView tvJingc2;
    @BindView(R.id.tv_jingc3)
    TextView tvJingc3;
    @BindView(R.id.tv_jingc4)
    TextView tvJingc4;
    @BindView(R.id.tv_jingc5)
    TextView tvJingc5;
    @BindView(R.id.tv_yin30)
    TextView tv_yin30;
    @BindView(R.id.iv)
    ImageView iv;
    BaoBean baoBean;
    String balance = "";
    String margin = "";
    String type = "2";
    String recharge="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safe);
        ButterKnife.bind(this);
        loadData();//保证金数据
    }

    private void loadData() {
        OkHttpParam param = new OkHttpParam();
        if (TextUtils.isEmpty(AppData.Init().getLoginBean().getUid())) {
            Toast.makeText(SafeActivity.this, "用户的ID不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        param.add("uid", AppData.Init().getLoginBean().getUid());
        _PostEntry(Urls.getuserbond, param, Urls.ActionId.getuserbond, true);
    }


    @OnClick({R.id.iv_back, R.id.tv_title, R.id.re_title, R.id.iv_icon, R.id.tv_jing, R.id.tv_mony, R.id.tv_detail, R.id.re_moneyde,R.id.ck_1, R.id.iv2, R.id.tv_yin1, R.id.tv_yin2, R.id.tv_content, R.id.re_1, R.id.ck_2, R.id.iv3, R.id.tv_yin3,R.id.tv_content2, R.id.re_2, R.id.ck_3, R.id.iv4, R.id.tv_yin5, R.id.tv_yin6, R.id.tv_content3, R.id.re_3, R.id.ck_4, R.id.iv5, R.id.tv_yin7, R.id.tv_yin8, R.id.tv_content4, R.id.re_4, R.id.ck_5, R.id.iv6, R.id.tv_yin9, R.id.tv_yin10, R.id.tv_content5, R.id.re_5, R.id.iv_xie, R.id.tv_xie, R.id.re_xie, R.id.tv_bao, R.id.iv_jin, R.id.tv_jingc, R.id.tv_jingc2, R.id.tv_jingc3, R.id.tv_jingc4, R.id.tv_jingc5, R.id.iv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_title:
                break;
            case R.id.re_title:
                break;
            case R.id.iv_icon:
                break;
            case R.id.tv_jing:
                break;
            case R.id.tv_mony:
                break;
            case R.id.tv_detail:
                margin = tvMony.getText().toString();
                Intent intent2 = new Intent(SafeActivity.this, PayActivity.class);
                intent2.putExtra("margin", margin);
                intent2.putExtra("type", type);
                startActivity(intent2);
                break;
            case R.id.re_moneyde:
                margin = tvMony.getText().toString();
                Intent intent = new Intent(SafeActivity.this, PayActivity.class);
                intent.putExtra("margin", margin);
                intent.putExtra("type", type);
                startActivity(intent);
                break;
            case R.id.re_title2:
                break;
            case R.id.ck_1:
                break;
            case R.id.iv2:
                break;
            case R.id.tv_yin1:
                break;
            case R.id.tv_yin2:
                break;
            case R.id.tv_content:
                break;
            case R.id.re_1:
                XGlide.init(this).display(ivIcon,R.drawable.yinone);
                ck1.setChecked(true);
                ck2.setChecked(false);
                ck3.setChecked(false);
                ck4.setChecked(false);
                ck5.setChecked(false);
                ck1.setEnabled(true);
                ck2.setEnabled(false);
                ck3.setEnabled(false);
                ck4.setEnabled(false);
                ck5.setEnabled(false);
                recharge= tvYin2.getText().toString();
                break;
            case R.id.ck_2:
                break;
            case R.id.iv3:
                break;
            case R.id.tv_yin3:
                break;
            case R.id.tv_content2:
                break;
            case R.id.re_2:
                XGlide.init(this).display(ivIcon,R.drawable.yintwo);
                ck1.setChecked(false);
                ck2.setChecked(true);
                ck3.setChecked(false);
                ck4.setChecked(false);
                ck5.setChecked(false);
                ck1.setEnabled(false);
                ck2.setEnabled(true);
                ck3.setEnabled(false);
                ck4.setEnabled(false);
                ck5.setEnabled(false);
                recharge=tv_yin30.getText().toString();
                break;
            case R.id.ck_3:
                break;
            case R.id.iv4:
                break;
            case R.id.tv_yin5:
                break;
            case R.id.tv_yin6:
                break;
            case R.id.tv_content3:
                break;
            case R.id.re_3:
                XGlide.init(this).display(ivIcon,R.drawable.jinone);
                ck1.setChecked(false);
                ck2.setChecked(false);
                ck3.setChecked(true);
                ck4.setChecked(false);
                ck5.setChecked(false);
                ck1.setEnabled(false);
                ck2.setEnabled(false);
                ck3.setEnabled(true);
                ck4.setEnabled(false);
                ck5.setEnabled(false);
                recharge=tvYin6.getText().toString();
                break;
            case R.id.ck_4:
                break;
            case R.id.iv5:
                break;
            case R.id.tv_yin7:
                break;
            case R.id.tv_yin8:
                break;
            case R.id.tv_content4:
                break;
            case R.id.re_4:
                XGlide.init(this).display(ivIcon,R.drawable.jintwo);
                ck1.setChecked(false);
                ck2.setChecked(false);
                ck3.setChecked(false);
                ck4.setChecked(true);
                ck5.setChecked(false);
                ck1.setEnabled(false);
                ck2.setEnabled(false);
                ck3.setEnabled(false);
                ck4.setEnabled(true);
                ck5.setEnabled(false);
                recharge=tvYin8.getText().toString();
                break;
            case R.id.ck_5:
                break;
            case R.id.iv6:
                break;
            case R.id.tv_yin9:
                break;
            case R.id.tv_yin10:
                break;
            case R.id.tv_content5:
                break;
            case R.id.re_5:
                XGlide.init(this).display(ivIcon,R.drawable.zhushu);
                ck1.setChecked(false);
                ck2.setChecked(false);
                ck3.setChecked(false);
                ck4.setChecked(false);
                ck5.setChecked(true);
                ck1.setEnabled(false);
                ck2.setEnabled(false);
                ck3.setEnabled(false);
                ck4.setEnabled(false);
                ck5.setEnabled(true);
                recharge=tvYin10.getText().toString();
                break;
            case R.id.iv_xie:
                break;
            case R.id.tv_xie:
                break;
            case R.id.re_xie:
                break;
            case R.id.tv_bao:
                Intent intent3=new Intent(SafeActivity.this,SubmitActivity.class);
                intent3.putExtra("recharge", recharge);
                startActivity(intent3);
                break;
            case R.id.iv_jin:
                break;
            case R.id.tv_jingc:
                break;
            case R.id.tv_jingc2:
                break;
            case R.id.tv_jingc3:
                break;
            case R.id.tv_jingc4:
                break;
            case R.id.tv_jingc5:
                break;
            case R.id.iv:
                break;
        }
    }

    @Override
    protected void onNetSuccess(int actionId, ResponseEntry result) {
        super.onNetSuccess(actionId, result);
        switch (actionId) {
            case Urls.ActionId.getuserbond:
                if (result.isSuccess()) {
                    baoBean = FJson.getObject(result.getData().toString(), BaoBean.class);
                    if (baoBean != null) {
                        initView();
                    }
                }
                break;
        }
    }

    private void initView() {
        if(Integer.parseInt(baoBean.getMargin())>0){
            re_title2.setVisibility(View.VISIBLE);
            LeeTools.setText(tvMony, baoBean.getMargin() + "元");
            if ("2000".equals(baoBean.getMargin())) {
                XGlide.init(this).display(ivIcon,R.drawable.yinone);
                ck1.setChecked(true);
                ck2.setChecked(false);
                ck3.setChecked(false);
                ck4.setChecked(false);
                ck5.setChecked(false);
                re1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(SafeActivity.this,"您已经是当前等级",Toast.LENGTH_SHORT).show();
                    }
                });
                re2.setEnabled(true);
                re3.setEnabled(true);
                re4.setEnabled(true);
                re5.setEnabled(true);
                ck1.setEnabled(true);
                ck2.setEnabled(false);
                ck3.setEnabled(false);
                ck4.setEnabled(false);
                ck5.setEnabled(false);
            } else if ("5000".equals(baoBean.getMargin())) {
                XGlide.init(this).display(ivIcon,R.drawable.yintwo);
                ck2.setChecked(true);
                ck1.setChecked(false);
                ck3.setChecked(false);
                ck4.setChecked(false);
                ck5.setChecked(false);
                ck1.setEnabled(false);
                ck2.setEnabled(true);
                ck3.setEnabled(false);
                ck4.setEnabled(false);
                ck5.setEnabled(false);
                re1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(SafeActivity.this,"只能选择比当前更高的等级",Toast.LENGTH_SHORT).show();
                    }
                });

                re2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(SafeActivity.this,"您已经是当前等级",Toast.LENGTH_SHORT).show();
                    }
                });
                re3.setEnabled(true);
                re4.setEnabled(true);
                re5.setEnabled(true);
            } else if ("10000".equals(baoBean.getMargin())) {
                XGlide.init(this).display(ivIcon,R.drawable.jinone);
                ck3.setChecked(true);
                ck1.setChecked(false);
                ck2.setChecked(false);
                ck4.setChecked(false);
                ck5.setChecked(false);
                re4.setEnabled(true);
                re5.setEnabled(true);
                ck1.setEnabled(false);
                ck2.setEnabled(false);
                ck3.setEnabled(true);
                ck4.setEnabled(false);
                ck5.setEnabled(false);
                re1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(SafeActivity.this,"只能选择比当前更高的等级",Toast.LENGTH_SHORT).show();
                    }
                });
                re2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(SafeActivity.this,"只能选择比当前更高的等级",Toast.LENGTH_SHORT).show();
                    }
                });

                re3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(SafeActivity.this,"您已经是当前等级",Toast.LENGTH_SHORT).show();
                    }
                });

            } else if ("30000".equals(baoBean.getMargin())) {
                XGlide.init(this).display(ivIcon,R.drawable.jintwo);
                ck4.setChecked(true);
                ck1.setChecked(false);
                ck2.setChecked(false);
                ck3.setChecked(false);
                ck5.setChecked(false);
                re5.setEnabled(true);
                ck1.setEnabled(false);
                ck2.setEnabled(false);
                ck3.setEnabled(false);
                ck4.setEnabled(true);
                ck5.setEnabled(false);
                re1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(SafeActivity.this,"只能选择比当前更高的等级",Toast.LENGTH_SHORT).show();
                    }
                });
                re2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(SafeActivity.this,"只能选择比当前更高的等级",Toast.LENGTH_SHORT).show();
                    }
                });
                re3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(SafeActivity.this,"只能选择比当前更高的等级",Toast.LENGTH_SHORT).show();
                    }
                });

                re4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(SafeActivity.this,"您已经是当前等级",Toast.LENGTH_SHORT).show();
                    }
                });

            } else if ("50000".equals(baoBean.getMargin())) {
                XGlide.init(this).display(ivIcon,R.drawable.zhushu);
                ck5.setChecked(true);
                ck1.setChecked(false);
                ck2.setChecked(false);
                ck3.setChecked(false);
                ck4.setChecked(false);
                ck1.setEnabled(false);
                ck2.setEnabled(false);
                ck3.setEnabled(false);
                ck4.setEnabled(false);
                ck5.setEnabled(true);
                re1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(SafeActivity.this,"只能选择比当前更高的等级",Toast.LENGTH_SHORT).show();
                    }
                });
                re2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(SafeActivity.this,"只能选择比当前更高的等级",Toast.LENGTH_SHORT).show();
                    }
                });
                re3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(SafeActivity.this,"只能选择比当前更高的等级",Toast.LENGTH_SHORT).show();
                    }
                });
                re4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(SafeActivity.this,"只能选择比当前更高的等级",Toast.LENGTH_SHORT).show();
                    }
                });

                re4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(SafeActivity.this,"您已经是当前等级",Toast.LENGTH_SHORT).show();
                    }
                });

            }
            if(Integer.parseInt(baoBean.getCharge())>0){
                tvBao.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(SafeActivity.this,"您当前正处于退保中暂时不可以缴纳保证金",Toast.LENGTH_SHORT).show();
                    }
                });

            }else{

            }
        }

    }


}
