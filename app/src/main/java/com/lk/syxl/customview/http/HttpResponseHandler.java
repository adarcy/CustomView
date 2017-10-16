package com.lk.syxl.customview.http;

/**
 * 具体业务网络请求结果实例的基类
 */
public interface HttpResponseHandler<T> {
	void onResponse(T response);
	void onError(String errorResponse);
}
