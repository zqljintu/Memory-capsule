package com.zql.comm.netbean.response;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class LoginResponse implements Serializable {

    /**
     * error_name : 203
     * sex : 男
     * msg : login_success
     */

    private int error_name;
    private String sex;
    private String msg;

    public int getError_name() {
        return error_name;
    }

    public void setError_name(int error_name) {
        this.error_name = error_name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
                "error_name=" + error_name +
                ", sex='" + sex + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}
