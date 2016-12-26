package com.example.fairy2016.test;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.Calendar;

/**
 * Created by Fairy2016 on 2016/12/19.
 */

public class Clock2 extends SurfaceView implements SurfaceHolder.Callback, Runnable{
    private SurfaceHolder mHolder;
    private Canvas mCanvas;
    private boolean mIsdrawing;

    private int mRadius = 400;
    private int mCircleWidth = 20;
    private int offset = 10;
    private int mBigLine = 50;
    private int mSmallLine = 25;
    private Point points[];
    private Handler handler;
    private boolean isRunning = false;

    public Clock2(Context context) {
        super(context);
        initView();
    }

    public Clock2(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public Clock2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        mIsdrawing = true;
        new Thread(this).start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        mIsdrawing = false;
    }

    private void initView() {
        mHolder = getHolder();
        mHolder.addCallback(this);
        setFocusable(true);
        setFocusableInTouchMode(true);
        this.setKeepScreenOn(true);
        setZOrderOnTop(true);
        mHolder.setFormat(PixelFormat.TRANSLUCENT);
        handler = new Handler();
    }

    @Override
    public void run() {
        while(mIsdrawing) {
            draw();
        }
    }

    private void draw() {
        try {
            mCanvas = mHolder.lockCanvas();
            Paint mCirclePaint = new Paint();
            mCirclePaint.setStyle(Paint.Style.STROKE);
            mCirclePaint.setStrokeWidth(mCircleWidth);
            mCirclePaint.setAntiAlias(true);
            mCirclePaint.setColor(Color.GREEN);
            mCanvas.drawCircle(getSuitableSize(), getSuitableSize(), getSuitableSize()-offset, mCirclePaint);
            Paint mDegreePaint = new Paint();
            mDegreePaint.setColor(Color.BLACK);
            mCanvas.rotate(6, getSuitableSize(), getSuitableSize());
            mDegreePaint.setTextSize(50);
            getPoints(mDegreePaint);
            for(int i=1; i<=60; i++) {
                if(i%5 == 0) {
                    mDegreePaint.setStrokeWidth(5);
                    mCanvas.drawLine(getSuitableSize(), offset, getSuitableSize(), offset+mBigLine, mDegreePaint);
                    //canvas.drawText(String.valueOf(i/5), getSuitableSize()-mDegreePaint.measureText(String.valueOf(i))/2, offset+mBigLine+50, mDegreePaint);
                } else {
                    mDegreePaint.setStrokeWidth(3);
                    mCanvas.drawLine(getSuitableSize(), offset, getSuitableSize(), offset+mSmallLine, mDegreePaint);
                }
                mCanvas.save();
                mCanvas.rotate(6, getSuitableSize(), getSuitableSize());
            }
            mCanvas.restore();
            for(int i = 0; i<points.length;i++) {
                String s = String.valueOf(3*i);
                if(i == 0) {
                    s = String.valueOf(12);
                }
                mCanvas.drawText(s, points[i].x, points[i].y, mDegreePaint);
            }
            mCanvas.restore();
            mCanvas.save();
            mCanvas.translate(getSuitableSize(), getSuitableSize());
            mCirclePaint.setStrokeWidth(10);
            mCanvas.drawCircle(0, 0, 20, mCirclePaint);
            Paint bounds = new Paint();
            bounds.setStrokeWidth(10);
            bounds.setColor(Color.RED);
            Calendar ca = Calendar.getInstance();
            mCanvas.save();
            mCanvas.rotate(ca.get(Calendar.SECOND)*6, 0, 0);
            mCanvas.drawLine(0, 0, 0, -280, bounds);
            mCanvas.restore();
        } catch (Exception e) {

        } finally {
            if(mCanvas != null) {
                mHolder.unlockCanvasAndPost(mCanvas);
            }
        }
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

    public int getSuitableSize() {
        return getMeasuredWidth()<getMeasuredHeight()?getMeasuredWidth()/2:getMeasuredHeight()/2;
    }

    class Point {
        float x;
        float y;
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
        Log.e("width", result+"");
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
        Log.e("height", result+"");
        return result;
    }
}
