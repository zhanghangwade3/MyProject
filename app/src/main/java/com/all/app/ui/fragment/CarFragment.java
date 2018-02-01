package com.all.app.ui.fragment;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.all.app.R;
import com.all.app.base.BaseFragment;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static android.app.Activity.RESULT_OK;

public class CarFragment extends BaseFragment {
    private static final int CAMERA_JAVA_REQUEST_CODE = 3;
    private int type;
    private int CAMERA_RESULT = 100;
    private int RESULT_LOAD_IMAGE = 200;
    private Bitmap photo;
    private File mPhotoFile;
    private String saveDir = Environment.getExternalStorageDirectory()
            .getPath() + "/temp_image";

    //模拟的数据
    List<String> mDatas = new ArrayList<String>(Arrays.asList("Hello",
            "World", "Welcome", "Hello",
            "World", "Welcome", "World", "Welcome", "Hello", "World", "Welcome", "Hello"));
    @BindView(R.id.iv_zhengp)
    ImageView ivZhengp;
    @BindView(R.id.tv_zheng)
    TextView tvZheng;
    @BindView(R.id.iv_car)
    ImageView ivCar;
    @BindView(R.id.textVi4)
    TextView textVi4;
    @BindView(R.id.iv_number)
    ImageView ivNumber;
    @BindView(R.id.tv_numer)
    TextView tvNumer;
    @BindView(R.id.text_submit)
    TextView textSubmit;
    Unbinder unbinder;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void Init() {

    }

    @Override
    public void onResume() {
        super.onResume();
      /*  teamAdpter = new TeamAdpter(getActivity(), mDatas);
        lvContent.setAdapter(teamAdpter);*/
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, CAMERA_JAVA_REQUEST_CODE);
        }
    }

    @Override
    protected int InitLayer() {
        return R.layout.fragment_car;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);

        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();

    }

    @OnClick({R.id.iv_zhengp, R.id.tv_zheng, R.id.iv_car, R.id.textVi4, R.id.iv_number, R.id.tv_numer, R.id.text_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_zhengp:
                showDialog();
                type=1;
                break;
            case R.id.tv_zheng:
                break;
            case R.id.iv_car:
                showDialog();
                type=2;
                break;
            case R.id.textVi4:
                break;
            case R.id.iv_number:
                showDialog();
                type=3;
                break;
            case R.id.tv_numer:
                break;
            case R.id.text_submit:
                break;
        }
    }
    Dialog dialog;
    private void showDialog() {
        dialog = new Dialog(getActivity(), R.style.Dialog_image);
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
                            Toast.makeText(getActivity(), "照片创建失败!",
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
                    Toast.makeText(getActivity(), "sdcard无效或没有插入!",
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
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
                    ivZhengp.setImageBitmap(bitmap);
                    ivZhengp.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    //frontImagePath=mPhotoFile.getPath();
                }else if(type==2){
                    ivCar.setImageBitmap(bitmap);
                    ivCar.setScaleType(ImageView.ScaleType.CENTER_CROP);
                }else if(type==3){
                    ivNumber.setImageBitmap(bitmap);
                    ivNumber.setScaleType(ImageView.ScaleType.CENTER_CROP);
                }
            }
        }
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK
                && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor cursor =getActivity().getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            if(type==1){
                ivZhengp.setImageBitmap(BitmapFactory
                        .decodeFile(picturePath));
                ivZhengp.setScaleType(ImageView.ScaleType.CENTER_CROP);
                //frontImagePath=getRealFilePath(this,selectedImage);
            }else if(type==2){
                ivCar.setImageBitmap(BitmapFactory
                        .decodeFile(picturePath));
                ivCar.setScaleType(ImageView.ScaleType.CENTER_CROP);
            }else if(type==3){
                ivNumber.setImageBitmap(BitmapFactory
                        .decodeFile(picturePath));
                ivNumber.setScaleType(ImageView.ScaleType.CENTER_CROP);
            }
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
     destoryImage();
    }
}



