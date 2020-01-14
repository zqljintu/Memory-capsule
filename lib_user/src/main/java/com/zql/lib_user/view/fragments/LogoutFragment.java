package com.zql.lib_user.view.fragments;

import android.view.View;

import com.zql.base.ui.mvp.BaseLifecycleFragment;
import com.zql.lib_user.R;

public class LogoutFragment extends BaseLifecycleFragment<LogoutPresenter> implements LogoutContract.view {

    public static LogoutFragment newInstance(){
        return new LogoutFragment();
    }


    @Override
    protected void initView(View view) {

    }

    @Override
    protected LogoutPresenter getPresenter() {
        return new LogoutPresenter(this);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_logout;
    }
}
