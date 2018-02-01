package com.all.app.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.all.app.R;
import com.all.app.app.AppData;
import com.all.app.base.BaseActivity;
import com.all.app.bean.FileBean;
import com.all.app.bean.OrderInfoBean;
import com.all.app.configs.Urls;
import com.all.app.http.OkHttpParam;
import com.all.app.http.ResponseEntry;
import com.all.app.utils.FJson;
import com.all.app.utils.LeeTools;
import com.all.app.utils.XGlide;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OrderDetailsActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_comment)
    TextView tvComment;
    @BindView(R.id.vi2)
    View vi2;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.vi3)
    View vi3;
    @BindView(R.id.iv_name)
    ImageView ivName;
    @BindView(R.id.tv_news)
    TextView tvNews;
    @BindView(R.id.vi4)
    View vi4;
    @BindView(R.id.tv_news1)
    TextView tvNews1;
    @BindView(R.id.tv_number)
    TextView tvNumber;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.tv_pric)
    TextView tvPric;
    @BindView(R.id.tv_addre)
    TextView tvAddre;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.vi5)
    View vi5;
    @BindView(R.id.iv_pro)
    ImageView ivPro;
    @BindView(R.id.tv_pro)
    TextView tvPro;
    @BindView(R.id.vi6)
    View vi6;
    @BindView(R.id.iv_pic)
    ImageView ivPic;
    @BindView(R.id.vi7)
    View vi7;
    @BindView(R.id.tv_xuqiu)
    TextView tvXuqiu;
    @BindView(R.id.vi8)
    View vi8;
    @BindView(R.id.iv_cus)
    ImageView ivCus;
    @BindView(R.id.tv_cuss)
    TextView tvCuss;
    @BindView(R.id.vi9)
    View vi9;
    @BindView(R.id.tv_cus1)
    TextView tvCus1;
    @BindView(R.id.tv_cusname)
    TextView tvCusname;
    @BindView(R.id.tv_floor)
    TextView tvFloor;
    @BindView(R.id.tv_floornum)
    TextView tvFloornum;
    @BindView(R.id.tv_wrok)
    TextView tvWrok;
    @BindView(R.id.tv_wrokp)
    TextView tvWrokp;
    @BindView(R.id.vi10)
    View vi10;
    @BindView(R.id.iv_wu)
    ImageView ivWu;
    @BindView(R.id.tv_wu)
    TextView tvWu;
    @BindView(R.id.vi11)
    View vi11;
    @BindView(R.id.tv_wunum)
    TextView tvWunum;
    @BindView(R.id.tv_wuname)
    TextView tvWuname;
    @BindView(R.id.tv_wuming)
    TextView tvWuming;
    @BindView(R.id.tv_nicheng)
    TextView tvNicheng;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_phonen)
    TextView tvPhonen;
    @BindView(R.id.tv_accp)
    TextView tvAccp;
    @BindView(R.id.tv_acc)
    TextView tvAcc;
    @BindView(R.id.tv_tiji)
    TextView tvTiji;
    @BindView(R.id.tv_tinum)
    TextView tvTinum;
    @BindView(R.id.tv_get)
    TextView tvGet;
    @BindView(R.id.tv_getaddress)
    TextView tvGetaddress;
    @BindView(R.id.tv_bao)
    TextView  tv_bao;
    @BindView(R.id.tv_nubder)
    TextView  tv_nubder;
    @BindView(R.id.tv_fu1)
    TextView  tv_fu1;
    @BindView(R.id.tv_fu2)
    TextView  tv_fu2;
    @BindView(R.id.tv_fu3)
    TextView  tv_fu3;
    @BindView(R.id.tv_fu4)
    TextView  tv_fu4;
    @BindView(R.id.tv_fu5)
    TextView  tv_fu5;
    @BindView(R.id.ll_wuliu)
    LinearLayout ll_wuliu;
    int size=0;
    String info="";
    String info2="";
    String info3="";
    String info4="";
    String info5="";
    String single="";
    String taskid="";//订单的Id
    OrderInfoBean orderInfoBean;
    ArrayList<FileBean> task_file;
    ArrayList<String> task_pic;
    String pic="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);
        ButterKnife.bind(this);
        taskid=getIntent().getStringExtra("data-id");
        loadData();//请求详情的数据

    }

    private void loadData() {
        OkHttpParam param=new OkHttpParam();
        if (TextUtils.isEmpty(AppData.Init().getLoginBean().getUid())) {
            Toast.makeText(OrderDetailsActivity.this, "用户的ID不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(taskid)) {
            Toast.makeText(OrderDetailsActivity.this, "任务的ID不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        param.add("uid", AppData.Init().getLoginBean().getUid());
        param.add("taskid", taskid);
    _PostEntry(Urls.orderinfo,param, Urls.ActionId.orderinfo,true);
    }

    @OnClick({R.id.tv_bao,R.id.iv_back, R.id.vi2, R.id.iv_name, R.id.tv_news})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                String type="1";
                Intent intent1=new Intent(OrderDetailsActivity.this,MainActivity.class);
                intent1.putExtra("type",type);
                startActivity(intent1);
                break;
            case R.id.tv_bao:
               Intent intent=new Intent(OrderDetailsActivity.this,TaskOfferActivity.class);
                intent.putExtra("taskid",taskid);
                startActivity(intent);
                break;
            case R.id.vi2:
                break;
            case R.id.iv_name:
                break;
            case R.id.tv_news:
                break;
        }
    }

    @Override
    protected void onNetSuccess(int actionId, ResponseEntry result) {
        super.onNetSuccess(actionId, result);
       switch (actionId){
           case  Urls.ActionId.orderinfo:
               if (result.isSuccess()){
                   orderInfoBean= FJson.getObject(result.getData().toString(),OrderInfoBean.class);
                  if(null!= orderInfoBean){
                      notifyData();
                      task_pic= orderInfoBean.getTask_pic();
                      task_file= orderInfoBean.getTask_file();
                      if(task_pic.size()==0){
                          XGlide.init(this).display(ivPic,R.drawable.xiaotu);
                          tv_nubder.setText("0");
                      }else {
                          XGlide.init(this).display(ivPic, task_pic.get(0));
                          pic=task_pic.get(0);
                          ivPic.setOnClickListener(new View.OnClickListener() {
                              @Override
                              public void onClick(View v) {
                                  Intent intent=new Intent(OrderDetailsActivity.this,PicActivity.class);
                                  intent.putExtra("pic",pic);
                                  startActivity(intent);
                              }
                          });
                          tv_nubder.setText(String.valueOf(task_pic.size()));
                      }
                      initFuData();
                  }


               }else{
                   showTips("加载失败，请重新刷新",null);
               }

       }

    }
     //初始化附件信息
    private void initFuData() {
        size=task_file.size();
        if(1==size){
            info=task_file.get(0).getSave_name();
            tv_fu1.setVisibility(View.VISIBLE);
            tv_fu1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    single="1" ;
                    Intent intent1=new Intent(OrderDetailsActivity.this,LookFuActivity.class);
                    intent1.putExtra("info",info);
                    intent1.putExtra("single",single);
                    startActivity(intent1);
                }
            });
        }else if (2==size){
            info=task_file.get(0).getSave_name();
            info2=task_file.get(1).getSave_name();
            tv_fu1.setVisibility(View.VISIBLE);
            tv_fu2.setVisibility(View.VISIBLE);
            tv_fu1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    single="1" ;
                    Intent intent1=new Intent(OrderDetailsActivity.this,LookFuActivity.class);
                    intent1.putExtra("info",info);
                    intent1.putExtra("single",single);
                    startActivity(intent1);
                }
            });
            tv_fu2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    single="2" ;
                    Intent intent1=new Intent(OrderDetailsActivity.this,LookFuActivity.class);
                    intent1.putExtra("info2",info2);
                    intent1.putExtra("single",single);
                    startActivity(intent1);
                }
            });
        }else if (3==size){
            info=task_file.get(0).getSave_name();
            info2=task_file.get(1).getSave_name();
            info3=task_file.get(2).getSave_name();
            tv_fu1.setVisibility(View.VISIBLE);
            tv_fu2.setVisibility(View.VISIBLE);
            tv_fu3.setVisibility(View.VISIBLE);
            tv_fu1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    single="1" ;
                    Intent intent1=new Intent(OrderDetailsActivity.this,LookFuActivity.class);
                    intent1.putExtra("info",info);
                    intent1.putExtra("single",single);
                    startActivity(intent1);
                }
            });
            tv_fu2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    single="2" ;
                    Intent intent1=new Intent(OrderDetailsActivity.this,LookFuActivity.class);
                    intent1.putExtra("info2",info2);
                    intent1.putExtra("single",single);
                    startActivity(intent1);
                }
            });
            tv_fu3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    single="3" ;
                    Intent intent1=new Intent(OrderDetailsActivity.this,LookFuActivity.class);
                    intent1.putExtra("info3",info3);
                    intent1.putExtra("single",single);
                    startActivity(intent1);
                }
            });
        }else if (4==size){
            info=task_file.get(0).getSave_name();
            info2=task_file.get(1).getSave_name();
            info3=task_file.get(2).getSave_name();
            info4=task_file.get(3).getSave_name();
            tv_fu1.setVisibility(View.VISIBLE);
            tv_fu2.setVisibility(View.VISIBLE);
            tv_fu3.setVisibility(View.VISIBLE);
            tv_fu4.setVisibility(View.VISIBLE);
            tv_fu1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    single="1" ;
                    Intent intent1=new Intent(OrderDetailsActivity.this,LookFuActivity.class);
                    intent1.putExtra("info",info);
                    intent1.putExtra("single",single);
                    startActivity(intent1);
                }
            });
            tv_fu2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    single="2" ;
                    Intent intent1=new Intent(OrderDetailsActivity.this,LookFuActivity.class);
                    intent1.putExtra("info2",info2);
                    intent1.putExtra("single",single);
                    startActivity(intent1);
                }
            });
            tv_fu3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    single="3" ;
                    Intent intent1=new Intent(OrderDetailsActivity.this,LookFuActivity.class);
                    intent1.putExtra("info3",info3);
                    intent1.putExtra("single",single);
                    startActivity(intent1);
                }
            });
            tv_fu4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    single="4" ;
                    Intent intent1=new Intent(OrderDetailsActivity.this,LookFuActivity.class);
                    intent1.putExtra("info4",info4);
                    intent1.putExtra("single",single);
                    startActivity(intent1);
                }
            });
        }else if (5==size){
            info=task_file.get(0).getSave_name();
            info2=task_file.get(1).getSave_name();
            info3=task_file.get(2).getSave_name();
            info4=task_file.get(3).getSave_name();
            info5=task_file.get(4).getSave_name();
            tv_fu1.setVisibility(View.VISIBLE);
            tv_fu2.setVisibility(View.VISIBLE);
            tv_fu3.setVisibility(View.VISIBLE);
            tv_fu4.setVisibility(View.VISIBLE);
            tv_fu5.setVisibility(View.VISIBLE);
            tv_fu1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    single="1" ;
                    Intent intent1=new Intent(OrderDetailsActivity.this,LookFuActivity.class);
                    intent1.putExtra("info",info);
                    intent1.putExtra("single",single);
                    startActivity(intent1);
                }
            });
            tv_fu2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    single="2" ;
                    Intent intent1=new Intent(OrderDetailsActivity.this,LookFuActivity.class);
                    intent1.putExtra("info2",info2);
                    intent1.putExtra("single",single);
                    startActivity(intent1);
                }
            });
            tv_fu3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    single="3" ;
                    Intent intent1=new Intent(OrderDetailsActivity.this,LookFuActivity.class);
                    intent1.putExtra("info3",info3);
                    intent1.putExtra("single",single);
                    startActivity(intent1);
                }
            });
            tv_fu4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    single="4" ;
                    Intent intent1=new Intent(OrderDetailsActivity.this,LookFuActivity.class);
                    intent1.putExtra("info4",info4);
                    intent1.putExtra("single",single);
                    startActivity(intent1);
                }
            });
            tv_fu5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    single="5" ;
                    Intent intent1=new Intent(OrderDetailsActivity.this,LookFuActivity.class);
                    intent1.putExtra("info5",info5);
                    intent1.putExtra("single",single);
                    startActivity(intent1);
                }
            });
        }


    }

    //详情的数据填充
    private void notifyData() {
        if(orderInfoBean!=null){
            LeeTools.setText(tvName, orderInfoBean.getTask_title());
            LeeTools.setText(tvNumber, orderInfoBean.getOnly_id());
            if(TextUtils.isEmpty(orderInfoBean.getTask_price())){
                tvPric.setText("暂无");
            }else{
                LeeTools.setText(tvPric, orderInfoBean.getTask_price());
            }
            LeeTools.setText(tvAddress, orderInfoBean.getSjaddress());
            LeeTools.setText(tvXuqiu, "需求描述："+orderInfoBean.getTask_desc());
            LeeTools.setText(tvCusname, orderInfoBean.getProject_name());
            LeeTools.setText(tvFloornum, orderInfoBean.getIselevator());
            LeeTools.setText(tvWrokp, orderInfoBean.getKhaddress());
            String is_pick=orderInfoBean.getIs_pick();
            if("1".equals(is_pick)){
                LeeTools.setText(tvWuname, orderInfoBean.getPick_id());
                LeeTools.setText(tvNicheng, orderInfoBean.getPick_department());
                LeeTools.setText(tvPhonen, orderInfoBean.getPick_tel());
                LeeTools.setText(tvAcc, orderInfoBean.getPick_name());
                LeeTools.setText(tvTinum, orderInfoBean.getPick_number());
                LeeTools.setText(tvGetaddress, orderInfoBean.getPick_address());
            }else{
                ll_wuliu.setVisibility(View.GONE);
            }
            int offer= Integer.parseInt(orderInfoBean.getTaskbidnum());
              if(offer>0){
                  tv_bao.setEnabled(false);
                  tv_bao.setText("您已报价");
              }else{
                  tv_bao.setEnabled(true);
                  tv_bao.setText("我要报价");
              }

        }

    }
}
