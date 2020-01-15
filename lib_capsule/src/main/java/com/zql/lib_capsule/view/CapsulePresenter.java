package com.zql.lib_capsule.view;

import androidx.lifecycle.LifecycleOwner;

import com.zql.base.ui.mvp.BasePresenter;
import com.zql.comm.data.UserData;
import com.zql.comm.net.HttpData;
import com.zql.comm.net.OnHttpRequestListener;
import com.zql.comm.netbean.request.LoginRequest;
import com.zql.comm.netbean.response.CapsulesResponse;

public class CapsulePresenter extends BasePresenter<CapsuleContract.view> implements CapsuleContract.presenter {

    private HttpData mHttpdata;

    public CapsulePresenter(CapsuleContract.view view) {
        super(view);
        mHttpdata = new HttpData();
    }

    @Override
    public void loadCapsuleDataFromService() {
        mHttpdata.LoadCapsule(new LoginRequest(UserData.getUserName(), UserData.getUserPass()),
                new OnHttpRequestListener<CapsulesResponse>() {
                    @Override
                    public void onHttpRequestSuccess(CapsulesResponse result) {
                        getView().setCapsuleDataToView(result);
                    }

                    @Override
                    public void onHttpRequestFailed(String error) {

                    }
                });
    }

    @Override
    public void onDestroy(LifecycleOwner owner) {
        super.onDestroy(owner);
        if (null != mHttpdata){
            mHttpdata.Destory();
        }
    }
}
