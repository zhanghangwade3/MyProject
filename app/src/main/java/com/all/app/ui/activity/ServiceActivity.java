package com.all.app.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.all.app.R;
import com.all.app.app.AppData;
import com.all.app.base.BaseActivity;
import com.all.app.bean.GetServiceBean;
import com.all.app.configs.Urls;
import com.all.app.http.OkHttpParam;
import com.all.app.http.ResponseEntry;
import com.all.app.utils.ContainsEmojiEditText;
import com.all.app.utils.FJson;
import com.all.app.utils.LeeTools;
import com.all.app.utils.UnitChoose;
import com.all.app.utils.UnitChooseDate;
import com.all.app.utils.XGridView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//服务信息页面
public class ServiceActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_baocun)
    TextView tvBaocun;
    @BindView(R.id.re_title)
    RelativeLayout reTitle;
    @BindView(R.id.textView2)
    TextView textView2;
    @BindView(R.id.tv_jiaju)
    TextView tvJiaju;
    @BindView(R.id.iv_right2)
    ImageView ivRight2;
    @BindView(R.id.re_money)
    RelativeLayout reMoney;
    @BindView(R.id.tv_qu)
    TextView tvQu;
    @BindView(R.id.iv_right3)
    ImageView ivRight3;
    @BindView(R.id.re_renzheng)
    RelativeLayout reRenzheng;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_time_start)
    TextView tvTimeStart;
    @BindView(R.id.type_cang)
    TextView type_cang;
    @BindView(R.id.tv_area)
    TextView  tv_area;
    @BindView(R.id.iv_right4)
    ImageView ivRight4;
    @BindView(R.id.re_service)
    RelativeLayout reService;
    @BindView(R.id.re_cang)
    RelativeLayout  re_cang;
    @BindView(R.id.person)
    TextView person;
    @BindView(R.id.re_pater)
    RelativeLayout rePater;
   /* @BindView(R.id.tv_che)
    TextView tvChe;
    @BindView(R.id.type)
    TextView type;
    @BindView(R.id.tv_dun)
    TextView tvDun;*/
    @BindView(R.id.edt_desc)
    ContainsEmojiEditText edtDesc;
    @BindView(R.id.re_team)
    LinearLayout reTeam;
    @BindView(R.id.et_peo)
    ContainsEmojiEditText et_peo;
     XGridView service_grid;

    String type="";
     String area="";
     String time="";
     String teamNuber="";
     String storage="";
     String promise="";
     GetServiceBean getServiceBean;

    List<String> date = new ArrayList<String>(Arrays.asList("周一",
            "周二", "周三", "周四",
            "周五", "周六", "周日"));
    List<String> date2 = new ArrayList<String>(Arrays.asList("周一",
            "周二", "周三", "周四",
            "周五", "周六", "周日"));

    List<String> cangchu = new ArrayList<String>(Arrays.asList("不提供",
            "300平米以内", "300平米~1000平米以内", "1000平米以上"));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        service_grid= (XGridView) findViewById(R.id.service_grid);
         ButterKnife.bind(this);
        loadServiceData();//服务信息页面的读取

    }

    private void loadServiceData() {
        OkHttpParam param=new OkHttpParam();
        if (TextUtils.isEmpty(AppData.Init().getLoginBean().getUid())) {
            Toast.makeText(ServiceActivity.this, "用户的ID不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        param.add("uid", AppData.Init().getLoginBean().getUid());
        _PostEntry(Urls.getservice,param, Urls.ActionId.getservice,true);

    }

    @OnClick({R.id.re_cang,R.id.iv_back,R.id.et_peo, R.id.tv_baocun, R.id.re_title, R.id.textView2, R.id.tv_jiaju, R.id.iv_right2, R.id.re_money, R.id.tv_qu, R.id.iv_right3, R.id.re_renzheng, R.id.tv_time, R.id.tv_time_start,R.id.iv_right4, R.id.re_service, R.id.person,  R.id.re_pater, R.id.edt_desc, R.id.re_team})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
             break;
            case R.id.re_cang:
                UnitChoose<String> cheNum4 = new UnitChoose<String>(this, cangchu
                );
                cheNum4.setChooseListener(new UnitChoose.IChooseUnitListener<String>() {
                    @Override
                    public void onFinished(String result) {
                        type_cang.setText(result);
                    }
                });
                cheNum4.showDialog();
                break;
            case R.id.tv_baocun:
                submitServiceData();
                break;
            case R.id.et_peo:
                et_peo.setHint("");
                et_peo.setCursorVisible(true);
                break;
            case R.id.re_title:
                break;
            case R.id.textView2:
                break;
            case R.id.tv_jiaju:
                break;
            case R.id.iv_right2:
                break;
            case R.id.re_money:
                startActivity(new Intent(ServiceActivity.this, ServiceTypeActivity.class));
                break;
            case R.id.tv_qu:
                break;
            case R.id.iv_right3:
                break;
            case R.id.re_renzheng:
                startActivity(new Intent(ServiceActivity.this, SeriveAdressActivity.class));
                break;
            case R.id.tv_time:
                break;
            case R.id.tv_time_start:
                UnitChooseDate<String> cheNum2 = new UnitChooseDate<String>(this, date,date2);
                cheNum2.setChooseListener(new UnitChooseDate.IChooseUnitListener<String>() {
                    @Override
                    public void onFinished(String result) {
                        tvTimeStart.setText(result);
                    }
                });
                cheNum2.showDialog();
                break;
            case R.id.iv_right4:
                break;
            case R.id.re_service:
                break;
            case R.id.person:
                break;
            case R.id.re_pater:
                break;
          /*  case R.id.re_approve:
               startActivity(new Intent(ServiceActivity.this,CarListActivity.class));
                break;*/
            case R.id.edt_desc:
                break;
            case R.id.re_team:
                break;
        }
    }
    //上传服务信息的数据
    private void submitServiceData() {
        time=tvTimeStart.getText().toString();
        teamNuber=et_peo.getText().toString();
        storage=type_cang.getText().toString();
        promise=edtDesc.getText().toString();
         OkHttpParam param=new OkHttpParam();
        if (TextUtils.isEmpty(time)) {
            Toast.makeText(this, "请输入服务时间", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(teamNuber)) {
            Toast.makeText(this, "请输入团队人数", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(storage)) {
            Toast.makeText(this, "请输入仓储服务", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(promise)) {
            Toast.makeText(this, "请输入服务承诺", Toast.LENGTH_SHORT).show();
            return;
        }
         param.add("uid", AppData.Init().getLoginBean().getUid());
         param.add("serviceTime",time);
         param.add("teamnum",  teamNuber);
         param.add("storage", storage);
         param.add("promise",  promise);
        _PostEntry(Urls.saveservice,param, Urls.ActionId.saveservice,true);
    }

    @Override
    protected void onNetSuccess(int actionId, ResponseEntry result) {
        super.onNetSuccess(actionId, result);
       switch (actionId){
           case Urls.ActionId.getservice:
               if(result.isSuccess()){
                   getServiceBean= FJson.getObject(result.getData().toString(),GetServiceBean.class);
                    notifyData();

               }
               break;
           case Urls.ActionId.saveservice:
               if(result.isSuccess()){
                  startActivity(new Intent(ServiceActivity.this,MainActivity.class));
               }
               break;
       }
    }
     //刷新数据
    private void notifyData() {
        LeeTools.setText(tvJiaju,getServiceBean.getService_type());
        LeeTools.setText(tv_area,getServiceBean.getService_area());
        LeeTools.setText(tvTimeStart,getServiceBean.getService_time());
        LeeTools.setText(et_peo,getServiceBean.getTeamnum());
        LeeTools.setText(type_cang,getServiceBean.getStorage_size());
        LeeTools.setText(edtDesc,getServiceBean.getPromise());

    }
}
