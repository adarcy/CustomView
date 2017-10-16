package com.lk.syxl.customview.http;

/**
 * 网络请求基类中将请求结果处理后的返回内容转化为业务逻辑相关错误
 */
public interface HTTPRequestHandler {
    void onSuccess(int statusCode, String response);
    void onFailure(int statusCode, String responseBody);
}
