package com.zql.comm.net;

import com.zql.comm.net.model.BaseModel;
import com.zql.comm.net.model.BaseModels;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * 2019-06-18 17:52
 **/
public interface Api {

    /**
     * app_config
     */
    @POST("")
    Observable<BaseModel<ConfigModel>> getServiceConfig(@Body RequestBody body);

    @POST("")
    Observable<BaseModel<UpdateModel>> getUpdateConfig(@Body RequestBody body);


    /**
     * ad_position
     */
    @POST("")
    Observable<BaseModels<List<AdInfoModel>>> getAd(@Body RequestBody body);


    @POST("")
    Observable<BaseModels<SdkModel>> getSdk(@Body RequestBody body);
}
