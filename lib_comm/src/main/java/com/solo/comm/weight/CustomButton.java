package com.solo.comm.weight;

/**
 * Create by Totoro
 * 2019-06-20 10:48
 **/

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.os.Build;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatButton;

import com.solo.base.utils.DisplayUtils;

public class CustomButton extends AppCompatButton {

    public CustomButton(Context context) {
        super(context);
        init();
    }

    public CustomButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private int mViewWidth;
    private int mViewHeight;
    private Paint mPaint;
    private LinearGradient mLinearGradient;
    private Matrix mGrandientMatrix;
    private float mTranslate;
    private ValueAnimator mAnimator;
    private int mRound;

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mRound = DisplayUtils.dp2px(22);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mViewWidth = w;
        mViewHeight = h;
        mLinearGradient = new LinearGradient(0,
                0,
                mViewWidth,
                mViewHeight + 400,
                new int[]{Color.TRANSPARENT, 0x33ffffff, Color.TRANSPARENT},
                new float[]{0f, 0.2f, 0.4f}, Shader.TileMode.CLAMP);
        mPaint.setShader(mLinearGradient);
        mGrandientMatrix = new Matrix();
        if (mAnimator != null) {
            mAnimator.removeAllListeners();
            mAnimator.cancel();
            mAnimator = null;
        }
        mAnimator = ValueAnimator.ofFloat(-50, mViewWidth + 50);
        mAnimator.setRepeatCount(-1);
        mAnimator.setDuration(600);
        mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mTranslate = (float) animation.getAnimatedValue();
                mGrandientMatrix.setTranslate(mTranslate, 0);
                mLinearGradient.setLocalMatrix(mGrandientMatrix);
                postInvalidate();
            }
        });
        mAnimator.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (Build.VERSION.SDK_INT >= 21){
            canvas.drawRoundRect(0, 0, mViewWidth, mViewHeight, mRound, mRound, mPaint);
        }else {
            canvas.drawRect(0, 0, mViewWidth, mViewHeight, mPaint);
        }
    }


    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (mAnimator != null) {
            mAnimator.removeAllListeners();
            mAnimator.cancel();
            mAnimator = null;
        }
    }
}
