package com.solo.base.ui.mvp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.solo.base.R;
import com.solo.base.ui.BaseFragment;
import com.solo.base.ui.mvp.im.BaseLifecyclePresenter;

/**
 * @author 夜斗
 * @date 2019/11/6
 */
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
