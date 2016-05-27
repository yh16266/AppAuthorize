package com.jf.haozi.appauthorize;

import android.content.Context;
import android.content.Intent;

import com.jf.haozi.appauthorize.client.ui.RegisteActivity;
import com.jf.haozi.appauthorize.common.utils.FileUtil;

import java.io.File;

/**
 * Created by Admin on 2016/5/27.
 */
public class AuthorizeUtils {

    public boolean isAuthorized(Context mContext){
        boolean rst = FileUtil.isRegisteFileExist();
        if(rst == false){
            Intent intent = new Intent(mContext, RegisteActivity.class);
            mContext.startActivity(intent);
        }else{
            String key = FileUtil.ReadRegisteFile();
        }
        return rst;
    }

}
