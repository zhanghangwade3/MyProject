/*
 *******************************************
 * File: AppManager.java
 * Author: Lee
 * Date: 2016年5月3日
 ********************************************/
package com.all.app.app;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;

import java.util.Stack;

/**
 * 
 * @ClassName: AppManager
 * @Description: TODO(描述: Activity 管理类)
 * @author Lee
 * @date 2016年5月3日 上午10:14:41
 * @version V1.0
 */
public class AppManager {

	// Activity栈
	private static Stack<Activity> activityStack;
	// 单例模式
	private static AppManager instance;

	private AppManager() {
	}

	public static AppManager Manager() {
		// TODO 双重校验锁
		if (null == instance)
			synchronized (AppManager.class) {
				if (null == instance) {
					instance = new AppManager();
				}
			}
		return instance;
	}

	/**
	 * 添加Activity到堆栈
	 */
	public void onCreate(Activity activity) {
		if (activityStack == null) {
			activityStack = new Stack<Activity>();
		}
		activityStack.add(activity);
	}

	/**
	 * 获取当前Activity（堆栈中最后一个压入的）
	 */
	public Activity Current() {
		if (activityStack == null)
			return null;
		Activity activity = activityStack.lastElement();
		return activity;
	}
//
	/**
	 * 结束当前Activity（堆栈中最后一个压入的）
	 */
	public void onFinish() {
		if (activityStack == null)
			return;
		Activity activity = activityStack.lastElement();
		onFinish(activity);
	}

	/**
	 * 结束指定的Activity
	 */
	public void onFinish(Activity activity) {
		if (activityStack == null)
			return;
		try{
			if (activity != null) {
				activityStack.remove(activity);
				activity.finish();
				activity = null;
			}
		}catch(Exception ex){
			
		}
	}

	/**
	 * 结束指定类名的Activity
	 */
	public void onFinish(Class<?> cls) {
		if (activityStack == null)
			return;
		for (Activity activity : activityStack) {
			if (activity.getClass().equals(cls)) {
				onFinish(activity);
			}
		}
	}

	/**
	 * 结束所有Activity
	 */
	public void Kill() {
		if (activityStack == null)
			return;
		for (int i = 0; i < activityStack.size(); i++) {
			if (null != activityStack.get(i)) {
				activityStack.get(i).finish();
			}
		}
		activityStack.clear();
	}
	/**
	 * 退出应用程序
	 */
	public void AppExit(Context context) {
		try {
			Kill();
			ActivityManager activityMgr = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
			activityMgr.killBackgroundProcesses(context.getPackageName());
			System.exit(0);
		} catch (Exception e) {
		}
	}
}
