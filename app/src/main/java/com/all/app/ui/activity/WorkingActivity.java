package com.all.app.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.all.app.R;
import com.all.app.app.AppData;
import com.all.app.base.BaseActivity;
import com.all.app.bean.FileBean;
import com.all.app.bean.OrderDeatailBean;
import com.all.app.configs.Urls;
import com.all.app.http.OkHttpParam;
import com.all.app.http.ResponseEntry;
import com.all.app.utils.AlertDialog;
import com.all.app.utils.FJson;
import com.all.app.utils.LeeTools;
import com.all.app.utils.XGlide;

import java.lang.reflect.Array;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//待施工详情
public class WorkingActivity extends BaseActivity {


    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_comment)
    TextView tvComment;
    @BindView(R.id.vi2)
    View vi2;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_days)
    TextView tvDays;
    @BindView(R.id.tv_day)
    TextView tvDay;
    @BindView(R.id.tv_hours)
    TextView tvHours;
    @BindView(R.id.tv_hour)
    TextView tvHour;
    @BindView(R.id.tv_minut)
    TextView tvMinut;
    @BindView(R.id.tv_minutr)
    TextView tvMinutr;
    @BindView(R.id.tv_secondp)
    TextView tvSecondp;
    @BindView(R.id.tv_second)
    TextView tvSecond;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.vi3)
    View vi3;
    @BindView(R.id.vi4)
    View vi4;
    @BindView(R.id.tv_news1)
    TextView tvNews1;
    @BindView(R.id.tv_number)
    TextView tvNumber;
    @BindView(R.id.tv_wen)
    TextView tvWen;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.tv_pric)
    TextView tvPric;
    @BindView(R.id.tv_addre)
    TextView tvAddre;
    @BindView(R.id.shangjia)
    ImageView shangjia;
    @BindView(R.id.vi5)
    View vi5;
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
    @BindView(R.id.custom)
    ImageView custom;
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
    @BindView(R.id.logis)
    ImageView logis;
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
    @BindView(R.id.iv_miao)
    ImageView ivMiao;
    @BindView(R.id.tv_miao)
    TextView tvMiao;
    @BindView(R.id.vi6)
    View vi6;
    @BindView(R.id.iv_tu)
    ImageView ivTu;

    @BindView(R.id.tv_fu1)
    TextView tv_fu1;
    @BindView(R.id.tv_fu2)
    TextView tv_fu2;
    @BindView(R.id.tv_fu3)
    TextView tv_fu3;
    @BindView(R.id.tv_fu4)
    TextView tv_fu4;
    @BindView(R.id.tv_fu5)
    TextView tv_fu5;
    @BindView(R.id.tv_nubder)
    TextView  tv_nubder;
    @BindView(R.id.tv_getadd)
    TextView tvGetadd;
    @BindView(R.id.tv_content)
    TextView tv_content;
    @BindView(R.id.tv_shi)
    TextView tv_shi;
    @BindView(R.id.tv_tel)
    TextView tv_tel;
    @BindView(R.id.ll_time)
    LinearLayout ll_time;
    @BindView(R.id.ll_wuliu)
    LinearLayout ll_wuliu;
    @BindView(R.id.re_deail)
    RelativeLayout re_deail;
    String taskid = "";
    String type = "";
    OrderDeatailBean orderDeatailBean;
    String is_pick = "";
    String timestr="";
    ArrayList<FileBean>task_file;
    ArrayList<String> task_pic;
    int time=0;
    private long mDay = 0;
    private long mHour = 0;
    private long mMin = 0;
    private long mSecond = 0;// 天 ,小时,分钟,秒
    private boolean isRun = true;
     int size=0;
    String info="";
    String info2="";
    String info3="";
    String info4="";
    String info5="";
    String single="";
    String pic="";
    //倒计时功能
    private Handler timeHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                computeTime();
                     tvDays.setText(mDay + "");
                     tvHours.setText(mHour + "");
                     tvMinut.setText(mMin + "");
                     tvSecondp.setText(mSecond + "");

                if (mDay == 0 && mHour == 0 && mMin == 0 && mSecond == 0) {
                    //countDown.setVisibility(View.GONE);
                }
            }
        }
    };

    private void computeTime() {
        mSecond--;
        if (mSecond < 0) {
            mMin--;
            mSecond = 59;
            if (mMin < 0) {
                mMin = 59;
                mHour--;
                if (mHour < 0) {
                    // 倒计时结束
                    mHour = 23;
                    mDay--;
                }
            }
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shi_details);
        ButterKnife.bind(this);
         type=getIntent().getStringExtra("type");
         if("1".equals(type)){
             tvComment.setText("待提货详情");
             tv_shi.setText("完成提货");
         }else if("2".equals(type)){
             tvComment.setText("待施工详情");
             tv_shi.setText("完成施工");
         }
        taskid = getIntent().getStringExtra("taskid");
        loadOrderData(); //请求详情页数据

    }

    private void loadOrderData() {
        OkHttpParam param = new OkHttpParam();
        if (TextUtils.isEmpty(AppData.Init().getLoginBean().getUid())) {
            Toast.makeText(WorkingActivity.this, "用户的ID不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        param.add("uid", AppData.Init().getLoginBean().getUid());
        param.add("taskid", taskid);
        _PostEntry(Urls.getorderinfo, param, Urls.ActionId.getorderinfo, true);
    }

    @OnClick({R.id.tv_shi, R.id.tv_wen, R.id.iv_back, R.id.vi2, R.id.shangjia, R.id.custom, R.id.logis})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_shi:
                Intent intent1=new Intent(WorkingActivity.this,SubFinishActivity.class);
                intent1.putExtra("taskid",taskid);
                intent1.putExtra("type",type);
                intent1.putExtra("is_pick",is_pick);
                startActivity(intent1);
                break;
            case R.id.tv_wen:
                Intent intent=new Intent(WorkingActivity.this,FeedbackActivity.class);
                intent.putExtra("taskid",taskid);
                startActivity(intent);
                break;
            case R.id.shangjia:
                AlertDialog dialog1 = new AlertDialog(WorkingActivity.this);
                dialog1.builder();
                dialog1.setTitle("商家电话");
                dialog1.setMsg("是否拨打商家电话：\n" + orderDeatailBean.getMobile());
                dialog1.setPositiveButton("拨打", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                        Uri uri = Uri.parse("tel:" + orderDeatailBean.getMobile());
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
            case R.id.custom:
                AlertDialog dialog2 = new AlertDialog(WorkingActivity.this);
                dialog2.builder();
                dialog2.setTitle("客户电话");
                dialog2.setMsg("是否拨打客户电话：\n" + orderDeatailBean.getProject_tel());
                dialog2.setPositiveButton("拨打", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                        Uri uri = Uri.parse("tel:" + orderDeatailBean.getProject_tel());
                        Intent intent = new Intent(Intent.ACTION_DIAL, uri);
                        startActivity(intent);
                    }
                });
                dialog2.setNegativeButton("取消", new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                    }
                });
                dialog2.show();
                break;
            case R.id.logis:
                AlertDialog dialog = new AlertDialog(WorkingActivity.this);
                dialog.builder();
                dialog.setTitle("物流电话");
                dialog.setMsg("是否拨打物流电话：\n" + orderDeatailBean.getPick_tel());
                dialog.setPositiveButton("拨打", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                        Uri uri = Uri.parse("tel:" + orderDeatailBean.getPick_tel());
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
        }
    }

    @Override
    protected void onNetSuccess(int actionId, ResponseEntry result) {
        super.onNetSuccess(actionId, result);
        switch (actionId) {
            case Urls.ActionId.getorderinfo:
                if (result.isSuccess()) {
                    System.out.println("888888888888888888888888888888888888"+result);
                    orderDeatailBean = FJson.getObject(result.getData().toString(), OrderDeatailBean.class);
                    if (null != orderDeatailBean) {
                        notifyView();
                        is_pick = orderDeatailBean.getIs_pick();
                        timestr = orderDeatailBean.getTimestr();
                        task_file=orderDeatailBean.getTask_file();
                        task_pic=orderDeatailBean.getTask_pic();
                        System.out.println("8888888888888888888888888888888888--"+ task_pic);
                        if(task_pic.size()==0){
                         XGlide.init(this).display(ivTu,R.drawable.xiaotu);
                         tv_nubder.setText("0");
                        }else {
                          XGlide.init(this).display(ivTu, task_pic.get(0));
                            pic=task_pic.get(0);
                            ivTu.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent=new Intent(WorkingActivity.this,PicActivity.class);
                                    intent.putExtra("pic",pic);
                                    startActivity(intent);
                                }
                            });
                          tv_nubder.setText(String.valueOf(task_pic.size()));
                    }
                        if ("货未到".equals(timestr)) {
                            re_deail.setVisibility(View.VISIBLE);
                            ll_time.setVisibility(View.VISIBLE);
                            tvTime.setVisibility(View.INVISIBLE);
                            tvDays.setVisibility(View.VISIBLE);
                            tvDay.setVisibility(View.INVISIBLE);
                            tvHour.setVisibility(View.INVISIBLE);
                            tvMinutr.setVisibility(View.INVISIBLE);
                            tvSecondp.setVisibility(View.INVISIBLE);
                            tvSecond.setVisibility(View.INVISIBLE);
                            tvDays.setText("货未到");
                            tv_shi.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                   if("1".equals(type)){
                                       Toast.makeText(WorkingActivity.this, "货未到不能完成配送", Toast.LENGTH_SHORT).show();
                                   }else if("2".equals(type)){}
                                    Toast.makeText(WorkingActivity.this, "货未到不能完成施工", Toast.LENGTH_SHORT).show();
                                }
                            });
                        } else if (!TextUtils.isEmpty(timestr)&&!"货未到".equals(timestr)) {
                            time= Integer.parseInt(timestr);
                            mDay = time /(60 * 60 * 24);
                            mHour =time/(60*60) - mDay  * 24;
                            mMin = time/60 - mHour*60 - mDay  * 24 * 60;
                            mSecond = time-mMin*60-mHour*60*60 - mDay  * 24 * 60 * 60;
                            re_deail.setVisibility(View.VISIBLE);
                            ll_time.setVisibility(View.VISIBLE);
                            tvTime.setVisibility(View.VISIBLE);
                            tvDays.setVisibility(View.VISIBLE);
                            tvDay.setVisibility(View.VISIBLE);
                            tvHour.setVisibility(View.VISIBLE);
                            tvHours.setVisibility(View.VISIBLE);
                            tvMinutr.setVisibility(View.VISIBLE);
                            tvMinut.setVisibility(View.VISIBLE);
                            tvSecondp.setVisibility(View.VISIBLE);
                            tvSecond.setVisibility(View.VISIBLE);
                            startRun(); //开始倒计时
                        } else if (TextUtils.isEmpty(timestr)) {
                            re_deail.setVisibility(View.GONE);
                        }
                        if ("1".equals(is_pick)) {
                            ll_wuliu.setVisibility(View.VISIBLE);
                        } else {
                            ll_wuliu.setVisibility(View.GONE);
                        }
                         initFuData();
                    } else {
                        return;
                    }
                }
                break;
        }
    }

    private void initFuData() {
        size=task_file.size();
        if(1==size){
            info=task_file.get(0).getSave_name();
            tv_fu1.setVisibility(View.VISIBLE);
            tv_fu1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    single="1" ;
                    Intent intent1=new Intent(WorkingActivity.this,LookFuActivity.class);
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
                    Intent intent1=new Intent(WorkingActivity.this,LookFuActivity.class);
                    intent1.putExtra("info",info);
                    intent1.putExtra("single",single);
                    startActivity(intent1);
                }
            });
            tv_fu2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    single="2" ;
                    Intent intent1=new Intent(WorkingActivity.this,LookFuActivity.class);
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
                    Intent intent1=new Intent(WorkingActivity.this,LookFuActivity.class);
                    intent1.putExtra("info",info);
                    intent1.putExtra("single",single);
                    startActivity(intent1);
                }
            });
            tv_fu2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    single="2" ;
                    Intent intent1=new Intent(WorkingActivity.this,LookFuActivity.class);
                    intent1.putExtra("info2",info2);
                    intent1.putExtra("single",single);
                    startActivity(intent1);
                }
            });
            tv_fu3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    single="3" ;
                    Intent intent1=new Intent(WorkingActivity.this,LookFuActivity.class);
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
                    Intent intent1=new Intent(WorkingActivity.this,LookFuActivity.class);
                    intent1.putExtra("info",info);
                    intent1.putExtra("single",single);
                    startActivity(intent1);
                }
            });
            tv_fu2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    single="2" ;
                    Intent intent1=new Intent(WorkingActivity.this,LookFuActivity.class);
                    intent1.putExtra("info2",info2);
                    intent1.putExtra("single",single);
                    startActivity(intent1);
                }
            });
            tv_fu3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    single="3" ;
                    Intent intent1=new Intent(WorkingActivity.this,LookFuActivity.class);
                    intent1.putExtra("info3",info3);
                    intent1.putExtra("single",single);
                    startActivity(intent1);
                }
            });
            tv_fu4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    single="4" ;
                    Intent intent1=new Intent(WorkingActivity.this,LookFuActivity.class);
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
                    Intent intent1=new Intent(WorkingActivity.this,LookFuActivity.class);
                    intent1.putExtra("info",info);
                    intent1.putExtra("single",single);
                    startActivity(intent1);
                }
            });
            tv_fu2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    single="2" ;
                    Intent intent1=new Intent(WorkingActivity.this,LookFuActivity.class);
                    intent1.putExtra("info2",info2);
                    intent1.putExtra("single",single);
                    startActivity(intent1);
                }
            });
            tv_fu3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    single="3" ;
                    Intent intent1=new Intent(WorkingActivity.this,LookFuActivity.class);
                    intent1.putExtra("info3",info3);
                    intent1.putExtra("single",single);
                    startActivity(intent1);
                }
            });
            tv_fu4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    single="4" ;
                    Intent intent1=new Intent(WorkingActivity.this,LookFuActivity.class);
                    intent1.putExtra("info4",info4);
                    intent1.putExtra("single",single);
                    startActivity(intent1);
                }
            });
            tv_fu5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    single="5" ;
                    Intent intent1=new Intent(WorkingActivity.this,LookFuActivity.class);
                    intent1.putExtra("info5",info5);
                    intent1.putExtra("single",single);
                    startActivity(intent1);
                }
            });
        }

    }


    private void startRun() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                while (isRun) {
                    try {
                        Thread.sleep(1000); //sleep1000ms
                        Message message = Message.obtain();
                        message.what = 1;
                        timeHandler.sendMessage(message);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

    }

    private void notifyView() {
        LeeTools.setText(tvNumber, orderDeatailBean.getTask_id());
        LeeTools.setText(tvPric, orderDeatailBean.getOnly_id());
        LeeTools.setText(tv_tel, orderDeatailBean.getMobile());
        LeeTools.setText(tvCusname, orderDeatailBean.getProject_name());
        LeeTools.setText(tvFloornum, orderDeatailBean.getProject_tel());
        LeeTools.setText(tvWrokp, orderDeatailBean.getKhaddress());
        LeeTools.setText(tvNumber, orderDeatailBean.getTask_id());
        LeeTools.setText(tvNumber, orderDeatailBean.getTask_id());
        LeeTools.setText(tvNumber, orderDeatailBean.getTask_id());
        LeeTools.setText(tvWuname, orderDeatailBean.getPick_id());
        LeeTools.setText(tvNicheng, orderDeatailBean.getPick_department());
        LeeTools.setText(tvPhonen, orderDeatailBean.getPick_tel());
        LeeTools.setText(tvAcc, orderDeatailBean.getPick_name());
        LeeTools.setText(tvTinum, orderDeatailBean.getPick_number());
        LeeTools.setText(tvGetaddress, orderDeatailBean.getPick_address());
        LeeTools.setText(tvGetadd, "需求描述："+orderDeatailBean.getTask_desc());

    }
}
