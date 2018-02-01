package com.all.app.utils;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * 
 * @ClassName: ScrollGridView
 * @Description: TODO(描述: ScrollView 中嵌套 GridView)
 * @author Lee
 * @date 2016年5月3日 下午3:00:03
 * @version V1.0
 */
public class ScrollGridView extends GridView {
	public ScrollGridView(Context context) {
        super(context);  
    }  
  
    public ScrollGridView(Context context, AttributeSet attrs) {
        super(context, attrs);  
    }  
  
    public ScrollGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);  
        // TODO 自动生成的构造函数存根  
    }  
  
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {  
        // TODO 自动生成的方法存根  
        int expandSpec = MeasureSpec.makeMeasureSpec(
                Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);   
    }  
}
