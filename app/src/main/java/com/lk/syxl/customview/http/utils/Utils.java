package com.lk.syxl.customview.http.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;

import java.util.List;
import java.util.Map;

/**
 * <pre>
 *     author: Blankj
 *     blog  : http://blankj.com
 *     time  : 16/12/08
 *     desc  : Utils初始化相关
 * </pre>
 */
public final class Utils {

    @SuppressLint("StaticFieldLeak")
    private static Context context;

    private Utils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 初始化工具类
     *
     * @param context 上下文
     */
    public static void init(@NonNull final Context context) {
        Utils.context = context.getApplicationContext();
    }

    /**
     * 获取ApplicationContext
     *
     * @return ApplicationContext
     */
    public static Context getContext() {
        if (context != null) {
            CookieSyncManager.createInstance(context);
            return context;
        }
        throw new NullPointerException("u should init first");
    }


    /**
     * 设置coockie
     * @param url
     * @param headerFields
     */
    public static void setCookies(String url, Map<String, List<String>> headerFields) {
        if (null == headerFields) {
            return;
        }
        List<String> cookies = headerFields.get("Set-Cookie");
        if (null == cookies) {
            return;
        }
        CookieSyncManager.getInstance().startSync();
        for (String cookie : cookies) {
            setCookie(url, cookie);
        }
        CookieSyncManager.getInstance().sync();
    }

    private static void setCookie(String url, String cookie) {
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
//        if(cookie.indexOf("Expires") < 0){
//            cookie = addExpireToCookie(cookie);
//        }
        cookieManager.setCookie(url, cookie);
    }

    public static String getCookie(String url){
        CookieManager cookieManager = CookieManager.getInstance();
        return cookieManager.getCookie(url);
    }

}
