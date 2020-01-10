package com.zql.comm.net;

public interface OnHttpRequestListener<T> {

    void onHttpRequestSuccess(T result);

    void onHttpRequestFailed(String error);
}
