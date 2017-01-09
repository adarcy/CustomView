package com.lk.syxl.customview.foldinglayout;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.lk.syxl.customview.R;
import com.lk.syxl.customview.foldinglayout.view.FoldLayout;

public class FoldLayoutActivity extends AppCompatActivity {

    private FoldLayout mMFoldLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fold_layout);
        mMFoldLayout = (FoldLayout) findViewById(R.id.id_fold_layout);
    }
}
