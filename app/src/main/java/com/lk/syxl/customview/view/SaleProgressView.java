package com.lk.syxl.customview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.lk.syxl.customview.R;

import java.text.DecimalFormat;

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
    private int textColor;
    private Bitmap bgBitmap;
    private Bitmap fgBitmap;
    private Bitmap srcBitmap;
    private Bitmap srcBitmapFg;
    private Paint srcPaint;
    private PorterDuffXfermode xfermode;
    private Paint textPaint;
    private float textSize;
    private float baseLineY;
    private float nearOverTextWidth;
    private float overTextWidth;
    private String overText;
    private String nearOverText;
    private boolean isNeedAnim;

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
        slideColor = array.getColor(R.styleable.SaleProgressView_slideColor,0xffff3c32);
        textColor = array.getColor(R.styleable.SaleProgressView_textColor,0xffff3c32);
        slideWidth = array.getDimension(R.styleable.SaleProgressView_slideWidth,dp2px(2));
        overText = array.getString(R.styleable.SaleProgressView_overText);
        nearOverText = array.getString(R.styleable.SaleProgressView_nearOverText);
        textSize = array.getDimension(R.styleable.SaleProgressView_textSize,sp2px(16));
        isNeedAnim = array.getBoolean(R.styleable.SaleProgressView_isNeedAnim,true);

        array.recycle();
    }

    private void initPaint() {
        slidePaint = new Paint();
        slidePaint.setColor(slideColor);
        slidePaint.setStyle(Paint.Style.STROKE);
        slidePaint.setAntiAlias(true);

        srcPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setTextSize(textSize);

        xfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
        nearOverTextWidth = textPaint.measureText(nearOverText);
        overTextWidth = textPaint.measureText(overText);
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

        if (baseLineY == 0.0f) {
            Paint.FontMetricsInt fm = textPaint.getFontMetricsInt();
            baseLineY = height / 2 - (fm.descent / 2 + fm.ascent / 2);
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
        drawBG(canvas);
        //fg
        drawFG(canvas);

//        String scaleText = new DecimalFormat("0").format(scale*100) + "%";
        String scaleText = new DecimalFormat("#%").format(scale) + "%";//格式化  否则会有59。9999999
        String saleText = String.format("以抢购%s件",currentCount);

        float scaleTextWidth = textPaint.measureText(scaleText);
        Bitmap textBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas textCanvas = new Canvas(textBitmap);
        textPaint.setColor(textColor);

        if (scale < 0.8f) {
            textCanvas.drawText(saleText, dp2px(10), baseLineY, textPaint);
            textCanvas.drawText(scaleText, width - scaleTextWidth - dp2px(10), baseLineY, textPaint);
        } else if (scale < 1.0f) {
            textCanvas.drawText(nearOverText, width / 2 - nearOverTextWidth / 2, baseLineY, textPaint);
            textCanvas.drawText(scaleText, width - scaleTextWidth - dp2px(10), baseLineY, textPaint);
        } else {
            textCanvas.drawText(overText, width / 2 - overTextWidth / 2, baseLineY, textPaint);
        }

        textPaint.setXfermode(xfermode);
        textPaint.setColor(Color.WHITE);
        textCanvas.drawRoundRect(
                new RectF(slideWidth, slideWidth, (width - slideWidth) * scale, height - slideWidth),
                radius, radius, textPaint);
        canvas.drawBitmap(textBitmap, 0, 0, null);
        textPaint.setXfermode(null);
    }

    private void drawFG(Canvas canvas) {
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

    private void drawBG(Canvas canvas) {
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
    }

    public void setTotalAndCurrent(int totalCount, int currentCount) {
        this.totalCount = totalCount;
        if (currentCount > totalCount){
            currentCount = totalCount;
        }
        this.currentCount = currentCount;
        postInvalidate();
    }

    private int dp2px(float dp){
        float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dp*scale+0.5f);
    }

    private  int sp2px(float sp){
        float scale = getContext().getResources().getDisplayMetrics().scaledDensity;
        return (int) (sp*scale+0.5f);
    }


}
