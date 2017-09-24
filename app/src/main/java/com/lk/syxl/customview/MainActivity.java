package com.lk.syxl.customview;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Toast;

import com.lk.syxl.customview.entity.PieData;
import com.lk.syxl.customview.utils.DeviceUtils;
import com.lk.syxl.customview.view.PieView;
import com.lk.syxl.customview.view.SaleProgressView;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";

    private ArrayList<PieData> mData = new ArrayList<>();
    private RelativeLayout mMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final SaleProgressView saleProgressView = (SaleProgressView) findViewById(R.id.saleProgressView);
        SeekBar seekBar = (SeekBar) findViewById(R.id.seekbar);
        Button start = (Button) findViewById(R.id.bt_start);
        Button stop = (Button) findViewById(R.id.bt_stop);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                saleProgressView.setTotalAndCurrent(100,progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

            start.setOnClickListener(this);
            stop.setOnClickListener(this);
//        boolean isBg = DeviceUtils.isApplicationBroughtToBackground(this);
//        if (isBg){
//            Toast.makeText(this,"application is background",Toast.LENGTH_SHORT).show();
//        }else {
//            Toast.makeText(this,"application is not background",Toast.LENGTH_SHORT).show();
//        }

//        mMain = (RelativeLayout) findViewById(R.id.activity_main);
//
//        PieView pie = new PieView(this);
//        mData.add(new PieData("a",12));
//        mData.add(new PieData("b",112));
//        mData.add(new PieData("c",42));
//        mData.add(new PieData("d",62));
//        mData.add(new PieData("e",82));
//        mData.add(new PieData("f",32));
//        pie.setStartAngle(0);
//        pie.setData(mData);
//        mMain.addView(pie);

//        checkIsBackground();


    }

    /**
     * 应用是否在前台
     */
    private void checkIsBackground() {
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                boolean isBg = DeviceUtils.isApplicationBroughtToBackground(MainActivity.this);
                if (isBg){
                    Log.e(TAG,"application is background");
                }else {
                    Log.e(TAG,"application is not background");
                }
            }
        };
        timer.schedule(timerTask,5000,5000);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_start:
                break;
            case R.id.bt_stop:
                break;
        }
    }
}
