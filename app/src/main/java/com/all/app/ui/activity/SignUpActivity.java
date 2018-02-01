package com.all.app.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.all.app.R;
import com.all.app.app.AppData;
import com.all.app.base.BaseActivity;
import com.all.app.bean.AddressBean;
import com.all.app.bean.CityBean;
import com.all.app.bean.DataBean;
import com.all.app.configs.Urls;
import com.all.app.http.OkHttpParam;
import com.all.app.http.ResponseEntry;
import com.all.app.utils.FJson;
import com.all.app.utils.StringUtil;
import com.all.app.utils.StringUtils;
import com.all.app.utils.Timer;
import com.all.app.utils.Utils;
import com.all.app.utils.city.CityChoose2;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;


import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignUpActivity extends BaseActivity implements AMapLocationListener {

    @BindView(R.id.et_shouji)
    EditText etShouji;
    @BindView(R.id.et_yan)
    EditText etYan;
    @BindView(R.id.bt_yan)
    Button btYan;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.imagec)
    ImageView imagec;
    @BindView(R.id.iv_back)
    ImageView iv_back;
    @BindView(R.id.tv_xieyi)
    TextView tv_xieyi;
    @BindView(R.id.rl_username_del)
    RelativeLayout rlUsernameDel;
    @BindView(R.id.re_address)
    RelativeLayout re_address;


    @BindView(R.id.et_address)
    TextView etAddress;
    @BindView(R.id.et_sheng)
    TextView et_sheng;

    @BindView(R.id.iv_address)
    ImageView ivAddress;
    @BindView(R.id.bt_sign)
    Button btSign;
    @BindView(R.id.ll_content)
    LinearLayout llContent;
    String uphone = "";
    String ucode = "";
    String upwd = "";
    String provincestr = "";
    String city = "";
    String citystr = "";
    String utypes = "1";//(1安装队2家具公司)
    String source = "3";//(网站1 ios=2 3 Android=3)
    String single = "1";
    int type;
    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    //声明定位回调监听器
    //声明定位回调监听器
    public AMapLocationListener mLocationListener = null;
    //声明AMapLocationClientOption对象
    public AMapLocationClientOption mLocationOption = null;
    List<AddressBean> addressBean;
    private static final int BAIDU_READ_PHONE_STATE =100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);
        findAddress();//获取数据的接口
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (Build.VERSION.SDK_INT>=23){
            showContacts();
        }else{
            initLocation();  //初始化定位
        }



    }
    //Android6.0申请权限的回调方法
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            // requestCode即所声明的权限获取码，在checkSelfPermission时传入
            case BAIDU_READ_PHONE_STATE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // 获取到权限，作相应处理（调用定位SDK应当确保相关权限均被授权，否则可能引起定位失败）
                    initLocation();  //初始化定位
                } else {
                    // 没有获取到权限，做特殊处理
                    Toast.makeText(getApplicationContext(), "获取位置权限失败，请手动开启", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }

    private void showContacts() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(getApplicationContext(),"没有权限,请手动开启定位权限",Toast.LENGTH_SHORT).show();
            // 申请一个（或多个）权限，并提供用于回调返回的获取码（用户定义）
            ActivityCompat.requestPermissions(SignUpActivity.this,new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.READ_PHONE_STATE}, BAIDU_READ_PHONE_STATE);
        }else{
            initLocation();  //初始化定位
        }

    }

    private void findAddress() {
        OkHttpParam param = new OkHttpParam();
        _PostEntry(Urls.address, param, Urls.ActionId.address, true);
    }

    private void initLocation() {
        mLocationClient = new AMapLocationClient(this); //初始化定位
        mLocationClient.setLocationListener(this); //设置定位回调监听
        mLocationOption = new AMapLocationClientOption();  //初始化AMapLocationClientOption对象
        //设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式。
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置定位模式为AMapLocationMode.Battery_Saving，低功耗模式。
        //mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Battery_Saving);
        mLocationOption.setOnceLocation(true);//获取一次定位结果,该方法默认为false。
        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);
        //设置是否强制刷新WIFI，默认为true，强制刷新。
        mLocationOption.setWifiActiveScan(false);
        //设置是否强制刷新WIFI，默认为true，强制刷新。
        mLocationOption.setWifiActiveScan(false);
        //单位是毫秒，默认30000毫秒，建议超时时间不要低于8000毫秒。
        mLocationOption.setHttpTimeOut(20000);
        //设置定位模式为AMapLocationMode.Device_Sensors，仅设备模式。
       // mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Device_Sensors);
        //关闭缓存机制
        mLocationOption.setLocationCacheEnable(false);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位
        mLocationClient.startLocation();
        //mLocationClient.stopLocation();//停止定位后，本地定位服务并不会被销毁
    }

    @OnClick({R.id.re_address, R.id.tv_xieyi, R.id.iv_back, R.id.et_shouji, R.id.et_yan, R.id.bt_yan, R.id.et_password, R.id.imagec, R.id.rl_username_del, R.id.et_address, R.id.iv_address, R.id.bt_sign, R.id.ll_content})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.re_address:
                break;
            case R.id.et_shouji:
                break;
            case R.id.tv_xieyi:
                Intent intent = new Intent(SignUpActivity.this, WebViewActivity.class);
                intent.putExtra("single", single);
                startActivity(intent);
                break;
            case R.id.et_yan:
                break;
            case R.id.bt_yan:
                getVerifCode();
                break;
            case R.id.et_password:
                break;
            case R.id.imagec:
                break;
            case R.id.rl_username_del:
                break;
            case R.id.et_address:
                break;
            case R.id.iv_address:
                break;
            case R.id.bt_sign:
                confirm();
                break;
            case R.id.ll_content:
                break;
        }
    }

    //注册
    private void confirm() {
        uphone = etShouji.getText().toString().trim();
        ucode = etYan.getText().toString().trim();
        upwd = etPassword.getText().toString().trim();
        OkHttpParam param = new OkHttpParam();
        if (StringUtil.isEmpty(uphone)) {
            Toast.makeText(this, "请输入手机号码", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!StringUtils.isPhone(uphone)) {
            Toast.makeText(this, "请输入正确的手机号码", Toast.LENGTH_SHORT).show();
        }
        if (StringUtil.isEmpty(ucode)) {
            Toast.makeText(this, "请输入验证码", Toast.LENGTH_SHORT).show();
            return;
        }
        if (StringUtil.isEmpty(upwd)) {
            Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(provincestr)) {
            Toast.makeText(this, "正在重新定位", Toast.LENGTH_SHORT).show();
            mLocationClient.startLocation();
            return;
        }
        if (StringUtil.isEmpty(citystr)) {
            Toast.makeText(this, "正在重新定位", Toast.LENGTH_SHORT).show();
            return;
        }
        param.add("uphone", uphone);
        param.add("ucode", ucode);
        param.add("upwd", upwd);
        param.add("utypes", utypes);
        param.add("source", source);
        param.add("citystr", citystr);
        param.add("provincestr",provincestr);
        _PostEntry(Urls.register, param, Urls.ActionId.register, true);
    }

    //获取验证码的方法
    private void getVerifCode() {
        uphone = etShouji.getText().toString().trim();
        OkHttpParam param = new OkHttpParam();
        if (StringUtil.isEmpty(uphone)) {
            Toast.makeText(this, "请输入电话号码", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!StringUtils.isPhone(uphone)) {
            Toast.makeText(this, "请输入正确的电话号码", Toast.LENGTH_SHORT).show();
            return;
        }

        param.add("uphone", uphone);
        _PostEntry(Urls.sendregistermsg, param, Urls.ActionId.sendregistermsg, true);
    }

    //发送验证码
    boolean isSendSms = false;
    Timer.OnTimeFinishListener listener = new Timer.OnTimeFinishListener() {

        @Override
        public void onTimeFinished() {
            // 倒计时结束后 手机号码设置可以更改
            etShouji.setEnabled(true);
        }
    };

    @Override
    protected void onNetSuccess(int actionId, ResponseEntry result) {
        super.onNetSuccess(actionId, result);
        switch (actionId) {
            case Urls.ActionId.sendregistermsg:
                sendSmsFinish(result);
                break;
            case Urls.ActionId.register:
                if (result.isSuccess()) {
                    startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
                }
                break;
            case Urls.ActionId.address:
                if (result.isSuccess()) {
                    System.out.println("1111111111111111111111111111" + result);
                }
        }
    }
    //发送验证码结束
    private void sendSmsFinish(ResponseEntry result) {
        isSendSms = true;
        if (result.isSuccess()) {
            Log.e(getClass().getSimpleName() + "TAG", "request successful!");
            showTips(result.getMsg(), null);
            // 倒计时结束前 手机号码设置不可更改
            etShouji.setEnabled(false);
            // 开启倒计时
            Timer timer = new Timer(60000, 1000, this, btYan);
            timer.setOnTimeFinishListener(listener);
            timer.start();
        } else {
            Log.e(getClass().getSimpleName() + "TAG", "request failure!");
            showTips(result.getMsg(), null);
        }

    }

    @Override
    public void onLocationChanged(AMapLocation amapLocation) {

        if (amapLocation != null) {
            if (amapLocation.getErrorCode() == 0) {
                //可在其中解析amapLocation获取相应内容。
                amapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
                amapLocation.getLatitude();//获取纬度
                amapLocation.getLongitude();//获取经度
                amapLocation.getAccuracy();//获取精度信息
                amapLocation.getAddress();//地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
                amapLocation.getCountry();//国家信息
                provincestr= amapLocation.getProvince();//省信息
                citystr=amapLocation.getCity();//城市信息
                amapLocation.getDistrict();//城区信息
                etAddress.setText(amapLocation.getCity());
                String address = etAddress.getText().toString();
                AppData.Init().saveCityStr(citystr);
                System.out.println("4444444444444444444444444444444444444"+citystr);

            } else {
                //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                Log.e("AmapError", "location Error, ErrCode:"
                        + amapLocation.getErrorCode() + ", errInfo:"
                        + amapLocation.getErrorInfo());
            }
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLocationClient.onDestroy();//销毁定位客户端，同时销毁本地定位服务。
    }
}