package com.solo.comm.weight;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.solo.base.utils.DisplayUtils;
import com.solo.comm.R;


public class LineProgress extends View {
    private static int ROUND_OFFSET = 18;
    private static int STROKE_WIDTH_DEFAULT = 18;
    private static final int BACKGROUND_COLOR_DEFAULT = Color.parseColor("#4dffffff");
    private static final int PROGRESS_NORMAL_COLOR = Color.parseColor("#50e3c2");
    private static final int PROGRESS_HIGH_COLOR = Color.parseColor("#f09a70");
    private int mHighColor = PROGRESS_HIGH_COLOR;
    private int mNormalColor = PROGRESS_NORMAL_COLOR;

    private Paint mBackgroudPaint;
    private Paint mProgressPaint;
    private int mBackgroudColor = BACKGROUND_COLOR_DEFAULT;
    private int mProgressColor = PROGRESS_NORMAL_COLOR;
    private int mStrokeWidth = STROKE_WIDTH_DEFAULT;

    private int mWidth;
    private int mHeight;
    private float mProgress = 0f;
    private ValueAnimator mAnim;
    private boolean isShowRound = true;

    public LineProgress(Context context) {
        this(context, null);
    }

    public LineProgress(Context context, AttributeSet attrs) {
        super(context, attrs);
        ROUND_OFFSET = DisplayUtils.dip2px(6);
        STROKE_WIDTH_DEFAULT = DisplayUtils.dip2px(6);
        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.LineProgress);
            mStrokeWidth = typedArray.getDimensionPixelOffset(R.styleable.LineProgress_progress_height, STROKE_WIDTH_DEFAULT);
            mBackgroudColor = typedArray.getColor(R.styleable.LineProgress_progress_bg_color, BACKGROUND_COLOR_DEFAULT);
            mProgressColor = typedArray.getColor(R.styleable.LineProgress_progress_color, PROGRESS_NORMAL_COLOR);
            mNormalColor = typedArray.getColor(R.styleable.LineProgress_progress_normal_color, PROGRESS_NORMAL_COLOR);
            mHighColor = typedArray.getColor(R.styleable.LineProgress_progress_high_color, PROGRESS_HIGH_COLOR);
            isShowRound = typedArray.getBoolean(R.styleable.LineProgress_show_round, true);
            typedArray.recycle();
        }
        mBackgroudPaint = new Paint();
        mBackgroudPaint.setAntiAlias(true);
        mBackgroudPaint.setColor(mBackgroudColor);
        mBackgroudPaint.setStyle(Paint.Style.FILL);
        mBackgroudPaint.setStrokeWidth(mStrokeWidth);

        mProgressPaint = new Paint();
        mProgressPaint.setAntiAlias(true);
        mProgressPaint.setColor(mProgressColor);
        mProgressPaint.setStyle(Paint.Style.FILL);
        mProgressPaint.setStrokeWidth(mStrokeWidth);

        if (isShowRound) {
            mProgressPaint.setStrokeCap(Paint.Cap.ROUND);
            mBackgroudPaint.setStrokeCap(Paint.Cap.ROUND);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (isShowRound) {
            canvas.drawLine(ROUND_OFFSET, mHeight / 2, mWidth - ROUND_OFFSET, mHeight / 2, mBackgroudPaint);
            if (mProgress > 0) {
                canvas.drawLine(ROUND_OFFSET - 2, mHeight / 2, (mWidth - ROUND_OFFSET + 2) * mProgress, mHeight / 2, mProgressPaint);
            }
        } else {
            canvas.drawLine(0, mHeight / 2, mWidth, mHeight / 2, mBackgroudPaint);
            canvas.drawLine(0, mHeight / 2, mWidth * mProgress, mHeight / 2, mProgressPaint);
        }

    }

    public float getProgress() {
        return mProgress * 100;
    }

    public void setProgress(float progress) {
        this.mProgress = progress / 100;
        invalidate();
    }

    public void setProgress(float progress, boolean normal) {
        this.mProgress = progress / 100;
        if (normal) {
            setNormalColor();
        } else {
            setHighColor();
        }
    }

    public void setProgressAndCancelAnim(float progress) {
        if (mAnim != null && mAnim.isRunning()) {
            mAnim.setFloatValues(mProgress, progress);
        } else {
            this.mProgress = progress / 100;
            invalidate();
        }
    }

    public void setProgressWithAnim(float progress) {
        if (mAnim != null && mAnim.isRunning()) {
            mAnim.end();
        }
        mAnim = ValueAnimator.ofFloat(0, progress);
        mAnim.setDuration(1000);
        mAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                setProgress((Float) animation.getAnimatedValue());
            }
        });
        mAnim.start();
    }

    public void refreshProgressWithAnim() {
        if (mAnim != null && mAnim.isRunning()) {
            mAnim.end();
        }
        if (mProgress == 0) {
            setProgress(0);
            return;
        }
        if (mAnim == null) {
            mAnim = ValueAnimator.ofFloat(0, mProgress * 100);
            mAnim.setDuration(1000);
            mAnim.setInterpolator(new LinearInterpolator());
            mAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    setProgress((Float) animation.getAnimatedValue());
                }
            });
        } else {
            mAnim.setFloatValues(0, mProgress * 100);
        }
        mAnim.start();
    }

    public void setNormalColor() {
        mProgressColor = mNormalColor;
        mProgressPaint.setColor(mProgressColor);
        invalidate();
    }

    public void setHighColor() {
        mProgressColor = mHighColor;
        mProgressPaint.setColor(mProgressColor);
        invalidate();
    }

}
