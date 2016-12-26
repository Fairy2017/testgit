package com.example.fairy2016.test;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Fairy2016 on 2016/12/8.
 */

public class TestTextView extends TextView{
    //private Paint mPaint;
    private int mViewWidth;
    private LinearGradient mLinearGradient;
    private Matrix mGradientMatrix;
    private int mTranslate;


    public TestTextView(Context context) {
        super(context);
    }

    public TestTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TestTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint mPaint1 = new Paint();
        mPaint1.setColor(ContextCompat.getColor(getContext(), android.R.color.holo_blue_light));
        mPaint1.setStyle(Paint.Style.FILL);
        Paint mPaint2 = new Paint();
        mPaint2.setColor(Color.YELLOW);
        mPaint2.setStyle(Paint.Style.FILL);

        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaint1);
        canvas.drawRect(10, 10, getMeasuredWidth()-10, getMeasuredHeight()-10, mPaint2);
        canvas.save();
        canvas.translate(10, 0);
        super.onDraw(canvas);
        /*if(mGradientMatrix != null) {
            mTranslate += mViewWidth/5;
            if(mTranslate > 2*mViewWidth) {
                mTranslate -= mViewWidth;
            }
            mGradientMatrix.setTranslate(mTranslate, 0);
            mLinearGradient.setLocalMatrix(mGradientMatrix);
            postInvalidateDelayed(100);
        }*/
        canvas.restore();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(MeasureWidth(widthMeasureSpec), MeasureHeight(heightMeasureSpec));
    }

    private int MeasureWidth(int widthMeasureSpec) {
        int result = 0;
        int measureMode = MeasureSpec.getMode(widthMeasureSpec);
        int measureSize = MeasureSpec.getSize(widthMeasureSpec);
        if(measureMode == MeasureSpec.EXACTLY) {
            result = measureSize;
        } else {
            result = 200;
            if(measureMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, measureSize);
            }
        }
        return result;
    }
    private int MeasureHeight(int heightMeasureSpec) {
        int result = 0;
        int measureMode = MeasureSpec.getMode(heightMeasureSpec);
        int measureSize = MeasureSpec.getSize(heightMeasureSpec);
        if(measureMode == MeasureSpec.EXACTLY) {
            result = measureSize;
        } else {
            result = 100;
            if(measureMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, measureSize);
            }
        }
        return result;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Paint mPaint;
        if(mViewWidth == 0) {
            mViewWidth = getMeasuredWidth();
            if(mViewWidth > 0) {
                mPaint = new Paint();
                mLinearGradient = new LinearGradient(0, 0, mViewWidth, 0, new int[] {
                   Color.BLUE, 0xffffffff, Color.BLUE}, null, Shader.TileMode.CLAMP);
                mPaint.setShader(mLinearGradient);
                mGradientMatrix = new Matrix();
            }
        }
    }
}
