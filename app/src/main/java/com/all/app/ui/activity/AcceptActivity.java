package com.all.app.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
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
import com.all.app.bean.AccBean;
import com.all.app.bean.BidBean;
import com.all.app.configs.Urls;
import com.all.app.http.OkHttpParam;
import com.all.app.http.ResponseEntry;
import com.all.app.ui.adapter.AccepAdpter;
import com.all.app.ui.adapter.AcceptAdpter;
import com.all.app.ui.adapter.BidAdpter;
import com.all.app.utils.AlertDialog;
import com.all.app.utils.FJson;
import com.all.app.utils.pullrefresh.PullToRefreshBase;
import com.all.app.utils.pullrefresh.PullToRefreshListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//待验收页面
public class AcceptActivity extends BaseActivity {

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
    AcceptAdpter acceptAdpter;
    int page = 1;
    String taskstatus = "6";
    List<AccBean> accBean;
    List<AccBean> mDatas = new ArrayList<AccBean>();
    String bid_id="";
     String type="2";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accept);
        ButterKnife.bind(this);
        initAdapter();//初始化listView的刷新适配器
        page = 1;
        loadAccData(true);//请求待验收列表数据
    }
    private void loadAccData(boolean b) {
        OkHttpParam param = new OkHttpParam();
        if (TextUtils.isEmpty(AppData.Init().getLoginBean().getUid())) {
            Toast.makeText(AcceptActivity.this, "用户的ID不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        param.add("uid", AppData.Init().getLoginBean().getUid());
        param.add("page", page);
        param.add("taskstatus", taskstatus);
        _PostEntry(Urls.getorderlist4, param, Urls.ActionId.getorderlist4, true);

    }

    private void initAdapter() {
        lvContent.setMode(PullToRefreshBase.Mode.BOTH);
        lvContent.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                // TODO Auto-generated method stub
                //刷新时重新加载数据的第一页
                page = 1;
                loadAccData(false);
            }

            //上拉刷新
            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                // TODO Auto-generated method stub
                //加载的页面开始累加
                page++;
                System.out.println("333333333333333333333" + page);
                loadAccData(false);
            }
        });
        acceptAdpter = new AcceptAdpter(this, mDatas);
        acceptAdpter.setAdpListener(new AdapterBase.IAdpBaseListener() {
            @Override
            public void onLazyAdpListener(int key, int position, Object data) {
               AccBean info = (AccBean) data;
                 bid_id=info.getBid_id();
                if(key==AcceptAdpter.CLICK_ZHONG){
                   Intent intent =new Intent(AcceptActivity.this,ConfirmActivity.class);
                    intent.putExtra("bid_id",bid_id);
                    intent.putExtra("type", type);
                    startActivity(intent);
                }
            }
        });
        lvContent.setAdapter(acceptAdpter);
    }
    @OnClick({R.id.iv_back, R.id.re_title})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                String type="2";
                Intent intent=new Intent(AcceptActivity.this,MainActivity.class);
                intent.putExtra("type",type);
                startActivity(intent);
                break;
            case R.id.re_title:
                break;
        }
    }

    @Override
    protected void onNetSuccess(int actionId, ResponseEntry result) {
        super.onNetSuccess(actionId, result);
        switch (actionId) {
            case Urls.ActionId.getorderlist4:
                lvContent.onRefreshComplete();
                if (result.isSuccess()) {
                    if (!TextUtils.isEmpty(result.getData().toString())) {
                        accBean = FJson.getObjects(result.getData().toString(), AccBean.class);

                        if (page == 1) {
                            mDatas.clear();//每次下拉刷新时，先清空数据
                        }
                        if (null != mDatas) {
                            //每次把从服务器刷新获得的数据添加到数据集合中
                            mDatas.addAll(accBean);
                        } else {
                            if (page > 1)
                                page--;
                        }
                        acceptAdpter.setDataList(mDatas);
                    } else {
                        if (page == 1) {
                            mDatas.clear();
                        }
                        if (page > 1)
                            page--;
                        acceptAdpter.setDataList(mDatas);
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
                    acceptAdpter.setDataList(mDatas);
                }
                break;

        }
    }

    }

