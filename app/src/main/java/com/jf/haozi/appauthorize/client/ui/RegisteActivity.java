package com.jf.haozi.appauthorize.client.ui;

import android.os.Bundle;

import com.jf.haozi.appauthorize.R;
import com.jf.haozi.appauthorize.common.base.BaseActivity;
import com.jf.haozi.appauthorize.common.control.MProgressDialog;

public class RegisteActivity extends BaseActivity {

    MProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registe_activity);
        initView();
    }

    public void initView(){
        progressDialog = MProgressDialog.show(this,"获取注册信息中...");
    }

    @Override
    protected void onDestroy() {
        if(progressDialog != null){
            progressDialog.dismiss();
        }
        super.onDestroy();
    }
}
