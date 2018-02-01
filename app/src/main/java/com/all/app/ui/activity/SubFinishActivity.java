package com.all.app.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.all.app.R;
import com.all.app.app.AppData;
import com.all.app.base.BaseActivity;
import com.all.app.configs.Urls;
import com.all.app.http.OkHttpParam;
import com.all.app.http.ResponseEntry;
import com.all.app.ui.adapter.GrAdapter;
import com.all.app.utils.AlertDialog;
import com.all.app.utils.ContainsEmojiEditText;
import com.all.app.utils.StringUtil;
import com.all.app.utils.StringUtils;
import com.all.app.utils.XGridView;
import com.all.app.utils.image_selector.MultiImageSelectorActivity;

import java.io.File;
import java.text.SimpleDateFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SubFinishActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.re_title)
    RelativeLayout reTitle;
    @BindView(R.id.tv_number)
    TextView tvNumber;
    @BindView(R.id.person)
    TextView person;
    @BindView(R.id.tv_shi)
    LinearLayout tv_shi;
    @BindView(R.id.ll_ok)
    LinearLayout  ll_ok;
    @BindView(R.id.ll_fou)
    LinearLayout  ll_fou;

    @BindView(R.id.tv_miaoshu)
    TextView tvMiaoshu;
    @BindView(R.id.tv_huo)
    TextView tv_huo;
    @BindView(R.id.edt_desc)
    ContainsEmojiEditText edtDesc;
    @BindView(R.id.ck_fou)
    CheckBox ck_fou;
    @BindView(R.id.ck_ok)
    CheckBox ck_ok;
    XGridView viewGrid;
    GrAdapter grAdapter;
    public static int MAX_PIC_NUM = 5;// 最大支持的图片数量
    int pos = 0;
    String taskid="";
    String type="";
    String content="";
    String is_yc_ps="0";
    String is_ecsm="0";
    String is_pick="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_finish);
        viewGrid= (XGridView) findViewById(R.id.view_grid);
        ButterKnife.bind(this);
        SimpleDateFormat sDateFormat= new SimpleDateFormat("yyyy年MM月dd日");
        String date = sDateFormat.format(new java.util.Date());
        tvMiaoshu.setText(date);
        taskid=getIntent().getStringExtra("taskid");
        is_pick=getIntent().getStringExtra("is_pick");
        type=getIntent().getStringExtra("type");
         if("1".equals(type)){
             tvTitle.setText("提交完成配送");
             tv_huo.setText("完成配送");
             person.setText("是否异常签收");
         }else if("2".equals(type)){
             tvTitle.setText("提交完成施工");
             tv_huo.setText("完成施工");
             person.setText("是否二次上门");
         }
          getPic();//获取图片
    }
    private void getPic() {
        grAdapter = new GrAdapter(this, mSelectPath);
        viewGrid.setAdapter(grAdapter);
        viewGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> arg0, View arg1, final int arg2, long arg3) {
                if (arg2 == mSelectPath.size()) {
                    /**
                     * 选择图片
                     */
                    openChosePic(MAX_PIC_NUM, true, true);
                } else {
                    /**
                     * 查看大图
                     */
                    AlertDialog dialog = new AlertDialog(SubFinishActivity.this);
                    dialog.builder();
                    dialog.setTitle("确认删除");
                    dialog.setMsg("真的要删除图片吗？");
                    dialog.setPositiveButton("确定", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            pos = arg2;
                            if (mSelectPath.get(pos).indexOf("http") != -1) {
                                //deleteImg(mSelectPath.get(arg2));
                            } else {
                                showTips("删除成功", null);
                                mSelectPath.remove(pos);
                                grAdapter.setDataList(mSelectPath);
                                StringUtils.setGridViewHeight(viewGrid, 3);
                            }
                        }

                    });
                    dialog.setNegativeButton("取消", new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            // TODO Auto-generated method stub
                        }
                    });
                    dialog.show();
                }
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        cbinit();//判断勾选框

    }

    private void cbinit() {
        ck_fou.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    ck_ok.setChecked(false);
                    is_yc_ps="0";
                    is_ecsm="0";
                    tv_shi.setVisibility(View.GONE);
                }
            }
        });
        ck_ok.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    ck_fou.setChecked(false);
                    is_yc_ps="1";
                    is_ecsm="1";
                    tv_shi.setVisibility(View.VISIBLE);
                }
            }
        });

    }

    @OnClick({R.id.ll_fou,R.id.ll_ok,R.id.tv_huo,R.id.iv_back, R.id.tv_title, R.id.re_title, R.id.tv_number, R.id.tv_miaoshu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.ll_ok:
                ck_ok.setChecked(true);
                ck_fou.setChecked(false);
                is_yc_ps="1";
                is_ecsm="1";
                tv_shi.setVisibility(View.VISIBLE);
                break;
            case R.id.ll_fou:
                ck_fou.setChecked(true);
                ck_ok.setChecked(false);
                is_yc_ps="0";
                is_ecsm="0";
                tv_shi.setVisibility(View.GONE);
                break;
            case R.id.tv_huo:
                submitData();//提交数据
                break;
            case R.id.tv_title:
                break;
            case R.id.re_title:
                break;
            case R.id.tv_number:
                break;
            case R.id.tv_miaoshu:
                break;

        }
    }

    private void submitData() {
        content=edtDesc.getText().toString();
        OkHttpParam param=new OkHttpParam();
        if (TextUtils.isEmpty(AppData.Init().getLoginBean().getUid())) {
            Toast.makeText(this, "用户id不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(content)) {
            Toast.makeText(this, "请输入问题描述", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(taskid)) {
            Toast.makeText(this, "任务id不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
           if("2".equals(type)){
            if (mSelectPath.size() <= 0) {
                Toast.makeText(this, "请先上传图片", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        int j = 0;
        for (int i = 0; i < mSelectPath.size(); i++) {
            if (mSelectPath.get(i).indexOf("http") == -1) {
                File file = new File(mSelectPath.get(i));
                param.add("file" + (j + 1), file);
                j++;
            }
        }
        param.add("uid", AppData.Init().getLoginBean().getUid());
        param.add("taskid",taskid);
        param.add("content",content);
        param.add("picnum",mSelectPath.size());
        if("1".equals(type)){
             param.add("is_yc_ps",is_yc_ps);
             _PostEntry(Urls.distribution,param, Urls.ActionId.distribution,true);
         }else if("2".equals(type)){
             param.add("is_ecsm",is_ecsm);
             _PostEntry(Urls.construct,param, Urls.ActionId.construct,true);
         }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_IMAGE:
                if (resultCode == RESULT_OK) {
                    mSelectPath = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
                    grAdapter.setDataList(mSelectPath);
                    StringUtil.setGridViewHeight(viewGrid, 3);

                }
                break;
        }
    }

    @Override
    protected void onNetSuccess(int actionId, ResponseEntry result) {
        super.onNetSuccess(actionId, result);
        switch (actionId){
            case Urls.ActionId.distribution:
                 if(result.isSuccess()){
                     if("1".equals(is_pick)){
                         startActivity(new Intent(SubFinishActivity.this ,ExecutionActivity.class));
                     }else{
                         startActivity(new Intent(SubFinishActivity.this ,AcceptActivity.class));
                     }
                 }
                break;
            case Urls.ActionId.construct:
                if(result.isSuccess()){
                        startActivity(new Intent(SubFinishActivity.this ,AcceptActivity.class));
                    }

                break;
        }
    }

}
