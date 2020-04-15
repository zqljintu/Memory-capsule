package com.zql.comm.net;

import com.zql.comm.bean.Noteinfo;
import com.zql.comm.netbase.HttpClient;
import com.zql.comm.netbean.request.LoginRequest;
import com.zql.comm.netbean.request.LogupRequest;
import com.zql.comm.netbean.response.BaseResponse;
import com.zql.comm.netbean.response.CapsulesResponse;
import com.zql.comm.netbean.response.LoginResponse;
import com.zql.comm.netbean.response.SizeResponse;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

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
        File file = null;
        RequestBody body = null;
        MultipartBody.Builder builder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM);//表单类型
        if (logupRequest.getmUserImg() != null){
            file = new File(logupRequest.getmUserImg());
            body = RequestBody.create(MediaType.parse("multipart/form-data"),file);//表单类型
        }
        builder.addFormDataPart("username",logupRequest.getUsername());//传入服务器需要的key，和相应value值
        builder.addFormDataPart("password",logupRequest.getPassword());//传入服务器需要的key，和相应value值
        builder.addFormDataPart("email",logupRequest.getEmail());//传入服务器需要的key，和相应value值
        builder.addFormDataPart("sex",logupRequest.getSex());//传入服务器需要的key，和相应value值
        if (file != null && body != null){
            builder.addFormDataPart("userimage",file.getName(),body); //添加图片数据，body创建的请求体
        }
        Disposable subscribe = HttpClient.getInstance()
                .create(ApiService.class)
                .logup(builder.build().parts())
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
    public void LoadCapsule(OnHttpRequestListener<CapsulesResponse> listener, int page) {
        Disposable subscribe = HttpClient.getInstance()
                .create(ApiService.class)
                .loadCapsules(page)
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
        mCompositeDisposable.add(subscribe);
    }

    /**
     * 获取Capsule的数量方法
     */
    public void SizeCapsule(OnHttpRequestListener<SizeResponse> listener){
        Disposable subscribe = HttpClient.getInstance()
                .create(ApiService.class)
                .sizeCapsule()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(sizeResponse -> {
                    if (null != listener){
                        listener.onHttpRequestSuccess(sizeResponse);
                    }
                }, throwable -> {
                    if (null != listener){
                        listener.onHttpRequestFailed(throwable.getMessage());
                    }
                });
        mCompositeDisposable.add(subscribe);
    }

    /**
     * 获取用户相关信息
     */
    public void getUserInfo(OnHttpRequestListener<LoginResponse> listener){
        Disposable subscribe = HttpClient.getInstance()
                .create(ApiService.class)
                .getUserInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(loginResponse -> {
                    if (listener != null){
                        listener.onHttpRequestSuccess(loginResponse);
                    }
                },throwable -> {
                    if (listener != null){
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













