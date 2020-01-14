package com.zql.comm.net;

import com.zql.comm.netbean.response.CapsulesResponse;
import com.zql.comm.netbean.response.LoginResponse;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {

    /**
     * 登陆接口
     */
    @FormUrlEncoded
    @POST("/api/user_login")
    Observable<LoginResponse> login(@Field("username") String username, @Field("password") String password);

    /**
     * 拉取接口
     */
    @GET("/api/show_capsules")
    Observable<CapsulesResponse> loadCapsules(@Query("username") String username);
}
