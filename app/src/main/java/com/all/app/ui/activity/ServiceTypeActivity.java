package com.all.app.ui.activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.alibaba.fastjson.JSONException;
import com.all.app.R;
import com.all.app.app.AppData;
import com.all.app.base.BaseActivity;
import com.all.app.bean.CheckState;
import com.all.app.bean.ServiceBean;
import com.all.app.configs.Urls;
import com.all.app.http.OkHttpParam;
import com.all.app.http.ResponseEntry;
import com.all.app.ui.adapter.ServiceTypeAdpter;
import com.all.app.utils.FJson;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ServiceTypeActivity extends BaseActivity {
    String uid = "";
    ServiceTypeAdpter serviceTypeAdpter;
    List<ServiceBean> serviceBean;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_chucun)
    TextView tvChucun;
    @BindView(R.id.re_title)
    RelativeLayout reTitle;
    ListView lvService;
    String info="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_type);
        lvService = (ListView) findViewById(R.id.lv_service);
        ButterKnife.bind(this);
        uid = AppData.Init().getLoginBean().getUid();
        loadData();//请求服务类型的数据

    }

    private void loadType() {
        OkHttpParam param = new OkHttpParam();
        if (TextUtils.isEmpty(AppData.Init().getLoginBean().getUid())) {
            Toast.makeText(ServiceTypeActivity.this, "用户的ID不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        param.add("uid",AppData.Init().getLoginBean().getUid());
        _PostEntry(Urls.getservicetype, param, Urls.ActionId.getservicetype, true);

    }

    private void loadData() {
        OkHttpParam param = new OkHttpParam();
        _PostEntry(Urls.ServiceType, param, Urls.ActionId.ServiceType, true);
    }


    @Override
    protected void onNetSuccess(int actionId, ResponseEntry result) {
        super.onNetSuccess(actionId, result);
        switch (actionId) {
            case Urls.ActionId.ServiceType:
                if (result.isSuccess()) {
                    System.out.println("33333333333333333333333333" + result);
                    serviceBean = FJson.getObjects(result.getData().toString(), ServiceBean.class);
                    serviceTypeAdpter = new ServiceTypeAdpter(this, serviceBean);
                    lvService.setAdapter(serviceTypeAdpter);
                    setListViewHeightBasedOnChildren(lvService);
                    loadType();//服务类型保存状态的读取
                }
                break;
            case Urls.ActionId.saveservicetype:
                if (result.isSuccess()) {
                    Toast.makeText(this,"保存成功",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(ServiceTypeActivity.this,ServiceActivity.class));
                }
                break;
            case Urls.ActionId.getservicetype:
                if (result.isSuccess()) {
                    List<CheckState> data=dealData(result.getData().toString(),serviceTypeAdpter.getDataList());
                    serviceTypeAdpter.setCheckDataList(data);
                    lvService.setAdapter(serviceTypeAdpter);
                }
                break;

        }

    }

    private void setListViewHeightBasedOnChildren(ListView lvService) {
        ListAdapter listAdapter = lvService.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;

        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, lvService);
            if (listItem != null) {
                listItem.measure(0, 0);
                totalHeight += listItem.getMeasuredHeight();
            }
        }
        ViewGroup.LayoutParams params = lvService.getLayoutParams();
        params.height = totalHeight + (lvService.getDividerHeight() * (listAdapter.getCount() - 1));
        lvService.setLayoutParams(params);

    }

    @OnClick({R.id.iv_back, R.id.tv_title, R.id.tv_chucun, R.id.re_title})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
               finish();
                break;
            case R.id.tv_title:
                break;
            case R.id.tv_chucun:
                submitDataToServe();
                break;
            case R.id.re_title:
                break;
        }
    }

    private void submitDataToServe()  {
        List<CheckState>  data=  serviceTypeAdpter.getDataList();
        JSONObject obj = new JSONObject();
        try {
            for (int i=0;i<data.size();i++){
                String putvalue="";
                List<Boolean>  checks=  data.get(i).getmGvSelects();
                //遍历每个元素里的集合
                for (int j=0;j<checks.size();j++){
                    if (checks.get(j)){//是选中状态
                        putvalue+=(i+1)*10+(j+1)+",";//"11,12,13,14,15,"
                    }
                }
                //处理数据并保存
                if (putvalue!=""){
                    putvalue = putvalue.substring(0,putvalue.length()-1);//去除末尾的“，”号
                    obj.put((i+1)*10+"",putvalue);
                }
            }
            info=obj.toString().replace("\",\"","\"|\"");
            System.out.println("==========666666666666666666"+ obj.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (org.json.JSONException e) {
            e.printStackTrace();
        }
        OkHttpParam param = new OkHttpParam();
        param.add("serviceType", info);
        param.add("uid", uid);
        _PostEntry(Urls.saveservicetype, param, Urls.ActionId.saveservicetype, true);
    }

    private List<CheckState> dealData(String json,List<CheckState> states){
        try {
            JSONObject myJsonObject = new JSONObject(json);
            Iterator it = myJsonObject.keys();
            while (it.hasNext()) {//遍历key
                String key= (String) it.next();
                System.out.println("key===="+key);
                for (int j=0;j<states.size();j++){//遍历值
                    if (key.equals((j+1)*10+"")){//10，20,30。。。,只能用于10个元素以内的情况
                        CheckState state = states.get(j);
                        state.setCheck(true);
                        String value= (String) myJsonObject.get(key);
                        String[] values= value.split(",");
                        ArrayList<Boolean> datas=state.getmGvSelects();

                        for (int l=0;l<values.length;l++){//遍历值
                            for (int k=0;k<datas.size();k++){//遍历值

                                if (values[l].equals((j+1)*10+k+1+"")){//11，12,13。。。111,只能用于10个元素以内的情况
                                    datas.set(k,true);
                                }
                            }
                        }
                        states.set(j,state);
                    }
                }
            }
        } catch (org.json.JSONException e) {
            e.printStackTrace();

        }
        for (int k=0;k<states.size();k++){
            System.out.println("=============isCheck====="+states.get(k).isCheck());
            for (int q=0;q<states.get(k).getmGvSelects().size();q++){
                System.out.println(" =============getmGvSelects=====  "+states.get(k).getmGvSelects().get(q));
            }
        }
        return states;
    }
}