package com.all.app.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.all.app.R;
import com.all.app.app.AppData;
import com.all.app.base.BaseFragment;
import com.all.app.bean.OrderBean;
import com.all.app.configs.Urls;
import com.all.app.http.OkHttpParam;
import com.all.app.http.ResponseEntry;
import com.all.app.ui.activity.AcceptActivity;
import com.all.app.ui.activity.BidActivity;
import com.all.app.ui.activity.ExecutionActivity;
import com.all.app.ui.activity.FinishActivity;
import com.all.app.ui.activity.GetActivity;
import com.all.app.ui.activity.ProOrderActivity;
import com.all.app.ui.activity.WebHelpActivity;
import com.all.app.utils.FJson;
import com.all.app.utils.LeeTools;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class OrderFragment extends BaseFragment {

    @BindView(R.id.tv_zhong)
    TextView tv_zhong;
    @BindView(R.id.tv_dai)
    TextView tv_dai;
    @BindView(R.id.tv_shi)
    TextView tv_shi;
    @BindView(R.id.tv_accept)
    TextView tv_accept;
    @BindView(R.id.tv_finish)
    TextView tv_finish;
    @BindView(R.id.tv_pro)
    TextView tv_pro;
    @BindView(R.id.iv_biao)
    ImageView ivBiao;
    @BindView(R.id.re_zhong)
    RelativeLayout reZhong;
    @BindView(R.id.iv_ti)
    ImageView ivTi;
    @BindView(R.id.re_ti)
    RelativeLayout reTi;
    @BindView(R.id.iv_wrok)
    ImageView ivWrok;
    @BindView(R.id.re_shi)
    RelativeLayout reShi;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.iv_help)
    ImageView iv_help;
    @BindView(R.id.re_accept)
    RelativeLayout reAccept;
    @BindView(R.id.iv_finish)
    ImageView ivFinish;
    @BindView(R.id.re_finish)
    RelativeLayout reFinish;
    @BindView(R.id.iv_problem)
    ImageView ivProblem;
    @BindView(R.id.re_problem)
    RelativeLayout reProblem;
    Unbinder unbinder;
    OrderBean orderBean;
    private boolean isGetData = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public void onResume() {
        super.onResume();
        loadData();

    }

    @Override
    protected void Init() {

    }

    private void loadData() {
        OkHttpParam param = new OkHttpParam();
        param.add("uid", AppData.Init().getLoginBean().getUid());
        _PostEntry(Urls.orderstatenum, param, Urls.ActionId.orderstatenum, true);
    }

    @Override
    protected int InitLayer() {
        return R.layout.fragment_order;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);


        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();

    }


    @OnClick({R.id.iv_help, R.id.iv_biao, R.id.re_zhong, R.id.iv_ti, R.id.re_ti, R.id.iv_wrok, R.id.re_shi, R.id.imageView, R.id.re_accept, R.id.iv_finish, R.id.re_finish, R.id.iv_problem, R.id.re_problem})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_biao:
                break;
            case R.id.iv_help:
                startActivity(new Intent(getActivity(), WebHelpActivity.class));
                break;
            case R.id.re_zhong:
                startActivity(new Intent(getActivity(), BidActivity.class));
                break;
            case R.id.iv_ti:
                break;
            case R.id.re_ti:
                startActivity(new Intent(getActivity(), GetActivity.class));
                break;
            case R.id.iv_wrok:
                break;
            case R.id.re_shi:
                startActivity(new Intent(getActivity(), ExecutionActivity.class));
                break;
            case R.id.imageView:
                break;
            case R.id.re_accept:
                startActivity(new Intent(getActivity(), AcceptActivity.class));
                break;
            case R.id.iv_finish:
                break;
            case R.id.re_finish:
                startActivity(new Intent(getActivity(), FinishActivity.class));
                break;
            case R.id.iv_problem:
                break;
            case R.id.re_problem:
                startActivity(new Intent(getActivity(), ProOrderActivity.class));
                break;
        }
    }

    @Override
    protected void onNetSuccess(int actionId, ResponseEntry result) {
        super.onNetSuccess(actionId, result);
        if (result.isSuccess()) {
            System.out.println("==========666666666666666666" + result);
            orderBean = FJson.getObject(result.getData().toString(), OrderBean.class);
            if(orderBean!=null){
                initView();
            }

        }
    }

    //刷新数据
    private void initView() {
            if (orderBean.getZbnum()>0) {
                tv_zhong.setVisibility(View.VISIBLE);
                LeeTools.setText(tv_zhong, String.valueOf(orderBean.getZbnum()));
            }
            if (orderBean.getThnum()>0) {
                tv_dai.setVisibility(View.VISIBLE);
                LeeTools.setText(tv_dai, String.valueOf(orderBean.getThnum()));
            }
            if (orderBean.getSgnum()>0) {
                tv_shi.setVisibility(View.VISIBLE);
                LeeTools.setText(tv_shi, String.valueOf(orderBean.getSgnum()));
            }
            if (orderBean.getYsnum()>0) {
                tv_accept.setVisibility(View.VISIBLE);
                LeeTools.setText(tv_accept, String.valueOf(orderBean.getYsnum()));
            }
            if (orderBean.getWcnum()>0) {
                tv_finish.setVisibility(View.VISIBLE);
                LeeTools.setText(tv_finish, String.valueOf(orderBean.getWcnum()));
            }
            if (orderBean.getWtdnum()>0) {
                tv_pro.setVisibility(View.VISIBLE);
                LeeTools.setText(tv_pro, String.valueOf(orderBean.getWtdnum()));
            }
        }
    }

