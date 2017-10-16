package com.lk.syxl.customview.http;


import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;

/**
 * HTTPS 请求类
 */
public class HTTPSConnection extends BaseConnection {

    private HttpsURLConnection mConn = null;
    private SSLContext mSSLContext = null;

    public HTTPSConnection(String url) {
        super();
        TrustManager tm = null;
        try {
            tm = com.lk.syxl.customview.http.MyX509TrustManager.getInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            mSSLContext = SSLContext.getInstance("TLS");
            mSSLContext.init(null, new TrustManager[] { tm }, null);
            mConn.setDefaultSSLSocketFactory(mSSLContext.getSocketFactory());
            HostnameVerifier verifier = new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            };
            mConn = (HttpsURLConnection)new URL(url).openConnection();
            mConn.setHostnameVerifier(verifier);
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected HttpURLConnection getURLConnection() {
        return mConn;
    }

}
