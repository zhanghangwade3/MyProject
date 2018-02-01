package com.all.app.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.all.app.R;
import com.all.app.app.AppData;
import com.all.app.base.BaseFragment;
import com.all.app.bean.BanerBean;
import com.all.app.bean.HomeBean;
import com.all.app.bean.NewsBean;
import com.all.app.configs.Urls;
import com.all.app.http.OkHttpParam;
import com.all.app.http.ResponseEntry;
import com.all.app.ui.activity.DetailActivity;
import com.all.app.ui.activity.MainActivity;
import com.all.app.ui.activity.MessgeActivity;
import com.all.app.ui.activity.ProOrderActivity;
import com.all.app.ui.activity.QuoteListActivity;
import com.all.app.ui.activity.SchoolActivity;
import com.all.app.ui.activity.ShopActivity;
import com.all.app.ui.adapter.HomeBannerAdapter;
import com.all.app.utils.AutoTextView2;
import com.all.app.utils.BannerGallery;
import com.all.app.utils.FJson;
import com.all.app.utils.LeeTools;
import com.all.app.utils.XGlide;
import com.all.app.utils.city.CityChoose2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class HomeFragment extends BaseFragment {
    @BindView(R.id.tv_city)
    TextView tvCity;
    @BindView(R.id.ib_hongdian)
    ImageButton ibHongdian;
    @BindView(R.id.ib_hongdian2)
    ImageButton ibHongdian2;
    @BindView(R.id.rl_xiaoxi)
    RelativeLayout rlXiaoxi;
    @BindView(R.id.banner)
    BannerGallery banner;
    @BindView(R.id.banner_circle)
    LinearLayout bannerCircle;
    @BindView(R.id.iv_time)
    ImageView ivTime;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.iv_voice)
    ImageView ivVoice;
    @BindView(R.id.tv_news)
    TextView tvNews;
    @BindView(R.id.tv_news_content)
    AutoTextView2 tvNewsContent;
    @BindView(R.id.image_gongying)
    ImageView imageGongying;
    @BindView(R.id.ll_school)
    LinearLayout llSupply;
    @BindView(R.id.image_caigou)
    ImageView imageCaigou;
    @BindView(R.id.ll_shop)
    LinearLayout llBuyer;
    @BindView(R.id.ll_order)
    LinearLayout llCar;
    @BindView(R.id.ll_problem)
    LinearLayout llNews;
    @BindView(R.id.tv_number)
    TextView tvNumber;
    @BindView(R.id.iv_pic)
    ImageView ivPic;
    @BindView(R.id.tv_task)
    TextView tvTask;
    @BindView(R.id.iv_next)
    ImageView ivNext;
    Unbinder unbinder;
    @BindView(R.id.tv_days)
    TextView tv_days;
    @BindView(R.id.tv_hours)
    TextView tv_hours;
    @BindView(R.id.tv_minut)
    TextView tv_minut;
    @BindView(R.id.tv_secondp)
    TextView tv_secondp;
    String endtime = "";
    String endtimenum = "";
    HomeBean homeBean;
    List<NewsBean> newsBean;
    List<BanerBean> bewsBean;
    int time = 0;
    private long mDay = 0;
    private long mHour = 0;
    private long mMin = 0;
    private long mSecond = 0;// 天 ,小时,分钟,秒
    private boolean isRun = true;
    String title = "";
    //倒计时功能
    private Handler timeHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                if (mDay == 0 && mHour == 0 && mMin == 0 && mSecond == 0) {
                    tv_days.setText("0");
                    tv_hours.setText("0");
                    tv_minut.setText("0");
                    tv_secondp.setText("0");
                } else {
                    computeTime();
                    tv_days.setText(mDay + "");
                    tv_hours.setText(mHour + "");
                    tv_minut.setText(mMin + "");
                    tv_secondp.setText(mSecond + "");

                }
            }
        }
    };

    //倒计时计算
    private void computeTime() {
        mSecond--;
        if (mSecond < 0) {
            mMin--;
            mSecond = 59;
            if (mMin < 0) {
                mMin = 59;
                mHour--;
                if (mHour < 0) {
                    // 倒计时结束
                    mHour = 23;
                    mDay--;
                }
            }
        }
    }

    HomeBannerAdapter homeBannerAdapter;
    int position = 0;
    protected static final int GUIUPDATEIDENTIFIER = 0x101;
    Timer mTimer;
    private boolean isFirstComeIn;
    List<String> mDatas = new ArrayList<String>(Arrays.asList("Hello",
            "World", "Welcome", "Hello"
    ));
    //标题的翻转
    Handler myHandler = new Handler() {
        //2.重写消息处理函数
        public void handleMessage(Message msg) {
            switch (msg.what) {
                //判断发送的消息
                case GUIUPDATEIDENTIFIER:
                    //第一次显示从0开始
                    if (newsBean != null && newsBean.size() > 0) {
                        title = newsBean.get(position).getArt_title();
                        tvNewsContent.setText(title);
                        tvNewsContent.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent1 = new Intent(getActivity(), DetailActivity.class);
                                intent1.putExtra("art_url", newsBean.get(position).getArt_url());
                                intent1.putExtra("art_title", title);
                                startActivity(intent1);
                            }
                        });
                        notifyPostion();//第二次累加一次

                    }
                    break;
            }
            super.handleMessage(msg);
        }
    };

    //更新消息的位置
    private void notifyPostion() {
        position++;
        if (newsBean != null && position == newsBean.size()) {
            position = 0;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void Init() {
        tvCity.setText(AppData.Init().getLoginBean().getCityname());
        loadData();//获取首页数据
        initBanner();
        loadNewsData();//获取公告列表的数据
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onDetach() {
        super.onDetach();
        System.exit(0);
    }

    private void initBanner() {
        OkHttpParam param = new OkHttpParam();
        param.add("uid", AppData.Init().getLoginBean().getUid());
        _PostEntry(Urls.bannerlist, param, Urls.ActionId.bannerlist, true);

    }

    private void loadNewsData() {
        OkHttpParam param = new OkHttpParam();
        param.add("uid", AppData.Init().getLoginBean().getUid());
        _PostEntry(Urls.notice, param, Urls.ActionId.notice, true);

    }

    private void loadData() {
        OkHttpParam param = new OkHttpParam();
        param.add("uid", AppData.Init().getLoginBean().getUid());
        _PostEntry(Urls.getotherinfo, param, Urls.ActionId.getotherinfo, true);
    }

    //开始倒计时
    private void startRun() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                while (isRun) {
                    try {
                        Thread.sleep(1000); //sleep1000ms
                        Message message = Message.obtain();
                        message.what = 1;
                        timeHandler.sendMessage(message);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    //刷新消息
    private void ntifyTitle() {
        mTimer = new Timer();
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (isFirstComeIn){
                    tvNewsContent.next();
                    isFirstComeIn = true;
                }

                Message message = new Message();
                //发送消息与处理函数里一致
                message.what = GUIUPDATEIDENTIFIER;
                myHandler.sendMessage(message);
            }
        }, 0, 5 * 1000);
    }

    //初始化Banner数据
    private void notifyBanner() {
        homeBannerAdapter = new HomeBannerAdapter(mContext, bewsBean);
        banner.Start(homeBannerAdapter, bannerCircle, R.drawable.judian, R.drawable.ic_dot_normal, 3000);
        banner.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
                AppData.Init().openAdvert(getActivity(), bewsBean.get(position));
            }
        });
    }

    @Override
    protected int InitLayer() {
        return R.layout.fragment_home;
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

    @OnClick({R.id.rl_xiaoxi, R.id.tv_city, R.id.ib_hongdian, R.id.ll_school, R.id.ll_shop, R.id.ll_order, R.id.ll_problem, R.id.tv_task, R.id.iv_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_school:
                Toast.makeText(getActivity(), "暂未开放", Toast.LENGTH_SHORT).show();
                //startActivity(new Intent(getActivity(), SchoolActivity.class));
                break;
            case R.id.tv_city:
                break;
            case R.id.ll_shop:
                Toast.makeText(getActivity(), "暂未开放", Toast.LENGTH_SHORT).show();
                //startActivity(new Intent(getActivity(),ShopActivity.class));
                break;
            case R.id.ll_problem:
                startActivity(new Intent(getActivity(), ProOrderActivity.class));
                break;
            case R.id.tv_task:
                startActivity(new Intent(getActivity(), QuoteListActivity.class));
                break;
            case R.id.iv_next:
                startActivity(new Intent(getActivity(), QuoteListActivity.class));
                break;
            case R.id.ib_hongdian:
                startActivity(new Intent(getActivity(), MessgeActivity.class));
                break;
            case R.id.rl_xiaoxi:
                startActivity(new Intent(getActivity(), MessgeActivity.class));
                break;
        }
    }

    @Override
    protected void onNetSuccess(int actionId, ResponseEntry result) {
        super.onNetSuccess(actionId, result);
        switch (actionId) {
            case Urls.ActionId.getotherinfo:
                homeBean = FJson.getObject(result.getData().toString(), HomeBean.class);
                if (homeBean != null) {
                    initView();
                }
                break;
            case Urls.ActionId.notice:
                if (result.isSuccess()) {
                    newsBean = FJson.getObjects(result.getData().toString(), NewsBean.class);
                    if (newsBean != null) {
                        ntifyTitle();
                    }

                }
                break;
            case Urls.ActionId.bannerlist:
                if (result.isSuccess()) {
                    if (result.getData().toString() == null) {
                        return;
                    }
                    bewsBean = FJson.getObjects(result.getData().toString(), BanerBean.class);
                    if (bewsBean != null) {
                        notifyBanner();//加载banner图
                    }

                } else {
                    showTips(result.getMsg(), null);
                }
                break;
        }
    }

    private void initView() {
        if ("0".equals(homeBean.getOrdercount())) {
            ivPic.setImageResource(R.drawable.czsc);
        } else {
            ivPic.setImageResource(R.drawable.zckc);
        }
        LeeTools.setText(tvNumber, homeBean.getOrdercount());
        if (0 < Integer.parseInt(homeBean.getXxnum())) {
            ibHongdian2.setVisibility(View.VISIBLE);
        } else {
            ibHongdian2.setVisibility(View.GONE);
        }
        endtime = homeBean.getEndtimestr();
        if (!TextUtils.isEmpty(endtime)) {
            time = Integer.parseInt(endtime);
            mDay = time / (60 * 60 * 24);
            mHour = time / (60 * 60) - mDay * 24;
            mMin = time / 60 - mHour * 60 - mDay * 24 * 60;
            mSecond = time - mMin * 60 - mHour * 60 * 60 - mDay * 24 * 60 * 60;
            startRun(); //开始倒计时
        }
        endtimenum = homeBean.getEndtimenum();
        System.out.println("555555555555555555555555555555555555" + endtimenum);
        tvTime.setText(endtimenum + "条任务倒计时:");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
