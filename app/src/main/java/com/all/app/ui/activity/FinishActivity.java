package com.all.app.ui.activity;

import android.content.Intent;
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
import com.all.app.bean.FinishBean;
import com.all.app.bean.ProBean;
import com.all.app.bean.ProDetailBean;
import com.all.app.configs.Urls;
import com.all.app.http.OkHttpParam;
import com.all.app.http.ResponseEntry;
import com.all.app.ui.adapter.BidAdpter;
import com.all.app.ui.adapter.FinishAdpter;
import com.all.app.ui.adapter.ProOrderAdpter;
import com.all.app.utils.FJson;
import com.all.app.utils.pullrefresh.PullToRefreshBase;
import com.all.app.utils.pullrefresh.PullToRefreshListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class FinishActivity extends BaseActivity {
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
    FinishAdpter finishAdpter;
    List<FinishBean> finishBean;
    List<FinishBean> mDatas = new ArrayList<FinishBean>();
    int page=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);
        ButterKnife.bind(this);
        initAdapter();//初始化listView的刷新适配器
        page = 1;
        loadFinishData(true);//请求已中标列表数据
    }

    private void initAdapter() {
        finishAdpter = new FinishAdpter(this, mDatas);
        lvContent.setMode(PullToRefreshBase.Mode.BOTH);
        lvContent.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                // TODO Auto-generated method stub
                //刷新时重新加载数据的第一页
                page = 1;
                loadFinishData(false);
            }

            //上拉刷新
            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                // TODO Auto-generated method stub
                //加载的页面开始累加
                page++;
                System.out.println("333333333333333333333" + page);
                loadFinishData(false);
            }
        });

        lvContent.setAdapter(finishAdpter);

    }

    private void loadFinishData(boolean v) {
        OkHttpParam param = new OkHttpParam();
        if (TextUtils.isEmpty(AppData.Init().getLoginBean().getUid())) {
            Toast.makeText(FinishActivity.this, "用户的ID不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        param.add("uid", AppData.Init().getLoginBean().getUid());
        param.add("page",page);
        _PostEntry(Urls.completeorder, param, Urls.ActionId.completeorder, true);
    }

    @OnClick({R.id.iv_back, R.id.re_title})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                String type="2";
                Intent intent=new Intent(FinishActivity.this,MainActivity.class);
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
            case Urls.ActionId.completeorder:
                lvContent.onRefreshComplete();
                if (result.isSuccess()) {
                    if (!TextUtils.isEmpty(result.getData().toString())) {

                        finishBean = FJson.getObjects(result.getData().toString(), FinishBean.class);
                        if (page == 1) {
                            mDatas.clear();//每次下拉刷新时，先清空数据
                        }
                        if (null != mDatas) {
                            //每次把从服务器刷新获得的数据添加到数据集合中
                            mDatas.addAll(finishBean );
                        } else {
                            if (page > 1)
                                page--;
                        }
                        finishAdpter.setDataList(mDatas);
                    } else {
                        if (page == 1) {
                            mDatas.clear();
                        }
                        if (page > 1)
                            page--;
                        finishAdpter.setDataList(mDatas);
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
                    finishAdpter.setDataList(mDatas);
                }
                break;

        }
    }


}
