package com.all.app.app;

import android.app.Application;

import com.all.app.http.OkHttpTools;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by Administrator on 2017/3/29 0029.
 *  所有三方和类库的初始化
 */

public class Myapplication extends Application {
    // 单例模式
    private static Myapplication instance;
    public static Myapplication APP() {
        // TODO 双重校验锁
        if (null == instance)
            synchronized (Myapplication.class) {
                if (null == instance) {
                    instance = new Myapplication();
                }
            }
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        OkHttpTools.getOkHttp().Init();
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
    }
}
