package com.solo.base.ui.mvp;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;

import com.solo.base.ui.BaseActivity;
import com.solo.base.ui.mvp.im.BaseLifecyclePresenter;

/**
 * @author 夜斗
 * @date 2019/11/6
 */
public abstract class BaseLifecycleActivity<T extends BasePresenter> extends BaseActivity {
    /**
     *  T => 具体Presenter 的接口
     */
    protected T mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = getPresenter();
        if (mPresenter != null) {
            getLifecycle().addObserver(mPresenter);
        }
        initView();
    }

    protected abstract void initView();

    protected abstract T getPresenter();




}
