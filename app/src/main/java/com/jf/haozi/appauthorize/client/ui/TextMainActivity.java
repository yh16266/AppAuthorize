package com.jf.haozi.appauthorize.client.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.jf.haozi.appauthorize.R;
import com.jf.haozi.appauthorize.common.base.BaseActivity;

/**
 * Created by Admin on 2016/5/26.
 */
public class TextMainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.textmain_actvity);
        initView();
    }

    private void initView() {
        findViewById(R.id.button).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this,RegisteActivity.class);
        startActivity(intent);
    }
}
