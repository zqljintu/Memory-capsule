package com.zql.comm.netbean.response;

public class BaseResponse {
    /**
     * error_name : 0
     * msg : logup_success
     */

    private int error_name;
    private String msg;

    public int getError_name() {
        return error_name;
    }

    public void setError_name(int error_name) {
        this.error_name = error_name;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
