package com.all.app.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.all.app.R;
import com.all.app.base.AdapterBase;
import com.all.app.base.BaseActivity;
import com.all.app.bean.BankNameBean;
import com.all.app.configs.Urls;
import com.all.app.http.OkHttpParam;
import com.all.app.http.ResponseEntry;
import com.all.app.ui.adapter.BankNameAdpter;
import com.all.app.utils.FJson;

import java.util.List;

public class BankCarListActivity extends BaseActivity {
    ListView list;
    List<BankNameBean>bankNameBean;
    BankNameAdpter bankNameAdpter;
    String bank_name="";
    String name="";
    String realname = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_car_list);
        realname=getIntent().getStringExtra("realname");
        list = (ListView) findViewById(R.id.list);
        loadData();
    }

    private void loadData() {
        OkHttpParam param = new OkHttpParam();
        _PostEntry(Urls.getbanktype, param, Urls.ActionId.getbanktype, true);
    }

    @Override
    protected void onNetSuccess(int actionId, ResponseEntry result) {
        super.onNetSuccess(actionId, result);
        switch (actionId) {
          case Urls.ActionId.getbanktype:
              if(result.isSuccess()){
                  bankNameBean= FJson.getObjects(result.getData().toString(),BankNameBean.class);
                if(bankNameBean!=null){
                    bankNameAdpter=new BankNameAdpter(this,bankNameBean);
                    bankNameAdpter.setAdpListener(new AdapterBase.IAdpBaseListener() {
                        @Override
                        public void onLazyAdpListener(int key, int position, Object data) {
                            BankNameBean info=(BankNameBean) data;
                            name=info.getName();
                            bank_name=info.getBank_name();
                            Intent intent=new Intent(BankCarListActivity.this,EditBankActivity.class);
                            intent.putExtra("name",name);
                            intent.putExtra("bank_name",bank_name);
                            intent.putExtra("realname", realname);
                            startActivity(intent);
                        }
                    });
                    list.setAdapter( bankNameAdpter);
                }
              }
              break;
        }
    }
}
