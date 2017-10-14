package com.lk.syxl.customview.mvp.impl;

import android.content.Context;

/**
 * Created by likun on 2017/10/11.
 * presenter基类
 */

public class BasePresenter {

    public Context mContext;
    public void onAttach(Context context){
        mContext = context;
    }
    public void onResume(){};
    public void onStop(){};
    public void onDestroy(){
        mContext = null;
    }
}
