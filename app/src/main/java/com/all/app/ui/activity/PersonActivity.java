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
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.all.app.R;
import com.all.app.app.AppData;
import com.all.app.base.BaseActivity;
import com.all.app.bean.PersonBean;
import com.all.app.configs.Urls;
import com.all.app.http.OkHttpParam;
import com.all.app.http.ResponseEntry;
import com.all.app.utils.BirthdayChoose;
import com.all.app.utils.CircularImage;
import com.all.app.utils.ContainsEmojiEditText;
import com.all.app.utils.FJson;
import com.all.app.utils.LeeTools;
import com.all.app.utils.XGlide;
import com.all.app.utils.city.CityChoose;


import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PersonActivity extends BaseActivity {
    private static final int CAMERA_JAVA_REQUEST_CODE = 5;

    String mImgPath = "";
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
    @BindView(R.id.iv_tou)
    CircularImage ivTou;
    @BindView(R.id.re_money)
    RelativeLayout reMoney;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.et_peo)
    ContainsEmojiEditText etPeo;
    @BindView(R.id.et_xiang)
    ContainsEmojiEditText et_xiang;
    @BindView(R.id.re_name)
    RelativeLayout reName;
    @BindView(R.id.person)
    TextView person;
    @BindView(R.id.ck_man)
    CheckBox ckMan;
    @BindView(R.id.ck_woman)
    CheckBox ckWoman;
    @BindView(R.id.re_sale)
    RelativeLayout reSale;
    @BindView(R.id.tv_number)
    TextView tvNumber;
    @BindView(R.id.tv_birthday)
    TextView tv_birthday;
    @BindView(R.id.re_renzheng)
    RelativeLayout reRenzheng;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.tv_addres)
    TextView tvAddres;
    @BindView(R.id.iv_right6)
    ImageView ivRight6;
    @BindView(R.id.re_address)
    RelativeLayout reAddress;
    @BindView(R.id.tv_xiang)
    TextView tvXiang;
    @BindView(R.id.et_phone)
    ContainsEmojiEditText et_phone;
    @BindView(R.id.re_xiang)
    RelativeLayout reXiang;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.re_phone)
    RelativeLayout rePhone;
    @BindView(R.id.iv_right9)
    ImageView iv_right9;
    String sex = "";
    String address = "";
    String phone = "";
    String birthday = "";// 生日
    String province = "";//省份ID
    String city = "";//市ID
    String area = "";//区ID
    PersonBean personBean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);
        ButterKnife.bind(this);
        loadPersondata();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, CAMERA_JAVA_REQUEST_CODE);
        }
        initCheckBox();
    }

    private void loadPersondata() {
        OkHttpParam param = new OkHttpParam();
        if (TextUtils.isEmpty(AppData.Init().getLoginBean().getUid())) {
            Toast.makeText(this, "用户id不能为空", Toast.LENGTH_SHORT).show();
        }
        param.add("uid", AppData.Init().getLoginBean().getUid());
        _PostEntry(Urls.getuserinfo, param, Urls.ActionId.getuserinfo, true);
    }

    private void initCheckBox() {
        ckMan.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (ckMan.isChecked()) {
                    ckWoman.setChecked(false);
                    sex = "男";
                }
            }
        });
        ckWoman.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (ckWoman.isChecked()) {
                    ckMan.setChecked(false);
                    sex = "女";
                }
            }
        });

    }

    @OnClick({R.id.et_xiang, R.id.iv_right6, R.id.re_renzheng, R.id.iv_right9, R.id.et_phone, R.id.ck_man, R.id.ck_woman, R.id.re_sale, R.id.iv_tou, R.id.re_address, R.id.tv_birthday, R.id.et_peo, R.id.iv_back, R.id.tv_baocun, R.id.re_title, R.id.re_money, R.id.tv_name})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.et_xiang:
                et_xiang.setHint("");
                et_xiang.setCursorVisible(true);
                break;
            case R.id.iv_right6:
                CityChoose cityChoose2 = new CityChoose(this);
                cityChoose2.showDialog();
                cityChoose2.setChooseCityListener(new CityChoose.IChooseCityListener() {
                    @Override
                    public void onFinished(String provinceId, String provinceName,
                                           String cityId, String cityName, String areaId,
                                           String areaName) {
                        if (provinceName.equals(cityName)) {
                            tvAddres.setText(provinceName + areaName);

                        } else {
                            tvAddres.setText(provinceName + cityName + areaName);

                        }
                        province = provinceId;
                        city = cityId;
                        area = areaId;
                    }
                });
                break;
            case R.id.iv_right9:
                BirthdayChoose choose = new BirthdayChoose(this);
                choose.setTimeSelectedListener(new BirthdayChoose.TimeSelectedListener() {
                    @Override
                    public void onTimeSelected(String Y, String M, String D) {
                        String m = M.length() == 1 ? "0" + M : M;
                        String d = D.length() == 1 ? "0" + D : D;
                        birthday = Y + "年" + m + "月" + d + "日";
                        tv_birthday.setText(birthday);
                    }
                });
                choose.showDialog();
                break;
            case R.id.et_phone:
                et_phone.setHint("");
                et_phone.setCursorVisible(true);
                break;
            case R.id.ck_man:
                ckMan.setChecked(true);
                ckWoman.setChecked(false);
                break;
            case R.id.ck_woman:
                ckMan.setChecked(false);
                ckWoman.setChecked(true);
                break;
            case R.id.re_sale:
                break;
            case R.id.iv_tou://上传头像
                //showDialog();
                break;
            case R.id.et_peo:
                etPeo.setHint("");
                etPeo.setCursorVisible(true);
                break;
            case R.id.tv_birthday:
                BirthdayChoose choose2 = new BirthdayChoose(this);
                choose2.setTimeSelectedListener(new BirthdayChoose.TimeSelectedListener() {
                    @Override
                    public void onTimeSelected(String Y, String M, String D) {
                        String m = M.length() == 1 ? "0" + M : M;
                        String d = D.length() == 1 ? "0" + D : D;
                        birthday = Y + "年" + m + "月" + d + "日";
                        tv_birthday.setText(birthday);
                    }
                });
                choose2.showDialog();
                break;
            case R.id.re_address://选择省市区
                CityChoose cityChoose = new CityChoose(this);
                cityChoose.showDialog();
                cityChoose.setChooseCityListener(new CityChoose.IChooseCityListener() {

                    @Override
                    public void onFinished(String provinceId, String provinceName,
                                           String cityId, String cityName, String areaId,
                                           String areaName) {
                        if (provinceName.equals(cityName)) {
                            tvAddres.setText(provinceName + areaName);

                        } else {
                            tvAddres.setText(provinceName + cityName + areaName);
                        }
                        province = provinceId;
                        city = cityId;
                        area = areaId;
                    }
                });
                break;
            case R.id.tv_baocun:
                submitData();
                break;
            case R.id.re_title:
                break;
            case R.id.re_money:
                break;
            case R.id.tv_name:
                break;
        }
    }
    private void submitData() {
        address = et_xiang.getText().toString();
        phone = et_phone.getText().toString();
        OkHttpParam param = new OkHttpParam();
        if (TextUtils.isEmpty(AppData.Init().getLoginBean().getUid())) {
            Toast.makeText(this, "用户id不能为空", Toast.LENGTH_SHORT).show();
        }
        if (TextUtils.isEmpty(sex)) {
            Toast.makeText(this, "请选择用户性别", Toast.LENGTH_SHORT).show();
        }
        if (TextUtils.isEmpty(birthday)) {
            Toast.makeText(this, "请选择出生日期", Toast.LENGTH_SHORT).show();
        }
        if (TextUtils.isEmpty(province)) {
            Toast.makeText(this, "省份id不能为空", Toast.LENGTH_SHORT).show();
        }
        if (TextUtils.isEmpty(city)) {
            Toast.makeText(this, "城市id不能为空", Toast.LENGTH_SHORT).show();
        }
        if (TextUtils.isEmpty(area)) {
            Toast.makeText(this, "区id不能为空", Toast.LENGTH_SHORT).show();
        }
        if (TextUtils.isEmpty(address)) {
            Toast.makeText(this, "请输入详细地址", Toast.LENGTH_SHORT).show();
        }
        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "请输入备用电话", Toast.LENGTH_SHORT).show();
        }
        param.add("uid", AppData.Init().getLoginBean().getUid());
        param.add("sex", sex);
        param.add("birthday", birthday);
        param.add("province", province);
        param.add("city", city);
        param.add("area", area);
        param.add("address", address);
        param.add("phone", phone);
        _PostEntry(Urls.saveuserinfo, param, Urls.ActionId.saveuserinfo, true);

    }

    Dialog dialog;
    /* 头像文件 */
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

    //判断是否有内存卡
    private boolean hasSdcard() {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }

    //照片获取的回调
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
                    mImgPath = getRealFilePath(PersonActivity.this, data.getData());
                    //XGlide.init(this).display(ci_touxiang, outUri);
                    //mImgPath = getRealFilePath(ChangeUserInfoActivity.this, outUri);
                    break;
                case 1001: //选择地址管理回调
                    if (null == data) {
                        return;
                    }
                    break;
            }
        }


    }

    private void setPicToView(Intent data) {

        Uri uri = data.getData();

        if (uri == null) {
            return;
        }
        ivTou.setImageURI(uri);

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

    @Override
    protected void onNetSuccess(int actionId, ResponseEntry result) {
        super.onNetSuccess(actionId, result);
        switch (actionId) {
            case Urls.ActionId.saveuserinfo:
                if (result.isSuccess()) {
                    Toast.makeText(this, "修改用户信息成功", Toast.LENGTH_SHORT).show();
                }
                break;
            case Urls.ActionId.getuserinfo :
                if (result.isSuccess()) {
                    System.out.println("999999999999999999999999999999999999999999"+result);
                    personBean= FJson.getObject(result.getData().toString(),PersonBean.class);
                    if(personBean!=null){
                        initView();
                    }
                }
                break;
        }
    }

    private void initView() {
        LeeTools.setText(etPeo,personBean.getTruename());
        if("男".equals(personBean.getSex())){
            ckMan.setChecked(true);
            ckWoman.setChecked(false);
        }else{
            ckWoman.setChecked(true);
            ckMan.setChecked(false);
        }
        LeeTools.setText(tv_birthday,personBean.getBirthday());
        LeeTools.setText(tvAddres,personBean.getProvincename()+personBean.getCityname()+personBean.getAreaname());
        LeeTools.setText(et_xiang,personBean.getAddress());
        LeeTools.setText(et_phone,personBean.getPhone());
        if(!TextUtils.isEmpty(personBean.getUser_pic())){
            XGlide.init(this).display(ivTou,personBean.getUser_pic());
        }else {
            XGlide.init(this).display(ivTou,R.drawable.xiaotu);
        }

    }
}
