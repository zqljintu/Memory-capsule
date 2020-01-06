package com.zql.base.net;

import com.zql.base.BaseApplication;
import com.zql.base.BaseLogUtil;
import com.zql.base.utils.AesCbcUtil;
import com.zql.base.utils.ApiSignUtil;
import com.zql.base.utils.AppUtil;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 2019-11-11 09:30
 **/
public class HeadInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request newRequest;

        HttpUrl url = request.url().newBuilder()
                .encodedPath("/" + ApiSignUtil.makeMd5(BaseApplication.getApplication().getPackageName() + request.url().encodedPath()))
                .build();

        String md5 = ApiSignUtil.getMd5(BaseApplication.getApplication());
        BaseLogUtil.d("sign", md5);
        newRequest = request.newBuilder()
                .url(url)
                .addHeader("package-name", BaseApplication.getApplication().getPackageName())
                .addHeader("sign", md5)
                .addHeader("app_version_code", String.valueOf(AppUtil.getVersionCode()))
                .addHeader("iv", AesCbcUtil.ivParameter)
                .build();

        return chain.proceed(newRequest);
    }
}
