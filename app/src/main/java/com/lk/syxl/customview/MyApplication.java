package com.lk.syxl.customview;

import android.app.Application;
import android.support.v7.app.AppCompatDelegate;

import com.lk.syxl.customview.utils.Utils;

/**
 * Created by likun on 2017/9/28.
 */

public class MyApplication extends Application {

    boolean nightMode;

    @Override
    public void onCreate() {
        super.onCreate();

        Utils.init(this);
    }

    private void setNightMode() {
//        boolean nightMode = UserInfoTools.isNightMode(this);
        AppCompatDelegate.setDefaultNightMode(nightMode ?
                AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO);
    }
}
