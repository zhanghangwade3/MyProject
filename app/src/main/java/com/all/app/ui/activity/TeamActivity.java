package com.all.app.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.all.app.R;
import com.all.app.base.AdapterBase;
import com.all.app.base.BaseActivity;
import com.all.app.ui.adapter.TeamAdpter;
import com.all.app.utils.pullrefresh.PullToRefreshListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TeamActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.re_title)
    RelativeLayout reTitle;
    @BindView(R.id.re_add)
    RelativeLayout re_add;

    @BindView(R.id.lv_content)
    PullToRefreshListView lvContent;
    //模拟的数据
    List<String> mDatas = new ArrayList<String>(Arrays.asList("Hello",
            "World", "Welcome", "Hello"));
    TeamAdpter teamAdpter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);
        ButterKnife.bind(this);
        teamAdpter=new TeamAdpter(this,mDatas);
        teamAdpter.setAdpListener(new AdapterBase.IAdpBaseListener() {
            @Override
            public void onLazyAdpListener(int key, int position, Object data) {

                if(key==TeamAdpter.CLICK_ITEM){
                    Intent intent=new Intent(TeamActivity.this,EditTeamActivity.class);
                     intent.putExtra("edit","1");
                     startActivity(intent);
                }
            }
        });

        lvContent.setAdapter(teamAdpter);
    }

    @OnClick({R.id.re_add,R.id.iv_back, R.id.tv_title, R.id.re_title, R.id.lv_content})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.re_add:
                Intent intent=new Intent(TeamActivity.this,EditTeamActivity.class);
                intent.putExtra("edit","2");
                startActivity(intent);
                break;
            case R.id.tv_title:
                break;
            case R.id.re_title:
                break;
            case R.id.lv_content:
                break;
        }
    }
}
