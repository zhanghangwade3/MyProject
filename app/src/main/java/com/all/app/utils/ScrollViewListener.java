package com.all.app.utils;
//滑动事件的监听接口
public interface ScrollViewListener {

	void onScrollChanged(MyScrollView scrollView, int x, int y, int oldx, int oldy);

}