package com.all.app.ui.activity;

import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.all.app.R;
import com.all.app.app.AppData;
import com.all.app.base.BaseActivity;
import com.all.app.base.BaseFragmentActivity;
import com.all.app.bean.StatueBean;
import com.all.app.configs.Urls;
import com.all.app.http.OkHttpParam;
import com.all.app.http.ResponseEntry;
import com.all.app.ui.fragment.CarFragment;
import com.all.app.ui.fragment.CompanyFragment;
import com.all.app.ui.fragment.RealNameFragment;
import com.all.app.ui.fragment.StutasFragment;
import com.all.app.ui.fragment.TeamFragment;
import com.all.app.utils.FJson;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//认证管理页面
public class RenActivity extends BaseFragmentActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.re_title)
    RelativeLayout reTitle;
    @BindView(R.id.vi2)
    View vi2;
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
    @BindView(R.id.tv_team5)
    TextView tvTeam5;
    @BindView(R.id.iv_thrid3)
    View ivThrid3;
    @BindView(R.id.ll_title)
    LinearLayout llTitle;
    @BindView(R.id.iv)
    View iv;
    @BindView(R.id.fl)
    FrameLayout fl;
    RealNameFragment realNameFragment;
    StutasFragment stutasFragment;
    CarFragment carFragment;
    CompanyFragment companyFragment;
    FragmentTransaction ft;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ren);
        ButterKnife.bind(this);
      /*  sp=getSharedPreferences("config",MODE_PRIVATE);
        sp.edit().putBoolean("is_user_ren_showed", true).commit();
        //跳转到主页
        startActivity(new Intent(RenActivity.this, MainActivity.class));
        finish();*/
        realNameFragment=new RealNameFragment();
        this.getSupportFragmentManager().beginTransaction().add(R.id.fl, realNameFragment).commit();

    }

    @OnClick({R.id.iv_back, R.id.tv_title, R.id.re_title, R.id.vi2, R.id.tv_team2, R.id.iv_one, R.id.tv_team3, R.id.iv_two, R.id.tv_team4, R.id.iv_thrid, R.id.tv_team5, R.id.iv_thrid3, R.id.ll_title, R.id.iv, R.id.fl})
    public void onViewClicked(View view) {
        ft=this.getSupportFragmentManager().beginTransaction();
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
            case R.id.tv_team2:
                tvTeam2.setTextColor(getResources().getColor(R.color.APP_EC801B));
                tvTeam3.setTextColor(getResources().getColor(R.color.item_title));
                tvTeam4.setTextColor(getResources().getColor(R.color.item_title));
                tvTeam5.setTextColor(getResources().getColor(R.color.item_title));
                ivOne.setVisibility(View.VISIBLE);
                ivTwo.setVisibility(View.GONE);
                ivThrid.setVisibility(View.GONE);
                ivThrid3.setVisibility(View.GONE);
                realNameFragment = new RealNameFragment();
                ft.replace(R.id.fl, realNameFragment);
                break;
            case R.id.iv_one:
                break;
            case R.id.tv_team3:
                tvTeam2.setTextColor(getResources().getColor(R.color.item_title));
                tvTeam3.setTextColor(getResources().getColor(R.color.APP_EC801B));
                tvTeam4.setTextColor(getResources().getColor(R.color.item_title));
                tvTeam5.setTextColor(getResources().getColor(R.color.item_title));
                ivOne.setVisibility(View.GONE);
                ivTwo.setVisibility(View.VISIBLE);
                ivThrid.setVisibility(View.GONE);
                ivThrid3.setVisibility(View.GONE);
                stutasFragment = new StutasFragment();
                ft.replace(R.id.fl, stutasFragment);
                break;
            case R.id.iv_two:
                break;
            case R.id.tv_team4:
                tvTeam2.setTextColor(getResources().getColor(R.color.item_title));
                tvTeam3.setTextColor(getResources().getColor(R.color.item_title));
                tvTeam4.setTextColor(getResources().getColor(R.color.APP_EC801B));
                tvTeam5.setTextColor(getResources().getColor(R.color.item_title));
                ivOne.setVisibility(View.GONE);
                ivTwo.setVisibility(View.GONE);
                ivThrid.setVisibility(View.VISIBLE);
                ivThrid3.setVisibility(View.GONE);
                carFragment = new CarFragment();
                ft.replace(R.id.fl, carFragment);
                break;
            case R.id.iv_thrid:
                break;
            case R.id.tv_team5:
                tvTeam2.setTextColor(getResources().getColor(R.color.item_title));
                tvTeam3.setTextColor(getResources().getColor(R.color.item_title));
                tvTeam4.setTextColor(getResources().getColor(R.color.item_title));
                tvTeam5.setTextColor(getResources().getColor(R.color.APP_EC801B));
                ivOne.setVisibility(View.GONE);
                ivTwo.setVisibility(View.GONE);
                ivThrid.setVisibility(View.GONE);
                ivThrid3.setVisibility(View.VISIBLE);
                companyFragment = new CompanyFragment();
                ft.replace(R.id.fl, companyFragment);
                break;
            case R.id.iv_thrid3:
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

    @Override
    protected void onNetSuccess(int actionId, ResponseEntry result) {
        super.onNetSuccess(actionId, result);
      switch (actionId){

   }
   }
}

