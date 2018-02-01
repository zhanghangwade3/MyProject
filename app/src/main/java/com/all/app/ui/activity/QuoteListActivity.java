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
import com.all.app.bean.OfferListBean;
import com.all.app.configs.Urls;
import com.all.app.http.OkHttpParam;
import com.all.app.http.ResponseEntry;
import com.all.app.ui.adapter.BidAdpter;
import com.all.app.ui.adapter.QuoteListAdpter;
import com.all.app.utils.FJson;
import com.all.app.utils.pullrefresh.PullToRefreshBase;
import com.all.app.utils.pullrefresh.PullToRefreshListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class QuoteListActivity extends BaseActivity {

    QuoteListAdpter quoteListAdpter;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_niu)
    ImageView iv_niu;
    @BindView(R.id.re_title)
    RelativeLayout reTitle;
    @BindView(R.id.lv_content)
    PullToRefreshListView lvContent;
    int page=0;
    List<OfferListBean> offerListBean;
    List<OfferListBean> mDatas = new ArrayList<OfferListBean>();
    String bid_id="";
    String task_id="";
    String type ="1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quote_list);
        ButterKnife.bind(this);
        initAdapter();//初始化listView的刷新适配器
        page = 1;
        loadQutaData(true);//请求已中标列表数据

    }

    private void loadQutaData(boolean b) {
        OkHttpParam param = new OkHttpParam();
        if (TextUtils.isEmpty(AppData.Init().getLoginBean().getUid())) {
            Toast.makeText(QuoteListActivity.this, "用户的ID不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        param.add("uid", AppData.Init().getLoginBean().getUid());
        param.add("page", page);
        _PostEntry(Urls.offerlist, param, Urls.ActionId.offerlist, true);
    }

    private void initAdapter() {
        lvContent.setMode(PullToRefreshBase.Mode.BOTH);
        lvContent.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                // TODO Auto-generated method stub
                //刷新时重新加载数据的第一页
                page = 1;
                loadQutaData(false);
            }

            //上拉刷新
            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                // TODO Auto-generated method stub
                //加载的页面开始累加
                page++;
                System.out.println("333333333333333333333" + page);
                loadQutaData(false);
            }
        });
        quoteListAdpter = new QuoteListAdpter(this, mDatas);
        quoteListAdpter.setAdpListener(new AdapterBase.IAdpBaseListener() {
            @Override
            public void onLazyAdpListener(int key, int position, Object data) {
                OfferListBean info = (OfferListBean) data;
                bid_id=info.getBid_id();
                task_id=info.getTask_id();
               if(key==QuoteListAdpter.CLICK_QUTA){
                       Intent intent=new Intent( QuoteListActivity.this ,ChangePriceActivity.class);
                       intent.putExtra("bid_id",bid_id);
                       intent.putExtra("type",type);
                       intent.putExtra("task_id",task_id);
                       startActivity(intent);
               }
              if(key==QuoteListAdpter.CLICK_ITEM){

                  Intent intent=new Intent( QuoteListActivity.this ,QuoteDetailActivity.class);
                  intent.putExtra("task_id",task_id);
                  startActivity(intent);
              }
            }
        });
        lvContent.setAdapter(quoteListAdpter);
    }

    @OnClick({R.id.iv_back, R.id.re_title})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                startActivity(new Intent(QuoteListActivity.this,MainActivity.class));
                break;
            case R.id.re_title:
                break;
        }
    }
    @Override
    protected void onNetSuccess(int actionId, ResponseEntry result) {
        super.onNetSuccess(actionId, result);
        switch (actionId) {
            case Urls.ActionId.offerlist:
                lvContent.onRefreshComplete();
                if (result.isSuccess()) {
                    if (!TextUtils.isEmpty(result.getData().toString())) {
                        offerListBean = FJson.getObjects(result.getData().toString(), OfferListBean.class);
                        if (page == 1) {
                            mDatas.clear();//每次下拉刷新时，先清空数据
                        }
                        if (null != mDatas) {
                            //每次把从服务器刷新获得的数据添加到数据集合中
                            mDatas.addAll(offerListBean);
                        } else {
                            if (page > 1)
                                page--;
                        }
                        quoteListAdpter.setDataList(mDatas);
                    } else {

                        if (page == 1) {
                            mDatas.clear();
                        }
                        if (page > 1)
                            page--;
                        quoteListAdpter.setDataList(mDatas);
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
                    quoteListAdpter.setDataList(mDatas);
                }

                break;

        }
    }
}
