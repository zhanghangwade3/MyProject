package com.all.app.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.all.app.R;
import com.all.app.base.BaseFragment;
import com.all.app.ui.adapter.TeamAdpter;
import com.all.app.utils.pullrefresh.PullToRefreshListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class TeamFragment extends BaseFragment {
    //模拟的数据
    List<String> mDatas = new ArrayList<String>(Arrays.asList("Hello",
            "World", "Welcome", "Hello",
            "World", "Welcome", "World", "Welcome", "Hello", "World", "Welcome", "Hello"));
    TeamAdpter teamAdpter;//接单适配器
    @BindView(R.id.lv_content)
    PullToRefreshListView lvContent;
    Unbinder unbinder;

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
        teamAdpter = new TeamAdpter(getActivity(), mDatas);
        lvContent.setAdapter(teamAdpter);

    }

    @Override
    protected int InitLayer() {
        return R.layout.shop_listview;
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
}



