package com.all.app.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.all.app.R;
import com.all.app.app.AppData;
import com.all.app.base.AdapterBase;
import com.all.app.base.BaseActivity;
import com.all.app.bean.BidBean;
import com.all.app.bean.OrderAccptBean;
import com.all.app.configs.Urls;
import com.all.app.http.OkHttpParam;
import com.all.app.http.ResponseEntry;
import com.all.app.ui.adapter.BidAdpter;
import com.all.app.ui.adapter.ReOrderAdpter;
import com.all.app.utils.AlertDialog;
import com.all.app.utils.FJson;
import com.all.app.utils.pullrefresh.PullToRefreshBase;
import com.all.app.utils.pullrefresh.PullToRefreshListView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//已中标页面
public class BidActivity extends BaseActivity {

    BidAdpter bidAdpter;//接单适配器
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.iv_niu)
    ImageView iv_niu;

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.re_title)
    RelativeLayout reTitle;
    @BindView(R.id.lv_content)
    PullToRefreshListView lvContent;
    int page = 1;
    String taskstatus = "3";
    List<BidBean> bidBean;
    List<BidBean> mDatas = new ArrayList<BidBean>();
    String taskid="";
    String  is_pick="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bid);
        ButterKnife.bind(this);
        initAdapter();//初始化listView的刷新适配器
        page = 1;
        loadBidData(true);//请求已中标列表数据
    }

    private void initAdapter() {
        lvContent.setMode(PullToRefreshBase.Mode.BOTH);
        lvContent.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                // TODO Auto-generated method stub
                //刷新时重新加载数据的第一页
                page = 1;
                loadBidData(false);
            }

            //上拉刷新
            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                // TODO Auto-generated method stub
                //加载的页面开始累加
                page++;
                System.out.println("333333333333333333333" + page);
                loadBidData(false);
            }
        });
        bidAdpter = new BidAdpter(this, mDatas);
        bidAdpter.setAdpListener(new AdapterBase.IAdpBaseListener() {
            @Override
            public void onLazyAdpListener(int key, int position, Object data) {
                BidBean info = (BidBean) data;
                 taskid=info.getTask_id();
                 is_pick=info.getIs_pick();
                if(key==BidAdpter.CLICK_OK){
                    OkHttpParam param = new OkHttpParam();
                    if (TextUtils.isEmpty(AppData.Init().getLoginBean().getUid())) {
                        Toast.makeText(BidActivity.this, "用户的ID不能为空", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (TextUtils.isEmpty(taskid)) {
                        Toast.makeText(BidActivity.this, "任务的ID不能为空", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (TextUtils.isEmpty(is_pick)) {
                        Toast.makeText(BidActivity.this, "服务的类型不能为空", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    param.add("uid", AppData.Init().getLoginBean().getUid());
                    param.add("taskid",taskid);
                    param.add("t_status",is_pick);
                    _PostEntry(Urls.saveorderstate, param, Urls.ActionId.saveorderstate, true);
                }
            }
        });

        lvContent.setAdapter(bidAdpter);
    }

    private void loadBidData(boolean isShow) {
        OkHttpParam param = new OkHttpParam();
        param.add("uid", AppData.Init().getLoginBean().getUid());
        param.add("page", page);
        param.add("taskstatus", taskstatus);
        _PostEntry(Urls.getorderlist, param, Urls.ActionId.getorderlist, true);


    }

    @OnClick({R.id.iv_back, R.id.re_title, R.id.lv_content})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                String type="2";
                Intent intent=new Intent(BidActivity.this,MainActivity.class);
                intent.putExtra("type",type);
                startActivity(intent);
                break;
            case R.id.re_title:
                break;
            case R.id.lv_content:
                break;
        }
    }

    @Override
    protected void onNetSuccess(int actionId, ResponseEntry result) {
        super.onNetSuccess(actionId, result);
        switch (actionId) {
            case Urls.ActionId.getorderlist:
                lvContent.onRefreshComplete();
                if (result.isSuccess()){
                    if (!TextUtils.isEmpty(result.getData().toString())) {
                        bidBean = FJson.getObjects(result.getData().toString(), BidBean.class);
                        if (page == 1) {
                            mDatas.clear();//每次下拉刷新时，先清空数据
                        }
                        if (null != mDatas) {
                            //每次把从服务器刷新获得的数据添加到数据集合中
                            mDatas.addAll(bidBean);
                        } else  {
                            if (page > 1)
                                page--;
                        }
                        bidAdpter.setDataList(mDatas);
                    } else {
                        if (page == 1) {
                            mDatas.clear();
                        }
                        if (page > 1)
                            page--;
                        bidAdpter.setDataList(mDatas);
                    }
                } else {
                    if(page==1){
                        iv_niu.setVisibility(View.VISIBLE);
                    }else{
                        Toast.makeText(this, "已到最底部", Toast.LENGTH_SHORT).show();
                    }
                    if (page == 1) {
                        mDatas.clear();
                    }
                    if (page > 1)
                        page--;
                    bidAdpter.setDataList(mDatas);
                }
                break;
            case Urls.ActionId.saveorderstate:
               if(result.isSuccess()){
                  if("0".equals(is_pick)||"4".equals( is_pick)||"5".equals(is_pick)){
                       startActivity(new Intent(BidActivity.this,ExecutionActivity.class));
                  }else if("1".equals(is_pick)||"2".equals(is_pick)||"3".equals(is_pick)){
                      startActivity(new Intent(BidActivity.this,GetActivity.class));
                  }
               }
                break;
        }
    }

}

