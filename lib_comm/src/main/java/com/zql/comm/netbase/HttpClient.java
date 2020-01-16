package com.zql.comm.netbase;

import com.zql.base.BaseApplication;
import com.zql.base.BuildConfig;
import com.zql.base.R;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 2019-11-11 09:24
 **/
public class HttpClient {

    private final long TIME_OUT = 20;

    private boolean isDebug = false;

    private static HttpClient instance;
    private final Retrofit mRetrofit;

    public static HttpClient getInstance() {
        if (instance == null) {
            synchronized (HttpClient.class) {
                if (instance == null) {
                    instance = new HttpClient();
                }
            }
        }
        return instance;
    }


    private HttpClient() {
        OkHttpClient build = new OkHttpClient.Builder()
                .writeTimeout(TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(TIME_OUT, TimeUnit.SECONDS)
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .addInterceptor(new HeadInterceptor())//header携带了用户token
                .addInterceptor(new HttpLoggingInterceptor().setLevel(BuildConfig.isLogDebug ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE))
                .build();

        mRetrofit = new Retrofit.Builder()
                .client(build)
                .baseUrl(getUrl())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private String getUrl(){
        if (isDebug){
            return "http://127.0.0.1:8000";
        }
        return BaseApplication.getApplication().getString(R.string.baseUrl);
    }


    public <T> T create(final Class<T> service) {
        return mRetrofit.create(service);
    }

}
