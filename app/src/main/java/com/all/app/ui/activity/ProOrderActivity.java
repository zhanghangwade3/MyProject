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
import com.all.app.bean.BidBean;
import com.all.app.bean.ProBean;
import com.all.app.configs.Urls;
import com.all.app.http.OkHttpParam;
import com.all.app.http.ResponseEntry;
import com.all.app.ui.adapter.BidAdpter;
import com.all.app.ui.adapter.ProOrderAdpter;
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

//问题单页面
public class ProOrderActivity extends BaseActivity {
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
    ProOrderAdpter proOrderAdpter;
    int page = 1;
    String quid = "";
    List<ProBean> proBean;
    List<ProBean> mDatas = new ArrayList<ProBean>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pro_order);
        ButterKnife.bind(this);
        initAdapter();//初始化listView的刷新适配器
        page = 1;
        loadProData(true);//请求已中标列表数据

    }

    private void loadProData(boolean b) {
        OkHttpParam param = new OkHttpParam();
        if (TextUtils.isEmpty(AppData.Init().getLoginBean().getUid())) {
            Toast.makeText(ProOrderActivity.this, "用户的ID不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        param.add("uid", AppData.Init().getLoginBean().getUid());
        param.add("page", page);
        _PostEntry(Urls.getproblemlist, param, Urls.ActionId.getproblemlist, true);

    }

    private void initAdapter() {
        lvContent.setMode(PullToRefreshBase.Mode.BOTH);
        lvContent.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                // TODO Auto-generated method stub
                //刷新时重新加载数据的第一页
                page = 1;
                loadProData(false);
            }

            //上拉刷新
            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                // TODO Auto-generated method stub
                //加载的页面开始累加
                page++;
                System.out.println("333333333333333333333" + page);
                loadProData(false);
            }
        });
        proOrderAdpter = new ProOrderAdpter(this, mDatas);
        proOrderAdpter.setAdpListener(new AdapterBase.IAdpBaseListener() {
            @Override
            public void onLazyAdpListener(int key, int position, Object data) {
                ProBean proBean=( ProBean)data;
                quid=proBean.getQuid();
                if (key == ProOrderAdpter.CLICK_ITEM) {
                    Intent intent = new Intent(ProOrderActivity.this, ProDetailActivity.class);
                     intent.putExtra("quid",quid);
                     startActivity(intent);
                    return;
                }
            }
        });

        lvContent.setAdapter( proOrderAdpter);

    }

    @OnClick({R.id.iv_back, R.id.re_title, R.id.lv_content})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                String type="2";
                Intent intent=new Intent(ProOrderActivity.this,MainActivity.class);
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
            case Urls.ActionId.getproblemlist:
                lvContent.onRefreshComplete();
                if (result.isSuccess()) {
                    if (!TextUtils.isEmpty(result.getData().toString())) {

                        proBean = FJson.getObjects(result.getData().toString(),ProBean.class);
                        if (page == 1) {
                            mDatas.clear();//每次下拉刷新时，先清空数据
                        }
                        if (null != mDatas) {
                            //每次把从服务器刷新获得的数据添加到数据集合中
                            mDatas.addAll(proBean);
                        } else {
                            if (page > 1)
                                page--;
                        }
                        proOrderAdpter.setDataList(mDatas);
                    } else {
                        if (page == 1) {
                            mDatas.clear();
                        }
                        if (page > 1)
                            page--;
                        proOrderAdpter.setDataList(mDatas);
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
                    proOrderAdpter.setDataList(mDatas);
                }
                break;

        }
    }
}
