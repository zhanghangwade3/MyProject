package com.all.app.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.all.app.R;
import com.all.app.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//经营分析页面
public class OpanalActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.re_title)
    RelativeLayout reTitle;
    @BindView(R.id.iv_wen)
    ImageView ivWen;
    @BindView(R.id.tv_wen)
    TextView tvWen;
    @BindView(R.id.tv_baocun)
    TextView tvBaocun;
    @BindView(R.id.iv_next)
    ImageView ivNext;
    @BindView(R.id.re_wen)
    RelativeLayout reWen;
    @BindView(R.id.iv_tong)
    ImageView ivTong;
    @BindView(R.id.tv_tong)
    TextView tvTong;
    @BindView(R.id.tv_tong2)
    TextView tvTong2;
    @BindView(R.id.iv_next2)
    ImageView ivNext2;
    @BindView(R.id.re_tong)
    RelativeLayout reTong;
    @BindView(R.id.tv_zhuan)
    TextView tvZhuan;
    @BindView(R.id.tv_yes)
    TextView tvYes;
    @BindView(R.id.tv_number)
    TextView tvNumber;
    @BindView(R.id.re_zhuan)
    RelativeLayout reZhuan;
    @BindView(R.id.tv_number1)
    TextView tvNumber1;
    @BindView(R.id.tv_number2)
    TextView tvNumber2;
    @BindView(R.id.tv_number3)
    TextView tvNumber3;
    @BindView(R.id.tv_number4)
    TextView tvNumber4;
    @BindView(R.id.tv_number5)
    TextView tvNumber5;
    @BindView(R.id.tv_number6)
    TextView tvNumber6;
    @BindView(R.id.ll_number)
    LinearLayout llNumber;
    @BindView(R.id.v1)
    View v1;
    @BindView(R.id.v2)
    View v2;
    @BindView(R.id.v3)
    View v3;
    @BindView(R.id.v4)
    View v4;
    @BindView(R.id.v5)
    View v5;
    @BindView(R.id.v6)
    View v6;
    @BindView(R.id.ll_view)
    LinearLayout llView;
    @BindView(R.id.view)
    View view;
    @BindView(R.id.vi1)
    TextView vi1;
    @BindView(R.id.vi2)
    TextView vi2;
    @BindView(R.id.vi3)
    TextView vi3;
    @BindView(R.id.vi4)
    TextView vi4;
    @BindView(R.id.vi5)
    TextView vi5;
    @BindView(R.id.ll_yellow)
    LinearLayout llYellow;
    @BindView(R.id.vii1)
    TextView vii1;
    @BindView(R.id.vii2)
    TextView vii2;
    @BindView(R.id.vii3)
    TextView vii3;
    @BindView(R.id.vii4)
    TextView vii4;
    @BindView(R.id.vii5)
    TextView vii5;
    @BindView(R.id.ll_lv)
    LinearLayout llLv;
    @BindView(R.id.vio1)
    TextView vio1;
    @BindView(R.id.vio2)
    TextView vio2;
    @BindView(R.id.vio3)
    TextView vio3;
    @BindView(R.id.vio4)
    TextView vio4;
    @BindView(R.id.vio5)
    TextView vio5;
    @BindView(R.id.ll_lan)
    LinearLayout llLan;
    @BindView(R.id.iv_gu)
    ImageView ivGu;
    @BindView(R.id.tv_gu)
    TextView tvGu;
    @BindView(R.id.iv_tui)
    ImageView ivTui;
    @BindView(R.id.tv_tui)
    TextView tvTui;
    @BindView(R.id.iv_bao)
    ImageView ivBao;
    @BindView(R.id.tv_bao)
    TextView tvBao;
    @BindView(R.id.iv_yue)
    ImageView ivYue;
    @BindView(R.id.tv_yue)
    TextView tvYue;
    @BindView(R.id.tv_yue2)
    TextView tvYue2;
    @BindView(R.id.iv_yue2)
    ImageView ivYue2;
    @BindView(R.id.re_yue)
    RelativeLayout reYue;
    @BindView(R.id.tv_zhuan2)
    TextView tvZhuan2;
    @BindView(R.id.tv_yes2)
    TextView tvYes2;
    @BindView(R.id.tv_numbe)
    TextView tvNumbe;
    @BindView(R.id.re_zhuan2)
    RelativeLayout reZhuan2;
    @BindView(R.id.tv_number12)
    TextView tvNumber12;
    @BindView(R.id.tv_number22)
    TextView tvNumber22;
    @BindView(R.id.tv_number33)
    TextView tvNumber33;
    @BindView(R.id.tv_number43)
    TextView tvNumber43;
    @BindView(R.id.tv_number53)
    TextView tvNumber53;
    @BindView(R.id.tv_number63)
    TextView tvNumber63;
    @BindView(R.id.ll_number2)
    LinearLayout llNumber2;
    @BindView(R.id.v12)
    View v12;
    @BindView(R.id.v22)
    View v22;
    @BindView(R.id.v33)
    View v33;
    @BindView(R.id.v43)
    View v43;
    @BindView(R.id.v53)
    View v53;
    @BindView(R.id.v63)
    View v63;
    @BindView(R.id.ll_view2)
    LinearLayout llView2;
    @BindView(R.id.view2)
    View view2;
    @BindView(R.id.vi12)
    TextView vi12;
    @BindView(R.id.vi22)
    TextView vi22;
    @BindView(R.id.vi32)
    TextView vi32;
    @BindView(R.id.vi42)
    TextView vi42;
    @BindView(R.id.vi52)
    TextView vi52;
    @BindView(R.id.ll_yellow2)
    LinearLayout llYellow2;
    @BindView(R.id.vii12)
    TextView vii12;
    @BindView(R.id.vii22)
    TextView vii22;
    @BindView(R.id.vii32)
    TextView vii32;
    @BindView(R.id.vii42)
    TextView vii42;
    @BindView(R.id.vii52)
    TextView vii52;
    @BindView(R.id.ll_lv2)
    LinearLayout llLv2;
    @BindView(R.id.vio12)
    TextView vio12;
    @BindView(R.id.vio22)
    TextView vio22;
    @BindView(R.id.vio32)
    TextView vio32;
    @BindView(R.id.vio42)
    TextView vio42;
    @BindView(R.id.vio52)
    TextView vio52;
    @BindView(R.id.ll_lan2)
    LinearLayout llLan2;
    @BindView(R.id.iv_gu2)
    ImageView ivGu2;
    @BindView(R.id.tv_gu2)
    TextView tvGu2;
    @BindView(R.id.iv_tui2)
    ImageView ivTui2;
    @BindView(R.id.tv_tui2)
    TextView tvTui2;
    @BindView(R.id.iv_bao2)
    ImageView ivBao2;
    @BindView(R.id.tv_bao2)
    TextView tvBao2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opanal);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tv_tong2,R.id.iv_back, R.id.tv_title, R.id.re_title, R.id.iv_wen, R.id.tv_wen, R.id.tv_baocun, R.id.iv_next, R.id.re_wen, R.id.iv_tong, R.id.tv_tong, R.id.iv_next2, R.id.re_tong, R.id.tv_zhuan, R.id.tv_yes, R.id.tv_number, R.id.re_zhuan, R.id.tv_number1, R.id.tv_number2, R.id.tv_number3, R.id.tv_number4, R.id.tv_number5, R.id.tv_number6, R.id.ll_number, R.id.v1, R.id.v2, R.id.v3, R.id.v4, R.id.v5, R.id.v6, R.id.ll_view, R.id.view, R.id.vi1, R.id.vi2, R.id.vi3, R.id.vi4, R.id.vi5, R.id.ll_yellow, R.id.vii1, R.id.vii2, R.id.vii3, R.id.vii4, R.id.vii5, R.id.ll_lv, R.id.vio1, R.id.vio2, R.id.vio3, R.id.vio4, R.id.vio5, R.id.ll_lan, R.id.iv_gu, R.id.tv_gu, R.id.iv_tui, R.id.tv_tui, R.id.iv_bao, R.id.tv_bao, R.id.iv_yue, R.id.tv_yue, R.id.tv_yue2, R.id.iv_yue2, R.id.re_yue, R.id.tv_zhuan2, R.id.tv_yes2, R.id.tv_numbe, R.id.re_zhuan2, R.id.tv_number12, R.id.tv_number22, R.id.tv_number33, R.id.tv_number43, R.id.tv_number53, R.id.tv_number63, R.id.ll_number2, R.id.v12, R.id.v22, R.id.v33, R.id.v43, R.id.v53, R.id.v63, R.id.ll_view2, R.id.view2, R.id.vi12, R.id.vi22, R.id.vi32, R.id.vi42, R.id.vi52, R.id.ll_yellow2, R.id.vii12, R.id.vii22, R.id.vii32, R.id.vii42, R.id.vii52, R.id.ll_lv2, R.id.vio12, R.id.vio22})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_tong2:
                startActivity(new Intent(OpanalActivity.this,MonthDeActivity.class));
                break;
            case R.id.tv_title:
                break;
            case R.id.re_title:
                break;
            case R.id.iv_wen:
                break;
            case R.id.tv_wen:
                break;
            case R.id.tv_baocun:
                break;
            case R.id.iv_next:
                break;
            case R.id.re_wen:
                break;
            case R.id.iv_tong:
                break;
            case R.id.tv_tong:
                break;
            case R.id.iv_next2:
                break;
            case R.id.re_tong:
                break;
            case R.id.tv_zhuan:
                break;
            case R.id.tv_yes:
                break;
            case R.id.tv_number:
                break;
            case R.id.re_zhuan:
                break;
            case R.id.tv_number1:
                break;
            case R.id.tv_number2:
                break;
            case R.id.tv_number3:
                break;
            case R.id.tv_number4:
                break;
            case R.id.tv_number5:
                break;
            case R.id.tv_number6:
                break;
            case R.id.ll_number:
                break;
            case R.id.v1:
                break;
            case R.id.v2:
                break;
            case R.id.v3:
                break;
            case R.id.v4:
                break;
            case R.id.v5:
                break;
            case R.id.v6:
                break;
            case R.id.ll_view:
                break;
            case R.id.view:
                break;
            case R.id.vi1:
                break;
            case R.id.vi2:
                break;
            case R.id.vi3:
                break;
            case R.id.vi4:
                break;
            case R.id.vi5:
                break;
            case R.id.ll_yellow:
                break;
            case R.id.vii1:
                break;
            case R.id.vii2:
                break;
            case R.id.vii3:
                break;
            case R.id.vii4:
                break;
            case R.id.vii5:
                break;
            case R.id.ll_lv:
                break;
            case R.id.vio1:
                break;
            case R.id.vio2:
                break;
            case R.id.vio3:
                break;
            case R.id.vio4:
                break;
            case R.id.vio5:
                break;
            case R.id.ll_lan:
                break;
            case R.id.iv_gu:
                break;
            case R.id.tv_gu:
                break;
            case R.id.iv_tui:
                break;
            case R.id.tv_tui:
                break;
            case R.id.iv_bao:
                break;
            case R.id.tv_bao:
                break;
            case R.id.iv_yue:
                break;
            case R.id.tv_yue:
                break;
            case R.id.tv_yue2:
                startActivity(new Intent(OpanalActivity.this,MonthActivity.class));
                break;
            case R.id.iv_yue2:
                break;
            case R.id.re_yue:
                break;
            case R.id.tv_zhuan2:
                break;
            case R.id.tv_yes2:
                break;
            case R.id.tv_numbe:
                break;
            case R.id.re_zhuan2:
                break;
            case R.id.tv_number12:
                break;
            case R.id.tv_number22:
                break;
            case R.id.tv_number33:
                break;
            case R.id.tv_number43:
                break;
            case R.id.tv_number53:
                break;
            case R.id.tv_number63:
                break;
            case R.id.ll_number2:
                break;
            case R.id.v12:
                break;
            case R.id.v22:
                break;
            case R.id.v33:
                break;
            case R.id.v43:
                break;
            case R.id.v53:
                break;
            case R.id.v63:
                break;
            case R.id.ll_view2:
                break;
            case R.id.view2:
                break;
            case R.id.vi12:
                break;
            case R.id.vi22:
                break;
            case R.id.vi32:
                break;
            case R.id.vi42:
                break;
            case R.id.vi52:
                break;
            case R.id.ll_yellow2:
                break;
            case R.id.vii12:
                break;
            case R.id.vii22:
                break;
            case R.id.vii32:
                break;
            case R.id.vii42:
                break;
            case R.id.vii52:
                break;
            case R.id.ll_lv2:
                break;
            case R.id.vio12:
                break;
            case R.id.vio22:
                break;
        }
    }
}
