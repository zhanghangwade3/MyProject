package com.all.app.ui.activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.all.app.R;
import com.all.app.base.BaseActivity;
import com.all.app.configs.Urls;
import com.all.app.http.OkHttpParam;
import com.all.app.http.ResponseEntry;
import com.all.app.utils.ContainsEmojiEditText;
import com.all.app.utils.StringUtil;
import com.all.app.utils.StringUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AdviceActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.re_title)
    RelativeLayout reTitle;
    @BindView(R.id.tv_content)
    ContainsEmojiEditText tvContent;
    @BindView(R.id.tv_contact)
    ContainsEmojiEditText tvContact;
    @BindView(R.id.bt_submit)
    Button btSubmit;
    String pro_title="安卓意见反馈";
    String pro_desc="";
    String pro_contact_way ="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advice);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_back, R.id.tv_title, R.id.re_title, R.id.tv_content, R.id.tv_contact, R.id.bt_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                 finish();
                break;
            case R.id.tv_title:
                break;
            case R.id.re_title:
                break;
            case R.id.tv_content:
                break;
            case R.id.tv_contact:
                break;
            case R.id.bt_submit:
                submitData();
                break;
        }
    }

    private void submitData() {
        pro_desc=tvContent.getText().toString();
        pro_contact_way=tvContact.getText().toString();
        OkHttpParam param = new OkHttpParam();
        if (TextUtils.isEmpty(pro_desc)) {
            Toast.makeText(this, "请输入建议反馈", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(pro_contact_way)) {
            Toast.makeText(this, "请输入联系方式", Toast.LENGTH_SHORT).show();
            return;
        }
        param.add("pro_desc",pro_desc);
        param.add("pro_title",pro_title);
        param.add("pro_contact_way",pro_contact_way);

        _PostEntry(Urls.saveproposal, param, Urls.ActionId.saveproposal, true);
    }

    @Override
    protected void onNetSuccess(int actionId, ResponseEntry result) {
        super.onNetSuccess(actionId, result);
        switch (actionId){
            case Urls.ActionId.saveproposal:
                if(result.isSuccess()){
                    Toast.makeText(this,"意见提交成功",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
