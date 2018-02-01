package com.all.app.base;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.all.app.R;
import com.all.app.configs.Urls;
import com.all.app.http.OkHttpEntry;
import com.all.app.http.OkHttpParam;
import com.all.app.http.OkHttpTools;
import com.all.app.http.ResponseEntry;
import com.all.app.ui.popupWindow.BubblePopupWindow;
import com.all.app.utils.AlertDialog;
import com.all.app.utils.Tools;
import com.all.app.utils.XDialog;
import java.io.File;

import okhttp3.Call;

/**
 * @author Lee
 * @version V1.0
 * @ClassName: BaseFragment
 * @Description: TODO(描述: Fragment基类)
 * @date 2017年3月28日 下午3:42:31
 */
@SuppressLint("NewApi")
public abstract class BaseFragment extends Fragment {
    public Context mContext;
    protected View rootView;
    LayoutInflater inflater;

    @Override
    public void onAttach(Activity activity) {
        // TODO Auto-generated method stub
        super.onAttach(activity);
        mContext = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        this.inflater = inflater;
        rootView = inflater.inflate(InitLayer(), null);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);

        Init();
    }

    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            showTips("当前网络不通，请检查网络", null);

        }

    };

    //使用okhttp请求框架
    protected void _PostEntry(String url, OkHttpParam param,
                              final int actionId, boolean isShow) {
		if (!Tools.isNetworkAvailable(getActivity())) {
            showTips("当前网络不通，请检查网络", null);
            return;
		}
        if (isShow) {
            showDialog(mContext, "");
        }
        OkHttpTools.getOkHttp()._Post(Urls._URL + url, param,
                new OkHttpEntry() {

                    @Override
                    public void onResponse(Call call, final ResponseEntry result) {
                        // TODO Auto-generated method stub
                        getActivity().runOnUiThread(new Runnable() {
                            public void run() {
                                dismissDialog();
                                if (!result.isSuccess()
                                        && TextUtils.isEmpty(result.getMsg())) {
                                    result.setMsg("操作失败");
                                }
                                onNetSuccess(actionId, result);
                            }
                        });
                    }

                    @Override
                    public void onFailure(final Call call, final Exception ex) {
                        // TODO Auto-generated method stub
                        getActivity().runOnUiThread(new Runnable() {
                            public void run() {
                                dismissDialog();
                                onNetFailure(call, ex);
                                showTips("操作失败", null);
                            }
                        });
                    }
                });
    }

    protected void onNetFailure(Call call, Exception ex) {
    }

    protected void onNetSuccess(int actionId, String result) {

    }

    protected void onNetSuccess(int actionId, ResponseEntry result) {
    }

    /**
     * 多种隐藏软件盘方法的其中一种
     *
     * @param token
     */
    protected void hideSoftInput(IBinder token) {
        if (token != null) {
            InputMethodManager im = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            im.hideSoftInputFromWindow(token,
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    /**
     * 提示 Toast
     */
    //private XToast tipsToast;
    public void showTips(String msg, OnClickListener confirmListener, OnClickListener cancleListener) {
        if (null != getActivity() && !getActivity().isDestroyed())
            new AlertDialog(mContext).builder().setTitle("提示").setMsg(msg).setCancelable(false)
                    .setPositiveButton("确定", confirmListener).setNegativeButton("取消", cancleListener).show();
    }

    public void showTips(String msg, OnClickListener ll) {
        //做个标记，不确定该不该
        if (Looper.myLooper() == null) {
            Looper.prepare();
        }
        if (null != getActivity() && !getActivity().isDestroyed())
            new AlertDialog(mContext).builder().setTitle("提示").setMsg(msg).setCancelable(false).setPositiveButton("确定", ll).show();

    }

    public void showTips(String title, String msg, OnClickListener ll) {
        if (null != getActivity() && !getActivity().isDestroyed())
            new AlertDialog(mContext).builder().setTitle(title).setMsg(msg).setCancelable(false).setPositiveButton("确定", ll).show();
    }

    /**
     * Fragment 入口
     */
    protected abstract void Init();

    /**
     * 初始化Fragment布局ID
     */
    protected abstract int InitLayer();

    Dialog dialog;

    /* 头像名称 */
    private static final String PHOTO_FILE_NAME = "temp_photo.jpg";

    /**
     * 一个按钮提示框
     *
     * @param
     * @param
     * @param
     */
    protected void showDialog(final int photoType, final int imageType) {
        // TODO Auto-generated method stub
        dialog = new Dialog(mContext, R.style.Dialog_image);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.xdg_image);
        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        Button photograph = (Button) dialog.findViewById(R.id.photograph);
        Button album = (Button) dialog.findViewById(R.id.album);
        Button cancel = (Button) dialog.findViewById(R.id.cancel);

        Window dialogWindow = dialog.getWindow();
        dialogWindow.setGravity(Gravity.CENTER);
        dialog.show();
        dialog.getWindow().setAttributes(params);

        // 拍照
        photograph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                // 判断存储卡是否可以用，可用进行存储
                if (hasSdcard()) {
                    intent.putExtra(MediaStore.EXTRA_OUTPUT,
                            Uri.fromFile(new File(Environment.getExternalStorageDirectory(), PHOTO_FILE_NAME)));
                }
                startActivityForResult(intent, photoType);
                dialog.dismiss();

            }
        });
        // 从相册
        album.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, imageType);
                dialog.dismiss();

            }
        });
        // 取消
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();

            }
        });

    }

    private boolean hasSdcard() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }
    /**
     * 显示dialog
     *
     * @param mContext
     * 上下文
     * @param msg
     * 提示语
     */
    protected XDialog loadingDialog;

    public void showDialog(Context mContext, String msg) {
        if (null != loadingDialog) {
            loadingDialog.dismiss();
        }
        loadingDialog = new XDialog(mContext, R.style.Dialog_image, msg);
        loadingDialog.show();
    }

    /*添加气泡提示框*/
    public void showPopTips(String msg, View target, int gravity, float bubbleOffset) {

        BubblePopupWindow pw = new BubblePopupWindow(getActivity());
        TextView tvContent = new TextView(getActivity());
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.addRule(Gravity.CENTER);
        tvContent.setPadding(15, 15, 15, 15);
        tvContent.setText(msg);
        tvContent.setLayoutParams(params);
        pw.setBubbleView(tvContent); // 设置气泡内容
        pw.show(target, gravity, bubbleOffset); // 显示弹窗
    }
    /**
     * 让dialog消失
     */
    public void dismissDialog() {
        if (null != loadingDialog) {
            loadingDialog.dismiss();
        }
    }
}
