package com.zql.lib_user.view;

import com.zql.base.ui.mvp.BasePresenter;

public class UserPresenter extends BasePresenter<UserContract.view> implements UserContract.presenter {
    public UserPresenter(UserContract.view view) {
        super(view);
    }
}
