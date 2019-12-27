package com.zql.lib_splash;

import com.solo.base.ui.mvp.BaseLifecycleActivity;

public class SplashActivity extends BaseLifecycleActivity<SplashPresenter> implements SplashContract.view {
    @Override
    protected void initView() {

    }

    @Override
    protected SplashPresenter getPresenter() {
        return new SplashPresenter(this);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_splash;
    }
}
