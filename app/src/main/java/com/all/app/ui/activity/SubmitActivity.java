package com.all.app.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.all.app.R;
import com.all.app.app.AppData;
import com.all.app.base.BaseActivity;
import com.all.app.bean.AiBean;
import com.all.app.configs.Urls;
import com.all.app.http.OkHttpParam;
import com.all.app.http.ResponseEntry;
import com.all.app.utils.FJson;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SubmitActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.re_title)
    RelativeLayout reTitle;
    @BindView(R.id.tv_bao)
    TextView tvBao;
    @BindView(R.id.iv_right4)
    ImageView ivRight4;
    @BindView(R.id.re_service)
    RelativeLayout reService;
    @BindView(R.id.iv_bao)
    ImageView ivBao;
    @BindView(R.id.iv_right2)
    ImageView ivRight2;
    @BindView(R.id.re_bao)
    RelativeLayout reBao;
    @BindView(R.id.re_bao2)
    RelativeLayout reBao2;
    @BindView(R.id.tv_money)
    TextView tv_money;
    String recharge="";
    String type="3";
    AiBean aiBean;
    String data="";
    /** 支付宝支付业务：入参app_id */
    public static final String APPID = "2017052207309154";

    /** 支付宝账户登录授权业务：入参pid值 */
    public static final String PID = "";
    /** 支付宝账户登录授权业务：入参target_id值 */
    public static final String TARGET_ID = "";

    /** 商户私钥，pkcs8格式 */
    /** 如下私钥，RSA2_PRIVATE 或者 RSA_PRIVATE 只需要填入一个 */
    /** 如果商户两个都设置了，优先使用 RSA2_PRIVATE */
    /** RSA2_PRIVATE 可以保证商户交易在更加安全的环境下进行，建议使用 RSA2_PRIVATE */
    /** 获取 RSA2_PRIVATE，建议使用支付宝提供的公私钥生成工具生成， */
    /** 工具地址：https://doc.open.alipay.com/docs/doc.htm?treeId=291&articleId=106097&docType=1 */
    public static final String RSA2_PRIVATE = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCYb9NulamRj2Rr/Co37Kl7CYqmooPsQAmem3WSpkc+lWWSRJK1o6nYVwZ64l4mLA16Htrte3OgRwsRVAWODBlA7SbF17OegKsw1t0ZZz907AwZ/qCboI9nLAxfoMqT2kYII8GhlLxJmadX2pkR3WXLIDwnxLFvQ+YlSym9vX9JMbJBjtIOiptrDha62pvVVeHTmJU00xhmeMaeLAfpbNjAwA/HJC721sAR0fCg+aQfNXWnDEtAUWFpArWuqfERMviboDdnHv8/mnZL7//iws98V34bYaq8SbLdva88/FJjVvxEgylFxkALcDbGaXYdwydA/1saKCei91i3sZp0IzW9AgMBAAECggEAHpRYStfVI3EriQoWAhAE07zPRqxVYKBcDahMBrY6lpBrxtLgx3VkjhSvJRmiH3A45+Wmr3tTb7SwwLIlRyCgNEkevROPYd2X+f0wb3cp4K+1ocVVvMKR4fWRKeHcoZpo8BsULfDjcLRVQKeGi3HpiL0XtDaeDZ8GXTbd0cVg8nKncDC6xWGHjQ0nkS6V6iro+9a0+5KBIWwspqwuOuhVOQq8EFrOKydnA5hGSYmYBPBgwiiiWSiTxMtLQuaypwbzbPte2ucLX7zbOVZj6sCpwVPifBByWcRyjoGgnl9MWNy+UrTJf46XCJDvDTxo2Dp9PMjQyCxsgYGyR0u2uDvO1QKBgQD3piibYqfKK5J7ly3Hb5G8NYAy+AqNH9U+McF0S8ipANdDpPZrkpqOz+Y80fQUEU1tAkAAT90X5IrqKPZzzUHl2bqDhSfHjL3tHrQKrKBORTzXGzA3zBOuvs71rNvtROCRfKHbLbhDw3mk0W1mi44azsR3hOOHHrxMBeYSPah07wKBgQCdk75TXwYQMKabnp5/Dlb7t+vavrYcF260HzZuovI/atsgO1vX9HQ623jlpDls9IbGwlRfn2moJQ2mwU5cHBcI+9Sv7K1ks/eGY27Bc6fIExF3FLLaRdB7bWvKt8r56/0zFO3cT8g5jozdCcWYgZJPnqUZMKbPy9NiWj+f5FX4EwKBgQCKlhaVVjE1pwqTVTfOKrZwj7QNUdd9wDmyU1/COAKz6P+OIEuySVlq4BclJA0mCcS9Ywvz4L2GeGPwxDme9leRTDXfEe3/8tf6UTtJ2e7fvklcyx9Bqle9a5Qf0btFXU0avZdKM5yxdzukq3pAb4p3oAw8Ika9v8p59LcasByMSwKBgGiljHkLmxg3Zv3kKjgQyt1iOGTN0Fz5htBiBiEVX7GUAi/2m7jgJCxOQoynMmfUQIlQ1v2l/WHoMZezuplbIkUaERZAJKrDBbge4pfgeeVaHHgrq9dfE+RUN4ip5EdzXHSHCqwL49mtfIjyi2ILcqIjplsDP1SydRBVBPnnhtw3AoGBANQyLqb1IZFrbUYJFGwmuM5rG1Vqjk9y7g2SplD/y07qhoNEcInJrjiRbOw+j+yCtnBTKZtD6baW5yP++DywfQz/yXaXp6+PlJi2jr9ESp1M/hLveFysr8hiN2F1WZ3hUaHrz0z83P7TTQH8urTMw9Sf4oodpX3im26gJmrVYzTp\n";
    public static final String RSA_PRIVATE = "";

    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_AUTH_FLAG = 2;

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == SDK_PAY_FLAG) {
                @SuppressWarnings("unchecked")
                PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                /**
                 对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                 */
                String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                String resultStatus = payResult.getResultStatus();
                // 判断resultStatus 为9000则代表支付成功
                if (TextUtils.equals(resultStatus, "9000")) {
                    // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                    Toast.makeText(SubmitActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                } else {
                    // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                    Toast.makeText(SubmitActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                }
            }

        };
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);
        ButterKnife.bind(this);
        recharge=getIntent().getStringExtra("recharge");
        tv_money.setText( recharge+"元");
    }

    @OnClick({R.id.re_bao2,R.id.iv_back, R.id.tv_title, R.id.re_title, R.id.tv_bao, R.id.iv_right4, R.id.re_service, R.id.iv_bao, R.id.iv_right2, R.id.re_bao})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_title:
                break;
            case R.id.re_title:
                break;
            case R.id.tv_bao:
                break;
            case R.id.re_bao2:
                 Intent intent=new Intent(SubmitActivity.this,PayActivity.class);
                 intent.putExtra("recharge",recharge);
                 intent.putExtra("type",type);
                  startActivity(intent);
                 break;
            case R.id.iv_right4:
                break;
            case R.id.re_service:
                break;
            case R.id.iv_bao:
                break;
            case R.id.iv_right2:
                break;
            case R.id.re_bao:
                OkHttpParam param=new OkHttpParam();
                param.add("uid", AppData.Init().getLoginBean().getUid());
                param.add("pay_amount", recharge);
                _PostEntry(Urls.creatordercharge, param, Urls.ActionId.creatordercharge, true);
                break;
        }
    }

    @Override
    protected void onNetSuccess(int actionId, ResponseEntry result) {
        super.onNetSuccess(actionId, result);
    switch (actionId){
        case Urls.ActionId.creatordercharge:
           if(result.isSuccess()){
               System.out.println("777777777777777777777777777777777777"+result);
               data=result.getData().toString();
               System.out.println("888888888888888888888888888888888888888888"+data);
               onAliPay();
           }
            break;
    }
    }
    private void onAliPay() {
        final String orderInfo=data;
        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(SubmitActivity.this);
                Map<String, String> result = alipay.payV2(orderInfo, true);
                Log.i("msp", result.toString());
                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        Thread payThread = new Thread(payRunnable);
        payThread.start();

    }



}
