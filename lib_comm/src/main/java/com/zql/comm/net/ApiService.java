package com.zql.comm.net;

import com.zql.comm.netbean.response.BaseResponse;
import com.zql.comm.netbean.response.CapsulesResponse;
import com.zql.comm.netbean.response.LoginResponse;
import com.zql.comm.netbean.response.SizeResponse;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
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
    @Multipart
    @POST("/api/user_loginup")
    Observable<LoginResponse> logup(@Part List<MultipartBody.Part> partLis);

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
    Observable<CapsulesResponse> loadCapsules(@Query("page") int page);

    /**
     * 拉取用户信息
     */
    @GET("/api/user_info")
    Observable<LoginResponse> getUserInfo();

    /**
     * capsuleSize接口
     */
    @FormUrlEncoded
    @POST("/api/size_capsule")
    Observable<SizeResponse> sizeCapsule();

    /**
     * 添加接口
     */
    @FormUrlEncoded
    @POST("/api/add_capsule")
    Observable<BaseResponse> addCapsule(@FieldMap Map<String, String> capsuleMap);

    /**
     * 修改接口
     */
    @FormUrlEncoded
    @POST("/api/edit_capsule")
    Observable<BaseResponse> editCapsule(@Field("capsule_pk") int capsule_pk, @FieldMap Map<String, String> capsuleMap);


    /**
     * 删除接口
     */
    @FormUrlEncoded
    @POST("/api/delete_capsule")
    Observable<BaseResponse> deleteCapsule(@Field("capsule_pk") int capsule_pk);


}
