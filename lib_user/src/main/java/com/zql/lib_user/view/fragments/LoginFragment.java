package com.zql.lib_user.view.fragments;

import android.view.View;

import com.zql.base.ui.mvp.BaseLifecycleFragment;
import com.zql.lib_user.R;

public class LoginFragment extends BaseLifecycleFragment<LoginPresenter> implements LoginContract.view {


    public static LoginFragment newInstance(){
        return new LoginFragment();
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected LoginPresenter getPresenter() {
        return new LoginPresenter(this);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_login;
    }
}
