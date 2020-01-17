package com.zql.comm.netbean.response;

public class BaseResponse {

    public static final int ADD_SUCCESS = 206;

    public static final int EDIT_SUCCESS = 213;

    public static final int DELETE_SUCCESS = 213;

    /**
     * code : 0
     * msg : logup_success
     */

    private int code;
    private String msg;

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
}
