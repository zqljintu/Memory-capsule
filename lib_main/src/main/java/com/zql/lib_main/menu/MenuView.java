package com.zql.lib_main.menu;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.annotation.NonNull;

import com.lxj.xpopup.core.AttachPopupView;
import com.zql.lib_main.R;

public class MenuView extends AttachPopupView {

    private OnMenuListClickListener mListener;

    public MenuView(@NonNull Context context, OnMenuListClickListener listener) {
        super(context);
        mListener = listener;
        findViewById(R.id.con_list).setOnClickListener(v -> {
            if (null != mListener){
                mListener.OnItemClick(0);
                dismiss();
            }
        });
        findViewById(R.id.con_listse).setOnClickListener(v -> {
            if (null != mListener){
                mListener.OnItemClick(1);
                dismiss();
            }
        });
        findViewById(R.id.con_chart).setOnClickListener(v -> {
            if (null != mListener){
                mListener.OnItemClick(2);
                dismiss();
            }
        });
        findViewById(R.id.con_setting).setOnClickListener(v -> {
            if (null != mListener){
                mListener.OnItemClick(3);
                dismiss();
            }
        });
        findViewById(R.id.con_net).setOnClickListener(v -> {
            if (null != mListener){
                mListener.OnItemClick(4);
                dismiss();
            }
        });
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.menu;
    }

    @Override
    protected Drawable getPopupBackground() {
        return super.getPopupBackground();
    }

    public interface OnMenuListClickListener{
        void OnItemClick(int pos);
    }
}
