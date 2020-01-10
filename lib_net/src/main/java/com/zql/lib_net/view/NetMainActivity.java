package com.zql.lib_net.view;

import android.util.Log;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.zql.base.ui.mvp.BaseLifecycleActivity;
import com.zql.comm.data.CommData;
import com.zql.comm.net.HttpData;
import com.zql.comm.net.OnHttpRequestListener;
import com.zql.comm.netbean.request.LoginRequest;
import com.zql.comm.netbean.response.LoginResponse;
import com.zql.comm.route.RouteUrl;
import com.zql.lib_net.R;

@Route(path = RouteUrl.Url_NetMainActivity)
public class NetMainActivity extends BaseLifecycleActivity<NetMainPresenter> implements NetMainContract.view {

    private HttpData mHttpData;

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_net_main;
    }

    @Override
    protected void initView() {
        mHttpData = new HttpData();
        find(R.id.text_local).setOnClickListener(v -> {
                    CommData.setLocalVerson();
                    ARouter.getInstance().build(RouteUrl.Url_MainActivity).navigation();
                    finish();
                }
        );
        find(R.id.text_login).setOnClickListener(v -> {
            mHttpData.Login(new LoginRequest("zqlzql","zqlzql"),new OnHttpRequestListener<LoginResponse>() {
                @Override
                public void onHttpRequestSuccess(LoginResponse result) {
                    Log.d("zzzzzzzzzzzz",result.getSex());
                }

                @Override
                public void onHttpRequestFailed(String error) {
                    Log.d("zzzzzzzzzzzz","failed--->" + error);
                }
            });
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != mHttpData){
            mHttpData.Destory();
        }
    }

    @Override
    protected NetMainPresenter getPresenter() {
        return new NetMainPresenter(this);
    }
}
