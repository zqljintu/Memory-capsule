package com.zql.comm.net;

import com.zql.base.net.HttpClient;
import com.zql.comm.netbean.request.LoginRequest;
import com.zql.comm.netbean.response.LoginResponse;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HttpData {

    private CompositeDisposable mCompositeDisposable;

    public HttpData(){
        mCompositeDisposable = new CompositeDisposable();
    }

    public void Login(LoginRequest loginRequest,OnHttpRequestListener<LoginResponse> listener){
        Disposable subscribe = HttpClient.getInstance()
                .create(ApiService.class)
                .login(loginRequest.getUsername(),loginRequest.getPassword())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(loginResponse -> {
                    if (null != listener){
                        listener.onHttpRequestSuccess(loginResponse);
                    }
                }, throwable -> {
                    if (null != listener){
                        listener.onHttpRequestFailed(throwable.getMessage());
                    }
                });
        mCompositeDisposable.add(subscribe);

    }

    public void Destory(){
        if (null != mCompositeDisposable){
            mCompositeDisposable.clear();
        }
    }
}