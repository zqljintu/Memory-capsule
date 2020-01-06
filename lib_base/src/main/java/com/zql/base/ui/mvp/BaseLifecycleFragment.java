package com.zql.base.ui.mvp;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.zql.base.ui.BaseFragment;


public abstract class BaseLifecycleFragment<T extends BasePresenter> extends BaseFragment {

    protected T mPresenter;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter = getPresenter();
        if (mPresenter != null) {
            getLifecycle().addObserver(mPresenter);
        }
        initView(view);
    }

    protected abstract void initView(View view);

    protected abstract T getPresenter();


}
