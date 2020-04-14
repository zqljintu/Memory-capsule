package com.zql.lib_splash;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.zql.base.ui.mvp.BaseLifecycleActivity;
import com.zql.comm.data.CommData;
import com.zql.comm.data.SplashData;
import com.zql.comm.route.RouteUrl;

@Route(path = RouteUrl.Url_SplashActivity)
public class SplashActivity extends BaseLifecycleActivity<SplashPresenter> implements SplashContract.view {

    private TextView mTextStart;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        immersive(this);
    }

    @Override
    protected void initView() {
        initMainActivity();
    }

    private void initMainActivity(){
        mTextStart = find(R.id.mBtnStart);
        /**
         * 判断是不是第一次进入
         */
        if (SplashData.isFirstOpen()){
            mTextStart.setVisibility(View.VISIBLE);
            mTextStart.setOnClickListener(v -> {
                SplashData.setOpen();
                ARouter.getInstance().build(RouteUrl.Url_MainActivity).navigation();
                finish();
            });
        }else {
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
