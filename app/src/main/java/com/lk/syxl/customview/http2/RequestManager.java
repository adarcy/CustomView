package com.lk.syxl.customview.http2;


import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;

import com.android.volley.Request;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.lk.syxl.customview.utils.Utils;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;


/**
 * 
 * @author Doots
 *
 */
public class RequestManager {
	
    public static RequestQueue mRequestQueue = Volley.newRequestQueue(Utils.getApp());
    private static RequestManager manager;
    private RequestManager() {
        // no instances
    }

    public static RequestManager getInsrance(){
        if (manager == null){
            synchronized (RequestManager.class){
                if (manager == null){
                    manager = new RequestManager();
                }
            }
            return manager;
        }
        return manager;
    }




    /**
     *
     * @param url
     * @param tag
     * @param params
     * @param listener
     */
    public static void get(String url, Object tag, Map<String,String> params, Response.Listener<String> listener, Response.ErrorListener errorListener) {
//        url = url + "?" + getInsrance().getBaseParams();
        StringRequestWithParams request = new StringRequestWithParams(Method.GET, url, listener, errorListener);
        addRequest(request , tag);
    }












    /**
     * 
     * @param <>
     * @param url
     * @param tag
     * @param listener
     */
    public static void get(String url, Object tag, RequestListener listener) {
    	get(url, tag, null, listener);
    }

    /**
     * 
     * @param url
     * @param tag
     * @param params
     * @param listener
     */
    public static void get(String url, Object tag, RequestParams params, RequestListener listener) {

    	ByteArrayRequest request = new ByteArrayRequest(Method.GET, url, null, responseListener(listener), responseError(listener));
    	addRequest(request , tag);
    }

    /**
     * 
     * @param url
     * @param tag
     * @param listener
     */
    public static void post(String url, Object tag, RequestListener listener) {
    	post(url, tag, null, listener);
    }
    
    /**
     * 
     * @param url
     * @param tag
     * @param params
     * @param listener
     */
    public static void post(String url, Object tag, RequestParams params, RequestListener listener) {
    	ByteArrayRequest request = new ByteArrayRequest(Method.POST, url, params, responseListener(listener), responseError(listener));
    	addRequest(request , tag);
    }
    
    public static void addRequest(Request<?> request, Object tag) {
        if (tag != null) {
            request.setTag(tag);
        }
        mRequestQueue.add(request);
    }

    public static void cancelAll(Object tag) {
        mRequestQueue.cancelAll(tag);
    }
    
    
    /**
     * 成功消息监听
     * @param l
     * @return
     */
    protected static Response.Listener<byte[]> responseListener(final RequestListener l) {
    	return new Response.Listener<byte[]>() {
			@Override
			public void onResponse(byte[] arg0) {
				String data = null;
				try {
					data = new String(arg0, "UTF-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				l.requestSuccess(data);
			}
		};
    }
    
    /**
     * 返回错误监听
     * @param l
     * @return
     */
    protected static Response.ErrorListener responseError(final RequestListener l) {
    	return new Response.ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError e) {
				l.requestError(e);
			}
		};
    }


    /**
     * 获取全局header
     * @return
     */
    public Map<String,String> getGlobalHeaders(){
        Map<String,String> headers = new HashMap<>();
        headers.put("Cookie", "token");
        headers.put("charset", "UTF-8");
        return headers;
    }

    /**
     * 获取全局参数
     * @return
     */
    public String getBaseParams(){
        Map<String,String> params = new HashMap<>();
        params.put("source", 1 + "");//1是android
        params.put("os", getOs());
        params.put("model", getDeviceInfo());
//        params.put("cache_key", getIdentity()+"");//区分不同用户之间的缓存
        params.put("appType", "1");//区分是普通版还是快销版
        params.put("_vs",getVersionName());

        StringBuilder builder = new StringBuilder();
        for (Map.Entry<String,String> entry : params.entrySet()){
            builder.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
        }
        return builder.substring(0,builder.length()-1);
    }

    private String getOs() {
        return Build.VERSION.SDK;
    }

    private String getDeviceInfo() {
        return Build.BRAND + "" + Build.DEVICE;
    }

    private String getVersionName() {

        PackageManager packageManager = Utils.getApp().getPackageManager();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(Utils.getApp().getPackageName(), 0);
            return packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }
}
