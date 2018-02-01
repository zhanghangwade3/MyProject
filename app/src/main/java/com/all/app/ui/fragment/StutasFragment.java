package com.all.app.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.all.app.R;
import com.all.app.base.BaseFragment;
import com.all.app.ui.adapter.StuAdpter;
import com.all.app.utils.pullrefresh.PullToRefreshListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class StutasFragment extends BaseFragment {
    //模拟的数据
    List<String> mDatas = new ArrayList<String>(Arrays.asList("Hello",
            "World", "Welcome", "Hello"));
    @BindView(R.id.lv_content)
    PullToRefreshListView lvContent;
    Unbinder unbinder;
    StuAdpter stuAdpter;

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
        stuAdpter = new StuAdpter(getActivity(), mDatas);
        lvContent.setAdapter(stuAdpter);
    }

    @Override
    protected int InitLayer() {
        return R.layout.fragment_zige;
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

    @OnClick(R.id.lv_content)
    public void onViewClicked() {
    }
}



