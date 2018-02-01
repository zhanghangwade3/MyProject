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

import com.all.app.R;
import com.all.app.base.AdapterBase;
import com.all.app.base.BaseFragment;
import com.all.app.bean.FindBean;
import com.all.app.configs.Urls;
import com.all.app.http.OkHttpParam;
import com.all.app.http.ResponseEntry;
import com.all.app.ui.activity.WebFindActivity;
import com.all.app.ui.activity.WebViewActivity;
import com.all.app.ui.adapter.FindAdpter;
import com.all.app.utils.FJson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class FindFragment extends BaseFragment {

    FindAdpter findAdpter;//接单适配器
    @BindView(R.id.iv_niu)
    ImageView iv_niu;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.lv_content)
    ListView lvContent;
    @BindView(R.id.view)
    View view;
    Unbinder unbinder;
    List<FindBean> findBean;
    String single = "3";
    String art_url = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void Init() {

    }

    @Override
    public void onResume() {
        super.onResume();
        laodData();//加载列表的数据
    }

    private void laodData() {
        OkHttpParam param = new OkHttpParam();
        _PostEntry(Urls.article, param, Urls.ActionId.article, true);
    }

    @Override
    protected int InitLayer() {
        return R.layout.fragment_find;
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
            case Urls.ActionId.article:
                if (result.isSuccess()) {
                    if(!TextUtils.isEmpty(result.getData().toString())){
                    findBean = FJson.getObjects(result.getData().toString(), FindBean.class);
                    if (findBean != null) {
                        findAdpter = new FindAdpter(getActivity(), findBean);
                        findAdpter.setAdpListener(new AdapterBase.IAdpBaseListener() {
                            @Override
                            public void onLazyAdpListener(int key, int position, Object data) {
                                FindBean info = (FindBean) data;
                                art_url = info.getArt_url();
                                if (key == FindAdpter.CLICK_ITEM) {
                                    Intent intent = new Intent(getActivity(), WebFindActivity.class);
                                    intent.putExtra("single",single);
                                    intent.putExtra("art_url",art_url);
                                    startActivity(intent);
                                }
                            }
                        });
                        lvContent.setAdapter(findAdpter);
                    }
                }
                }else{
                    iv_niu.setVisibility(View.VISIBLE);
                }
                break;
        }

    }
}
