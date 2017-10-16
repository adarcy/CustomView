package com.lk.syxl.customview.http;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;

import com.lk.syxl.customview.http.utils.LogUtils;

import java.net.HttpURLConnection;

/**
 * 网络请求分发、执行类
 */
public class HTTPServer {

    private  static final String LOG_TAG = "REQUEST";

    public static final int RET_SUCC      =   0;
    public static final int RET_FAIL      =   1;

    public final static int Succ = 0;
    public final static int Error = -1;
    public final static int NetWorkException = 100000;  //调用网络请求异常
    public final static int NetWorkTimeout = 100101;  //调用网络请求超时
    public final static int HttpSatutsError = 100102;   //HTTP请求状态异常
    public final static int HttpRespNull = 100103;      //HTTP响应为空
    public final static int HttpRespParseError = 100104;//HTTP响应解析错误
    public final static int HttpRespError = 100105;//返回数据错误


    private Handler mCallHandler;
    private static final int MSG_REQUEST = 0;

    private HandlerThread mRequestHandlerThread = null;

    private static volatile HTTPServer instance;
    public static HTTPServer getInstance () {
        if (instance == null) {
            synchronized (HTTPServer.class) {
                if (instance == null) {
                    instance = new HTTPServer();
                    instance.init();
                }
            }
        }
        return instance;
    }

    private HTTPServer() {}

    public void init () {

        mRequestHandlerThread = new HandlerThread("HTTPServer");
        mRequestHandlerThread.start();
        mCallHandler = new Handler(mRequestHandlerThread.getLooper()) {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case MSG_REQUEST:
                        if(msg.obj != null && msg.obj instanceof HttpRequest){
                            executeRequest((HttpRequest)msg.obj);
                        }else{
                            LogUtils.d(LOG_TAG,msg.toString());
                        }
                        break;
                    default:
                        break;
                }
            }
        };
    }

    
    public void doRequest(HttpRequest request) {
        Message msg = mCallHandler.obtainMessage();
        msg.what = MSG_REQUEST;
        msg.obj = request;
        mCallHandler.sendMessage(msg);
    }

    private void executeRequest(final HttpRequest request){
        com.lk.syxl.customview.http.ThreadManager.getInstance().start(new Runnable() {
            @Override
            public void run() {
                executeRequestInExecutor(request);
            }
        });
    }
    private void executeRequestInExecutor(HttpRequest request){
        request.requestTime = System.currentTimeMillis() / 1000;

        String url = request.getUrl();
        LogUtils.w(LOG_TAG,"=======================================");
        LogUtils.w(LOG_TAG,request.getClass().toString());
        LogUtils.w(LOG_TAG,url);
        LogUtils.w(LOG_TAG,"=======================================");
        BaseConnection connection = null;
        if(url.startsWith("https:")){
            connection = new HTTPSConnection(url);
        }else{
            connection = new HTTPConnection(url);
        }

        String result = connection.doRequest(request);
        LogUtils.w(LOG_TAG,"=======================================");
        LogUtils.w(LOG_TAG,request.getClass().toString());
        LogUtils.w(LOG_TAG,result);
//        LogUtils.w(LOG_TAG, String.valueOf(connection.getResponseCode()));
//        LogUtils.w(LOG_TAG,connection.getResponseMessage());
        LogUtils.w(LOG_TAG,"=======================================");

        if(connection.getResponseCode() == HttpURLConnection.HTTP_OK){
            request.mHttpRequestHandler.onSuccess(connection.getResponseCode(), result);
        }else{
            if (TextUtils.isEmpty(result)) {
                LogUtils.e(LOG_TAG, request.getClass().getName());
                Log.e(LOG_TAG,"responseBody is null");
                if(TextUtils.isEmpty(connection.getResponseMessage())){
                    request.mHttpRequestHandler.onFailure(connection.getResponseCode(), "");
                }else{
                    request.mHttpRequestHandler.onFailure(connection.getResponseCode(),connection.getResponseMessage());
                }
            } else {
                request.mHttpRequestHandler.onSuccess(connection.getResponseCode(), result);
            }
        }
    }
}
