package com.zql.comm.net.model;

import java.io.Serializable;

/**
 * Create by Totoro
 * 2019-11-11 09:51
 **/
public class BaseModels<T> implements Serializable {

    private int code;

    private String msg;

    private T datas;


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

    public T getDatas() {
        return datas;
    }

    public void setDatas(T datas) {
        this.datas = datas;
    }
}
