package com.all.app.app;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.all.app.bean.BanerBean;
import com.all.app.bean.LoginBean;
import com.all.app.bean.NewsBean;
import com.all.app.ui.activity.DetailActivity;
import com.all.app.utils.FJson;
import com.all.app.utils.SharedTools;
import com.all.app.utils.city.WheelA;
import com.all.app.utils.city.WheelC;
import com.all.app.utils.city.WheelP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/13 0013.
 */

public class AppData {
    //Shared
    public static final String APP_SHARED_NAME = "app.shared.tools.name";
    public static final String APP_LOGIN_INFO = "app.data.login.info";
    public static final String APP_SIGN_ADDRESS= "app.data.login.address";
    // 单例模式
    private static AppData instance;
    public static List<WheelP> wheelPs;
    public static List<WheelP> changeWheelPs = new ArrayList<WheelP>();



    static {
        try {
            String filename = "city.json";
            InputStreamReader inputReader = new InputStreamReader(Myapplication.APP().getResources().getAssets().open(filename));
            BufferedReader bufReader = new BufferedReader(inputReader);
            String line = "";
            String Result = "";
            while ((line = bufReader.readLine()) != null)
                Result += line;
            wheelPs = FJson.getObjects(Result, WheelP.class);
            changeDatas();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }






    /*市区加不限*/
    private static void changeDatas() {

        for (WheelP item : wheelPs) {
            try {
                WheelP temp = (WheelP) item.clone();
                changeWheelPs.add(temp);
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }

        for (WheelP item : changeWheelPs) {
            if (!item.getProvince_name().equals("全国")) {
                List<WheelC> cityDatas = new ArrayList<WheelC>();
                WheelC cityFirst = new WheelC();
                WheelA area1First = new WheelA();
                area1First.setArea_name("不限");
                area1First.setArea_id("");
                ArrayList firstAreas = new ArrayList<WheelA>();
                firstAreas.add(area1First);
                cityFirst.setCity_name("不限");
                cityFirst.setCity_id("");
                cityFirst.setAreaData(firstAreas);
                cityDatas.add(0, cityFirst);
                cityDatas.addAll(item.getCityData());
                for (WheelC city : item.getCityData()) {
                    if (city != null) {
                        List<WheelA> areaDatas = new ArrayList<WheelA>();
                        WheelA areaFirst = new WheelA();
                        areaFirst.setArea_name("不限");
                        areaFirst.setArea_id("");
                        areaDatas.add(0, areaFirst);
                        areaDatas.addAll(city.getAreaData());
                        city.setAreaData(areaDatas);
                    }
                }
                item.setCityData(cityDatas);
            }
        }
    }

    private AppData() {
    }


    public static AppData Init() {
        // mContext = ctx;
        // TODO 双重校验锁
        if (null == instance)
            synchronized (AppData.class) {
                if (null == instance) {
                    instance = new AppData();
                }
            }
        return instance;
    }

    public void saveLoginBean(LoginBean loginBean) {
        SharedTools sharedTools = new SharedTools(Myapplication.APP(), APP_SHARED_NAME);
        sharedTools.setObject(APP_LOGIN_INFO, loginBean);
    }

    public LoginBean getLoginBean() {
        SharedTools sharedTools = new SharedTools(Myapplication.APP(), APP_SHARED_NAME);
        return sharedTools.getObject(APP_LOGIN_INFO ,LoginBean.class);
    }

    //保存地址信息
    public void saveCityStr(String citystr) {
        SharedTools sharedTools = new SharedTools(Myapplication.APP().APP(), APP_SHARED_NAME);
        sharedTools.setString(APP_SIGN_ADDRESS, citystr);
    }

    public String getCityStr() {
        SharedTools sharedTools = new SharedTools(Myapplication.APP().APP(), APP_SHARED_NAME);
        return sharedTools.getString(APP_SIGN_ADDRESS);
    }
    /***/
    public void LogOut() {
        saveLoginBean(null);

    }
    /**
     * 打开Banner相应的数据
     */
    public void openAdvert(Context mContext, BanerBean item) {
        // TODO Auto-generated method stub
        Intent intent = new Intent(mContext, DetailActivity.class);
        intent.putExtra("art_url", item.getArt_url());
        intent.putExtra("art_title",item.getArt_title());
        mContext.startActivity(intent);
        return;
    }


}
