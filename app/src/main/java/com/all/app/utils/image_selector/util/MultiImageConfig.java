package com.all.app.utils.image_selector.util;
import android.content.Intent;

import com.all.app.utils.image_selector.MultiImageSelectorActivity;

public class MultiImageConfig {

    /**
     * 请求加载系统照相机
     * */ 
	public static final int REQUEST_CAMERA = 100;
	
    /**
     * 相册请求 
     * */  
	public static final int REQUEST_IMAGE = 200; 
    
    
    
	/**
	 * 最大可选的图片数量  
	 * */
	 public static int  maxNum = 9 ;  
	
	
	
    
    
    /**
	 * 配置  图片选择   多选 和 是否显示 照相
	 * */  
	public  static  void  initMultiImageSelector(Intent intent , boolean  isMultiSelect  , boolean isShowCamera ){
		  int selectedMode ; 
		
		 if(isMultiSelect ==  false){
             selectedMode = MultiImageSelectorActivity.MODE_SINGLE;
         }else{
             selectedMode = MultiImageSelectorActivity.MODE_MULTI;
         } 
		 // 是否显示拍摄图片
         intent.putExtra(MultiImageSelectorActivity.EXTRA_SHOW_CAMERA, isShowCamera);
         // 最大可选择图片数量
         intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_COUNT, maxNum);
         // 选择模式
         intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_MODE, selectedMode);

	} 
	
}
