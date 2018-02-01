package com.all.app.ui.activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
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
import android.widget.Toast;

import com.all.app.R;
import com.all.app.base.AdapterBase;
import com.all.app.base.BaseActivity;
import com.all.app.ui.adapter.PhotoAdpter;
import com.all.app.utils.pullrefresh.PullToRefreshListView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//完工照片页面
public class PhotoActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.re_title)
    RelativeLayout reTitle;
    @BindView(R.id.lv_content)
    PullToRefreshListView lvContent;
    private int type;
    private int CAMERA_RESULT = 100;
    private int RESULT_LOAD_IMAGE = 200;
    private Bitmap photo;
    private File mPhotoFile;
    private String saveDir = Environment.getExternalStorageDirectory()
            .getPath() + "/temp_image";
    //模拟的数据
    List<String> mDatas = new ArrayList<String>(Arrays.asList("Hello",
            "World", "Welcome", "Hello","Hello"));
    PhotoAdpter photoAdpter;
    ImageView iv1;
    ImageView iv2;
    ImageView iv3;
    ImageView iv4;
    ImageView iv5;
    int i;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        ButterKnife.bind(this);
        photoAdpter = new PhotoAdpter(this, mDatas);
        photoAdpter.setAdpListener(new AdapterBase.IAdpBaseListener() {

            @Override
            public void onLazyAdpListener(int key, int position, Object data) {

                if (key == PhotoAdpter.CLICK_IA) {
                    type = 1;
                    iv1 = (ImageView) findViewById(R.id.iv1);
                    showDialog();

                } else if (key == PhotoAdpter.CLICK_IB) {
                    type = 2;
                    showDialog();

                } else if (key == PhotoAdpter.CLICK_IC) {
                    type = 3;
                    showDialog();
                } else if (key == PhotoAdpter.CLICK_ID) {
                    type = 4;

                    showDialog();
                } else if (key == PhotoAdpter.CLICK_IE) {
                    type = 5;
                    showDialog();
                }
            }
        });
        lvContent.setAdapter(photoAdpter);

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

    @OnClick({R.id.iv_back, R.id.tv_title, R.id.re_title, R.id.lv_content})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_title:
                break;
            case R.id.re_title:
                break;
            case R.id.lv_content:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
       /* if (requestCode == CAMERA_RESULT && resultCode == RESULT_OK) {
            if (mPhotoFile != null && mPhotoFile.exists()) {
                BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
                bitmapOptions.inSampleSize = 8;
                //int degree = readPictureDegree(mPhotoFile.getAbsolutePath());
                Bitmap bitmap = BitmapFactory.decodeFile(mPhotoFile.getPath(),
                        bitmapOptions);
                // bitmap = rotaingImageView(degree, bitmap);

                if (type == 1) {
                    iv1.setImageBitmap(bitmap);
                    iv1.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    //frontImagePath=mPhotoFile.getPath();
                    iv2 = (ImageView) findViewById(R.id.iv2);
                    iv2.setVisibility(View.VISIBLE);
                } else if (type == 2) {
                    iv2.setImageBitmap(bitmap);
                    iv2.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    iv3 = (ImageView) findViewById(R.id.iv3);
                    iv3.setVisibility(View.VISIBLE);
                } else if (type == 3) {
                    iv3.setImageBitmap(bitmap);
                    iv3.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    iv4 = (ImageView) findViewById(R.id.iv4);
                    iv4.setVisibility(View.VISIBLE);
                } else if (type == 4) {
                    iv4.setImageBitmap(bitmap);
                    iv4.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    iv5 = (ImageView) findViewById(R.id.iv5);
                    iv5.setVisibility(View.VISIBLE);
                } else if (type == 5) {
                    iv5.setImageBitmap(bitmap);
                    iv5.setScaleType(ImageView.ScaleType.CENTER_CROP);
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
            if (type == 1) {
                iv1.setImageBitmap(BitmapFactory
                        .decodeFile(picturePath));
                iv1.setScaleType(ImageView.ScaleType.CENTER_CROP);
                iv2 = (ImageView) findViewById(R.id.iv2);
                iv2.setVisibility(View.VISIBLE);
                //frontImagePath=getRealFilePath(this,selectedImage);
            } else if (type == 2) {
                iv2.setImageBitmap(BitmapFactory
                        .decodeFile(picturePath));
                iv2.setScaleType(ImageView.ScaleType.CENTER_CROP);
                iv3 = (ImageView) findViewById(R.id.iv3);
                iv3.setVisibility(View.VISIBLE);
            } else if (type == 3) {
                iv3.setImageBitmap(BitmapFactory
                        .decodeFile(picturePath));
                iv3.setScaleType(ImageView.ScaleType.CENTER_CROP);
                iv4 = (ImageView) findViewById(R.id.iv4);
                iv4.setVisibility(View.VISIBLE);
            } else if (type == 4) {
                iv4.setImageBitmap(BitmapFactory
                        .decodeFile(picturePath));
                iv4.setScaleType(ImageView.ScaleType.CENTER_CROP);
                iv5 = (ImageView) findViewById(R.id.iv5);
                iv5.setVisibility(View.VISIBLE);
            } else if (type == 5) {
                iv5.setImageBitmap(BitmapFactory
                        .decodeFile(picturePath));
                iv5.setScaleType(ImageView.ScaleType.CENTER_CROP);
            }
        }
*/
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        destoryImage();
    }
}