package com.lk.syxl.customview.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.lk.syxl.customview.R;
import com.lk.syxl.customview.http2.RequestManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by likun on 2017/10/17.
 */

public class HttpActivity extends AppCompatActivity {

    @BindView(R.id.bt_get)
    Button btGet;
    @BindView(R.id.bt_http)
    Button btHttp;
    @BindView(R.id.tv_content)
    TextView tvContent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.bt_get, R.id.bt_http})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_get:
                RequestManager.get("https://www.baidu.com/", "baidu", null, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        tvContent.setText(response);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                break;
            case R.id.bt_http:
                break;
        }
    }
}
