package com.example.fairy2016.test;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;

import java.util.Calendar;

/**
 * Created by Fairy2016 on 2016/12/15.
 */

public class Clock  extends View{

    //private int mCircleXY = 400;
    private int mRadius = 400;
    private int mCircleWidth = 40;
    private int offset = 30;
    private int mBigLine = 50;
    private int mSmallLine = 25;
    private Point points[];
    private boolean isRunning;
    private Runnable clockRunnable;
    private Handler mHandler;
    public Clock(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    private void init() {
        mHandler = new Handler();
        clockRunnable = new Runnable() {
            @Override
            public void run() {
                postInvalidateDelayed(1000);
                mHandler.post(clockRunnable);
            }
        };
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(!isRunning) {
            runClock();
            return;
        }
        Paint mCirclePaint = new Paint();
        mCirclePaint.setStyle(Paint.Style.STROKE);
        mCirclePaint.setStrokeWidth(mCircleWidth);
        mCirclePaint.setAntiAlias(true);
        mCirclePaint.setColor(Color.rgb(255, 170, 0));
        canvas.drawCircle(getSuitableSize(), getSuitableSize(), getSuitableSize()-offset, mCirclePaint);
        Paint mDegreePaint = new Paint();
        mDegreePaint.setColor(Color.GRAY);
        canvas.rotate(6, getSuitableSize(), getSuitableSize());
        mDegreePaint.setTextSize(50);
        getPoints(mDegreePaint);
        for(int i=1; i<=60; i++) {
            if(i%5 == 0) {
                mDegreePaint.setStrokeWidth(5);
                canvas.drawLine(getSuitableSize(), offset, getSuitableSize(), offset+mBigLine, mDegreePaint);
                //canvas.drawText(String.valueOf(i/5), getSuitableSize()-mDegreePaint.measureText(String.valueOf(i))/2, offset+mBigLine+50, mDegreePaint);
            } else {
                mDegreePaint.setStrokeWidth(3);
                canvas.drawLine(getSuitableSize(), offset, getSuitableSize(), offset+mSmallLine, mDegreePaint);
            }
            canvas.save();
            canvas.rotate(6, getSuitableSize(), getSuitableSize());
        }
        canvas.restore();
        for(int i = 0; i<points.length;i++) {
            String s = String.valueOf(3*i);
            if(i == 0) {
                s = String.valueOf(12);
            }
            canvas.drawText(s, points[i].x, points[i].y, mDegreePaint);
        }
        canvas.save();
        canvas.translate(getSuitableSize(), getSuitableSize());
        mCirclePaint.setStrokeWidth(10);
        canvas.drawCircle(0, 0, 20, mCirclePaint);
        Paint bounds = new Paint();
        bounds.setStrokeWidth(10);
        bounds.setColor(Color.RED);
        Calendar ca = Calendar.getInstance();
        int hour = ca.get(Calendar.HOUR);
        int minute = ca.get(Calendar.MINUTE);
        int seconds = ca.get(Calendar.SECOND);
        float secondRotate = seconds*6.0f;
        float minuteRotate = minute*6.0f + seconds/60*6.0f;
        float hourRotate = hour*30.0f + minute/60.0f*30.0f + seconds/3600.0f*30.0f;
        canvas.save();
        canvas.rotate(secondRotate, 0, 0);
        canvas.drawLine(0, 0, 0, -280, bounds);
        canvas.restore();
        canvas.rotate(minuteRotate, 0, 0);
        bounds.setStrokeWidth(15);
        bounds.setColor(Color.BLACK);
        canvas.drawLine(0, 0, 0, -220, bounds);
        canvas.restore();

        canvas.translate(getSuitableSize(), getSuitableSize());
        canvas.save();
        canvas.rotate(hourRotate, 0, 0);
        bounds.setStrokeWidth(20);
        bounds.setColor(Color.BLACK);
        canvas.drawLine(0, 0, 0, -180, bounds);
        canvas.restore();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //super.onMeasure(widthMeasureSpec,heightMeasureSpec);
        setMeasuredDimension(measureWidth(widthMeasureSpec),measureHeight(heightMeasureSpec));
    }

    public int measureWidth(int widthMeasureSpec) {
        int size = MeasureSpec.getSize(widthMeasureSpec);
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        int result;
        if(mode == MeasureSpec.EXACTLY) {
            result = size;
        } else {
            result = 2*mRadius;
            if(mode == MeasureSpec.AT_MOST) {
                result = Math.min(size, result);
            }
        }
        return result;
    }
    public int measureHeight(int heightMeasureSpec) {
        int size = MeasureSpec.getSize(heightMeasureSpec);
        int mode = MeasureSpec.getMode(heightMeasureSpec);
        int result;
        if(mode == MeasureSpec.EXACTLY) {
            result = size;
        } else {
            result = 2*mRadius;
            if(mode == MeasureSpec.AT_MOST) {
                result = Math.min(size, result);
            }
        }
        return result;
    }

    public int getSuitableSize() {
        return getMeasuredWidth()<getMeasuredHeight()?getMeasuredWidth()/2:getMeasuredHeight()/2;
    }

    public void getPoints(Paint paint) {
        if(points == null) {
            points = new Point[4];
            for(int i=0; i< points.length; i++) {
                points[i] = new Point();
            }
        }
        points[0].x = getSuitableSize()- paint.measureText("12")/2;
        points[0].y = offset+mBigLine+50;
        points[1].x = 2*getSuitableSize()-offset-mBigLine-50;
        points[1].y = getSuitableSize()+paint.measureText("3")/2;
        points[2].x = getSuitableSize()-paint.measureText("6")/2;
        points[2].y = 2*getSuitableSize()-mBigLine-50;
        points[3].x = offset+mBigLine+50;
        points[3].y = getSuitableSize()+paint.measureText("9")/2;
    }

    class Point {
        float x;
        float y;
    }

    private void runClock() {
        isRunning = true;
        mHandler.post(clockRunnable);
    }
}
