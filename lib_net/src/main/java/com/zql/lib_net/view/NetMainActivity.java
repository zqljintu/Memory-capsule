package com.zql.lib_net.view;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.zql.base.ui.mvp.BaseLifecycleActivity;
import com.zql.comm.data.CommData;
import com.zql.comm.route.RouteUrl;
import com.zql.lib_net.R;

@Route(path = RouteUrl.Url_NetMainActivity)
public class NetMainActivity extends BaseLifecycleActivity<NetMainPresenter> implements NetMainContract.view {


    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_net_main;
    }

    @Override
    protected void initView() {
        find(R.id.text_local).setOnClickListener(v -> {
                    CommData.setLocalVerson();
                    ARouter.getInstance().build(RouteUrl.Url_MainActivity).navigation();
                    finish();
                }
        );
    }

    @Override
    protected NetMainPresenter getPresenter() {
        return new NetMainPresenter(this);
    }
}
