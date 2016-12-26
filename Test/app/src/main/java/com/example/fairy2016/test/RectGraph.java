package com.example.fairy2016.test;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by Fairy2016 on 2016/12/15.
 */

public class RectGraph extends View implements View.OnClickListener{

    private int mCount = 10;
    private int offset = 15;
    private int mWidth = 40;
    private int mHeight = 800;
    private Paint mRectPaint;
    private boolean flag = true;

    public RectGraph(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOnClickListener(this);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mRectPaint = new Paint();
        mRectPaint.setColor(Color.GRAY);
        mRectPaint.setStyle(Paint.Style.FILL);
        mRectPaint.setTextAlign(Paint.Align.CENTER);
        LinearGradient mLinearGradient = new LinearGradient(0, 0, mWidth, mHeight,
                Color.GREEN, Color.rgb(255,170,0), Shader.TileMode.CLAMP);
        mRectPaint.setShader(mLinearGradient);
        //一定要注意，这里的四个参数left,top,right,bootom均为坐标，都是指向X/Y正向
        for(int i=0; i<mCount; i++) {
            canvas.drawRect(mWidth*i+offset*i, getCurrentHeight(), mWidth*(i+1)+offset*i, mHeight, mRectPaint);
        }
        super.onDraw(canvas);
        canvas.restore();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measureWidth(widthMeasureSpec), measureHeight(heightMeasureSpec));
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

    }

    public int measureWidth(int widthMeasureSpec) {
        int size = MeasureSpec.getSize(widthMeasureSpec);
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        int result;
        if(mode == MeasureSpec.EXACTLY) {
            result = size;
        } else {
            result = getDefaultWidth();
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
            result = mHeight;
            if(mode == MeasureSpec.AT_MOST) {
                result = Math.min(size, result);
            }
        }
        Log.e("height", result+"");
        return result;
    }

    public int getDefaultWidth() {
        return (mCount)*mWidth+(mCount-1)*offset;
    }

    public float getCurrentHeight() {
        float mCurrentHeight = (float)Math.random()*mHeight;
        if(flag) {
            postInvalidateDelayed(500);
        }
        return mCurrentHeight;
    }

    @Override
    public void onClick(View view) {
        flag = !flag;
        if(flag) {
            postInvalidateDelayed(500);
        }
    }
}
