/*
 *******************************************
 * File: BannerGallery.java
 ********************************************/
package com.all.app.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 
 * @ClassName: BannerGallery
 * @Description: TODO(描述: )
 */
public class BannerGallery extends Gallery implements OnItemClickListener, OnTouchListener, OnItemSelectedListener {
	/** 显示的Activity */
	private Context mContext;
	/** 图片切换时间 */
	private int mSwitchTime;
	/** 自动滚动的定时器 */
	private Timer mTimer;
	/** 当前选中的数组索引 */
	private int curIndex = 0;
	/** 上次选中的数组索引 */
	private int oldIndex = 0;
	/** 圆点选中时的背景ID */
	private int mFocusedId;
	/** 圆点正常时的背景ID */
	private int mNormalId;
	/***/
	private BaseAdapter mAdapter;

	private boolean isAutoMax = true;
	private boolean isAutoStart = true;
	/*private int[] resId = { R.drawable.ying1, R.drawable.ying2, R.drawable.ying3,
			R.drawable.ying4,  };
	public ImageView getImageView(int resId) {
		ImageView image=new ImageView(mContext);
		image.setBackgroundResource(resId);
		return image;
	}*/

	public void setAotuMax(boolean isAutoMax) {
		this.isAutoMax = isAutoMax;
	}

	public void setAotuStart(boolean isAutoStart) {
		this.isAutoStart = isAutoStart;
	}

	public BannerGallery(Context context) {
		super(context);
		mContext = context;
	}

	public BannerGallery(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
	}

	public BannerGallery(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		mContext = context;
	}

	List<View> viewList;
	private LinearLayout mCircleGroup;

	public void Start(BaseAdapter adapter, LinearLayout circleGroup, int circleFocusedId, int circleNormalId, int longTime) {
		// TODO Auto-generated method stub
		if (adapter == null || adapter.getCount() == 0)
			return;
		this.mAdapter = adapter;
		this.mSwitchTime = longTime;
		this.mCircleGroup = circleGroup;
		this.mFocusedId = circleFocusedId;
		this.mNormalId = circleNormalId;
		initViews();
		if (isAutoMax)
			super.setAdapter(new AdAdapter());
		else
			super.setAdapter(new XAdAdapter());
		super.setOnTouchListener(this);
		super.setOnItemSelectedListener(this);
		super.setOnItemClickListener(this);
		this.setSoundEffectsEnabled(false);
		this.setAnimationDuration(700); // 动画时间
		this.setUnselectedAlpha(1); // 未选中项目的透明度

		// 不包含spacing会导致onKeyDown()失效!!! 失效onKeyDown()前先调用onScroll(null,1,0)可处理
		setSpacing(0);
		// 取靠近中间 图片数组的整倍数
		setSelection((getCount() / 2 / mAdapter.getCount()) * mAdapter.getCount()); // 默认选中中间位置为起始位置
		setFocusableInTouchMode(true);
		initCircle();
		if (isAutoStart)
			startTimer();
	}

	private void initCircle() {
		// TODO Auto-generated method stub
		if (mCircleGroup != null && viewList.size() < 2) {// 如果只有一第图时不显示圆点容器
			mCircleGroup.removeAllViews();
			mCircleGroup.getLayoutParams().height = 0;
		} else if (mCircleGroup != null) {
			mCircleGroup.removeAllViews();
			// 圆点的大小是 圆点窗口的 70%;
			int Ovalheight = (int) (mCircleGroup.getLayoutParams().height * 0.7);
			// 圆点的左右外边距是 圆点窗口的 20%;
			int Ovalmargin = (int) (mCircleGroup.getLayoutParams().height * 0.2);
			android.widget.LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(Ovalheight, Ovalheight);
			layoutParams.setMargins(Ovalmargin, 0, Ovalmargin, 0);
			for (int i = 0; i < viewList.size(); i++) {
				View v = new View(mContext); // 员点
				v.setLayoutParams(layoutParams);
				v.setBackgroundResource(mNormalId);
				layoutParams.leftMargin = 20;// 设置圆点间隔
				mCircleGroup.addView(v);
			}
			// 选中第一个
			mCircleGroup.getChildAt(0).setBackgroundResource(mFocusedId);
		}
	}

