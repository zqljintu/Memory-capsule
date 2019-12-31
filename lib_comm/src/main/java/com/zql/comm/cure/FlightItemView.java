package com.zql.comm.cure;

import android.content.Context;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatImageView;

/**
 * @author 夜斗
 * @date 2019/10/22
 */
 public class FlightItemView extends AppCompatImageView {
    public FlightItemView(Context context) {
        super(context);
    }

    public FlightItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FlightItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public void setFabLoc(ViewPoint newLoc){
        setTranslationX(newLoc.x);
        setTranslationY(newLoc.y);
    }

}
