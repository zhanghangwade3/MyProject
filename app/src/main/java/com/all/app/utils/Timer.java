package com.all.app.utils;
import android.app.Activity;
import android.os.CountDownTimer;
import android.text.Spannable;
import android.text.SpannableString;
import android.widget.TextView;

import com.all.app.R;

/**
 * @author xingyy
 * 获取短信验证码倒计时
 *
 */


public class Timer extends CountDownTimer {
	
	//当前对象
	private Activity activity;
	
	//触发  发送验证码的事件
	private TextView tv;
	
	public Timer(long millisInFuture, long countDownInterval, Activity activity, TextView tv) {
		super(millisInFuture, countDownInterval);
		// TODO Auto-generated constructor stub
		
		this.activity = activity;
		this.tv = tv;
	}

	@Override
	public void onTick(long millisUntilFinished) {
		// TODO Auto-generated method stub
		//设置不能点击
		tv.setClickable(false);
		tv.setText(millisUntilFinished /1000+activity.getString(R.string.count_down));  //倒计时时间
		//背景设置成灰色 此时不能点击
		//tv.setBackgroundColor(activity.getResources().getColor(R.color.grey));
		Spannable span = new SpannableString(tv.getText().toString());
		//将倒计时  时间显示成黑色
//		span.setSpan(new ForegroundColorSpan(R.color.white), 0,3, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
		tv.setText(span);
		
	}

	@Override
	public void onFinish() {
		tv.setText("重新获取");
		//重新获取点击功能
		tv.setClickable(true);
		mOnTimeFinishListener.onTimeFinished();
	}
	
	public interface OnTimeFinishListener{
		void onTimeFinished();
	}
	
	OnTimeFinishListener mOnTimeFinishListener;

	public void setOnTimeFinishListener(OnTimeFinishListener onTimeFinishListener) {
		this.mOnTimeFinishListener = onTimeFinishListener;
	}
	

}
