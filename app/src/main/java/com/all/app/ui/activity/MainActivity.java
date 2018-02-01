package com.all.app.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.all.app.R;
import com.all.app.app.AppData;
import com.all.app.app.AppManager;
import com.all.app.base.BaseFragmentActivity;
import com.all.app.bean.VerSionBean;
import com.all.app.configs.Urls;
import com.all.app.http.OkHttpParam;
import com.all.app.http.ResponseEntry;
import com.all.app.ui.FragmentAdp;
import com.all.app.ui.fragment.FindFragment;
import com.all.app.ui.fragment.HomeFragment;
import com.all.app.ui.fragment.MineFragment;
import com.all.app.ui.fragment.OrderFragment;
import com.all.app.ui.fragment.ReceiveFragment;
import com.all.app.ui.fragment.TeamFragment;
import com.all.app.utils.AlertDialog;
import com.all.app.utils.FJson;
import com.all.app.utils.XGlide;
import com.bumptech.glide.Glide;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseFragmentActivity {
    FragmentAdp mFragmentAdp;//加载主页切换的fragment适配器
    List<Fragment> fragList = new ArrayList<Fragment>();//主页面绑定的fragment列表集合
    @BindView(R.id.fl_content)
    FrameLayout flContent;
    @BindView(R.id.ll_center)
    RelativeLayout llCenter;
    @BindView(R.id.ck_home)
    CheckBox ckHome;
    @BindView(R.id.ll_home)
    LinearLayout llHome;
    @BindView(R.id.ck_classify)
    CheckBox ckOrder;
    @BindView(R.id.ll_classify)
    LinearLayout llClassify;
    @BindView(R.id.ll_left)
    LinearLayout llLeft;
    @BindView(R.id.ck_market)
    CheckBox ckFind;
    @BindView(R.id.ll_market)
    LinearLayout llMarket;
    @BindView(R.id.ck_mine)
    CheckBox ckMine;
    @BindView(R.id.ll_mine)
    LinearLayout llMine;
    @BindView(R.id.ll_right)
    LinearLayout llRight;
    @BindView(R.id.ck_recevice)
    CheckBox ck_recevice;
    String type="";
    String source="3";

    OrderFragment orderFragment;
    FragmentTransaction ft;
    VerSionBean verSionBean;
    String versionCode="";
    PackageManager pm;
    PackageInfo pi;
    int number=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        sHA1(this);
        pm = getPackageManager();
        try {
            pi = pm.getPackageInfo(getPackageName(), 0);
            number= Integer.parseInt(pi.versionName.substring(0, pi.versionName.length()-2));
       System.out.println("77777777777777777777777777777777"+ number);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        fragList.clear();
        fragList.add(new HomeFragment());//首页
        fragList.add(new OrderFragment());//订单
        fragList.add(new ReceiveFragment());//接单
        fragList.add(new FindFragment());//发现
        fragList.add(new MineFragment());//我的
        mFragmentAdp = new FragmentAdp(this, fragList, R.id.fl_content);
        type=getIntent().getStringExtra("type");
        if("1".equals(type)){
            ckOrder.setChecked(false);
            ckHome.setChecked(false);
            ck_recevice.setChecked(true);
            ckFind.setChecked(false);
            ckMine.setChecked(false);
            mFragmentAdp.CheckedIndex(2);
        }else if("2".equals(type)){
            ckOrder.setChecked(true);
            ckHome.setChecked(false);
            ckFind.setChecked(false);
            ckMine.setChecked(false);
            mFragmentAdp.CheckedIndex(1);
        }

        findNewVersion(); //判断是否版本更新
    }



        public static String sHA1(Context context) {
            try {
                PackageInfo info = context.getPackageManager().getPackageInfo(
                        context.getPackageName(), PackageManager.GET_SIGNATURES);
                byte[] cert = info.signatures[0].toByteArray();
                MessageDigest md = MessageDigest.getInstance("SHA1");
                byte[] publicKey = md.digest(cert);
                StringBuffer hexString = new StringBuffer();
                for (int i = 0; i < publicKey.length; i++) {
                    String appendString = Integer.toHexString(0xFF & publicKey[i])
                            .toUpperCase(Locale.US);
                    if (appendString.length() == 1)
                        hexString.append("0");
                    hexString.append(appendString);
                    hexString.append(":");
                }
                String result= hexString.toString();
                System.out.println("88888888888888888888888888888888888888888"+result);
                return result.substring(0, result.length()-1);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            return null;
        }




    private void findNewVersion() {
        OkHttpParam param = new OkHttpParam();
        param.add("source", source);
        _PostEntry(Urls.getversion, param, Urls.ActionId.getversion, true);
    }

    @Override
    protected void onResume() {
        super.onResume();

        flContent = (FrameLayout) findViewById(R.id.fl_content);
        LinearLayout ll_order = (LinearLayout)flContent.findViewById(R.id.ll_order);
        ll_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ckOrder.setChecked(true);
                ckHome.setChecked(false);
                ckFind.setChecked(false);
                ckMine.setChecked(false);
                mFragmentAdp.CheckedIndex(1);
            }
        });
        RelativeLayout re_time= (RelativeLayout)flContent.findViewById(R.id.re_time);
        re_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,ExecutionActivity.class));
            }
        });
        RelativeLayout re_bao= (RelativeLayout)flContent.findViewById(R.id.re_bao);
        re_bao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ckOrder.setChecked(false);
                ckHome.setChecked(false);
                ckFind.setChecked(false);
                ckMine.setChecked(false);
                mFragmentAdp.CheckedIndex(2);
            }
        });
        int id = getIntent().getIntExtra("release", 0);
        if (id == 1) {
            mFragmentAdp.CheckedIndex(2);
        }
        int index = mFragmentAdp.getCurrentTab();
        ckHome.setChecked(false);
        ckOrder.setChecked(false);
        ckFind.setChecked(false);
        ckMine.setChecked(false);
        switch (index) {
            case 0:
                ckHome.setChecked(true);
                break;
            case 1:
                ckOrder.setChecked(true);
                break;
            case 2:

                break;
            case 3:
                ckFind.setChecked(true);
                break;
            case 4:
                ckMine.setChecked(true);
                break;
        }
    }

    @OnClick({R.id.ll_classify,R.id.ll_home,R.id.ll_market,R.id.ll_mine,R.id.ck_home,
            R.id.ck_classify, R.id.ll_left, R.id.ck_market,
            R.id.ck_mine,  R.id.ll_right, R.id.ck_recevice})
    public void onViewClicked(View view) {
        // 状态清空
        ckHome.setChecked(false);
        ckOrder.setChecked(false);
        ckFind.setChecked(false);
        ckMine.setChecked(false);
        int index = 0;
        switch (view.getId()) {
            case R.id.ck_home:
                ckHome.setChecked(true);
                index = 0;
                break;
            case R.id.ll_home:
                ckHome.setChecked(true);
                index = 0;
                break;
            case R.id.ck_classify:
                ckOrder.setChecked(true);
                index = 1;
                break;
            case R.id.ll_classify:
                ckOrder.setChecked(true);
                index = 1;
                break;
            case R.id.ll_left:
                break;
            case R.id.ll_market:
                ckFind.setChecked(true);
                index = 3;
                break;
            case R.id.ck_market:
                ckFind.setChecked(true);
                index = 3;
                break;
            case R.id.ck_mine:
                ckMine.setChecked(true);
                index = 4;
                break;
            case R.id.ll_mine:
                ckMine.setChecked(true);
                index = 4;
                break;
            case R.id.ll_right:
                break;
            case R.id.ck_recevice:
                ckHome.setChecked(false);
                ckOrder.setChecked(false);
                ckFind.setChecked(false);
                ckMine.setChecked(false);
                index = 2;
                break;
        }
        mFragmentAdp.CheckedIndex(index);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
         if(keyCode==KeyEvent.KEYCODE_BACK && event.getAction()==KeyEvent.ACTION_DOWN){
             AlertDialog  dialog =new AlertDialog(this);
                 dialog.builder();
                 dialog.setTitle("退出确认");
                 dialog.setMsg("真的要退出万装吗？");
                 dialog.setPositiveButton("确定", new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {
                         AppManager.Manager().AppExit(MainActivity.this);
                         //android.os.Process.killProcess(android.os.Process.myPid());
                     }
                 });
                dialog.setNegativeButton("取消", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
             dialog.show();
             return true;
         }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onNetSuccess(int actionId, ResponseEntry result) {
        super.onNetSuccess(actionId, result);
      switch (actionId){
          case Urls.ActionId.getversion:
              if(result.isSuccess()){
                  System.out.println("44444444444444444444444444444444444"+result);
                  verSionBean= FJson.getObject(result.getData().toString(),VerSionBean.class);
                  versionCode=verSionBean.getVersionCode();
                  int nub= Integer.parseInt(versionCode);
                  if(number<nub){
                      AlertDialog dialog = new AlertDialog(this);
                      dialog.builder();
                      dialog.setTitle("有新版本更新");
                      dialog.setMsg("当前有新版本，点击去下载");
                      dialog.setPositiveButton("下载", new View.OnClickListener() {

                          @Override
                          public void onClick(View v) {
                              // TODO Auto-generated method stub
                              Intent intent = new Intent();
                              intent.setAction("android.intent.action.VIEW");
                              Uri content_url = Uri.parse(verSionBean.getDownlandUrl());
                              intent.setData(content_url);
                              startActivity(intent);
                          }
                      });
                      dialog.setNegativeButton("取消", new View.OnClickListener() {

                          @Override
                          public void onClick(View v) {
                              // TODO Auto-generated method stub
                          }
                      });
                      dialog.show();
                  }
              }
              break;
      }
    }
}





