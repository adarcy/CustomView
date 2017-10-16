package com.lk.syxl.customview.http.demo;


import android.net.Uri;

import com.lk.syxl.customview.http.HttpRequest;
import com.lk.syxl.customview.http.HttpResponseHandler;
import com.lk.syxl.customview.http.utils.StringUtil;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;


public class PostRequest extends HttpRequest {

    private  static final String LOG_TAG = "PostRequest";
	private HttpResponseHandler<String> mResponseHandlerHandler;

	public PostRequest(String path, HashMap<String, String> params, HttpResponseHandler<String> handler) {
        super();
        setPath(path);
        Uri.Builder builder = new Uri.Builder();
        Set<String> keys = params.keySet();
        Iterator<String> iterator = keys.iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            builder.appendQueryParameter(key, params.get(key));
        }
        String encodedParam = builder.toString();
        if (encodedParam.startsWith("?")){
            encodedParam = encodedParam.substring(1);
        }
        this.data = StringUtil.getBytesUTF8(encodedParam);
        this.mResponseHandlerHandler = handler;
    }

	@Override
	public String getUrl() {
        String url = getBaseUrl();
        return getBaseUrl();
	}
    
    @Override
    protected void onRequestSuccess(int statusCode, String response) {
        if (mResponseHandlerHandler != null) {
            mResponseHandlerHandler.onResponse(response);
        }
    }

    @Override
    protected void onRequestFailure(int statusCode, String errorResponse) {
        if (mResponseHandlerHandler != null) {
            mResponseHandlerHandler.onResponse(errorResponse);
        }
    }
}
