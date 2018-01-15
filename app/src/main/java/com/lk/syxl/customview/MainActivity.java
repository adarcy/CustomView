package com.lk.syxl.customview;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;

import com.lk.syxl.customview.entity.PieData;
import com.lk.syxl.customview.foldinglayout.ShowFoldActivity;
import com.lk.syxl.customview.ui.BouncingBallViewActivity;
import com.lk.syxl.customview.ui.HttpActivity;
import com.lk.syxl.customview.ui.NumberBelongActivity;
import com.lk.syxl.customview.utils.DeviceUtils;
import com.lk.syxl.customview.utils.SPUtils;
import com.lk.syxl.customview.view.SaleProgressView;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    @BindView(R.id.saleProgressView)
    SaleProgressView saleProgressView;
    @BindView(R.id.seekbar)
    SeekBar seekbar;
    @BindView(R.id.bt_start)
    Button btStart;
    @BindView(R.id.bt_stop)
    Button btStop;
    @BindView(R.id.bt_day_night)
    Button btDayNight;
    @BindView(R.id.bt_BouncingBallView)
    Button btBouncingBallView;
    @BindView(R.id.activity_main)
    LinearLayout activityMain;
    @BindView(R.id.bt_number_belong)
    Button btNumberBelong;
    @BindView(R.id.bt_show_fild)
    Button btShowFild;
    @BindView(R.id.bt_http)
    Button btHttp;

    private ArrayList<PieData> mData = new ArrayList<>();
    private RelativeLayout mMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        final SaleProgressView saleProgressView = (SaleProgressView) findViewById(R.id.saleProgressView);
        SeekBar seekBar = (SeekBar) findViewById(R.id.seekbar);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                saleProgressView.setTotalAndCurrent(100, progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

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
                if (isBg) {
                    Log.e(TAG, "application is background");
                } else {
                    Log.e(TAG, "application is not background");
                }
            }
        };
        timer.schedule(timerTask, 5000, 5000);
    }

    @OnClick({R.id.bt_start, R.id.bt_stop, R.id.bt_day_night, R.id.bt_BouncingBallView})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_start:
                break;
            case R.id.bt_stop:
                break;
            case R.id.bt_day_night:
                //  获取当前模式
                int currentNightMode = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
                //  将是否为夜间模式保存到SharedPreferences
                SPUtils.getInstance().put("nightMode", currentNightMode == Configuration.UI_MODE_NIGHT_NO);
                //  切换模式
                getDelegate().setDefaultNightMode(currentNightMode == Configuration.UI_MODE_NIGHT_NO ?
                        AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO);
                //  重启Activity recreate()有很多坑使用的时候注意，
//                recreate();
                //解决闪屏问题，从新启动当前界面 设置渐进渐出动画
                startActivity(new Intent(this, MainActivity.class));
                overridePendingTransition(R.anim.animo_alph_open, R.anim.animo_alph_close);
                finish();
                break;
            case R.id.bt_BouncingBallView:
                Intent intent = new Intent(this, BouncingBallViewActivity.class);
                startActivity(intent);
                break;
        }
    }

    @OnClick(R.id.bt_number_belong)
    public void onViewClicked1() {
        Intent intent = new Intent(this, NumberBelongActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.bt_show_fild)
    public void onViewClicked2() {
        Intent intent = new Intent(this, ShowFoldActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.bt_http)
    public void onViewClicked3() {
        Intent intent = new Intent(this, HttpActivity.class);
        startActivity(intent);
    }


    @OnClick(R.id.bt_palette)
    public void onViewClicked4() {
        Intent intent = new Intent(this, PaletteActivity.class);
        startActivity(intent);
    }
}
