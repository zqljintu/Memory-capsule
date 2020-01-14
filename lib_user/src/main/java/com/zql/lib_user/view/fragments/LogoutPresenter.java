package com.zql.lib_user.view.fragments;

import com.zql.base.ui.mvp.BasePresenter;

public class LogoutPresenter extends BasePresenter<LogoutContract.view> implements LogoutContract.presenter {

    public LogoutPresenter(LogoutContract.view view) {
        super(view);
    }
}
