package com.zql.comm.net;

import com.zql.comm.netbean.response.BaseResponse;
import com.zql.comm.netbean.response.CapsulesResponse;
import com.zql.comm.netbean.response.LoginResponse;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
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
     * 注册接口
     */
    @FormUrlEncoded
    @POST("/api/user_loginup")
    Observable<BaseResponse> logup(@FieldMap Map<String, String> logupMap);

    /**
     * 拉取接口
     */
    @GET("/api/show_capsules")
    Observable<CapsulesResponse> loadCapsules(@Query("username") String username);
}