	private void initViews() {
		// TODO Auto-generated method stub
		viewList = new ArrayList<View>();
		for (int i = 0; i < mAdapter.getCount(); i++) {
			View temp = mAdapter.getView(i, null, null);
			temp.setLayoutParams(new Gallery.LayoutParams(Gallery.LayoutParams.MATCH_PARENT, Gallery.LayoutParams.MATCH_PARENT));
			viewList.add(temp);
		}
	}

	/** 图片切换事件 */
	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
		curIndex = position % viewList.size();
		if (mCircleGroup != null && viewList.size() > 1) { // 切换圆点
			mCircleGroup.getChildAt(oldIndex).setBackgroundResource(mNormalId); // 圆点取消
			mCircleGroup.getChildAt(curIndex).setBackgroundResource(mFocusedId);// 圆点选中
			oldIndex = curIndex;
		}
		if(selectedListener!=null)
			selectedListener.onItemSelected(curIndex);
	}
	public interface BannerSelectedListener{
		public void onItemSelected(int position);
	}
	BannerSelectedListener selectedListener;
	public void setSelectedListener(BannerSelectedListener selectedListener) {
		this.selectedListener = selectedListener;
	}
	private OnItemClickListener mListener;

	@Override
	public void setOnItemClickListener(OnItemClickListener listener) {
		// TODO Auto-generated method stub
		mListener = listener;
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
	}

	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
		int kEvent;
		if (isScrollingLeft(e1, e2)) { // 检查是否往左滑动
			kEvent = KeyEvent.KEYCODE_DPAD_LEFT;
		} else { // 检查是否往右滑动
			kEvent = KeyEvent.KEYCODE_DPAD_RIGHT;
		}
		onKeyDown(kEvent, null);
		return true;

	}

	/** 检查是否往左滑动 */
	private boolean isScrollingLeft(MotionEvent e1, MotionEvent e2) {
		return e2.getX() > (e1.getX() + 50);
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
		return super.onScroll(e1, e2, distanceX, distanceY);
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		if (MotionEvent.ACTION_UP == event.getAction() || MotionEvent.ACTION_CANCEL == event.getAction()) {
			if (isAutoStart)
				startTimer();// 开始自动滚动任务
		} else {
			stopTimer();// 停止自动滚动任务
		}
		return false;
	}

	/** 停止自动滚动任务 */
	public void stopTimer() {
		if (mTimer != null) {
			mTimer.cancel();
			mTimer = null;
		}
	}

	/** 开始自动滚动任务 图片大于1张才滚动 */
	public void startTimer() {
		if (mTimer == null && mAdapter.getCount() > 1 && mSwitchTime > 0) {
			mTimer = new Timer();
			mTimer.schedule(new TimerTask() {
				public void run() {
					handler.sendMessage(handler.obtainMessage(1));
				}
			}, mSwitchTime, mSwitchTime);
		}
	}

	/** 处理定时滚动任务 */
	@SuppressLint("HandlerLeak")
	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			// 不包含spacing会导致onKeyDown()失效!!!
			// 失效onKeyDown()前先调用onScroll(null,1,0)可处理
			onScroll(null, null, 1, 0);
			onKeyDown(KeyEvent.KEYCODE_DPAD_RIGHT, null);
		}
	};

	/** 无限循环适配器 */
	class AdAdapter extends BaseAdapter {
		@Override
		public int getCount() {
			if (viewList.size() < 2)// 如果只有一张图时不滚动
				return viewList.size();
			return Integer.MAX_VALUE;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			return viewList.get(position % viewList.size()); // 返回ImageView
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}
	}

	/** 无限循环适配器 */
	class XAdAdapter extends BaseAdapter {
		@Override
		public int getCount() {
			return viewList.size();
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			return viewList.get(position); // 返回ImageView
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		// TODO Auto-generated method stub
		if (mListener != null)
			mListener.onItemClick(parent, view, position % viewList.size(), id);
	}
}
