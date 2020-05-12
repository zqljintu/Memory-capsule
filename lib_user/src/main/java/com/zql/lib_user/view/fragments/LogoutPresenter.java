package com.zql.lib_user.view.fragments;

import androidx.lifecycle.LifecycleOwner;

import com.zql.base.ui.mvp.BasePresenter;
import com.zql.comm.net.HttpData;
import com.zql.comm.net.OnHttpRequestListener;
import com.zql.comm.netbean.response.BaseResponse;

public class LogoutPresenter extends BasePresenter<LogoutContract.view> implements LogoutContract.presenter {

    private HttpData mHttpData;

    public LogoutPresenter(LogoutContract.view view) {
        super(view);
        mHttpData = new HttpData();
    }

    @Override
    public void logoutFromService(String pass) {
        mHttpData.Logout(pass, new OnHttpRequestListener<BaseResponse>() {
            @Override
            public void onHttpRequestSuccess(BaseResponse result) {
                if (getView() != null){
                    if (result.getCode() == BaseResponse.LOGOUT_SUCCESS){
                        getView().logoutToView();
                    }else {
                        getView().showToast("密码输入错误");
                    }
                }
            }

            @Override
            public void onHttpRequestFailed(String error) {

            }
        });
    }

    @Override
    public void onDestroy(LifecycleOwner owner) {
        super.onDestroy(owner);
        if (mHttpData != null){
            mHttpData.Destory();
        }
    }
}
