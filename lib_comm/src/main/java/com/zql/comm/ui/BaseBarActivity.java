package com.zql.comm.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import com.zql.base.ui.mvp.BaseLifecycleActivity;
import com.zql.base.ui.mvp.BasePresenter;
import com.zql.comm.R;

/**
 * @date 2019/11/14
 */
public abstract class BaseBarActivity<T extends BasePresenter> extends BaseLifecycleActivity<T> {

    private Toolbar mToolBar;

    @Override
    protected int getContentLayoutId() {
        return R.layout.base_bar_activity_layout;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mToolBar = findViewById(R.id.tool_bar);
        setSupportActionBar(mToolBar);
        if (showToolBar(mToolBar)) {
            mToolBar.setVisibility(View.VISIBLE);
        }
        ((FrameLayout) find(R.id.container)).addView(LayoutInflater.from(this).inflate(getLayoutId(), null));

    }

    protected abstract int getLayoutId();



    protected boolean showToolBar(Toolbar toolBar) {
        return false;
    }

    protected Toolbar getToolBar() {
        return mToolBar;
    }
}
