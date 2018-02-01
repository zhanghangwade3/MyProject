package com.all.app.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.all.app.R;
import com.all.app.app.AppData;
import com.all.app.base.BaseActivity;
import com.all.app.bean.FileBean;
import com.all.app.bean.OfferInfoBean;
import com.all.app.bean.YanBean;
import com.all.app.configs.Urls;
import com.all.app.http.OkHttpParam;
import com.all.app.http.ResponseEntry;
import com.all.app.ui.adapter.ListAdpter;
import com.all.app.utils.FJson;
import com.all.app.utils.LeeTools;
import com.all.app.utils.XGlide;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class QuoteDetailActivity extends BaseActivity {

    @BindView(R.id.ll_wu)
    LinearLayout ll_wu;
    String task_id = "";
    OfferInfoBean offerInfoBean;
    ArrayList<String> task_pic;
    ArrayList<FileBean> task_file;
    ArrayList<YanBean> lylist;
    ListAdpter listAdapter;
    int size = 0;
    String info = "";
    String info2 = "";
    String info3 = "";
    String info4 = "";
    String info5 = "";
    String single = "";
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
    @BindView(R.id.tv_nubder)
    TextView tvNubder;
    @BindView(R.id.tv_fu1)
    TextView tvFu1;
    @BindView(R.id.tv_fu2)
    TextView tvFu2;
    @BindView(R.id.tv_fu3)
    TextView tvFu3;
    @BindView(R.id.tv_fu4)
    TextView tvFu4;
    @BindView(R.id.tv_fu5)
    TextView tvFu5;
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
    @BindView(R.id.tv_getaddress)
    TextView tvGetaddress;
    @BindView(R.id.iv_bao)
    ImageView ivBao;
    @BindView(R.id.tv_bao)
    TextView tvBao;
    @BindView(R.id.tv_baop)
    TextView tvBaop;
    @BindView(R.id.vi12)
    View vi12;
    @BindView(R.id.tv_an)
    TextView tvAn;
    @BindView(R.id.tv_yun)
    TextView tvYun;
    @BindView(R.id.tv_yun2)
    TextView tvYun2;
    @BindView(R.id.other)
    TextView other;
    @BindView(R.id.tv_anp)
    TextView tvAnp;
    @BindView(R.id.tv_yunp)
    TextView tvYunp;
    @BindView(R.id.tv_yun2p)
    TextView tvYun2p;
    @BindView(R.id.otherp)
    TextView otherp;
    @BindView(R.id.tv_content)
    TextView tvContent;
    ListView lvLiu;
    String pic="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quote_detail);
        ButterKnife.bind(this);
        lvLiu = (ListView) findViewById(R.id.lv_liu);
        task_id = getIntent().getStringExtra("task_id");
        loadData();
    }
    private void loadData() {
        OkHttpParam param = new OkHttpParam();
        if (TextUtils.isEmpty(AppData.Init().getLoginBean().getUid())) {
            Toast.makeText(QuoteDetailActivity.this, "用户的ID不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(task_id)) {
            Toast.makeText(QuoteDetailActivity.this, "任务的ID不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        param.add("uid", AppData.Init().getLoginBean().getUid());
        param.add("taskid", task_id);
        _PostEntry(Urls.offerinfo, param, Urls.ActionId.offerinfo, true);
    }


    @Override
    protected void onNetSuccess(int actionId, ResponseEntry result) {
        super.onNetSuccess(actionId, result);
        switch (actionId) {
            case Urls.ActionId.offerinfo:
                if (result.isSuccess()) {
                    System.out.println("44444444444444444444444444444444444444444444444" + result);
                    offerInfoBean = FJson.getObject(result.getData().toString(), OfferInfoBean.class);
                    if (null != offerInfoBean) {
                        initView();
                        task_pic = offerInfoBean.getTask_pic();
                        task_file = offerInfoBean.getTask_file();
                        int g=task_pic.size();
                        System.out.println("00000000000000000000000000000000000000"+g);
                        if (task_pic.size() == 0) {
                            //XGlide.init(this).display(ivPic, R.drawable.xiaotu);
                            ivPic.setImageResource(R.drawable.xiaotu);
                            tvNubder.setText("0");
                        } else {
                            XGlide.init(this).display(ivPic, task_pic.get(0));
                             pic=task_pic.get(0);
                            ivPic.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent=new Intent(QuoteDetailActivity.this,PicActivity.class);
                                       intent.putExtra("pic",pic);
                                        startActivity(intent);
                                }
                            });
                            tvNubder.setText(String.valueOf(task_pic.size()));
                            initFuData();
                            lylist = offerInfoBean.getLylist();
                            listAdapter = new ListAdpter(this, lylist);
                            lvLiu.setAdapter(listAdapter);
                            setListViewHeightBasedOnChildren(lvLiu);
                        }
                    }
                    break;
                }
        }
    }

    private void setListViewHeightBasedOnChildren(ListView lvLiu) {
        ListAdapter listAdapter = lvLiu.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;

        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, lvLiu);
            if (listItem != null) {
                listItem.measure(0, 0);
                totalHeight += listItem.getMeasuredHeight();
            }
        }
        ViewGroup.LayoutParams params = lvLiu.getLayoutParams();
        params.height = totalHeight + (lvLiu.getDividerHeight() * (listAdapter.getCount() - 1));
        lvLiu.setLayoutParams(params);
    }

    private void initFuData() {
        size = task_file.size();
        if (1 == size) {
            info = task_file.get(0).getSave_name();
            tvFu1.setVisibility(View.VISIBLE);
            tvFu1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    single = "1";
                    Intent intent1 = new Intent(QuoteDetailActivity.this, LookFuActivity.class);
                    intent1.putExtra("info", info);
                    intent1.putExtra("single", single);
                    startActivity(intent1);
                }
            });
        } else if (2 == size) {
            info = task_file.get(0).getSave_name();
            info2 = task_file.get(1).getSave_name();
            tvFu1.setVisibility(View.VISIBLE);
            tvFu2.setVisibility(View.VISIBLE);
            tvFu1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    single = "1";
                    Intent intent1 = new Intent(QuoteDetailActivity.this, LookFuActivity.class);
                    intent1.putExtra("info", info);
                    intent1.putExtra("single", single);
                    startActivity(intent1);
                }
            });
            tvFu2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    single = "2";
                    Intent intent1 = new Intent(QuoteDetailActivity.this, LookFuActivity.class);
                    intent1.putExtra("info2", info2);
                    intent1.putExtra("single", single);
                    startActivity(intent1);
                }
            });
        } else if (3 == size) {
            info = task_file.get(0).getSave_name();
            info2 = task_file.get(1).getSave_name();
            info3 = task_file.get(2).getSave_name();
            tvFu1.setVisibility(View.VISIBLE);
            tvFu2.setVisibility(View.VISIBLE);
            tvFu3.setVisibility(View.VISIBLE);
            tvFu1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    single = "1";
                    Intent intent1 = new Intent(QuoteDetailActivity.this, LookFuActivity.class);
                    intent1.putExtra("info", info);
                    intent1.putExtra("single", single);
                    startActivity(intent1);
                }
            });
            tvFu2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    single = "2";
                    Intent intent1 = new Intent(QuoteDetailActivity.this, LookFuActivity.class);
                    intent1.putExtra("info2", info2);
                    intent1.putExtra("single", single);
                    startActivity(intent1);
                }
            });
            tvFu3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    single = "3";
                    Intent intent1 = new Intent(QuoteDetailActivity.this, LookFuActivity.class);
                    intent1.putExtra("info3", info3);
                    intent1.putExtra("single", single);
                    startActivity(intent1);
                }
            });
        } else if (4 == size) {
            info = task_file.get(0).getSave_name();
            info2 = task_file.get(1).getSave_name();
            info3 = task_file.get(2).getSave_name();
            info4 = task_file.get(3).getSave_name();
            tvFu1.setVisibility(View.VISIBLE);
            tvFu2.setVisibility(View.VISIBLE);
            tvFu3.setVisibility(View.VISIBLE);
            tvFu4.setVisibility(View.VISIBLE);
            tvFu1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    single = "1";
                    Intent intent1 = new Intent(QuoteDetailActivity.this, LookFuActivity.class);
                    intent1.putExtra("info", info);
                    intent1.putExtra("single", single);
                    startActivity(intent1);
                }
            });
            tvFu2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    single = "2";
                    Intent intent1 = new Intent(QuoteDetailActivity.this, LookFuActivity.class);
                    intent1.putExtra("info2", info2);
                    intent1.putExtra("single", single);
                    startActivity(intent1);
                }
            });
            tvFu3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    single = "3";
                    Intent intent1 = new Intent(QuoteDetailActivity.this, LookFuActivity.class);
                    intent1.putExtra("info3", info3);
                    intent1.putExtra("single", single);
                    startActivity(intent1);
                }
            });
            tvFu4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    single = "4";
                    Intent intent1 = new Intent(QuoteDetailActivity.this, LookFuActivity.class);
                    intent1.putExtra("info4", info4);
                    intent1.putExtra("single", single);
                    startActivity(intent1);
                }
            });
        } else if (5 == size) {
            info = task_file.get(0).getSave_name();
            info2 = task_file.get(1).getSave_name();
            info3 = task_file.get(2).getSave_name();
            info4 = task_file.get(3).getSave_name();
            info5 = task_file.get(4).getSave_name();
            tvFu1.setVisibility(View.VISIBLE);
            tvFu2.setVisibility(View.VISIBLE);
            tvFu3.setVisibility(View.VISIBLE);
            tvFu4.setVisibility(View.VISIBLE);
            tvFu5.setVisibility(View.VISIBLE);
            tvFu1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    single = "1";
                    Intent intent1 = new Intent(QuoteDetailActivity.this, LookFuActivity.class);
                    intent1.putExtra("info", info);
                    intent1.putExtra("single", single);
                    startActivity(intent1);
                }
            });
            tvFu2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    single = "2";
                    Intent intent1 = new Intent(QuoteDetailActivity.this, LookFuActivity.class);
                    intent1.putExtra("info2", info2);
                    intent1.putExtra("single", single);
                    startActivity(intent1);
                }
            });
            tvFu3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    single = "3";
                    Intent intent1 = new Intent(QuoteDetailActivity.this, LookFuActivity.class);
                    intent1.putExtra("info3", info3);
                    intent1.putExtra("single", single);
                    startActivity(intent1);
                }
            });
            tvFu4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    single = "4";
                    Intent intent1 = new Intent(QuoteDetailActivity.this, LookFuActivity.class);
                    intent1.putExtra("info4", info4);
                    intent1.putExtra("single", single);
                    startActivity(intent1);
                }
            });
            tvFu5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    single = "5";
                    Intent intent1 = new Intent(QuoteDetailActivity.this, LookFuActivity.class);
                    intent1.putExtra("info5", info5);
                    intent1.putExtra("single", single);
                    startActivity(intent1);
                }
            });
        }
    }
    private void initView() {
        LeeTools.setText(tvName, offerInfoBean.getTask_title());
        LeeTools.setText(tvNumber, offerInfoBean.getOnly_id());
        if(TextUtils.isEmpty(offerInfoBean.getTask_price())){
            tvPric.setText("暂无");
        }else{
            LeeTools.setText(tvPric, offerInfoBean.getTask_price());
        }
        LeeTools.setText(tvAddress, offerInfoBean.getSjaddress());
        LeeTools.setText(tvXuqiu, "需求描述：" + offerInfoBean.getTask_desc());
        LeeTools.setText(tvCusname, offerInfoBean.getProject_name());
        LeeTools.setText(tvFloornum, offerInfoBean.getIselevator());
        LeeTools.setText(tvWrokp, "施工地址：" + offerInfoBean.getKhaddress());
        if ("1".equals(offerInfoBean.getIs_pick())) {
            LeeTools.setText(tvWuname, offerInfoBean.getPick_id());
            LeeTools.setText(tvNicheng, offerInfoBean.getPick_department());
            LeeTools.setText(tvPhonen, offerInfoBean.getPick_tel());
            LeeTools.setText(tvAcc, offerInfoBean.getPick_name());
            LeeTools.setText(tvTinum, offerInfoBean.getPick_number());
            LeeTools.setText(tvGetaddress, "提货地址："+offerInfoBean.getPick_address());
        } else {
            ll_wu.setVisibility(View.GONE);
        }
        LeeTools.setText(tvBaop, offerInfoBean.getQuote()+"元");
        LeeTools.setText(tvAn, offerInfoBean.getInstallation_price());
        LeeTools.setText(tvYun, offerInfoBean.getTransportation_price());
        LeeTools.setText(tvYun2, offerInfoBean.getHandling_price());
        LeeTools.setText(other, offerInfoBean.getOther_price());
        LeeTools.setText(tvContent, offerInfoBean.getMessage());
    }


    @OnClick({R.id.iv_back, R.id.tv_comment, R.id.vi2, R.id.tv_name, R.id.vi3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_comment:
                break;
            case R.id.vi2:
                break;
            case R.id.tv_name:
                break;
            case R.id.vi3:
                break;
        }
    }
}
