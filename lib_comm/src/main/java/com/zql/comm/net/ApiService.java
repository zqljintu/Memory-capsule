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
    Observable<LoginResponse> logup(@FieldMap Map<String, String> logupMap);

    /**
     * 注销接口
     */
    @FormUrlEncoded
    @POST("/api/user_logout")
    Observable<BaseResponse> logout(@Field("password") String password);

    /**
     * 拉取接口
     */
    @GET("/api/show_capsules")
    Observable<CapsulesResponse> loadCapsules();

    /**
     * 添加接口
     */
    @FormUrlEncoded
    @POST("/api/add_capsules")
    Observable<BaseResponse> addCapsule(@FieldMap Map<String, String> capsuleMap);

    /**
     * 修改接口
     */
    @FormUrlEncoded
    @POST("/api/edit_capsules")
    Observable<BaseResponse> editCapsule(@Field("capsule_pk") int capsule_pk, @FieldMap Map<String, String> capsuleMap);


    /**
     * 删除接口
     */
    @FormUrlEncoded
    @POST("/api/delete_capsule")
    Observable<BaseResponse> deleteCapsule(@Field("capsule_pk") int capsule_pk);


}
