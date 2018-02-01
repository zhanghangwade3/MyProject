package com.all.app.ui.activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.all.app.R;
import com.all.app.app.AppData;
import com.all.app.app.AppManager;
import com.all.app.base.BaseActivity;
import com.all.app.bean.LoginBean;
import com.all.app.configs.Urls;
import com.all.app.http.OkHttpParam;
import com.all.app.http.ResponseEntry;
import com.all.app.utils.AlertDialog;
import com.all.app.utils.CircularImage;
import com.all.app.utils.FJson;
import com.all.app.utils.StringUtils;

import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

public class LoginActivity extends BaseActivity {


    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.imagec)
    ImageView imagec;
    @BindView(R.id.rl_username_del)
    RelativeLayout rlUsernameDel;
    @BindView(R.id.et_pwd)
    EditText etPwd;
    @BindView(R.id.imagec2)
    ImageView imagec2;
    @BindView(R.id.rl_password_del)
    RelativeLayout rlPasswordDel;
    @BindView(R.id.tv_find)
    TextView tvFind;
    @BindView(R.id.tv_number)
    TextView tvNumber;
    @BindView(R.id.find_pwd)
    LinearLayout findPwd;
    @BindView(R.id.bt_login)
    Button btLogin;
    @BindView(R.id.ll_content)
    LinearLayout llContent;
    @BindView(R.id.iv_tou)
    CircularImage ivTou;
    String uname="";
    String upwd="";
    String source="3";//2.表示安卓注册，3.表示ios注册
    String utypes="1";//1安装对，2安装公司
    LoginBean loginBean;
    String realname_status="";//认证的状态，1.已认证，2，未认证
    SharedPreferences sp;
    boolean mIsLogOut = false;
    String type = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    /*    sp=getSharedPreferences("config",MODE_PRIVATE);
        sp.edit().putBoolean("is_user_login_showed", true).commit();
        //跳转到主页
        startActivity(new Intent(LoginActivity.this, RenActivity.class));
        finish();*/
        getIntentData();

    }

    private void getIntentData() {
        mIsLogOut = getIntent().getBooleanExtra("isLogout", false);
        type = getIntent().getStringExtra("type");
        if (mIsLogOut) {
          /*  String account = AppData.Init().getAccount();
            et_phone.setText(account);*/
        }

    }


    @OnClick({R.id.et_name, R.id.imagec, R.id.rl_username_del, R.id.et_pwd, R.id.imagec2, R.id.rl_password_del, R.id.tv_find, R.id.tv_number, R.id.find_pwd, R.id.bt_login, R.id.ll_content, R.id.iv_tou})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.et_name:
                break;
            case R.id.imagec:
                break;
            case R.id.rl_username_del:
                break;
            case R.id.et_pwd:
                break;
            case R.id.imagec2:
                break;
            case R.id.rl_password_del:
                break;
            case R.id.tv_find:
                startActivity(new Intent(LoginActivity.this,BackActivity.class));
                break;
            case R.id.tv_number:
                startActivity(new Intent(LoginActivity.this,SignUpActivity.class));
                break;
            case R.id.find_pwd:
                break;
            case R.id.bt_login:
                Login();//登录的方法
                setAlias(); //设置别名
                break;
            case R.id.ll_content:
                break;
            case R.id.iv_tou:
                break;
        }
    }
    //设置别名
    private void setAlias() {
        uname=etName.getText().toString().trim();
        if (TextUtils.isEmpty(uname)) {
            Toast.makeText(LoginActivity.this, "你好", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!ExampleUtil.isValidTagAndAlias(uname)) {
            Toast.makeText(LoginActivity.this, "你好", Toast.LENGTH_SHORT).show();
            return;
        }
        //调用JPush API设置Alias
        mHandler.sendMessage(mHandler.obtainMessage(MSG_SET_ALIAS, uname));
    }

    private void Login() {
        uname=etName.getText().toString().trim();
        upwd=etPwd.getText().toString().trim();
        if (TextUtils.isEmpty(uname)) {
            Toast.makeText(this, "请输入手机号码", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!StringUtils.isPhone(uname)) {
            Toast.makeText(this, "请输入正确的手机号码", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty( uname) || uname.length() < 6) {
            Toast.makeText(this, "请您输入6~16位密码", Toast.LENGTH_SHORT).show();
            return;
        }
        OkHttpParam param = new OkHttpParam();
        param.add("uname",uname);
        param.add("upwd", upwd);
        param.add("source",source);
        param.add("utypes",utypes);
        _PostEntry(Urls.login, param, Urls.ActionId.login, true);
    }



     @Override
    protected void onNetSuccess(int actionId, ResponseEntry result) {
        super.onNetSuccess(actionId, result);
        switch (actionId) {
            case Urls.ActionId.login:
                System.out.println("88888888888888888888888888"+result.getCode());
              if (result.isSuccess()) {
                  loginBean = FJson.getObject(result.getData().toString(), LoginBean.class);
                  jumActivity();
                  AppData.Init().saveLoginBean(loginBean);
              }else {
                  showTips(result.getMsg(), null);
              }
                break;



        }
        }
    private void jumActivity() {
        realname_status=loginBean.getRealname_status();
        if("1".equals(realname_status)){
            startActivity(new Intent(LoginActivity.this,MainActivity.class));
        }else if("2".equals(realname_status)||TextUtils.isEmpty(realname_status)){
            startActivity(new Intent(LoginActivity.this,RenActivity.class));
        }else if("0".equals(realname_status)){
            startActivity(new Intent(LoginActivity.this,MainActivity.class));
        }
    }

    private static final int MSG_SET_ALIAS = 1001;
    private static final int MSG_SET_TAGS = 1002;

        private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(android.os.Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_SET_ALIAS:
                    JPushInterface.setAliasAndTags(getApplicationContext(), (String) msg.obj, null, mAliasCallback);
                    break;

             /*   case MSG_SET_TAGS:
                    //Log.d(TAG, "Set tags in handler.");
                    JPushInterface.setAliasAndTags(getApplicationContext(), null, (Set<String>) msg.obj, mTagsCallback);
                    break;*/

                default:
            }
        }
    };

    private final TagAliasCallback mAliasCallback = new TagAliasCallback() {

        @Override
        public void gotResult(int code, String alias, Set<String> tags) {
            String logs;
            switch (code) {
                case 0:
                    logs = "Set tag and alias success";
                    break;

                case 6002:
                    logs = "Failed to set alias and tags due to timeout. Try again after 60s.";
                    if (ExampleUtil.isConnected(getApplicationContext())) {
                        mHandler.sendMessageDelayed(mHandler.obtainMessage(MSG_SET_ALIAS, alias), 1000 * 60);
                    } else {
                    }
                    break;

                default:
                    logs = "Failed with errorCode = " + code;
            }

           // ExampleUtil.showToast(logs, getApplicationContext());
        }

    };
 /*   public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            // do something...
            if (mIsLogOut) {
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            }
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }*/


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK && event.getAction()==KeyEvent.ACTION_DOWN){
            AlertDialog dialog =new AlertDialog(this);
            dialog.builder();
            dialog.setTitle("退出确认");
            dialog.setMsg("真的要退出万装吗？");
            dialog.setPositiveButton("确定", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AppManager.Manager().AppExit(LoginActivity.this);
                    //android.os.Process.killProcess(android.os.Process.myPid());
                }
            });
            dialog.setNegativeButton("取消", new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            dialog.show();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }



    }
