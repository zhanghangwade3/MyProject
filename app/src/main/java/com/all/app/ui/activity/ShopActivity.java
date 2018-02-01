package com.all.app.ui.activity;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.all.app.R;
import com.all.app.base.BaseFragmentActivity;
import com.all.app.ui.fragment.ExampleFragment;
import com.all.app.ui.fragment.ServiceFragment;
import com.all.app.ui.fragment.TeamFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//我的店铺页面
public class ShopActivity extends BaseFragmentActivity {
    TeamFragment teamFragment;//团队介绍
    ExampleFragment exampleFragment;//案例展示
    ServiceFragment serviceFragment;//服务评价
    FragmentTransaction ft;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.re_title)
    RelativeLayout reTitle;
    @BindView(R.id.vi2)
    View vi2;
    @BindView(R.id.iv_head)
    ImageView ivHead;
    @BindView(R.id.tv_number)
    TextView tvNumber;
    @BindView(R.id.iv_shiming)
    ImageView ivShiming;
    @BindView(R.id.tv_shiming)
    TextView tvShiming;
    @BindView(R.id.iv_zige)
    ImageView ivZige;
    @BindView(R.id.tv_zige)
    TextView tvZige;
    @BindView(R.id.iv_qiye)
    ImageView ivQiye;
    @BindView(R.id.tv_qiye)
    TextView tvQiye;
    @BindView(R.id.ll_shop)
    LinearLayout llShop;
    @BindView(R.id.iv_baojin)
    ImageView ivBaojin;
    @BindView(R.id.tv_bao)
    TextView tvBao;
    @BindView(R.id.iv_huo)
    ImageView ivHuo;
    @BindView(R.id.tv_huo)
    TextView tvHuo;
    @BindView(R.id.iv_dengji)
    ImageView ivDengji;
    @BindView(R.id.tv_dengji2)
    TextView tvDengji2;
    @BindView(R.id.ll_shop2)
    LinearLayout llShop2;
    @BindView(R.id.iv_fenge1)
    ImageView ivFenge1;
    @BindView(R.id.tv_content8)
    TextView tvContent8;
    @BindView(R.id.tv_baoj)
    TextView tvBaoj;
    @BindView(R.id.tv_content5)
    TextView tvContent5;
    @BindView(R.id.tv_tea)
    TextView tvTea;
    @BindView(R.id.tv_content9)
    TextView tvContent9;
    @BindView(R.id.tv_servi)
    TextView tvServi;
    @BindView(R.id.ll_shop3)
    LinearLayout llShop3;
    @BindView(R.id.iv_fenge2)
    ImageView ivFenge2;
    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.tv_content6)
    TextView tvContent6;
    @BindView(R.id.rl_service)
    RelativeLayout rlService;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.tv_content4)
    TextView tvContent4;
    @BindView(R.id.rl_address)
    RelativeLayout rlAddress;
    @BindView(R.id.tv_service)
    TextView tvService;
    @BindView(R.id.tv_content2)
    TextView tvContent2;
    @BindView(R.id.rl_location)
    RelativeLayout rlLocation;
    @BindView(R.id.tv_zhuang)
    TextView tvZhuang;
    @BindView(R.id.tv_zhuan)
    TextView tvZhuan;
    @BindView(R.id.tv_num)
    TextView tvNum;
    @BindView(R.id.tv_ci)
    TextView tvCi;
    @BindView(R.id.tv_nemaer)
    TextView tvNemaer;
    @BindView(R.id.tv_fen)
    TextView tvFen;
    @BindView(R.id.tv_nem)
    TextView tvNem;
    @BindView(R.id.rl_zhuang)
    RelativeLayout rlZhuang;
    @BindView(R.id.re_xiang)
    RelativeLayout reXiang;
    @BindView(R.id.vi)
    View vi;
    @BindView(R.id.tv_team2)
    TextView tvTeam2;
    @BindView(R.id.iv_one)
    View ivOne;
    @BindView(R.id.tv_team3)
    TextView tvTeam3;
    @BindView(R.id.iv_two)
    View ivTwo;
    @BindView(R.id.tv_team4)
    TextView tvTeam4;
    @BindView(R.id.iv_thrid)
    View ivThrid;
    @BindView(R.id.ll_title)
    LinearLayout llTitle;
    @BindView(R.id.iv)
    View iv;
    @BindView(R.id.fl)
    FrameLayout fl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopp);
        ButterKnife.bind(this);
        tvTeam2.setTextColor(getResources().getColor(R.color.APP_EC801B));

        teamFragment = new TeamFragment();//默认添加一个fragement
        this.getSupportFragmentManager().beginTransaction().add(R.id.fl, teamFragment).commit();
    }


    @OnClick({R.id.iv_back, R.id.tv_title, R.id.re_title, R.id.vi2, R.id.iv_head, R.id.tv_number, R.id.iv_shiming, R.id.tv_shiming, R.id.iv_zige, R.id.tv_zige, R.id.iv_qiye, R.id.tv_qiye, R.id.ll_shop, R.id.iv_baojin, R.id.tv_bao, R.id.iv_huo, R.id.tv_huo, R.id.iv_dengji, R.id.tv_dengji2, R.id.ll_shop2, R.id.iv_fenge1, R.id.tv_content8, R.id.tv_baoj, R.id.tv_content5, R.id.tv_tea, R.id.tv_content9, R.id.tv_servi, R.id.ll_shop3, R.id.iv_fenge2, R.id.tv_type, R.id.tv_content6, R.id.rl_service, R.id.tv_address, R.id.tv_content4, R.id.rl_address, R.id.tv_service, R.id.tv_content2, R.id.rl_location, R.id.tv_zhuang, R.id.tv_zhuan, R.id.tv_num, R.id.tv_ci, R.id.tv_nemaer, R.id.tv_fen, R.id.tv_nem, R.id.rl_zhuang, R.id.re_xiang, R.id.vi, R.id.tv_team2, R.id.iv_one, R.id.tv_team3, R.id.iv_two, R.id.tv_team4, R.id.iv_thrid, R.id.ll_title, R.id.iv, R.id.fl})
    public void onViewClicked(View view) {
        ft = this.getSupportFragmentManager().beginTransaction();
        switch (view.getId()) {
            case R.id.iv_back:
                 finish();
                break;
            case R.id.tv_title:
                break;
            case R.id.re_title:
                break;
            case R.id.vi2:
                break;
            case R.id.iv_head:
                break;
            case R.id.tv_number:
                break;
            case R.id.iv_shiming:
                break;
            case R.id.tv_shiming:
                break;
            case R.id.iv_zige:
                break;
            case R.id.tv_zige:
                break;
            case R.id.iv_qiye:
                break;
            case R.id.tv_qiye:
                break;
            case R.id.ll_shop:
                break;
            case R.id.iv_baojin:
                break;
            case R.id.tv_bao:
                break;
            case R.id.iv_huo:
                break;
            case R.id.tv_huo:
                break;
            case R.id.iv_dengji:
                break;
            case R.id.tv_dengji2:
                break;
            case R.id.ll_shop2:
                break;
            case R.id.iv_fenge1:
                break;
            case R.id.tv_content8:
                break;
            case R.id.tv_baoj:
                break;
            case R.id.tv_content5:
                break;
            case R.id.tv_tea:
                break;
            case R.id.tv_content9:
                break;
            case R.id.tv_servi:
                break;
            case R.id.ll_shop3:
                break;
            case R.id.iv_fenge2:
                break;
            case R.id.tv_type:
                break;
            case R.id.tv_content6:
                break;
            case R.id.rl_service:
                break;
            case R.id.tv_address:
                break;
            case R.id.tv_content4:
                break;
            case R.id.rl_address:
                break;
            case R.id.tv_service:
                break;
            case R.id.tv_content2:
                break;
            case R.id.rl_location:
                break;
            case R.id.tv_zhuang:
                break;
            case R.id.tv_zhuan:
                break;
            case R.id.tv_num:
                break;
            case R.id.tv_ci:
                break;
            case R.id.tv_nemaer:
                break;
            case R.id.tv_fen:
                break;
            case R.id.tv_nem:
                break;
            case R.id.rl_zhuang:
                break;
            case R.id.re_xiang:
                break;
            case R.id.vi:
                break;
            case R.id.tv_team2:
                tvTeam2.setTextColor(getResources().getColor(R.color.APP_EC801B));
                tvTeam3.setTextColor(getResources().getColor(R.color.item_title));
                tvTeam4.setTextColor(getResources().getColor(R.color.item_title));
                ivOne.setVisibility(View.VISIBLE);
                ivTwo.setVisibility(View.GONE);
                ivThrid.setVisibility(View.GONE);
                teamFragment = new TeamFragment();
                ft.replace(R.id.fl, teamFragment);
                break;
            case R.id.iv_one:
                break;
            case R.id.tv_team3:
                tvTeam3.setTextColor(getResources().getColor(R.color.APP_EC801B));
                tvTeam2.setTextColor(getResources().getColor(R.color.item_title));
                tvTeam4.setTextColor(getResources().getColor(R.color.item_title));
                ivOne.setVisibility(View.GONE);
                ivTwo.setVisibility(View.VISIBLE);
                ivThrid.setVisibility(View.GONE);
                exampleFragment = new ExampleFragment();
                ft.replace(R.id.fl,exampleFragment);
                break;
            case R.id.iv_two:
                break;
            case R.id.tv_team4:
                tvTeam4.setTextColor(getResources().getColor(R.color.APP_EC801B));
                tvTeam2.setTextColor(getResources().getColor(R.color.item_title));
                tvTeam3.setTextColor(getResources().getColor(R.color.item_title));
                ivOne.setVisibility(View.GONE);
                ivTwo.setVisibility(View.GONE);
                ivThrid.setVisibility(View.VISIBLE);
                serviceFragment = new ServiceFragment();
                ft.replace(R.id.fl, serviceFragment);
                break;
            case R.id.iv_thrid:
                break;
            case R.id.ll_title:
                break;
            case R.id.iv:
                break;
            case R.id.fl:
                break;
        }
        ft.commit();
    }
}
