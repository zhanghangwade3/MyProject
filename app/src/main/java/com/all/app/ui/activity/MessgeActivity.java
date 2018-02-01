package com.all.app.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.all.app.R;
import com.all.app.app.AppData;
import com.all.app.base.BaseActivity;
import com.all.app.bean.MessageBean;
import com.all.app.configs.Urls;
import com.all.app.http.OkHttpParam;
import com.all.app.http.ResponseEntry;
import com.all.app.utils.FJson;
import com.all.app.utils.LeeTools;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MessgeActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.re_title)
    RelativeLayout reTitle;
    @BindView(R.id.iv_pic)
    ImageView ivPic;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_name2)
    TextView tvName2;
    @BindView(R.id.tv_content)
    LinearLayout tvContent;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_iv)
    TextView tvIv;
    @BindView(R.id.tv_content2)
    LinearLayout tvContent2;
    @BindView(R.id.re_content)
    RelativeLayout reContent;
    @BindView(R.id.iv_pic2)
    ImageView ivPic2;
    @BindView(R.id.tv_name26)
    TextView tvName26;
    @BindView(R.id.tv_name21)
    TextView tvName21;
    @BindView(R.id.tv_content24)
    LinearLayout tvContent24;
    @BindView(R.id.tv_time2)
    TextView tvTime2;
    @BindView(R.id.tv_iv2)
    TextView tvIv2;
    @BindView(R.id.tv_content22)
    LinearLayout tvContent22;
    @BindView(R.id.re_content2)
    RelativeLayout reContent2;
    @BindView(R.id.iv_pic3)
    ImageView ivPic3;
    @BindView(R.id.tv_name22)
    TextView tvName22;
    @BindView(R.id.tv_content11)
    LinearLayout tvContent11;
    @BindView(R.id.tv_iv3)
    TextView tvIv3;
    @BindView(R.id.tv_content25)
    LinearLayout tvContent25;
    @BindView(R.id.re_content3)
    RelativeLayout reContent3;
    @BindView(R.id.iv_pic4)
    ImageView ivPic4;
    @BindView(R.id.tv_name4)
    TextView tvName4;
    @BindView(R.id.tv_content4)
    LinearLayout tvContent4;
    @BindView(R.id.tv_iv33)
    TextView tvIv33;
    @BindView(R.id.tv_content23)
    LinearLayout tvContent23;
    @BindView(R.id.re_content4)
    RelativeLayout reContent4;
    MessageBean messageBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messge);
        ButterKnife.bind(this);
        loadNumBer();//消息数量接口
    }

    private void loadNumBer() {
        OkHttpParam param = new OkHttpParam();
        if (TextUtils.isEmpty(AppData.Init().getLoginBean().getUid())) {
            Toast.makeText(MessgeActivity.this, "用户的ID不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        param.add("uid", AppData.Init().getLoginBean().getUid());
        _PostEntry(Urls.mymsgnum, param, Urls.ActionId.mymsgnum, true);
    }

    @OnClick({R.id.iv_back, R.id.re_content4, R.id.re_title, R.id.iv_pic, R.id.tv_name, R.id.tv_name2, R.id.tv_content, R.id.tv_time, R.id.tv_iv, R.id.tv_content2, R.id.re_content, R.id.iv_pic2, R.id.tv_name26, R.id.tv_name21, R.id.tv_content24, R.id.tv_time2, R.id.tv_iv2, R.id.tv_content22, R.id.re_content2, R.id.iv_pic3, R.id.tv_name22, R.id.tv_content11, R.id.tv_iv3, R.id.tv_content25, R.id.re_content3, R.id.iv_pic4, R.id.tv_name4, R.id.tv_content4, R.id.tv_iv33, R.id.tv_content23})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
               startActivity(new Intent(MessgeActivity.this,MainActivity.class));
                break;
            case R.id.tv_title:
                break;
            case R.id.re_title:
                break;
            case R.id.iv_pic:
                break;
            case R.id.tv_name:
                break;
            case R.id.tv_name2:
                break;
            case R.id.tv_content:
                break;
            case R.id.tv_time:
                break;
            case R.id.tv_iv:
                break;
            case R.id.tv_content2:
                break;
            case R.id.re_content:
                startActivity(new Intent(MessgeActivity.this, DealActivity.class));
                break;
            case R.id.iv_pic2:
                break;
            case R.id.tv_name26:
                break;
            case R.id.tv_name21:
                break;
            case R.id.tv_content24:
                break;
            case R.id.tv_time2:
                break;
            case R.id.tv_iv2:
                break;
            case R.id.tv_content22:
                break;
            case R.id.re_content2:
                startActivity(new Intent(MessgeActivity.this, SystemActivity.class));
                break;
            case R.id.iv_pic3:
                break;
            case R.id.tv_name22:
                break;
            case R.id.tv_content11:
                break;
            case R.id.tv_iv3:
                break;
            case R.id.tv_content25:
                break;
            case R.id.re_content3:
                startActivity(new Intent(MessgeActivity.this, AeptMsgActivity.class));
                break;
            case R.id.iv_pic4:
                break;
            case R.id.tv_name4:
                break;
            case R.id.re_content4:
                startActivity(new Intent(MessgeActivity.this, SendActivity.class));
                break;
            case R.id.tv_iv33:
                break;
            case R.id.tv_content23:
                break;
        }
    }

    @Override
    protected void onNetSuccess(int actionId, ResponseEntry result) {
        super.onNetSuccess(actionId, result);
        switch (actionId) {
            case Urls.ActionId.mymsgnum:
                if (result.isSuccess()) {
                    messageBean = FJson.getObject(result.getData().toString(), MessageBean.class);
                    if (messageBean != null) {
                        initView();
                    }
                }

                break;
        }
    }

    private void initView() {
        LeeTools.setText(tvTime,messageBean.getJyedittime());
        if(Integer.parseInt(messageBean.getJynum())>0){
            tvIv.setVisibility(View.VISIBLE);
            LeeTools.setText(tvIv,messageBean.getJynum());
        }
        LeeTools.setText(tvTime2,messageBean.getXtedittime());
        if(Integer.parseInt(messageBean.getXtnum())>0){
            tvIv2.setVisibility(View.VISIBLE);
            LeeTools.setText(tvIv2,messageBean.getJynum());
        }
        if(Integer.parseInt(messageBean.getSjxnum())>0){
            tvIv3.setVisibility(View.VISIBLE);
            LeeTools.setText(tvIv3,messageBean.getJynum());
        }
    }
}
