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
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.all.app.R;
import com.all.app.app.AppData;
import com.all.app.base.BaseFragment;
import com.all.app.bean.RealNameBean;
import com.all.app.bean.StatueBean;
import com.all.app.configs.Urls;
import com.all.app.http.OkHttpParam;
import com.all.app.http.ResponseEntry;
import com.all.app.ui.activity.GetActivity;
import com.all.app.ui.activity.MainActivity;
import com.all.app.ui.activity.ServiceActivity;
import com.all.app.utils.ContainsEmojiEditText;
import com.all.app.utils.FJson;
import com.all.app.utils.IDCard;
import com.all.app.utils.XGlide;
import com.all.app.utils.image_selector.MultiImageSelectorActivity;

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

public class RealNameFragment extends BaseFragment {
    private static final int CAMERA_JAVA_REQUEST_CODE = 2;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_real_name)
    ContainsEmojiEditText tvRealName;
    @BindView(R.id.tv_card_num)
    ContainsEmojiEditText tvCardNum;
    @BindView(R.id.iv_zhengp)
    ImageView ivZhengp;
    @BindView(R.id.tv_zheng)
    TextView tvZheng;
    @BindView(R.id.ll_zhengp)
    LinearLayout llZhengp;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.ll_back)
    LinearLayout llBack;
    @BindView(R.id.iv_car)
    ImageView ivCar;
    @BindView(R.id.textVi4)
    TextView textVi4;
    @BindView(R.id.ll_car)
    LinearLayout llCar;
    @BindView(R.id.ll_pic)
    LinearLayout ll_pic;
    @BindView(R.id.iv_zheng)
    ImageView ivZheng;
    @BindView(R.id.iv_yang)
    ImageView iv_yang;

    @BindView(R.id.textVi5)
    TextView textVi5;
    @BindView(R.id.ll_zheng)
    LinearLayout llZheng;

    @BindView(R.id.text_submit)
    TextView textSubmit;
    @BindView(R.id.tishi)
    TextView  tishi;
    @BindView(R.id.ll_news)
    LinearLayout ll_news;
    @BindView(R.id.tv_photo)
    TextView tv_photo;
    Unbinder unbinder;
    private int type;
    private int CAMERA_RESULT = 100;
    private int RESULT_LOAD_IMAGE = 200;
    private Bitmap photo;
    private File mPhotoFile;
    private String saveDir = Environment.getExternalStorageDirectory()
            .getPath() + "/temp_image";
    String realname = "";//用户真实姓名
    String uid = ""; //用户的id
    String id_card = ""; //身份这证号
    /**
     * 图片类型标识
     */
    String mSign;
    private final static String IMAGE_FRONT = "frontImage";
    private final static String IMAGE_BACK = "backImage";
    private final static String IMAGE_HANDS = "handsImage";
    private final static String IMAGE_PERSON = "personImage";

    String mFrontImagePath = "";
    String mBackImagePath = "";
    String mHandsImagePath = "";
    String mPersonImagePath = "";
    RealNameBean realNameBean;
    StatueBean statueBean;
    String realname_status="";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, CAMERA_JAVA_REQUEST_CODE);
        }
    }

    @Override
    protected void Init() {

    }

    @Override
    public void onResume() {
        super.onResume();
        laodStatue();

    }

    private void laodStatue() {
        OkHttpParam param=new OkHttpParam();
        param.add("uid", AppData.Init().getLoginBean().getUid());
        _PostEntry(Urls.getrname,param, Urls.ActionId.getrname,true);
    }

    @Override
    protected int InitLayer() {
        return R.layout.fragment_name;
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

    @OnClick({R.id.tv_name, R.id.tv_real_name, R.id.tv_card_num, R.id.iv_zhengp, R.id.tv_zheng, R.id.ll_zhengp, R.id.iv_back, R.id.tv_back, R.id.ll_back, R.id.iv_car, R.id.textVi4, R.id.ll_car, R.id.iv_zheng, R.id.textVi5, R.id.ll_zheng, R.id.text_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_name:
                break;
            case R.id.tv_real_name:
                tvRealName.setCursorVisible(true);
                tvRealName.setHint("");
                break;
            case R.id.tv_card_num:
                tvCardNum.setCursorVisible(true);
                tvCardNum.setHint("");
                break;
            case R.id.iv_zhengp:
                mSign = IMAGE_FRONT;
                openChoseImg(1, true, false);
                break;
            case R.id.tv_zheng:
                break;
            case R.id.ll_zhengp:
                break;
            case R.id.iv_back:
                mSign = IMAGE_BACK;
                openChoseImg(1, true, false);
                break;
            case R.id.tv_back:
                break;
            case R.id.ll_back:
                break;
            case R.id.iv_car:
                mSign = IMAGE_HANDS;
                openChoseImg(1, true, false);
                break;
            case R.id.textVi4:
                break;
            case R.id.ll_car:
                break;
            case R.id.iv_zheng:
                mSign = IMAGE_PERSON;
                openChoseImg(1, true, false);
                break;
            case R.id.textVi5:
                break;
            case R.id.ll_zheng:
                break;
            case R.id.text_submit:
                submit();
                break;
        }
    }

    protected ArrayList<String> mImgPath = new ArrayList<String>();
    protected static final int REQUEST_IMG = 10;

    private void openChoseImg(int maxNum, boolean showCamera, boolean isMultiselect) {

        int selectedMode = MultiImageSelectorActivity.MODE_MULTI;

        if (isMultiselect) {
            selectedMode = MultiImageSelectorActivity.MODE_MULTI;
        } else {
            selectedMode = MultiImageSelectorActivity.MODE_SINGLE;

        }

        Intent intent = new Intent(getActivity(),
                MultiImageSelectorActivity.class);
        // 是否显示拍摄图片
        intent.putExtra(MultiImageSelectorActivity.EXTRA_SHOW_CAMERA,
                showCamera);
        // 最大可选择图片数量
        intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_COUNT, maxNum);
        // 选择模式
        intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_MODE,
                selectedMode);
        // 默认选择
        if (mImgPath != null && mImgPath.size() > 0) {
            intent.putExtra(
                    MultiImageSelectorActivity.EXTRA_DEFAULT_SELECTED_LIST,
                    mImgPath);
        }
        startActivityForResult(intent, REQUEST_IMG);
    }


    //提交用户实名认证的信息
    private void submit() {
        realname = tvRealName.getText().toString().trim();
        id_card = tvCardNum.getText().toString().trim();
        uid = AppData.Init().getLoginBean().getUid();
        OkHttpParam param = new OkHttpParam();

        if (TextUtils.isEmpty(realname)) {
            Toast.makeText(getActivity(), "请输入真实姓名", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(uid)) {
            Toast.makeText(getActivity(), "请输入用户ID", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(id_card)) {
            Toast.makeText(getActivity(), "请输入身份证号", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!IDCard.IDCardValidate(id_card.toUpperCase()).equals("1")) {
            showTips(IDCard.IDCardValidate(id_card.toUpperCase()), null);
            return;
        }

        if (!TextUtils.isEmpty(mFrontImagePath)) {
            File frontImageFile = new File(mFrontImagePath);
            if (frontImageFile.isFile()) {
                param.add("id_pic", frontImageFile);

            } else {
                showTips("请选择身份证正面照片。", null);
                return;
            }
            if (!TextUtils.isEmpty(mBackImagePath)) {
                File backImageFile = new File(mBackImagePath);
                if (backImageFile.isFile()) {
                    param.add("id_pic2", backImageFile);
                }
            } else {
                showTips("请选择身份证背面照片。", null);
                return;
            }
            if (!TextUtils.isEmpty(mHandsImagePath)) {
                File handsImageFile = new File(mHandsImagePath);
                if (handsImageFile.isFile()) {
                    param.add("id_card_hand", handsImageFile);
                }
            } else {
                showTips("请选择手持身份证照片。", null);
                return;
            }

            if (!TextUtils.isEmpty(mPersonImagePath)) {
                File personImageFile = new File(mPersonImagePath);
                if (personImageFile.isFile()) {
                    param.add("u_photo", personImageFile);
                }
            } else {
                showTips("请选择个人生活照片。", null);
                return;
            }
            param.add("uid",uid);
            param.add("id_card",id_card);
            param.add("realname",realname);
            _PostEntry(Urls.savername, param, Urls.ActionId.savername, true);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == -1) {
            switch (requestCode) {
                case REQUEST_IMG:
                    List<String> mSelectPath = data
                            .getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
                    if (mSign.equals(IMAGE_FRONT)) {
                        mFrontImagePath = mSelectPath.get(0);
                        XGlide.init(mContext).display(ivZhengp,
                                new File(mFrontImagePath));
                    } else if (mSign.equals(IMAGE_BACK)) {
                        mBackImagePath = mSelectPath.get(0);
                        XGlide.init(mContext).display(ivBack,
                                new File(mBackImagePath));
                    } else if (mSign.equals(IMAGE_HANDS)) {
                        mHandsImagePath = mSelectPath.get(0);
                        XGlide.init(mContext).display(ivCar,
                                new File(mHandsImagePath));
                    } else if (mSign.equals(IMAGE_PERSON)) {
                        mPersonImagePath = mSelectPath.get(0);
                        XGlide.init(mContext).display(ivZheng,
                                new File(mPersonImagePath));
                        break;
                    }
            }
        }

    }

    @Override
    protected void onNetSuccess(int actionId, ResponseEntry result) {
        super.onNetSuccess(actionId, result);
          switch (actionId){
              case Urls.ActionId.savername:
                  if(result.isSuccess()){
                      realNameBean= FJson.getObject(result.getData().toString(),RealNameBean.class);
                      startActivity(new Intent(getActivity(), ServiceActivity.class));
                  }
                  break;
              case Urls.ActionId.getrname:
                  if(result.isSuccess()){
                      statueBean= FJson.getObject(result.getData().toString(), StatueBean.class);
                      realname_status=statueBean.getRealname_status();
                      if("0".equals(realname_status)){
                          ll_pic.setVisibility(View.GONE);
                          ll_news.setVisibility(View.GONE);
                          tv_photo.setVisibility(View.GONE);
                          textSubmit.setVisibility(View.GONE);
                          iv_yang.setVisibility(View.GONE);
                          tishi.setVisibility(View.VISIBLE);
                          tishi.setText("实名认证等待审核");
                      }else if("1".equals(realname_status)){
                          ll_pic.setVisibility(View.GONE);
                          ll_news.setVisibility(View.GONE);
                          tv_photo.setVisibility(View.GONE);
                          textSubmit.setVisibility(View.GONE);
                          iv_yang.setVisibility(View.GONE);
                          tishi.setVisibility(View.VISIBLE);
                          tishi.setText("实名认证已通过");
                      } else if ("2".equals(realname_status)) {
                          ll_news.setVisibility(View.VISIBLE);
                          tv_photo.setVisibility(View.VISIBLE);
                          ll_pic.setVisibility(View.VISIBLE);
                          iv_yang.setVisibility(View.VISIBLE);
                          textSubmit.setVisibility(View.VISIBLE);

                          //showTips("请您重新上传",null);
                      }
                  }
                  break;
          }
    }
}