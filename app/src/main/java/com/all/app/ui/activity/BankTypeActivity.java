package com.all.app.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.all.app.R;
import com.all.app.app.AppData;
import com.all.app.base.AdapterBase;
import com.all.app.base.BaseActivity;
import com.all.app.bean.BankBean;
import com.all.app.bean.BankListBean;
import com.all.app.bean.PayBean;
import com.all.app.configs.Urls;
import com.all.app.http.OkHttpParam;
import com.all.app.http.ResponseEntry;
import com.all.app.ui.adapter.AliAdpter;
import com.all.app.ui.adapter.BankAdpter;
import com.all.app.utils.FJson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BankTypeActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_baocun)
    TextView tvBaocun;
    @BindView(R.id.re_title)
    RelativeLayout reTitle;
    ListView list;
    ListView list2;
    @BindView(R.id.iv_add)
    ImageView ivAdd;
    @BindView(R.id.tv_add)
    TextView tvAdd;
    @BindView(R.id.re_add)
    RelativeLayout reAdd;
    BankListBean bankListBean;
    BankAdpter bankAdpter;
    AliAdpter aliAdpter;
    String bank_id = "";
    String bank_name = "";
    List<BankBean> bankBeen = new ArrayList<BankBean>();
    String single="";
    String alipayjs_a_id="";
    String account="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank);
        list = (ListView) findViewById(R.id.list);
        list2 = (ListView) findViewById(R.id.list2);
        ButterKnife.bind(this);
        loadCarList();//加载银行卡列表数据
        //initAlipayAdapter();
    }

    private void initAlipayAdapter() {
        aliAdpter = new AliAdpter(this, bankListBean.getAlipay());
        list2.setAdapter(aliAdpter);
    }

    private void initBankAdapter() {
        if (bankListBean != null) {
            bankAdpter = new BankAdpter(this, bankBeen);
            bankAdpter.setAdpListener(new AdapterBase.IAdpBaseListener() {
                @Override
                public void onLazyAdpListener(int key, int position, Object data) {
                    if (key == BankAdpter.CLICK_DELETE) {
                        BankBean info = (BankBean) data;
                        bank_id = info.getBank_id();
                        OkHttpParam param = new OkHttpParam();
                        param.add("uid", AppData.Init().getLoginBean().getUid());
                        param.add("bank_id", bank_id);
                        _PostEntry(Urls.delbank, param, Urls.ActionId.delbank, true);
                    }
                    if (key == BankAdpter.CLICK_ITEM) {
                        single="3";
                        BankBean info = (BankBean) data;
                        bank_name = info.getBank_name();
                        bank_id=info.getBank_id();
                        Intent intent = new Intent(BankTypeActivity.this, WithdrawActivity.class);
                        intent.putExtra("bank_name", bank_name);
                        intent.putExtra("single",single);
                        intent.putExtra("bank_id",bank_id);
                        startActivity(intent);
                    }
                }


            });
            list.setAdapter(bankAdpter);
            setListViewHeightBasedOnChildren(list);
        }
    }

    private void loadCarList() {
        OkHttpParam param = new OkHttpParam();
        param.add("uid", AppData.Init().getLoginBean().getUid());
        _PostEntry(Urls.bankcardlist, param, Urls.ActionId.bankcardlist, true);

    }

    private void setListViewHeightBasedOnChildren(ListView lv) {
        ListAdapter listAdapter = lv.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;

        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, lv);
            if (listItem != null) {
                listItem.measure(0, 0);
                totalHeight += listItem.getMeasuredHeight();
            }
        }
        ViewGroup.LayoutParams params = lv.getLayoutParams();
        params.height = totalHeight + (lv.getDividerHeight() * (listAdapter.getCount() - 1));
        lv.setLayoutParams(params);

    }

    @OnClick({R.id.iv_back, R.id.re_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.re_add:
                startActivity(new Intent(BankTypeActivity.this, ChooseTypeActivity.class));
                break;
        }
    }

    @Override
    protected void onNetSuccess(int actionId, ResponseEntry result) {
        super.onNetSuccess(actionId, result);
        switch (actionId) {
            case Urls.ActionId.bankcardlist:
                if (result.isSuccess()) {
                    bankListBean = FJson.getObject(result.getData().toString(), BankListBean.class);
                    bankBeen = bankListBean.getBank();
                    initBankAdapter();
                    System.out.println("333333333333333333333333" + bankBeen);
                    aliAdpter = new AliAdpter(this, bankListBean.getAlipay());
                    aliAdpter.setAdpListener(new AdapterBase.IAdpBaseListener() {
                        @Override
                        public void onLazyAdpListener(int key, int position, Object data) {
                            if (key == AliAdpter.CLICK_SET) {
                                startActivity(new Intent(BankTypeActivity.this, AddAlipayActivity.class));
                            }
                            if (key == BankAdpter.CLICK_ITEM) {
                                single = "2";
                                PayBean info = (PayBean) data;
                                alipayjs_a_id = info.getAlipayjs_a_id();
                                System.out.println("888888888888888888888888888"+ alipayjs_a_id );
                                account=info.getAlipayjs_account();
                                Intent intent = new Intent(BankTypeActivity.this, WithdrawActivity.class);
                                intent.putExtra("alipayjs_a_id", alipayjs_a_id);
                                intent.putExtra("single",single);
                                intent.putExtra("account",account);
                                startActivity(intent);

                            }
                        }


                    });
                    list2.setAdapter(aliAdpter);
                }
                break;
            case Urls.ActionId.delbank:
                if (result.isSuccess()) {
                    Toast.makeText(this, "删除银行卡成功", Toast.LENGTH_SHORT).show();
                    loadCarList();//加载银行卡列表数据
                }
                break;


        }
    }

    private void setListViewHeightBasedOnChildren2(ListView list2) {
        ListAdapter listAdapter = list2.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;

        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, list2);
            if (listItem != null) {
                listItem.measure(0, 0);
                totalHeight += listItem.getMeasuredHeight();
            }
        }
        ViewGroup.LayoutParams params = list2.getLayoutParams();
        params.height = totalHeight + (list2.getDividerHeight() * (listAdapter.getCount() - 1));
        list2.setLayoutParams(params);
    }


}
