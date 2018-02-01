package com.all.app.ui.activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.all.app.R;
import com.all.app.base.BaseActivity;
import com.all.app.base.BaseFragmentActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SchoolActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school);

    }
}
