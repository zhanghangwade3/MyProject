package com.all.app.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.all.app.R;
import com.all.app.base.BaseActivity;
import com.all.app.ui.adapter.GrAdapter;
import com.all.app.utils.AlertDialog;
import com.all.app.utils.StringUtil;
import com.all.app.utils.StringUtils;
import com.all.app.utils.XGridView;
import com.all.app.utils.image_selector.MultiImageSelectorActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PhActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.re_title)
    RelativeLayout reTitle;
    @BindView(R.id.tv_name1)
    TextView tvName1;
    @BindView(R.id.view31)
    View view31;

    XGridView noScrollgridview1;
    @BindView(R.id.tv_name2)
    TextView tvName2;
    @BindView(R.id.view32)
    View view32;

    XGridView noScrollgridview2;
    @BindView(R.id.tv_name3)
    TextView tvName3;
    @BindView(R.id.view33)
    View view33;
    XGridView noScrollgridview3;
    GrAdapter grAdapter;
    GrAdapter grAdapter2;
    GrAdapter grAdapter3;
    public static int MAX_PIC_NUM = 5;// 最大支持的图片数量
    int pos = 0;
    int type=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ph);
        noScrollgridview1= (XGridView) findViewById(R.id.noScrollgridview1);
        noScrollgridview2= (XGridView) findViewById(R.id.noScrollgridview2);
        noScrollgridview3= (XGridView) findViewById(R.id.noScrollgridview3);
        ButterKnife.bind(this);
        grAdapter = new GrAdapter(this, mSelectPath);
        noScrollgridview1.setAdapter(grAdapter);
        noScrollgridview1.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> arg0, View arg1, final int arg2, long arg3) {
                type=1;
                if (arg2 == mSelectPath.size()) {
                    /**
                     * 选择图片
                     */
                    openChosePic(MAX_PIC_NUM, true, true);
                } else {
                    /**
                     * 查看大图
                     */
                    AlertDialog dialog = new AlertDialog(PhActivity.this);
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
                                StringUtils.setGridViewHeight( noScrollgridview1, 3);
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
        grAdapter2 = new GrAdapter(this, mSelectPath2);
        noScrollgridview2.setAdapter(grAdapter2);
        noScrollgridview2.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> arg0, View arg1, final int arg2, long arg3) {
                type=2;
                if (arg2 == mSelectPath2.size()) {
                    /**
                     * 选择图片
                     */
                    openChosePic2(MAX_PIC_NUM, true, true);
                } else {
                    /**
                     * 查看大图
                     */
                    AlertDialog dialog = new AlertDialog(PhActivity.this);
                    dialog.builder();
                    dialog.setTitle("确认删除");
                    dialog.setMsg("真的要删除图片吗？");
                    dialog.setPositiveButton("确定", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            pos = arg2;
                            if (mSelectPath2.get(pos).indexOf("http") != -1) {
                                //deleteImg(mSelectPath.get(arg2));
                            } else {
                                showTips("删除成功", null);
                                mSelectPath2.remove(pos);
                                grAdapter2.setDataList(mSelectPath2);
                                StringUtils.setGridViewHeight( noScrollgridview2, 3);
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
        grAdapter3 = new GrAdapter(this, mSelectPath3);
        noScrollgridview3.setAdapter(grAdapter3);
        noScrollgridview3.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> arg0, View arg1, final int arg2, long arg3) {
                type=3;
                if (arg2 == mSelectPath3.size()) {
                    /**
                     * 选择图片
                     */
                    openChosePic3(MAX_PIC_NUM, true, true);
                } else {
                    /**
                     * 查看大图
                     */
                    AlertDialog dialog = new AlertDialog(PhActivity.this);
                    dialog.builder();
                    dialog.setTitle("确认删除");
                    dialog.setMsg("真的要删除图片吗？");
                    dialog.setPositiveButton("确定", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            pos = arg2;
                            if (mSelectPath3.get(pos).indexOf("http") != -1) {
                                //deleteImg(mSelectPath.get(arg2));
                            } else {
                                showTips("删除成功", null);
                                mSelectPath3.remove(pos);
                                grAdapter3.setDataList(mSelectPath3);
                                StringUtils.setGridViewHeight( noScrollgridview3, 3);
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

    @OnClick({R.id.iv_back, R.id.tv_title, R.id.re_title, R.id.tv_name1, R.id.view31,  R.id.tv_name2, R.id.view32,  R.id.tv_name3, R.id.view33,})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_title:
                break;
            case R.id.re_title:
                break;
            case R.id.tv_name1:
                break;
            case R.id.view31:
                break;
            case R.id.tv_name2:
                break;
            case R.id.view32:
                break;
            case R.id.tv_name3:
                break;
            case R.id.view33:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_IMAGE:
                if (resultCode == RESULT_OK) {
                    if (type == 1) {
                        mSelectPath = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
                        grAdapter.setDataList(mSelectPath);
                        StringUtil.setGridViewHeight(noScrollgridview1, 3);
                    } else if (type == 2) {
                        mSelectPath2 = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
                        grAdapter2.setDataList(mSelectPath2);
                        StringUtil.setGridViewHeight(noScrollgridview2, 3);
                    } else if (type == 3) {
                        mSelectPath3 = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
                        grAdapter3.setDataList(mSelectPath3);
                        StringUtil.setGridViewHeight(noScrollgridview3, 3);
                    }
                    break;
                }
        }

    }
}
