package com.lk.syxl.customview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.icu.text.DecimalFormat;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.lk.syxl.customview.R;

/**
 * Created by likun on 2017/9/24.
 */

public class SaleProgressView extends View {


    private int totalCount;
    private int currentCount;
    private float scale;
    private int width;
    private int height;
    private float radius;
    private float slideWidth;
    private RectF bgRectF;
    private Paint slidePaint;
    private int slideColor;
    private Bitmap bgBitmap;
    private Bitmap fgBitmap;
    private Bitmap srcBitmap;
    private Bitmap srcBitmapFg;
    private Paint srcPaint;
    private PorterDuffXfermode xfermode;

    public SaleProgressView(Context context) {
        super(context);
    }

    public SaleProgressView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initAttrs(context,attrs);
        initPaint();
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.SaleProgressView);
        slideColor = array.getColor(R.styleable.SaleProgressView_slideColor, 0xff123456);
        slideWidth = array.getDimension(R.styleable.SaleProgressView_slideWidth, 2);
    }

    private void initPaint() {
        slidePaint = new Paint();
        slidePaint.setColor(slideColor);
        slidePaint.setStyle(Paint.Style.STROKE);
        slidePaint.setAntiAlias(true);

        srcPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        xfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = getMeasuredWidth();
        height = getMeasuredHeight();
        radius = height /2.0f;

        if (bgRectF == null){
            bgRectF = new RectF(slideWidth,slideWidth,width-slideWidth,height-slideWidth);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (totalCount == 0){
            scale = 0.0f;
        }else {
            scale = currentCount/ (float) totalCount;
        }

        //边框
        canvas.drawRoundRect(bgRectF,radius,radius,slidePaint);

        //bg
        if (bgBitmap == null) {
            bgBitmap = Bitmap.createBitmap(width,height, Bitmap.Config.ARGB_8888);
        }
        Canvas bgCav = new Canvas(bgBitmap);
        if (srcBitmap == null) {
            srcBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.bg);
        }
        bgCav.drawRoundRect(bgRectF,radius,radius,srcPaint);
        srcPaint.setXfermode(xfermode);
        bgCav.drawBitmap(srcBitmap,null,bgRectF,srcPaint);
        canvas.drawBitmap(bgBitmap,0,0,null);
        srcPaint.setXfermode(null);

        //fg
        if (fgBitmap == null) {
            fgBitmap = Bitmap.createBitmap(width,height, Bitmap.Config.ARGB_8888);
        }
        Canvas fgCav = new Canvas(fgBitmap);
        if (srcBitmapFg == null) {
            srcBitmapFg = BitmapFactory.decodeResource(getResources(), R.mipmap.fg);
        }
        fgCav.drawRoundRect(new RectF(slideWidth,slideWidth,(width-slideWidth)*scale,height-slideWidth),
                radius,radius,srcPaint);
        srcPaint.setXfermode(xfermode);
        fgCav.drawBitmap(srcBitmapFg,null,bgRectF,srcPaint);
        canvas.drawBitmap(fgBitmap,0,0,null);
        srcPaint.setXfermode(null);
    }

    public void setTotalAndCurrent(int totalCount, int currentCount) {
        this.totalCount = totalCount;
        if (currentCount > totalCount){
            currentCount = totalCount;
        }
        this.currentCount = currentCount;
        postInvalidate();
    }
}
