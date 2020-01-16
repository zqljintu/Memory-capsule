package com.zql.comm.net;

import com.zql.comm.bean.Noteinfo;
import com.zql.comm.netbase.HttpClient;
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

    /**
     * 注册方法
     * @param logupRequest
     * @param listener
     */

    public void Logup( LogupRequest logupRequest, OnHttpRequestListener<LoginResponse> listener) {
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



    /**
     * 登录方法
     * @param loginRequest
     * @param listener
     */

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


    /**
     * 注销方法
     * @param listener
     */

    public void Logout(String pass,OnHttpRequestListener<BaseResponse> listener){
        Disposable subscribe = HttpClient.getInstance()
                .create(ApiService.class)
                .logout(pass)
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
    /**
     * 拉取capsules方法
     * @param listener
     */
    public void LoadCapsule(OnHttpRequestListener<CapsulesResponse> listener) {
        Disposable subscribe = HttpClient.getInstance()
                .create(ApiService.class)
                .loadCapsules()
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

    /**
     * 添加capsule方法
     */

    public void AddCapsule(Noteinfo noteinfo, OnHttpRequestListener<BaseResponse> listener) {
        Map<String, String> map = new HashMap<>();
        map.put("capsule_type", noteinfo.getNotetype());
        map.put("capsule_content", noteinfo.getNoteinfo());
        map.put("capsule_time", noteinfo.getTime());
        map.put("capsule_date", noteinfo.getDate());
        map.put("capsule_location", noteinfo.getLocation());
        map.put("capsule_person", noteinfo.getPeople());
        map.put("capsule_image",noteinfo.getPhotopath());
        Disposable subscribe = HttpClient.getInstance()
                .create(ApiService.class)
                .addCapsule(map)
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

    /**
     * 修改capsule方法
     */

    public void EditCapsule(Noteinfo noteinfo, OnHttpRequestListener<BaseResponse> listener) {
        Map<String, String> map = new HashMap<>();
        map.put("capsule_type", noteinfo.getNotetype());
        map.put("capsule_content", noteinfo.getNoteinfo());
        map.put("capsule_time", noteinfo.getTime());
        map.put("capsule_date", noteinfo.getDate());
        map.put("capsule_location", noteinfo.getLocation());
        map.put("capsule_person", noteinfo.getPeople());
        map.put("capsule_image",noteinfo.getPhotopath());
        Disposable subscribe = HttpClient.getInstance()
                .create(ApiService.class)
                .editCapsule(noteinfo.getId().intValue(), map)
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

    /**
     * 删除capsule方法
     */
    public void DeleteCapsule(int capsule_pk, OnHttpRequestListener<BaseResponse> listener){
        Disposable subscribe = HttpClient.getInstance()
                .create(ApiService.class)
                .deleteCapsule(capsule_pk)
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
    }

    public void Destory(){
        if (null != mCompositeDisposable){
            mCompositeDisposable.clear();
        }
    }
}













