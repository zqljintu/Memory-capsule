package com.zql.lib_capsule.view;

import android.widget.Toast;

import androidx.lifecycle.LifecycleOwner;

import com.zql.base.event.BaseEvent;
import com.zql.base.event.EventBusUtil;
import com.zql.base.ui.mvp.BasePresenter;
import com.zql.base.utils.ToastUtil;
import com.zql.comm.bean.MessageEvent;
import com.zql.comm.net.HttpData;
import com.zql.comm.net.OnHttpRequestListener;
import com.zql.comm.netbean.response.BaseResponse;
import com.zql.comm.netbean.response.CapsulesResponse;

public class CapsulePresenter extends BasePresenter<CapsuleContract.view> implements CapsuleContract.presenter {

    private HttpData mHttpdata;

    public CapsulePresenter(CapsuleContract.view view) {
        super(view);
        mHttpdata = new HttpData();
    }

    @Override
    public void loadCapsuleDataFromService(int page) {
        mHttpdata.LoadCapsule(new OnHttpRequestListener<CapsulesResponse>() {
                    @Override
                    public void onHttpRequestSuccess(CapsulesResponse result) {
                        if (result.getCode() == CapsulesResponse.TOKEN_ERROR){
                            ToastUtil.showToast("token失效");
                            EventBusUtil.postEvent(new MessageEvent(MessageEvent.UPDATE_LOGOUT));
                        }else {
                            getView().setCapsuleDataToView(result);
                        }
                    }

                    @Override
                    public void onHttpRequestFailed(String error) {

                    }
                },page);
    }

    @Override
    public void deleteCapsuleFromService(int pk) {
        mHttpdata.DeleteCapsule(pk, new OnHttpRequestListener<BaseResponse>() {
            @Override
            public void onHttpRequestSuccess(BaseResponse result) {
                if (result.getCode() == BaseResponse.DELETE_SUCCESS){
                    loadCapsuleDataFromService(1);
                }else {
                    getView().showMessage(result.getMsg());
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
        if (null != mHttpdata){
            mHttpdata.Destory();
        }
    }
}
