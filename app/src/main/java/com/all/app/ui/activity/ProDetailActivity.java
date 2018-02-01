package com.all.app.ui.activity;

import android.content.Intent;
import android.net.Uri;
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
import com.all.app.bean.ProDetailBean;
import com.all.app.configs.Urls;
import com.all.app.http.OkHttpParam;
import com.all.app.http.ResponseEntry;
import com.all.app.ui.adapter.GridAdpter;
import com.all.app.utils.AlertDialog;
import com.all.app.utils.FJson;
import com.all.app.utils.LeeTools;
import com.all.app.utils.XGridView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
//问题详情页面
public class ProDetailActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.re_title)
    RelativeLayout reTitle;
    @BindView(R.id.tv_titl)
    TextView tvTitl;
    @BindView(R.id.tv_news1)
    TextView tvNews1;
    @BindView(R.id.tv_number)
    TextView tvNumber;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.tv_pric)
    TextView tvPric;
    @BindView(R.id.tv_wen)
    TextView tvWen;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.view_grid)
    XGridView viewGrid;
    @BindView(R.id.tv_advice)
    TextView tvAdvice;
    @BindView(R.id.tv_cont)
    TextView tvCont;
    @BindView(R.id.other_cancle)
    TextView otherCancle;
    @BindView(R.id.phone_kefu)
    TextView phone_kefu;
    @BindView(R.id.re_cancle)
    RelativeLayout reCancle;

    @BindView(R.id. ll_pro)
    LinearLayout ll_pro;


    List<String> pictures=new ArrayList<String>();
    GridAdpter gridAdpter;
    String quid = "";
    ProDetailBean proDetailBean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pro_detail);
        ButterKnife.bind(this);
        quid=getIntent().getStringExtra("quid");
        loadData();//请求问题详情的数据
    }

    private void loadData() {
        OkHttpParam param = new OkHttpParam();
        if (TextUtils.isEmpty(AppData.Init().getLoginBean().getUid())) {
            Toast.makeText(ProDetailActivity.this, "用户的ID不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(quid)) {
            Toast.makeText(ProDetailActivity.this, "问题详情的ID不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        param.add("uid", AppData.Init().getLoginBean().getUid());
        param.add("quid",quid);
        _PostEntry(Urls.getprobleminfo , param, Urls.ActionId.getprobleminfo , true);
    }

    @OnClick({R.id.other_cancle,R.id.phone_kefu,R.id.iv_back, R.id.tv_title, R.id.re_title})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.other_cancle:
                OkHttpParam param = new OkHttpParam();
                if (TextUtils.isEmpty(AppData.Init().getLoginBean().getUid())) {
                    Toast.makeText(ProDetailActivity.this, "用户的ID不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(quid)) {
                    Toast.makeText(ProDetailActivity.this, "问题详情的ID不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                param.add("uid", AppData.Init().getLoginBean().getUid());
                param.add("quid",quid);
                _PostEntry(Urls.processtask , param, Urls.ActionId.processtask , true);
                break;
            case R.id.phone_kefu:
                AlertDialog dialog = new AlertDialog(ProDetailActivity.this);
                dialog.builder();
                dialog.setTitle("客服电话");
                dialog.setMsg("是否拨打客服电话：\n" + proDetailBean.getKftel());

                dialog.setPositiveButton("拨打", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                        Uri uri = Uri.parse("tel:" +  proDetailBean.getKftel());
                        Intent intent = new Intent(Intent.ACTION_DIAL, uri);
                        startActivity(intent);
                    }
                });
                dialog.setNegativeButton("取消", new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                    }
                });
                dialog.show();

                break;
            case R.id.re_title:
                break;
        }
    }

    @Override
    protected void onNetSuccess(int actionId, ResponseEntry result) {
        super.onNetSuccess(actionId, result);
        switch (actionId){
            case Urls.ActionId.getprobleminfo:
                if(result.isSuccess()){
                    System.out.println("==================8888888888===========================>"+result);
                    proDetailBean= FJson.getObject(result.getData().toString(),ProDetailBean.class);
                    if(null!= proDetailBean){
                        notifyView();//刷新列表
                    }
                }
                break;
            case Urls.ActionId.processtask :
                if(result.isSuccess()){
                    Toast.makeText(this,"提交成功",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
    private void notifyView() {
        pictures=proDetailBean.getQpic();
        gridAdpter=new GridAdpter(this, (ArrayList<String>) pictures);
        viewGrid.setAdapter(gridAdpter);

        LeeTools.setText(tvTitl,proDetailBean.getTask_title());
        LeeTools.setText(tvNumber,proDetailBean.getTask_id());
        LeeTools.setText(tvPric,proDetailBean.getOnly_id());
        LeeTools.setText(tvContent,proDetailBean.getQuestion());
        LeeTools.setText(tvCont,proDetailBean.getOpinion());
        if(TextUtils.isEmpty(proDetailBean.getOpinion())){
            ll_pro.setVisibility(View.GONE);
            otherCancle.setVisibility(View.INVISIBLE);
        }else{
            ll_pro.setVisibility(View.VISIBLE);
            otherCancle.setVisibility(View.VISIBLE);
        }

    }
}
