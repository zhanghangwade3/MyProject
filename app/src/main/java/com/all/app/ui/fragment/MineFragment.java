package com.all.app.ui.fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.all.app.R;
import com.all.app.app.AppData;
import com.all.app.base.BaseFragment;
import com.all.app.bean.MineBean;
import com.all.app.bean.ServiceBean;
import com.all.app.bean.StatueBean;
import com.all.app.configs.Urls;
import com.all.app.http.OkHttpParam;
import com.all.app.http.ResponseEntry;
import com.all.app.ui.activity.ApproveActivity;
import com.all.app.ui.activity.EditTeamActivity;
import com.all.app.ui.activity.MainActivity;
import com.all.app.ui.activity.MartActivity;
import com.all.app.ui.activity.MoneyActivity;
import com.all.app.ui.activity.OpanalActivity;
import com.all.app.ui.activity.PersonActivity;
import com.all.app.ui.activity.PhActivity;
import com.all.app.ui.activity.PhotoActivity;
import com.all.app.ui.activity.RenActivity;
import com.all.app.ui.activity.ServiceActivity;
import com.all.app.ui.activity.SettingActivity;
import com.all.app.ui.activity.TeamActivity;
import com.all.app.ui.activity.WorkingActivity;
import com.all.app.utils.AlertDialog;
import com.all.app.utils.FJson;
import com.all.app.utils.LeeTools;
import com.all.app.utils.XGlide;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MineFragment extends BaseFragment {
    @BindView(R.id.iv_dengji)
    ImageView ivDengji;
    @BindView(R.id.tv_dengji)
    TextView tvDengji;
    @BindView(R.id.iv_head)
    ImageView ivHead;
    @BindView(R.id.iv_bianji)
    ImageView ivBianji;
    @BindView(R.id.tv_bianji)
    TextView tvBianji;
    @BindView(R.id.tv_number)
    TextView tvNumber;
    @BindView(R.id.tv_mingzi)
    TextView tvMingzi;
    @BindView(R.id.iv_shop)
    ImageView ivShop;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.ll_shop)
    LinearLayout llShop;
    @BindView(R.id.iv_manager)
    ImageView ivManager;
    @BindView(R.id.ll_jingyin)
    LinearLayout llJingyin;
    @BindView(R.id.iv_money)
    ImageView ivMoney;
    @BindView(R.id.textView2)
    TextView textView2;
    @BindView(R.id.iv_right2)
    ImageView ivRight2;
    @BindView(R.id.re_money)
    RelativeLayout reMoney;
    @BindView(R.id.iv_secret)
    ImageView ivSecret;
    @BindView(R.id.iv_right4)
    ImageView ivRight4;
    @BindView(R.id.re_service)
    RelativeLayout reService;
    @BindView(R.id.iv_parter)
    ImageView ivParter;
    @BindView(R.id.iv_right5)
    ImageView ivRight5;
    @BindView(R.id.re_pater)
    RelativeLayout rePater;
    @BindView(R.id.iv_approve)
    ImageView ivApprove;
    @BindView(R.id.iv_right6)
    ImageView ivRight6;
    @BindView(R.id.re_approve)
    RelativeLayout reApprove;
    @BindView(R.id.iv_team)
    ImageView ivTeam;
    @BindView(R.id.iv_right7)
    ImageView ivRight7;
    @BindView(R.id.re_team)
    RelativeLayout reTeam;
    @BindView(R.id.iv_finish)
    ImageView ivFinish;
    @BindView(R.id.iv_right8)
    ImageView ivRight8;
    @BindView(R.id.re_ok)
    RelativeLayout reOk;
    @BindView(R.id.iv_set)
    ImageView ivSet;
    @BindView(R.id.textView3)
    TextView textView3;
    @BindView(R.id.iv_right9)
    ImageView ivRight9;
    @BindView(R.id.check_name)
    CheckBox check_name;
    @BindView(R.id.re_set)
    RelativeLayout reSet;
    @BindView(R.id.re_kefu)
    RelativeLayout re_kefu;

    Unbinder unbinder;
    MineBean mineBean;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void Init() {
    }

    @Override
    public void onResume() {
        super.onResume();
        loadData();
    }
   //加载我的页面数据
    private void loadData() {
        OkHttpParam param = new OkHttpParam();
        param.add("uid", AppData.Init().getLoginBean().getUid());
        _PostEntry(Urls.my, param, Urls.ActionId.my, true);
    }

    @Override
    protected int InitLayer() {
        return R.layout.fragment_mine;
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

    @OnClick({R.id.re_kefu,R.id.tv_dengji, R.id.iv_head, R.id.iv_bianji, R.id.tv_bianji, R.id.tv_number, R.id.tv_mingzi, R.id.iv_shop, R.id.textView, R.id.ll_shop, R.id.iv_manager, R.id.ll_jingyin, R.id.iv_money, R.id.textView2, R.id.iv_right2, R.id.re_money,R.id.iv_secret, R.id.iv_right4, R.id.re_service, R.id.iv_parter, R.id.iv_right5, R.id.re_pater, R.id.iv_approve, R.id.iv_right6, R.id.re_approve, R.id.iv_team, R.id.iv_right7, R.id.re_team, R.id.iv_finish, R.id.iv_right8, R.id.re_ok, R.id.iv_set, R.id.textView3, R.id.iv_right9, R.id.re_set})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_dengji:
                break;
            case R.id.re_kefu:
                AlertDialog dialog1 = new AlertDialog(mContext);
                dialog1.builder();
                dialog1.setTitle("客服电话");
                dialog1.setMsg("是否拨打客服电话：\n" +mineBean.getKftel());
                dialog1.setPositiveButton("拨打", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                        Uri uri = Uri.parse("tel:" + mineBean.getKftel());
                        Intent intent = new Intent(Intent.ACTION_DIAL, uri);
                        startActivity(intent);
                    }
                });
                dialog1.setNegativeButton("取消", new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                    }
                });
                dialog1.show();
                break;
            case R.id.iv_head:
                break;
            case R.id.iv_bianji:
                startActivity(new Intent(getActivity(), PersonActivity.class));
                break;
            case R.id.tv_bianji:
                startActivity(new Intent(getActivity(), PersonActivity.class));
                break;
            case R.id.tv_number:
                break;
            case R.id.tv_mingzi:
                break;
            case R.id.iv_shop:
                break;
            case R.id.textView:
                break;
            case R.id.ll_shop:
                Toast.makeText(mContext, "暂未开放", Toast.LENGTH_SHORT).show();
                break;
            case R.id.iv_manager:
                break;
            case R.id.ll_jingyin:
                Toast.makeText(mContext, "暂未开放", Toast.LENGTH_SHORT).show();
                //startActivity(new Intent(getActivity(), OpanalActivity.class));
                break;
            case R.id.iv_money:
                break;
            case R.id.textView2:
                break;
            case R.id.iv_right2:
                break;
            case R.id.re_money:
                startActivity(new Intent(getActivity(), MoneyActivity.class));
                break;
            case R.id.iv_service:
                break;
            case R.id.iv_right3:
                break;
            case R.id.iv_secret:
                break;
            case R.id.iv_right4:
                break;
            case R.id.re_service:
                startActivity(new Intent(getActivity(), ServiceActivity.class));
                break;
            case R.id.iv_parter:
                break;
            case R.id.iv_right5:
                break;
            case R.id.re_pater:
                Toast.makeText(mContext, "此功能暂未开放", Toast.LENGTH_SHORT).show();
                break;
            case R.id.iv_approve:
                break;
            case R.id.iv_right6:
                break;
            case R.id.re_approve:
                startActivity(new Intent(getActivity(), RenActivity.class));
                break;
            case R.id.iv_team:
                break;
            case R.id.iv_right7:
                break;
            case R.id.re_team:
                startActivity(new Intent(getActivity(), TeamActivity.class));
                break;
            case R.id.iv_finish:
                break;
            case R.id.iv_right8:
                break;
            case R.id.re_ok:
                startActivity(new Intent(getActivity(), PhActivity.class));
                break;
            case R.id.iv_set:
                break;
            case R.id.textView3:
                break;
            case R.id.iv_right9:
                break;
            case R.id.re_set:
                startActivity(new Intent(getActivity(), SettingActivity.class));
                break;
        }
    }

    @Override
    protected void onNetSuccess(int actionId, ResponseEntry result) {
        super.onNetSuccess(actionId, result);
        switch (actionId){
            case Urls.ActionId.my:
                if(result.isSuccess()){
                    mineBean=FJson.getObject(result.getData().toString(),MineBean.class);
                    System.out.println("444444444444444444444444444444444444444444444"+result);
                    if(null!=mineBean){
                        initView();
                    }
                }
                break;
        }
    }

    private void initView() {
        LeeTools.setText(tvNumber,mineBean.getOnly_id());
        LeeTools.setText(tvMingzi,mineBean.getTruename());
        if("1".equals(mineBean.getRealname_status())){
            check_name.setChecked(true);
        }else{
            check_name.setChecked(false);
        }
        LeeTools.setText(tvDengji,mineBean.getTitle());
       if(TextUtils.isEmpty(mineBean.getUser_pic())){
           ivHead.setImageResource(R.drawable.xiaotu);
       }else{
           XGlide.init(mContext).display(ivHead,mineBean.getUser_pic());
       }
    }
}
