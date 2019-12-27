package com.solo.comm.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.is.lib_util.ImageUtils;
import com.solo.comm.R;


public class ImageCheckBox extends View {

    boolean isCheck = true;
    boolean isWait = false;

    private int width;
    private int height;
    private Paint checkPaint;

    private OnCheckListener mOnCheckListener;
    OnClickListener mOnClickListener = new OnClickListener() {
        @Override
        public void onClick(View view) {
            isWait = false;
            isCheck = !isCheck;
            if (mOnCheckListener != null) {
                mOnCheckListener.onCheck(isCheck);
            }
            invalidate();
        }
    };
    //开启状态图片
    private int onDrawableId;
    //关闭状态图片
    private int offDrawableId;
    //等待桩体的图片
    private int waitDrawableID;

    public ImageCheckBox(Context context) {
        this(context, null);
    }

    public ImageCheckBox(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }


    public ImageCheckBox(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        try {
            TypedArray t = getContext().obtainStyledAttributes(attrs, R.styleable.ImageCheckBox);
            onDrawableId = t.getResourceId(R.styleable.ImageCheckBox_onDrawable, 0);
            offDrawableId = t.getResourceId(R.styleable.ImageCheckBox_offDrawable, 0);
            waitDrawableID = t.getResourceId(R.styleable.ImageCheckBox_waitDrawable, 0);
            this.setOnClickListener(mOnClickListener);
        } catch (Exception e) {
            e.printStackTrace();
        }
        init();
    }


    private void init() {
        checkPaint = new Paint();
        checkPaint.setAntiAlias(true);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Bitmap fieldBitmap = null;

        if (!isWait) {
            if (isCheck) {
                fieldBitmap = ImageUtils.getBitmap(onDrawableId);
            } else {
                fieldBitmap = ImageUtils.getBitmap(offDrawableId);
            }
        } else {
            fieldBitmap = ImageUtils.getBitmap(waitDrawableID);
        }

        if (fieldBitmap == null) {
            return;
        }
        @SuppressLint("DrawAllocation") Rect src = new Rect(0, 0, fieldBitmap.getWidth(), fieldBitmap.getHeight());//资源绘制范围
        @SuppressLint("DrawAllocation") Rect dst = new Rect(0, 0, width, height);//画布范围
        canvas.drawBitmap(fieldBitmap, src, dst, checkPaint);
    }

    public void setOnCheckListener(OnCheckListener onCheckListener) {
        mOnCheckListener = onCheckListener;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = measureWidth(widthMeasureSpec);
        height = measureHeight(heightMeasureSpec);
        setMeasuredDimension(width, height);
    }

    private int measureWidth(int measureSpec) {
        int result = 200;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        switch (specMode) {
            case MeasureSpec.UNSPECIFIED:
                result = specSize;
                break;
            case MeasureSpec.AT_MOST:
                result = Math.min(result, specSize);
                break;
            case MeasureSpec.EXACTLY:
                result = specSize;
                break;
        }
        return result;
    }

    private int measureHeight(int measureSpec) {
        int result = 200;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        switch (specMode) {
            case MeasureSpec.UNSPECIFIED:
                result = specSize;
                break;
            case MeasureSpec.AT_MOST:
                result = Math.min(result, specSize);
                break;
            case MeasureSpec.EXACTLY:
                result = specSize;
                break;
        }
        return result;
    }

    /**
     * @param isCheck 选中状态
     */
    public void setCheck(boolean isCheck) {
        isWait = false;
        this.isCheck = isCheck;
        invalidate();
    }

    /**
     * @param wait 等待状态
     */
    public void setWait(boolean wait) {
        this.isWait = wait;
        if (!isWait) {
            setCheck(true);
        } else {
            if (waitDrawableID == 0) {
                throw new UnsupportedOperationException("没有设置等待状态的图片  app:waitDrawable");
            } else {
                invalidate();
            }
        }
    }


    public boolean isCheck() {
        return isCheck;
    }

    public interface OnCheckListener {
        void onCheck(boolean isCheck);
    }
}
