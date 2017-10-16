package com.lk.syxl.customview.http.utils;

import android.text.TextUtils;
import android.util.Log;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class LogUtils {

    static long startTime;
    private static final String LINE_SEPARATOR = System.getProperty("line.separator");
    private static final int MAX_LEN = 3500;

    private LogUtils() {
    }

    public static void e(String tag, String msg) {
        if (BuildConfig.debug && !TextUtils.isEmpty(tag) && !TextUtils.isEmpty(msg)){
            Log.e(tag, msg);
        }
    }

    public static void e(String tag, String msg, Throwable tr) {
        if (BuildConfig.debug)
            Log.e(tag, msg, tr);
    }

    public static void i(String tag, String msg) {
        if (BuildConfig.debug)
            Log.i(tag, msg);
    }

    public static void i(String tag, String msg, Throwable tr) {
        if (BuildConfig.debug)
            Log.i(tag, msg, tr);
    }

    public static void d(String tag, String msg) {
        if (BuildConfig.debug)
            Log.d(tag, msg);
    }

    public static void d(String tag, String msg, Throwable tr) {
        if (BuildConfig.debug)
            Log.d(tag, msg, tr);
    }

    public static void w(String tag, String msg) {
        if (BuildConfig.debug)
            Log.w(tag, msg);
    }

    public static void w(String tag, String msg, Throwable tr) {
        if (BuildConfig.debug)
            Log.w(tag, msg, tr);
    }


    public static void setStartTime(){
        if (BuildConfig.debug){
            startTime = System.currentTimeMillis();
        }
    }
    public static void getTakeTime(String str){
        if(startTime != 0&&BuildConfig.debug){
            String takeTime = String.valueOf(System.currentTimeMillis() - startTime);
            if(str!=null){
                e("takeTime:",str+" = "+ takeTime);
            }else{
                e("takeTime:",takeTime);
            }
        }
    }
    public static void getTakeTimeAndStart(String str){
        getTakeTime(str);
        setStartTime();
    }


    /**
     * 边界线
     * @param tag
     * @param isTop  顶部还是底部
     */
    public static void printLine(String tag, boolean isTop) {
        if (isTop) {
            i(tag, "╔═══════════════════════════════════════════════════════════════════════════════════════");
        } else {
            i(tag, "╚═══════════════════════════════════════════════════════════════════════════════════════");
        }
    }

    /**
     * 打印json数据
     * @param tag  tag标志
     * @param msg   json字符串
     * @param headString   第一行的信息
     */
    public static void printJson(String tag, String msg, String headString) {

        String message;

        try {
            if (msg != null) {
                if (msg.startsWith("{")) {
                    JSONObject jsonObject = new JSONObject(msg);
                    message = jsonObject.toString(4);//主要靠这个方法格式化json
                } else if (msg.startsWith("[")) {
                    JSONArray jsonArray = new JSONArray(msg);
                    message = jsonArray.toString(4);
                } else {
                    message = msg;
                }
            }else {
                message = "";
            }
        } catch (JSONException e) {
            message = msg;
        }

        printLine(tag, true);
        message = headString + LINE_SEPARATOR + message;
        int len = message.length();
        int countOfSub = len / MAX_LEN;
        if (countOfSub > 0) {
            i(tag, "║ " + message.substring(0, MAX_LEN));
            String sub;
            int index = MAX_LEN;
            for (int i = 1; i <= countOfSub; i++) {
                if (i == countOfSub){
                    sub = message.substring(index);
                    i(tag, "part "+ i + LINE_SEPARATOR + sub);
                }else {
                    sub = message.substring(index, index + MAX_LEN);
                    i(tag, "part "+ i + LINE_SEPARATOR + sub);
                    index += MAX_LEN;
                }

            }
        }else {
            i(tag, "║ " + message);
        }

        printLine(tag, false);
    }
}
