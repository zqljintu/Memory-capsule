package com.zql.lib_splash;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.zql.base.ui.mvp.BaseLifecycleActivity;
import com.zql.comm.data.CommData;
import com.zql.comm.route.RouteUrl;

@Route(path = RouteUrl.Url_SplashActivity)
public class SplashActivity extends BaseLifecycleActivity<SplashPresenter> implements SplashContract.view {
    @Override
    protected void initView() {
        initMainActivity();
    }

    private void initMainActivity(){
        /**
         * 判断是采用的本地模式还是网络模式
         */
        if (CommData.getIsLocalVersion()){
            ARouter.getInstance().build(RouteUrl.Url_MainActivity).navigation();
        }else {
            ARouter.getInstance().build(RouteUrl.Url_NetMainActivity).navigation();
        }
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
