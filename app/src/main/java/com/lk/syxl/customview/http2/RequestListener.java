package com.lk.syxl.customview.http2;


import com.android.volley.VolleyError;

public interface RequestListener {

	/** 成功 */
	public void requestSuccess(String json);
	
	/** 错误 */
	public void requestError(VolleyError e);
}