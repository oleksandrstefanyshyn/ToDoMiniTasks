package com.globallogic.trainee.ostefanyshyn.todominitasks;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;

public class CustomView extends View {
    private float DEVICE_DENSITY_DPI;

    private float mSalarySize = 0;

    private Context mContext;
    private Paint mPaint;
    private Rect mRect;
    private DisplayMetrics mDisplayMetrics;
    private WindowManager mWindowManager;

    public CustomView(Context context) {
        super(context);
        init(context);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void setmSalarySize(float mSalarySize) {
        this.mSalarySize = mSalarySize;
    }

    private void init(Context context) {
        this.mContext = context;
        mPaint = new Paint();
        mRect = new Rect();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        deviceDensity();
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(5);

        canvas.drawLine(convertFloDpToPixel(45), convertFloDpToPixel(200), convertFloDpToPixel(45), convertFloDpToPixel(55), mPaint);
        canvas.drawLine(convertFloDpToPixel(45), convertFloDpToPixel(200), convertFloDpToPixel(200), convertFloDpToPixel(200), mPaint);

        mPaint.setTextSize(30);
        mRect.set(convertDpToPixel(60), convertDpToPixel(95), convertDpToPixel(80), convertDpToPixel(200));
        canvas.drawRect(mRect, mPaint);
        canvas.drawText("2014", convertFloDpToPixel(59), convertFloDpToPixel(210), mPaint);
        mRect.set(convertDpToPixel(90), convertDpToPixel(120), convertDpToPixel(110), convertDpToPixel(200));
        canvas.drawRect(mRect, mPaint);
        canvas.drawText("2015", convertFloDpToPixel(89), convertFloDpToPixel(210), mPaint);
        if (mSalarySize != 0) {
            mRect.set(convertDpToPixel(120), convertDpToPixel(calcSalarySize()), convertDpToPixel(140), convertDpToPixel(200));
        } else {
            mRect.set(convertDpToPixel(120), convertDpToPixel(75), convertDpToPixel(140), convertDpToPixel(200));
        }
        canvas.drawRect(mRect, mPaint);
        canvas.drawText("2016", convertFloDpToPixel(119), convertFloDpToPixel(210), mPaint);
        mRect.set(convertDpToPixel(150), convertDpToPixel(130), convertDpToPixel(170), convertDpToPixel(200));
        canvas.drawRect(mRect, mPaint);
        canvas.drawText("2017", convertFloDpToPixel(149), convertFloDpToPixel(210), mPaint);

        mPaint.setTextSize(30);
        canvas.drawLine(convertFloDpToPixel(40), convertFloDpToPixel(75), convertFloDpToPixel(50), convertFloDpToPixel(75), mPaint);
        canvas.drawText("4000", convertFloDpToPixel(15), convertFloDpToPixel(78), mPaint);
        canvas.drawLine(convertFloDpToPixel(40), convertFloDpToPixel(107), convertFloDpToPixel(50), convertFloDpToPixel(107), mPaint);
        canvas.drawText("3000", convertFloDpToPixel(15), convertFloDpToPixel(110), mPaint);
        canvas.drawLine(convertFloDpToPixel(40), convertFloDpToPixel(139), convertFloDpToPixel(50), convertFloDpToPixel(139), mPaint);
        canvas.drawText("2000", convertFloDpToPixel(15), convertFloDpToPixel(142), mPaint);
        canvas.drawLine(convertFloDpToPixel(40), convertFloDpToPixel(171), convertFloDpToPixel(50), convertFloDpToPixel(171), mPaint);
        canvas.drawText("1000", convertFloDpToPixel(15), convertFloDpToPixel(174), mPaint);

        mPaint.setTextSize(60);
        canvas.drawText("Year", convertFloDpToPixel(175), convertDpToPixel(220), mPaint);
        canvas.drawText("Salary, $", convertFloDpToPixel(10), convertDpToPixel(40), mPaint);

    }

    public int convertDpToPixel(float dp) {
        return (int) (dp * (DEVICE_DENSITY_DPI / 160f));
    }

    public float convertFloDpToPixel(float dp) {
        return (dp * (DEVICE_DENSITY_DPI / 160f));
    }

    protected void deviceDensity() {
        mDisplayMetrics = new DisplayMetrics();
        mWindowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        mWindowManager.getDefaultDisplay().getMetrics(mDisplayMetrics);
        DEVICE_DENSITY_DPI = mDisplayMetrics.densityDpi;
    }

    protected float calcSalarySize() {
        return 171 - (mSalarySize / 1000) * 32;
    }
}

