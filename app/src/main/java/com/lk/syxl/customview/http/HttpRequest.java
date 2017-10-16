package com.lk.syxl.customview.http;


import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;

import com.lk.syxl.customview.http.utils.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 *
 * 网络请求实例类的基类，所有具体的网络请求都是他的实现，他与{@link HttpResponse}一般成对使用
 *
 */

public abstract class HttpRequest {

	private  static final String LOG_TAG = "HttpRequest";


	public static final String HTTP_REQ_ENTITY_MERGE = "=";
	public static final String HTTP_REQ_ENTITY_JOIN = "&";

	protected static String HTTP_DOMAIN = "https://crm.xiaoshouyi.com/";
	protected String path = "";
	protected long requestTime = 0;
	public byte[] data = null;
	public HashMap<String,String> cookieInfo = new HashMap<>();
	private String scode;

	protected abstract String getUrl();

	protected abstract void onRequestSuccess(int statusCode, String responseJson);

	protected abstract void onRequestFailure(int statusCode, String errorResponse);

	protected HttpRequest(){

	}

	protected HttpRequest(String path){
		setPath(path);
	}

	protected void setPath(String path){
		if(TextUtils.isEmpty(path)){
			Log.d(LOG_TAG,"path is null");
			this.path = "";
		}else{
			this.path = path;
		}
	}

	private String getDomain() {
		return HTTP_DOMAIN;
    }

	protected String getBaseUrl() {
		HashMap<String, String> globalParams = getGlobalParams();
		String url = getDomain() + path;
		return appendParams(url,globalParams);
	}

	public com.lk.syxl.customview.http.HTTPRequestHandler mHttpRequestHandler = new com.lk.syxl.customview.http.HTTPRequestHandler() {

		@Override
		public void onSuccess(int statusCode,String response) {
			try {
				JSONObject jsonObject = new JSONObject(response);
				scode = jsonObject.optString("scode");
				if (!"0".equals(scode)){
					statusCode = com.lk.syxl.customview.http.HTTPServer.HttpRespError;
					onRequestFailure(statusCode, response);
				}else {
					onRequestSuccess(statusCode,response);
				}
			} catch (JSONException e) {
				e.printStackTrace();
				statusCode = com.lk.syxl.customview.http.HTTPServer.HttpRespParseError;
				onRequestFailure(statusCode, e.toString());
			}
		}

		@Override
		public void onFailure(int statusCode, String responseBody) {
			onRequestFailure(statusCode, responseBody);
		}
	};

	protected String appendParams(String url, Map<String, String> params) {
		if (url == null || params == null || params.isEmpty()) {
			return url;
		}
		Uri.Builder builder = new Uri.Builder();

		Set<String> keys = params.keySet();
		Iterator<String> iterator = keys.iterator();
		while (iterator.hasNext()) {
			String key = iterator.next();
			builder.appendQueryParameter(key, params.get(key));
		}
		return url + builder.build().toString();
	}

	public HashMap<String, String> getGlobalParams() {
		HashMap<String, String> params = new HashMap<>();
		params.put("source", 1 + "");//1是android
		params.put("os", getOs());
		params.put("model", getDeviceInfo());
//		params.put("cache_key", user.getAuth().getIdentity()+"");//区分不同用户之间的缓存
		params.put("appType", "0");//区分是普通版还是快销版   0-普通版  1-快销版
//		params.put("_vs",getVersionName());
		params.put("_vs","1710");
		return params;
	}

	public static String getOs() {
		return Build.VERSION.SDK;
	}
	public static String getDeviceInfo() {
		return Build.BRAND + " " + Build.DEVICE;
	}
	public String getVersionName() {
		try {
			PackageManager manager = Utils.getContext().getPackageManager();
			PackageInfo info = null;
			String currentVersion = null;
			info = manager.getPackageInfo(Utils.getContext().getPackageName(), 0);
			currentVersion = info.versionName;
			return currentVersion;
		} catch (Exception e) {
		}

		return "";
	}
}
