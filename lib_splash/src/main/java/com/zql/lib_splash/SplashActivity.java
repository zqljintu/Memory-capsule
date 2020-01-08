package com.zql.lib_splash;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.zql.base.ui.mvp.BaseLifecycleActivity;
import com.zql.comm.route.RouteUrl;

@Route(path = RouteUrl.Url_SplashActivity)
public class SplashActivity extends BaseLifecycleActivity<SplashPresenter> implements SplashContract.view {
    @Override
    protected void initView() {
        initMainActivity();
    }

    private void initMainActivity(){
        ARouter.getInstance().build(RouteUrl.Url_MainActivity).navigation();
        finish();
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
