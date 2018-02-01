package com.all.app.ui.activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.all.app.R;
import com.all.app.base.BaseActivity;
import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GuideActivity extends BaseActivity {

    @BindView(R.id.vp_guide)
    ViewPager vpGuide;
    @BindView(R.id.btn_start)
    Button btnStart;
    @BindView(R.id.ll_point_group)
    LinearLayout llPointGroup;
    private static final int[] mImageIds = new int[]{R.drawable.first,
            R.drawable.second, R.drawable.third, R.drawable.four};
    private ArrayList<ImageView> mImageViewList;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        ButterKnife.bind(this);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        //应用区域
        Rect outRect1 = new Rect();
        getWindow().getDecorView().getWindowVisibleDisplayFrame(outRect1);
        int statusBar = dm.heightPixels - outRect1.height();  //状态栏高度=屏幕高度-应用区域高度
        System.out.println("333333333333333333333333333333333333333"+statusBar);
        btnStart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // 更新sp, 表示已经展示了新手引导
                sp = getSharedPreferences("config", MODE_PRIVATE);
                sp.edit().putBoolean("is_user_guide_showed", true).commit();
                //跳转到主页
                startActivity(new Intent(GuideActivity.this, LoginActivity.class));
                finish();
            }
        });
        initViews();//初始化界面
        vpGuide.setAdapter(new GuideAdapter());
        vpGuide.setOnPageChangeListener(new GuidePageListener());
    }
    //初始化界面
    private void initViews() {
        //把得到的图片用数组装起来
        mImageViewList = new ArrayList<ImageView>();
        // 初始化引导页的4个页面
        for (int i = 0; i < mImageIds.length; i++) {
            ImageView image = new ImageView(this);
            image.setBackgroundResource(mImageIds[i]);// 设置引导页背景
            mImageViewList.add(image);
        }

    }

    //创建适配器
    class GuideAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return mImageIds.length;
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }


        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(mImageViewList.get(position));
            return mImageViewList.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

    }

    //创建监听的内部类，实现ViewPager的几个方法
    private class GuidePageListener implements ViewPager.OnPageChangeListener {
        // 滑动事件
        @Override
        public void onPageScrolled(int position, float positionOffset,
                                   int positionOffsetPixels) {

        }

        // 某个页面被选中
        @Override
        public void onPageSelected(int position) {
            if (position == mImageIds.length - 1) {// 最后一个页面
                btnStart.setVisibility(View.VISIBLE);// 显示开始体验的按钮
            } else {
                btnStart.setVisibility(View.INVISIBLE);
            }
        }

        // 滑动状态发生变化
        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }


    @OnClick({R.id.vp_guide, R.id.btn_start, R.id.ll_point_group})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.vp_guide:
                break;
            case R.id.btn_start:
                break;
            case R.id.ll_point_group:
                break;
        }
    }
}
