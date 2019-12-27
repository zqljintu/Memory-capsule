package com.zql.lib_main;


import com.solo.base.ui.mvp.BaseLifecycleActivity;

public class MainActivity extends BaseLifecycleActivity<MainPresenter> implements MainContract.view {


    @Override
    protected void initView() {

    }

    @Override
    protected MainPresenter getPresenter() {
        return new MainPresenter(this);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_main;
    }
}
