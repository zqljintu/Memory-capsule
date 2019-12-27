package com.solo.base.net;

import com.solo.base.BaseApplication;
import com.solo.base.BuildConfig;
import com.solo.base.R;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * Create by Totoro
 * 2019-11-11 09:24
 **/
public class HttpClient {
    private final long TIME_OUT = 20;

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
                .addInterceptor(new HeadInterceptor())
                .addInterceptor(new HttpLoggingInterceptor().setLevel(BuildConfig.isLogDebug ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE))
                .build();

        mRetrofit = new Retrofit.Builder()
                .client(build)
                .baseUrl(BaseApplication.getApplication().getString(R.string.base_url))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(JsonConverterFactory.create())
                .build();
    }


    public <T> T create(final Class<T> service) {
        return mRetrofit.create(service);
    }

}
