package com.lk.syxl.customview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.lk.syxl.customview.entity.PieData;

import java.util.ArrayList;

/**
 * Description:
 *
 * @author: Administrator
 * @date: 2017/1/8 00:43
 */

public class PieView extends View {

    // 颜色表(注意: 此处定义颜色使用的是ARGB，带Alpha通道的)
    private int[] mColors = {0xFFCCFF00, 0xFF6495ED, 0xFFE32636, 0xFF800000, 0xFF808000, 0xFFFF8C69, 0xFF808080,
            0xFFE6B800, 0xFF7CFC00};
    // 饼状图初始绘制角度
    private float mStartAngle = 0;
    // 数据
    private ArrayList<PieData> mData;
    // 宽高
    private int mWidth;
    private int mHeight;
    // 画笔
    private Paint mPaint = new Paint();

    public PieView(Context context) {
        this(context,null);
    }

    public PieView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mData == null || mData.size() == 0){
            return;
        }

        float currentStartAngle = mStartAngle;
        canvas.translate(mWidth/2,mHeight/2);
        float r = (float) (Math.min(mWidth,mHeight)/2*0.8);
        RectF rectF = new RectF(-r,-r,r,r);
        for (int i = 0; i < mData.size(); i++) {
            PieData pieData = mData.get(i);
            float angle = pieData.getAngle();
            mPaint.setColor(pieData.getColor());
            canvas.drawArc(rectF,currentStartAngle,angle,true,mPaint);
            currentStartAngle += angle;
        }
    }

    public void setStartAngle(float startAngle){
        mStartAngle = startAngle;
        invalidate();
    }

    public void setData(ArrayList<PieData> data){
        mData = data;
        initData(mData);
        invalidate();
    }

    private void initData(ArrayList<PieData> data) {
        if (data == null || data.size() == 0){
            return;
        }
        float sumValue = 0;
        for (int i = 0; i < data.size(); i++) {
            PieData pieData = data.get(i);
            sumValue += pieData.getValue();
            int j = i%mColors.length;
            pieData.setColor(mColors[j]);
        }

        for (int i = 0; i < data.size(); i++) {
            PieData pieData = data.get(i);
            float percent = pieData.getValue()/sumValue;
            pieData.setPercent(percent);
            pieData.setAngle(percent*360);
        }
    }

}
