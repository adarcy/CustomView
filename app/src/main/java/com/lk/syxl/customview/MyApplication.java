package com.lk.syxl.customview;

import android.app.Application;
import android.content.Context;
import android.support.v7.app.AppCompatDelegate;

import com.lk.syxl.customview.utils.SPUtils;
import com.lk.syxl.customview.utils.Utils;

/**
 * Created by likun on 2017/9/28.
 */

public class MyApplication extends Application {

    boolean nightMode;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        // initialize最好放在attachBaseContext最前面，初始化直接在Application类里面，切勿封装到其他类
        SophixManager.getInstance().setContext(this)
                .setAppVersion(appVersion)
                .setAesKey(null)
                .setEnableDebug(true)
                .setPatchLoadStatusStub(new PatchLoadStatusListener() {
                    @Override
                    public void onLoad(final int mode, final int code, final String info, final int handlePatchVersion) {
                        // 补丁加载回调通知
                        if (code == PatchStatus.CODE_LOAD_SUCCESS) {
                            // 表明补丁加载成功
                        } else if (code == PatchStatus.CODE_LOAD_RELAUNCH) {
                            // 表明新补丁生效需要重启. 开发者可提示用户或者强制重启;
                            // 建议: 用户可以监听进入后台事件, 然后调用killProcessSafely自杀，以此加快应用补丁，详见1.3.2.3
                        } else {
                            // 其它错误信息, 查看PatchStatus类说明
                        }
                    }
                }).initialize();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Utils.init(this);
        // queryAndLoadNewPatch不可放在attachBaseContext 中，否则无网络权限，建议放在后面任意时刻，如onCreate中
        SophixManager.getInstance().queryAndLoadNewPatch();
    }

    private void setNightMode() {
//        boolean nightMode = UserInfoTools.isNightMode(this);
        nightMode = SPUtils.getInstance().getBoolean("nightMode",false);
        AppCompatDelegate.setDefaultNightMode(nightMode ?
                AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO);
    }
}
