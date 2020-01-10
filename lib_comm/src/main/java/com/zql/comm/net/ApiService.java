package com.zql.comm.net;

import com.zql.comm.netbean.request.LoginRequest;
import com.zql.comm.netbean.response.LoginResponse;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiService {

    /**
     * 登陆接口
     */
    @FormUrlEncoded
    @POST("/api/user_login")
    Observable<LoginResponse> login(@Field("username") String username, @Field("password") String password);
}
