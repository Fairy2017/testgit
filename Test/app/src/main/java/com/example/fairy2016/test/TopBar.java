package com.example.fairy2016.test;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.net.sip.SipAudioCall;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Fairy2016 on 2016/12/9.
 */

public class TopBar extends RelativeLayout {

    private int mTitleTextColor, mLeftTextColor, mRightTextColor;
    private float mTitleTextSize;
    private Drawable mLeftBackground, mRightBackground;
    private String mTitleText, mLeftText, mRightText;

    private TextView mTitleView;
    private Button mLeftButton;
    private Button mRightButon;

    private OnTopBarClickListener mListener;

    public TopBar(Context context) {
        super(context);
    }

    public TopBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        getStyleAttributes(context, attrs);
        mTitleView = new TextView(context);
        mLeftButton = new Button(context);
        mRightButon = new Button(context);

        mTitleView.setText(mTitleText);
        mTitleView.setTextColor(mTitleTextColor);
        mTitleView.setTextSize(mTitleTextSize);
        mTitleView.setGravity(Gravity.CENTER);

        mLeftButton.setText(mLeftText);
        mLeftButton.setTextColor(mLeftTextColor);
        mLeftButton.setBackground(mLeftBackground);

        mRightButon.setText(mRightText);
        mRightButon.setTextColor(mRightTextColor);
        mRightButon.setBackground(mRightBackground);

        LayoutParams leftParams = new LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        leftParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        addView(mLeftButton, leftParams);

        LayoutParams rightParams = new LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        rightParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        addView(mRightButon, rightParams);

        LayoutParams titleParams = new LayoutParams(
                LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        titleParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        addView(mTitleView, titleParams);

        mLeftButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onLeftClick();
            }
        });

        mRightButon.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onRightClick();
            }
        });

    }
    public TopBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void getStyleAttributes(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.TopBar);
        mTitleText = ta.getString(R.styleable.TopBar_title);
        mLeftText = ta.getString(R.styleable.TopBar_leftText);
        mRightText = ta.getString(R.styleable.TopBar_rightText);
        mTitleTextColor = ta.getColor(R.styleable.TopBar_titleTextColor, 0);
        mLeftTextColor = ta.getColor(R.styleable.TopBar_leftTextColor, 0);
        mRightTextColor = ta.getColor(R.styleable.TopBar_rightTextColor, 0);
        mLeftBackground = ta.getDrawable(R.styleable.TopBar_leftBackground);
        mRightBackground = ta.getDrawable(R.styleable.TopBar_rightBackground);
        mTitleTextSize = ta.getDimension(R.styleable.TopBar_titleTextSize, 10);
        ta.recycle();
    }

    public interface OnTopBarClickListener {
        void onLeftClick();
        void onRightClick();
    }

    public void setOnTopBarClickListener(OnTopBarClickListener listener) {
        mListener = listener;
    }
}
