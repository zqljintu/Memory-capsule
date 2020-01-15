package com.zql.comm.netbase;

import android.util.Log;

import com.google.gson.Gson;

import retrofit2.Converter;

/**
 * 接口加解密
 */
public class JsonConverterFactory extends Converter.Factory {
    private static final String TAG = "okhttp===>";
    private final Gson gson;

    private JsonConverterFactory(Gson gson) {
        if (gson == null) throw new NullPointerException("gson == null");
        this.gson = gson;
        Log.d(TAG,gson.toString());
    }

    public static JsonConverterFactory create() {
        return create(new Gson());
    }

    public static JsonConverterFactory create(Gson gson) {
        return new JsonConverterFactory(gson);
    }

}