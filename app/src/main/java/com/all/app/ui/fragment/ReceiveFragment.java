package com.all.app.ui.fragment;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.all.app.R;
import com.all.app.app.AppData;
import com.all.app.base.AdapterBase;
import com.all.app.base.BaseFragment;
import com.all.app.bean.OrderAccptBean;
import com.all.app.configs.Urls;
import com.all.app.http.OkHttpParam;
import com.all.app.http.ResponseEntry;
import com.all.app.ui.activity.OrderDetailsActivity;
import com.all.app.ui.adapter.ReOrderAdpter;
import com.all.app.utils.FJson;
import com.all.app.utils.pullrefresh.PullToRefreshBase;
import com.all.app.utils.pullrefresh.PullToRefreshListView;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ReceiveFragment extends BaseFragment {
    ReOrderAdpter reOrderAdpter;//接单适配器
    //PullToRefreshListView lv_content;//现在最主流的下拉刷新控件
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.lv_content)
    PullToRefreshListView lvContent;
    @BindView(R.id.line_popu)
    View linePopu;
    @BindView(R.id.iv_niu)
    ImageView iv_niu;
    Unbinder unbinder;
    String uid = "";
    int page = 1;
    List<OrderAccptBean> orderAccptBean;
    List<OrderAccptBean> mDatas = new ArrayList<OrderAccptBean>();//接收服务器返回数据的集合

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void Init() {
        initAdapter();
    }

    @Override
    public void onResume() {
        page = 1;
        uid = AppData.Init().getLoginBean().getUid();
        loadOrderData(true);
        super.onResume();
    }

    private void loadOrderData(boolean isShow) {
        OkHttpParam param = new OkHttpParam();
        param.add("uid", uid);
        param.add("page", page);
        _PostEntry(Urls.ordertask, param, Urls.ActionId.ordertask, true);
    }

    private void initAdapter() {
        lvContent.setMode(PullToRefreshBase.Mode.BOTH);
        lvContent.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                // TODO Auto-generated method stub
                //刷新时重新加载数据的第一页
                page = 1;
                loadOrderData(false);
            }

            //上拉刷新
            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                // TODO Auto-generated method stub
                //加载的页面开始累加
                 page++;
                System.out.println("333333333333333333333"+page);
                loadOrderData(false);
            }
        });
        reOrderAdpter = new ReOrderAdpter(getActivity(), mDatas);
        reOrderAdpter.setAdpListener(new AdapterBase.IAdpBaseListener() {

            @Override
            public void onLazyAdpListener(int key, int position, Object data) {
                // TODO Auto-generated method stub
                OrderAccptBean info = (OrderAccptBean) data;
                Intent intent = new Intent(getActivity(), OrderDetailsActivity.class);
                intent.putExtra("data-id", info.getTask_id());
                startActivity(intent);
            }
        });

        lvContent.setAdapter(reOrderAdpter);
    }
    @Override
    protected int InitLayer() {
        return R.layout.fragment_receive;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    protected void onNetSuccess(int actionId, ResponseEntry result) {
        super.onNetSuccess(actionId, result);
        switch (actionId) {
            case Urls.ActionId.ordertask:
                lvContent.onRefreshComplete();
                if (result.isSuccess()) {
                    if (!TextUtils.isEmpty(result.getData().toString())) {
                        orderAccptBean = FJson.getObjects(result.getData().toString(), OrderAccptBean.class);
                        if (page == 1) {
                            mDatas.clear();//每次下拉刷新时，先清空数据
                        }
                        if (null != mDatas) {
                            //每次把从服务器刷新获得的数据添加到数据集合中
                            mDatas.addAll(orderAccptBean);
                        } else {
                            if (page > 1)
                                page--;
                        }
                        reOrderAdpter.setDataList(mDatas);
                    } else {

                        if (page == 1) {
                            mDatas.clear();
                        }
                        if (page > 1)
                            page--;
                        reOrderAdpter.setDataList(mDatas);
                    }
                } else {
                    if(page==1){
                        iv_niu.setVisibility(View.VISIBLE);
                    }else{
                        Toast.makeText(mContext, "已到最底部", Toast.LENGTH_SHORT).show();
                    }
                    if (page== 1) {
                        mDatas.clear();
                    }
                    if (page > 1)
                        page--;
                    reOrderAdpter.setDataList(mDatas);
                }
                break;
        }
    }
}