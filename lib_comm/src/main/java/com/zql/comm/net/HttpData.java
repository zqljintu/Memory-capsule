package com.zql.comm.net;

import com.zql.base.net.HttpClient;
import com.zql.comm.netbean.request.LoginRequest;
import com.zql.comm.netbean.request.LogupRequest;
import com.zql.comm.netbean.response.BaseResponse;
import com.zql.comm.netbean.response.CapsulesResponse;
import com.zql.comm.netbean.response.LoginResponse;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HttpData {

    private CompositeDisposable mCompositeDisposable;

    public HttpData(){
        mCompositeDisposable = new CompositeDisposable();
    }

    public void Logup( LogupRequest logupRequest, OnHttpRequestListener<BaseResponse> listener) {
        Map<String, String> map = new HashMap<>();
        map.put("username",logupRequest.getUsername());
        map.put("password",logupRequest.getPassword());
        map.put("email",logupRequest.getEmail());
        map.put("sex",logupRequest.getSex());
        Disposable subscribe = HttpClient.getInstance()
                .create(ApiService.class)
                .logup(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(baseResponse -> {
                    if (null != listener){
                        listener.onHttpRequestSuccess(baseResponse);
                    }
                }, throwable -> {
                    if (null != listener){
                        listener.onHttpRequestFailed(throwable.getMessage());
                    }
                });
        mCompositeDisposable.add(subscribe);
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

    public void LoadCapsule(LoginRequest loginRequest, OnHttpRequestListener<CapsulesResponse> listener) {
        Disposable subscribe = HttpClient.getInstance()
                .create(ApiService.class)
                .loadCapsules(loginRequest.getUsername())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(capsulesResponse -> {
                    if (null != listener){
                        listener.onHttpRequestSuccess(capsulesResponse);
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
