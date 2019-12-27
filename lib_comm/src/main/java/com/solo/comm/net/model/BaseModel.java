package com.solo.comm.net.model;

import java.io.Serializable;

/**
 * Create by Totoro
 * 2019-11-11 09:51
 **/
public class BaseModel<T> implements Serializable {

    private int code;

    private String msg;

    private T data;


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
