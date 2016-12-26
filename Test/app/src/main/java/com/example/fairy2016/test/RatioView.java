package com.example.fairy2016.test;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Fairy2016 on 2016/12/9.
 */

public class RatioView extends View {

    private float length;
    private float mCircleXY ;
    private float mRadius ;
    private RectF mArcRectF;
    private float mSweepAngle = 45;
    private String mShowText = "Fairy is a fool";
    private int showTextSize = 50;


    public RatioView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        length = getMeasuredWidth();
        mCircleXY = length/2;
        mRadius = length/4;
        mArcRectF = new RectF((float)(length*0.15), (float)(length*0.15), (float)(length*0.85),
                (float)(length*0.85));
        Paint mCirclePaint = new Paint();
        mCirclePaint.setColor(Color.RED);
        Paint mArcPaint = new Paint();
        mArcPaint.setColor(Color.GREEN);
        mArcPaint.setStyle(Paint.Style.STROKE);
        mArcPaint.setStrokeWidth(60);
        Paint mTextPaint = new Paint();
        mTextPaint.setColor(Color.WHITE);
        mTextPaint.setTextSize(showTextSize);
        mTextPaint.setTextAlign(Paint.Align.CENTER);
        canvas.drawCircle(mCircleXY, mCircleXY, mRadius, mCirclePaint);
        canvas.drawArc(mArcRectF, 270, mSweepAngle, false, mArcPaint);
        //1-外接矩阵RectF 2-起始角度 3-扫过角度（顺时针） 4-圆心是否包在其中（扇形） 5-画笔
        canvas.drawText(mShowText, 0, mShowText.length(), mCircleXY,
                mCircleXY+showTextSize/4, mTextPaint);
        //第四个参数并非字符本身垂直居中，而是baseLine垂直居中，实际上下要作微调整
        super.onDraw(canvas);
        canvas.restore();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public void setmSweepAngle(float sweepAngle) {
        if(sweepAngle != 0) {
            this.mSweepAngle = sweepAngle;
        } else {
            this.mSweepAngle = 25;
        }
        this.invalidate();
    }

    public void setCenterText(float mSweepAngle) {
        float percent = mSweepAngle*100/360;
       /* DecimalFormat format = new DecimalFormat("0.00");
        mShowText = format.format(percent)+"%";*/
        BigDecimal bd = new BigDecimal(mSweepAngle*100/360);
        mShowText =  bd.setScale(2, BigDecimal.ROUND_HALF_UP)+"%";
        this.invalidate();

    }
}
