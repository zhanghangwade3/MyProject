package com.all.app.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
import com.all.app.bean.MeageListBean;
import com.all.app.configs.Urls;
import com.all.app.http.OkHttpParam;
import com.all.app.http.ResponseEntry;
import com.all.app.ui.adapter.BidAdpter;
import com.all.app.ui.adapter.DealAdpter;
import com.all.app.utils.FJson;
import com.all.app.utils.pullrefresh.PullToRefreshBase;
import com.all.app.utils.pullrefresh.PullToRefreshListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DealActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.re_title)
    RelativeLayout reTitle;
    @BindView(R.id.lv_content)
    PullToRefreshListView lvContent;
    DealAdpter dealAdpter;
    String type = "trends";
    List<MeageListBean> meageListBean;
    List<MeageListBean> mDatas = new ArrayList<MeageListBean>();
    int page = 0;
    String single = "4";
    String msgurl="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deal);
        ButterKnife.bind(this);
        initAdapter();//初始化listView的刷新适配器
        page = 1;
        loadData(true);//请求已中标列表数据
    }

    private void loadData(boolean b) {
        OkHttpParam param = new OkHttpParam();
        param.add("uid", AppData.Init().getLoginBean().getUid());
        param.add("type", type);
        param.add("page", page);
        _PostEntry(Urls.mymsg, param, Urls.ActionId.mymsg, true);
    }

    private void initAdapter() {
        lvContent.setMode(PullToRefreshBase.Mode.BOTH);
        lvContent.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                // TODO Auto-generated method stub
                //刷新时重新加载数据的第一页
                page = 1;
                loadData(false);
            }

            //上拉刷新
            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                // TODO Auto-generated method stub
                //加载的页面开始累加
                page++;
                System.out.println("333333333333333333333" + page);
                loadData(false);
            }
        });
        dealAdpter = new DealAdpter(this, mDatas);
        dealAdpter.setAdpListener(new AdapterBase.IAdpBaseListener() {
            @Override
            public void onLazyAdpListener(int key, int position, Object data) {
               if(key==DealAdpter.CLICK_ITEM){
                   MeageListBean info = (MeageListBean) data;
                   msgurl=info.getMsgurl();
                   Intent intent=new Intent(DealActivity.this,WebDetailActivity.class);
                   intent.putExtra("single",single);
                   intent.putExtra("msgurl",msgurl);
                   startActivity(intent);
               }

            }
        });
        lvContent.setAdapter(dealAdpter);
    }

    @OnClick({R.id.iv_back, R.id.tv_title, R.id.re_title, R.id.lv_content})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
               startActivity(new Intent(DealActivity.this,MessgeActivity.class));
                break;
            case R.id.tv_title:
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
            case Urls.ActionId.mymsg:
                lvContent.onRefreshComplete();
                if (result.isSuccess()) {
                    if (!TextUtils.isEmpty(result.getData().toString())) {
                       System.out.println("888888888888888888888888888888888888888888888888888"+result);

                        meageListBean = FJson.getObjects(result.getData().toString(), MeageListBean.class);
                        if (page == 1) {
                            mDatas.clear();//每次下拉刷新时，先清空数据
                        }
                        if (null != mDatas) {
                            //每次把从服务器刷新获得的数据添加到数据集合中
                            mDatas.addAll(meageListBean);
                        } else {
                            if (page > 1)
                                page--;
                        }
                        dealAdpter.setDataList(mDatas);
                    } else {
                        if (page == 1) {
                            mDatas.clear();
                        }
                        if (page > 1)
                            page--;
                        dealAdpter.setDataList(mDatas);
                    }
                } else {
                    Toast.makeText(this, "暂无数据", Toast.LENGTH_SHORT).show();
                    if (page == 1) {
                        mDatas.clear();
                    }
                    if (page > 1)
                        page--;
                    dealAdpter.setDataList(mDatas);
                }
                break;
        }
    }
}
