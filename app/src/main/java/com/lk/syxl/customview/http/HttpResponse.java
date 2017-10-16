package com.lk.syxl.customview.http;


import android.util.Log;

import com.lk.syxl.customview.http.utils.StringUtil;


/**
 *
 * 网络请求返回结果的基类，所有具体的网络请求返回都是他的实现，他与{@link HttpRequest}一般成对使用
 *
 */

public abstract class HttpResponse {
    private  static final String LOG_TAG = "HttpResponse";

    public String msg;
    
    public HttpResponse() {}
    
    public void parseSuccessResponse(int ret, String response) {
		if(response == null) {
			this.msg = "msg body is null, statusCode:" + ret;
            Log.e(LOG_TAG,this.msg);
		} else {
            this.onResponse(response);
		}
    }
    
    public void  parseFailureResponse(int flag,String errorResponse) {
        if(!StringUtil.isEmpty(errorResponse)){
            this.msg = errorResponse;
        }
    }
    
    public abstract void onResponse(String response);
    

    
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("&msg=" + msg);
        return builder.toString();
    }
}
