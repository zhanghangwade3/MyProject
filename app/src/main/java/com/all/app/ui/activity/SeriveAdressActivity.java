package com.all.app.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONException;
import com.all.app.R;
import com.all.app.app.AppData;
import com.all.app.base.BaseActivity;
import com.all.app.bean.AreaBean;
import com.all.app.bean.CheckState;
import com.all.app.bean.CheckState2;
import com.all.app.configs.Urls;
import com.all.app.http.OkHttpParam;
import com.all.app.http.ResponseEntry;
import com.all.app.ui.adapter.GridTextAdpter;
import com.all.app.utils.FJson;
import com.all.app.utils.XGridView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.media.CamcorderProfile.get;
import static com.all.app.R.drawable.service;

public class SeriveAdressActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_baocun)
    TextView tvBaocun;
    @BindView(R.id.re_title)
    RelativeLayout reTitle;
    @BindView(R.id.tv_ti)
    TextView tvTi;
    @BindView(R.id.tv_ti2)
    TextView tvTi2;
    GridTextAdpter gridTextAdpter;
    XGridView serviceGrid;
    List<AreaBean> areaBean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serive_adress);
        serviceGrid = (XGridView) findViewById(R.id.service_grid);
        ButterKnife.bind(this);
        loadAreaData();//请求服务区域的所有数据
    }

    private void loadAreaData() {
        OkHttpParam param = new OkHttpParam();
        if (TextUtils.isEmpty(AppData.Init().getLoginBean().getCityname())) {
            Toast.makeText(SeriveAdressActivity.this, "用户的ID不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        param.add("city", AppData.Init().getLoginBean().getCityname());
        System.out.println("55555555555555555555555555555555555555555"+AppData.Init().getCityStr());
        _PostEntry(Urls.getarea, param, Urls.ActionId.getarea, true);
    }

    @OnClick({R.id.iv_back, R.id.tv_title, R.id.tv_baocun, R.id.re_title, R.id.tv_ti, R.id.tv_ti2,})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                  finish();
                break;
            case R.id.tv_title:
                break;
            case R.id.tv_baocun:
                submitData();
                break;
            case R.id.re_title:
                break;
            case R.id.tv_ti:
                break;
            case R.id.tv_ti2:
                break;

        }
    }

    //保存服务区域数据
    private void submitData() {
        String service="";
        ArrayList<Boolean> stste=gridTextAdpter.getSelectState();
        for (int i=0;i<stste.size();i++){
            if ( stste.get(i)==true){
                service+=areaBean.get(i).getId()+",";
            }
        }
        String info=service.substring(0,service.length()-1);
        System.out.println("==========666666666666666666"+ info);
         OkHttpParam param = new OkHttpParam();
        if (TextUtils.isEmpty(AppData.Init().getLoginBean().getUid())) {
            Toast.makeText(SeriveAdressActivity.this, "用户的ID不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        param.add("uid", AppData.Init().getLoginBean().getUid());
        param.add("serviceArea", info);
        _PostEntry(Urls.savearea, param, Urls.ActionId.savearea, true);
    }

    @Override
    protected void onNetSuccess(int actionId, ResponseEntry result) {
        super.onNetSuccess(actionId, result);
        switch (actionId) {
            case Urls.ActionId.getarea:
                if (result.isSuccess()) {
                    areaBean = FJson.getObjects(result.getData().toString(), AreaBean.class);
                    gridTextAdpter = new GridTextAdpter(this, areaBean);
                    serviceGrid.setAdapter(gridTextAdpter);
                    readsStatue();
                }
                break;
            case Urls.ActionId.savearea:
                if(result.isSuccess()){
                    Toast.makeText(this,"区域信息保存成功！",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(SeriveAdressActivity.this,ServiceActivity.class));
                }
                break;
            case Urls.ActionId.readarea:
                if(result.isSuccess()){
                    String test=result.getData().toString();//测试数据，模拟用户保存到数据库的服务区域数据
                    String[] data=test.split(",");
                    ArrayList<Boolean> st= gridTextAdpter.getSelectState();
                    for (int i=0;i<st.size();i++){//遍历每个gridview条目的服务区域id
                        for (int j=0;j<data.length;j++){//遍历测试数据
                            if ( areaBean.get(i).getId().equals(data[j])){//当服务区域id=测试数据的id,说明该服务区域被选中
                                st.set(i,true);
                                break;
                            }
                        }
                    }
                    gridTextAdpter.setSelectState(st);
                    serviceGrid.setAdapter(gridTextAdpter);

                }
                break;
        }
    }

    private void readsStatue() {
        OkHttpParam param = new OkHttpParam();
        if (TextUtils.isEmpty(AppData.Init().getLoginBean().getUid())) {
            Toast.makeText(SeriveAdressActivity.this, "用户的ID不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        param.add("uid",AppData.Init().getLoginBean().getUid());
        _PostEntry(Urls.readarea, param, Urls.ActionId.readarea, true);
    }

}