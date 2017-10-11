package com.lk.syxl.customview.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.lk.syxl.customview.R;
import com.lk.syxl.customview.view.BouncingBallView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by likun on 2017/9/29.
 */

public class BouncingBallViewActivity extends AppCompatActivity {
    @BindView(R.id.view)
    BouncingBallView view;
    @BindView(R.id.bt_start)
    Button btStart;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bouncing_ball_view);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.bt_start)
    public void onViewClicked() {
        view.letUsAnimate();
    }
}
