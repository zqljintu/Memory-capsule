package com.zql.lib_net.menu;

import android.content.Context;

import androidx.annotation.NonNull;

import com.lxj.xpopup.core.PositionPopupView;
import com.zql.lib_net.R;

public class TypeMenu extends PositionPopupView {

    private OnMenuTypeClickListener mListener;

    public TypeMenu(@NonNull Context context, OnMenuTypeClickListener listener) {
        super(context);
        mListener = listener;
        findViewById(R.id.con_menu).setOnClickListener(v -> dismiss());
        findViewById(R.id.img_type0).setOnClickListener(v -> {
            mListener.OnTypeItemClick(0);
            dismiss();
        });
        findViewById(R.id.img_type1).setOnClickListener(v -> {
            mListener.OnTypeItemClick(1);
            dismiss();
        });
        findViewById(R.id.img_type2).setOnClickListener(v -> {
            mListener.OnTypeItemClick(2);
            dismiss();
        });
        findViewById(R.id.img_type3).setOnClickListener(v -> {
            mListener.OnTypeItemClick(3);
            dismiss();
        } );
        findViewById(R.id.img_type4).setOnClickListener(v -> {
            mListener.OnTypeItemClick(4);
            dismiss();
        });

    }


    @Override
    protected int getImplLayoutId() {
        return R.layout.menu_type;
    }

    public interface OnMenuTypeClickListener{
        void OnTypeItemClick(int pos);
    }
}
