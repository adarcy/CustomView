package com.lk.syxl.customview.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.lk.syxl.customview.R;
import com.lk.syxl.customview.view.BouncingBallView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by likun on 2017/9/29.
 */

public class BouncingBallViewActivity extends AppCompatActivity {
    @BindView(R.id.view)
    BouncingBallView view;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bouncing_ball_view);
        ButterKnife.bind(this);
    }
}
