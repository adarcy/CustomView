package com.lk.syxl.customview.utils;

import android.os.Handler;
import android.os.Looper;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by likun on 2017/10/11.
 */

public class HttpUtils {

    String mUrl;
    Map<String,String> prarams;
    HttpResponse httpResponse;
    Handler mHandler = new Handler(Looper.getMainLooper());

    private static final OkHttpClient client = new OkHttpClient();

    public interface HttpResponse{
        void onSuccess(Object obj);
        void onError(String error);
    }

    public HttpUtils(HttpResponse response){
        httpResponse = response;
    }

    public void sendPostRequest(String url, Map<String,String> praram){
        sendHttp(url,praram,true);
    }

    public void sendGetRequest(String url,Map<String,String> praram){
        sendHttp(url,praram,false);
    }

    public void sendHttp(String url, Map<String,String> praram,boolean isPost){
        mUrl =url;
        prarams = praram;

        run(isPost);
    }

    private void run(boolean isPost){
        final Request request = createRequest(isPost);
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        httpResponse.onError("请求失败");
                    }
                });
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (request != null) {
                            if (response.isSuccessful()){
                                try {
                                    httpResponse.onSuccess(response.body().string());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                    httpResponse.onError("请求失败"+response);
                                }
                            }else {
                                httpResponse.onError("请求失败"+response);
                            }
                        }else {
                            return;
                        }
                    }
                });
            }
        });
    }

    private Request createRequest(boolean isPost){

        Request request;
        if (isPost){
            MultipartBody.Builder builder = new MultipartBody.Builder();
            builder.setType(MultipartBody.FORM);
            Iterator<Map.Entry<String,String>> iterator = prarams.entrySet().iterator();
            while (iterator.hasNext()){
                Map.Entry<String,String> entry = iterator.next();
                builder.addFormDataPart(entry.getKey(),entry.getValue());
            }
            request = new Request.Builder()
                    .url(mUrl)
                    .post(builder.build())
                    .build();
        }else {
            StringBuilder builder = new StringBuilder();
            Iterator<Map.Entry<String,String>> iterator = prarams.entrySet().iterator();
            while (iterator.hasNext()){
                Map.Entry<String,String> entry = iterator.next();
                builder.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
            }
            String para = builder.toString().substring(0,builder.length()-1);

            request = new Request.Builder()
                    .url(mUrl +"?"+para)
                    .build();
        }

        return request;
    }
}
