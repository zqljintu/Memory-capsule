package com.zql.lib_user.view.fragments;

import com.zql.base.ui.mvp.BasePresenter;

public class LoginPresenter extends BasePresenter<LoginContract.view> implements LoginContract.presenter {

    public LoginPresenter(LoginContract.view view) {
        super(view);
    }
}
