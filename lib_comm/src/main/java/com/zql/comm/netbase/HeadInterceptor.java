package com.zql.comm.netbase;


import com.zql.comm.data.UserData;

import java.io.IOException;

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
        Request newrequest = request.newBuilder()
                .addHeader("Authorization", UserData.getUserLoginToken())
                .build();
        Response proceed = chain.proceed(newrequest);
        return proceed;
    }

}
