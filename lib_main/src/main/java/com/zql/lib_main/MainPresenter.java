package com.zql.lib_main;

import com.solo.base.ui.mvp.BasePresenter;

public class MainPresenter extends BasePresenter<MainContract.view> implements MainContract.presenter {

    public MainPresenter(MainContract.view view) {
        super(view);
    }
}
