package com.lk.syxl.customview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.lk.syxl.customview.R;

/**
 * Created by likun on 2017/9/29.
 */

public class BouncingBallView extends View {

    private static final String TAG = "BouncingBallView";
    public static final double PI = 3.14159265358979323846;

    private int mWidth = 300;//view的宽
    private int mHeight = 300;//view的高
    private int largeRadius;//(mWidth / 2 - ballRadius)

    private float wabbyBallX;//摇动中的小球x坐标
    private float wabbyBallY;//摇动中的小球y坐标
    private float wabbyBallStartAngle;//摇动的小球开始摇动的角度
    private float wabbyBallAngle;//摇动中小球的角度

    private float runningBallX;//转动小球的x坐标
    private float runningBallY;//转动小球的y坐标
    private float runningBallStartAngle;//转动小球开始转动的角度
    private float runningBallAngle;//转动中小球的角度

    private float perAngle;//将圆均分的角度
    private float restBallStartAngle = 60;//剩下的五个小球的开始分布的角度
    private float phaseAngle;//当两个小球碰撞时他们之间相差的角度

    private Paint mPaint;

    private STATUS currentStatus = STATUS.FIRSTCYCLE;

    private BouncingBallConfig config;//保存小球配置的类

    private enum STATUS {//运动状态的集合
        FIRSTCYCLE,
        RESTCYCLE
    }

    public BouncingBallView(Context context) {
        this(context,null);
    }

    public BouncingBallView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public BouncingBallView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        BouncingBallConfig config = new BouncingBallConfig();

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.BouncingBallView);
        config.ballCount = ta.getInteger(R.styleable.BouncingBallView_ballCount,6);
        config.ballColor = ta.getColor(R.styleable.BouncingBallView_ballColor, Color.BLUE);
        config.ballRadius = ta.getDimensionPixelSize(R.styleable.BouncingBallView_ballRadius,10);
        config.cycleTime = ta.getInteger(R.styleable.BouncingBallView_cycleTime, 1000);

        init(config);

    }

    public void init(BouncingBallConfig config) {
        this.config = config;
        runningBallAngle = 0;
        wabbyBallStartAngle = 0;
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(config.ballColor);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);
        if (widthMeasureSpec == MeasureSpec.EXACTLY){
            mWidth = widthSpecSize - getPaddingLeft() - getPaddingRight();
        }
        if (heightMeasureSpec == MeasureSpec.EXACTLY){
            mHeight = heightSpecSize - getPaddingTop() - getPaddingBottom();
        }
        setMeasuredDimension(mWidth,mHeight);

        largeRadius = mWidth/2 - config.ballRadius;
        perAngle = 360/config.ballCount;
        //计算转过的弧度
        double a = config.ballRadius/largeRadius;
        phaseAngle = (float) (Math.asin(a)*2*180/PI);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (float i = restBallStartAngle; i <perAngle*(config.ballCount - 1)+restBallStartAngle ; i+=perAngle) {
            double x = Math.sin(i * 1.0f / 180 * PI) * largeRadius + mWidth / 2;
            double y = Math.cos(i * 1.0f / 180 * PI) * largeRadius + mHeight / 2;
            //Log.e(TAG, "Math.sin = " + Math.sin(i * 1.0f) * largeRadius);
            //Log.e(TAG, "Math.cos = " + Math.cos(i * 1.0f) * largeRadius);
            //Log.e(TAG, "x = " + x);
            //Log.e(TAG, "y = " + y);
            canvas.drawCircle((float) x, (float) y, config.ballRadius, mPaint);
        }
    }
}
