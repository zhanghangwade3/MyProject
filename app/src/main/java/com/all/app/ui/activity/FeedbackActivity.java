package com.all.app.ui.activity;
import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
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
import com.all.app.ui.adapter.GridAdpter;
import com.all.app.utils.AlertDialog;
import com.all.app.utils.ContainsEmojiEditText;
import com.all.app.utils.StringUtil;
import com.all.app.utils.StringUtils;
import com.all.app.utils.image_selector.MultiImageSelectorActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//问题反馈页面
public class FeedbackActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.re_title)
    RelativeLayout reTitle;
    @BindView(R.id.tv_number)
    TextView tvNumber;
    @BindView(R.id.tv_miaoshu)
    TextView tvMiaoshu;
    @BindView(R.id.tv_content)
    ContainsEmojiEditText tvContent;
    @BindView(R.id.tv_submit)
    TextView  tv_submit;
    @BindView(R.id.view_grid)
    GridView viewGrid;
    GrAdapter grAdapter;
    public static int MAX_PIC_NUM = 5;// 最大支持的图片数量
    int pos = 0;
    String taskid="";
    String question="";
    private static final int CAMERA_JAVA_REQUEST_CODE = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, CAMERA_JAVA_REQUEST_CODE);
        }

        ButterKnife.bind(this);
        taskid=getIntent().getStringExtra("taskid");
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
                    AlertDialog dialog = new AlertDialog(FeedbackActivity.this);
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

    /**
     * 删除图片
     *
     * @param
     */
  /*  private void deleteImg(String imagePath) {
        OkHttpParam param = new OkHttpParam();
        param.add("imgPath", imagePath);
        param.add("imgGrpId", imageGrpId);
        _PostEntry(Urls.deleteImg, param, ActionId.deleteImg, true);
    }*/
    @OnClick({R.id.tv_submit,R.id.iv_back, R.id.re_title, R.id.tv_number, R.id.tv_miaoshu, R.id.tv_content})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_submit:
                submitProOrder();
                break;
            case R.id.re_title:
                break;
            case R.id.tv_number:
                break;
            case R.id.tv_miaoshu:
                break;
            case R.id.tv_content:
                tvContent.setCursorVisible(true);
                break;
        }
    }

    private void submitProOrder() {
        question=tvContent.getText().toString();
        OkHttpParam param=new OkHttpParam();
        if (TextUtils.isEmpty(AppData.Init().getLoginBean().getUid())) {
            Toast.makeText(this, "用户id不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(taskid)) {
            Toast.makeText(this, "任务id不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(question)) {
            Toast.makeText(this, "请输入问题描述", Toast.LENGTH_SHORT).show();
            return;
        }
        if (mSelectPath.size() <= 0) {
            Toast.makeText(this, "请先上传图片", Toast.LENGTH_SHORT).show();
            return;
        }
        System.out.println("0000000000000000000000000000000000000000000000000000000"+mSelectPath.size());
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
        param.add("question",question);
        param.add("picnum",mSelectPath.size());
        _PostEntry(Urls.saveproblemorder,param, Urls.ActionId.saveproblemorder,true);


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
          case Urls.ActionId.saveproblemorder:
                if(result.isSuccess()){
                     startActivity(new Intent(FeedbackActivity.this,ProOrderActivity.class));
                }
              break;
      }
    }
}