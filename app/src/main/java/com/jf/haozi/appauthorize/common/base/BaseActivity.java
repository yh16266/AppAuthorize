package com.jf.haozi.appauthorize.common.base;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by Admin on 2016/5/26.
 */
public class BaseActivity extends AppCompatActivity implements Handler.Callback, View.OnClickListener {

    /**类标签 */
    protected String TAG = getClass().getSimpleName();
    /**主处理UI器 */
    public Handler mMainHandler;

    /**
     * 注册UI处理器
     * */
    protected void registerHandler() {
        if(mMainHandler == null){
            mMainHandler = new Handler(this);
        }
    }

    /**
     * 发送空内容广播
     * */
    protected void sendEmtpyMessage(int what){
        if(mMainHandler != null){
            mMainHandler.sendEmptyMessage(what);
        }
    }

    @Override
    public boolean handleMessage(Message msg) {
        return false;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //注销handler
        if(mMainHandler != null){
            mMainHandler.removeCallbacksAndMessages(null);
        }
    }
}
