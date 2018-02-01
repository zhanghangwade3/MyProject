package com.all.app.ui.activity;

import android.Manifest;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.all.app.R;
import com.all.app.base.BaseActivity;
import com.all.app.utils.CircularImage;
import com.all.app.utils.ContainsEmojiEditText;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EditTeamActivity extends BaseActivity {
    private static final int CAMERA_JAVA_REQUEST_CODE = 7;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_baocun)
    TextView tvBaocun;
    @BindView(R.id.re_title)
    RelativeLayout reTitle;
    @BindView(R.id.textView2)
    TextView textView2;
    @BindView(R.id.iv_right2)
    CircularImage ivRight2;
    @BindView(R.id.re_money)
    RelativeLayout reMoney;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.et_name)
    ContainsEmojiEditText etName;
    @BindView(R.id.re_name)
    RelativeLayout reName;
    @BindView(R.id.tv_number)
    TextView tvNumber;
    @BindView(R.id.et_peo)
    ContainsEmojiEditText etPeo;
    @BindView(R.id.re_renzheng)
    RelativeLayout reRenzheng;
    @BindView(R.id.tv_gong)
    TextView tvGong;
    @BindView(R.id.et_gong)
    ContainsEmojiEditText etGong;
    @BindView(R.id.re_gong)
    RelativeLayout reGong;
    @BindView(R.id.bt_logout)
    Button btLogout;
    String type="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persone);
        ButterKnife.bind(this);
         type=getIntent().getStringExtra("edit");
         if("1".equals(type)){
             tvBaocun.setVisibility(View.VISIBLE);
         }else if("2".equals(type)){
             tvBaocun.setVisibility(View.GONE);
         }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, CAMERA_JAVA_REQUEST_CODE);
        }

    }

    @OnClick({R.id.et_name, R.id.et_peo, R.id.iv_back, R.id.et_gong, R.id.tv_title, R.id.tv_baocun, R.id.re_title, R.id.textView2, R.id.iv_right2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_title:
                break;
            case R.id.tv_baocun:
                break;
            case R.id.re_title:
                break;
            case R.id.textView2:
                break;
            case R.id.iv_right2:
                showDialog();
                break;
            case R.id.et_name:
                etName.setCursorVisible(true);
                etName.setHint("");
                break;
            case R.id.et_peo:
                etPeo.setCursorVisible(true);
                etPeo.setHint("");
                break;
            case R.id.et_gong:
                etGong.setCursorVisible(true);
                etGong.setHint("");
                break;

        }
    }
    Dialog dialog;
    private static final String PHOTO_FILE_NAME = "temp_photo.png";
    private static final int PHOTO_REQUEST_CAMERA = 1;// 拍照
    private static final int PHOTO_REQUEST_GALLERY = 2;// 从相册中选择
    private static final int PHOTO_REQUEST_CUT = 3;// 结果
    private boolean isModifyIcon = false;
    //修改用户图像
    private void showDialog() {
        dialog = new Dialog(this, R.style.Dialog_image);
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
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri
                            .fromFile(new File(Environment
                                    .getExternalStorageDirectory(),
                                    PHOTO_FILE_NAME)));
                }
                startActivityForResult(intent, PHOTO_REQUEST_CAMERA);
                dialog.dismiss();

            }
        });
        // 从相册
        album.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, PHOTO_REQUEST_GALLERY);
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
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PHOTO_REQUEST_CAMERA:
                    if (hasSdcard()) {
                        File tempFile = new File(
                                Environment.getExternalStorageDirectory(),
                                PHOTO_FILE_NAME);
                        crop(Uri.fromFile(tempFile));
                    } else {
                        showTips("未找到存储卡，无法存储照片！", null);
                    }
                    break;
                case PHOTO_REQUEST_GALLERY:
                    if (data != null) {
                        // 得到图片的全路径
                        Uri uri = data.getData();
                        crop(uri);
                    }
                    break;
                case PHOTO_REQUEST_CUT:
                    if (data == null)
                        return;
                    isModifyIcon = true;
                    setPicToView(data);
                    //mImgPath = getRealFilePath(EditTeamActivity.this, data.getData());
                    //XGlide.init(this).display(ci_touxiang, outUri);
                    //mImgPath = getRealFilePath(ChangeUserInfoActivity.this, outUri);
                    break;
            }
        }
    }
    private void setPicToView(Intent data) {

        Uri uri = data.getData();

        if (uri == null) {
            return;
        }
        ivRight2.setImageURI(uri);
    }

    //裁剪照片
    private void crop(Uri uri) {
        if (uri == null) {
            return;
        }
        Intent intent = new Intent(this, ClipHeaderActivity.class);
        intent.setData(uri);
        intent.putExtra("side_length", 200);//裁剪图片宽高
        startActivityForResult(intent, PHOTO_REQUEST_CUT);
    }
    //获得图片路径的方法
    public String getRealFilePath(final Context context, final Uri uri) {
        if (null == uri)
            return null;
        final String scheme = uri.getScheme();
        String data = null;
        if (scheme == null)
            data = uri.getPath();
        else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
            Cursor cursor = context.getContentResolver().query(uri,
                    new String[]{MediaStore.Images.ImageColumns.DATA}, null, null, null);
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    if (index > -1) {
                        data = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        }
        return data;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        isModifyIcon = false;
        super.onDestroy();
    }
}