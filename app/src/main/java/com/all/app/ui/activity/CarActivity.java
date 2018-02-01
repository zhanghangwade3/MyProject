package com.all.app.ui.activity;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.all.app.R;
import com.all.app.base.BaseActivity;
import com.all.app.utils.ContainsEmojiEditText;
import com.all.app.utils.XGlide;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.R.attr.angle;

public class CarActivity extends BaseActivity {

    private static final int CAMERA_JAVA_REQUEST_CODE = 1;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.iv_car)
    ImageView iv_car;
    @BindView(R.id.iv_zheng)
    ImageView iv_zheng;
    @BindView(R.id.iv_number)
    ImageView iv_number;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.re_title)
    RelativeLayout reTitle;
    @BindView(R.id.textVi1)
    TextView textVi1;
    @BindView(R.id.re_mon)
    RelativeLayout reMon;
    @BindView(R.id.textVi)
    TextView textVi;
    @BindView(R.id.re_mn)
    RelativeLayout reMn;
    @BindView(R.id.textVi3)
    TextView textVi3;
    @BindView(R.id.re_mo)
    RelativeLayout reMo;
    @BindView(R.id.textVi4)
    TextView textVi4;
    @BindView(R.id.ll_car)
    LinearLayout llCar;
    @BindView(R.id.textVi5)
    TextView textVi5;
    @BindView(R.id.ll_zheng)
    LinearLayout llZheng;
    @BindView(R.id.et_carNumber)
    ContainsEmojiEditText et_carNumber;
    @BindView(R.id.ck_small)
    CheckBox ckSmall;
    @BindView(R.id.ck_middle)
    CheckBox ckMiddle;
    @BindView(R.id.ck_big)
    CheckBox ckBig;
    private int type;
    private int CAMERA_RESULT = 100;
    private int RESULT_LOAD_IMAGE = 200;
    private Bitmap photo;
    private File mPhotoFile;
    private String saveDir = Environment.getExternalStorageDirectory()
            .getPath() + "/temp_image";

    String frontImagePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, CAMERA_JAVA_REQUEST_CODE);
        }
    }

    @OnClick({R.id.ck_small, R.id.ck_middle, R.id.ck_big, R.id.iv_back, R.id.et_carNumber, R.id.iv_car, R.id.iv_zheng, R.id.iv_number, R.id.ll_car, R.id.ll_zheng,})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;

            case R.id.ck_small:
                ckSmall.setChecked(true);
                ckMiddle.setChecked(false);
                ckBig.setChecked(false);
                break;
            case R.id.ck_middle:
                ckSmall.setChecked(false);
                ckMiddle.setChecked(true);
                ckBig.setChecked(false);
                break;

            case R.id.ck_big:
                ckSmall.setChecked(false);
                ckMiddle.setChecked(false);
                ckBig.setChecked(true);
                break;

            case R.id.et_carNumber:
                et_carNumber.setHint("");
                et_carNumber.setCursorVisible(true);
                break;
            case R.id.iv_car:
                showDialog();
                 type=1;
                break;
            case R.id.iv_zheng:
                showDialog();
                type=2;
                break;
            case R.id.iv_number:
                showDialog();
                type=3;
                break;
        }
    }

    Dialog dialog;

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
                dialog.dismiss();
                destoryImage();
                String state = Environment.getExternalStorageState();
                if (state.equals(Environment.MEDIA_MOUNTED)) {
                    mPhotoFile = new File(saveDir, "temp.jpg");
                    mPhotoFile.delete();
                    if (!mPhotoFile.exists()) {
                        try {
                            mPhotoFile.createNewFile();
                        } catch (IOException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplication(), "照片创建失败!",
                                    Toast.LENGTH_LONG).show();
                            return;
                        }
                    }
                    Intent intent = new Intent(
                            "android.media.action.IMAGE_CAPTURE");
                    intent.putExtra(MediaStore.EXTRA_OUTPUT,
                            Uri.fromFile(mPhotoFile));
                    startActivityForResult(intent, CAMERA_RESULT);
                } else {
                    Toast.makeText(getApplication(), "sdcard无效或没有插入!",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
        File savePath = new File(saveDir);
        if (!savePath.exists()) {
            savePath.mkdirs();
        }
        // 从相册
        album.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, RESULT_LOAD_IMAGE);
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

    private void destoryImage() {
        if (photo != null) {
            photo.recycle();
            photo = null;
        }
    }
    //判断是否有内存卡
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
        if (requestCode == CAMERA_RESULT && resultCode == RESULT_OK) {
            if (mPhotoFile != null && mPhotoFile.exists()) {
                BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
                bitmapOptions.inSampleSize = 8;
                //int degree = readPictureDegree(mPhotoFile.getAbsolutePath());
                Bitmap bitmap = BitmapFactory.decodeFile(mPhotoFile.getPath(),
                        bitmapOptions);
                // bitmap = rotaingImageView(degree, bitmap);
               if(type==1){
                   iv_car.setImageBitmap(bitmap);
                   iv_car.setScaleType(ImageView.ScaleType.CENTER_CROP);
                   //frontImagePath=mPhotoFile.getPath();
               }else if(type==2){
                   iv_zheng.setImageBitmap(bitmap);
                   iv_zheng.setScaleType(ImageView.ScaleType.CENTER_CROP);
               }else if(type==3){
                   iv_number.setImageBitmap(bitmap);
                   iv_number.setScaleType(ImageView.ScaleType.CENTER_CROP);
               }
            }
        }
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK
                && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            if(type==1){
                iv_car.setImageBitmap(BitmapFactory
                        .decodeFile(picturePath));
                iv_car.setScaleType(ImageView.ScaleType.CENTER_CROP);
                //frontImagePath=getRealFilePath(this,selectedImage);
            }else if(type==2){
                iv_zheng.setImageBitmap(BitmapFactory
                        .decodeFile(picturePath));
                iv_zheng.setScaleType(ImageView.ScaleType.CENTER_CROP);
            }else if(type==3){
                iv_number.setImageBitmap(BitmapFactory
                        .decodeFile(picturePath));
                iv_number.setScaleType(ImageView.ScaleType.CENTER_CROP);
            }
        }
    }

    private static Bitmap rotaingImageView(int degree, Bitmap bitmap) {
        // 旋转图片 动作
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        System.out.println("angle2=" + angle);
        // 创建新的图片
        Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0,
                bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        return resizedBitmap;

    }

    private static int readPictureDegree(String path) {
        int degree = 0;
        try {
            ExifInterface exifInterface = new ExifInterface(path);
            int orientation = exifInterface.getAttributeInt(
                    ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;

    }

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
        destoryImage();
    }
}
