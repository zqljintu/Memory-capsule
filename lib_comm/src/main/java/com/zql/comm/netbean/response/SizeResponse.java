package com.zql.comm.netbean.response;

public class SizeResponse {

    public static final int SIZE_SUCCESS = 222;

    /**
     * code : 0
     * msg : logup_success
     */

    private int code;
    private String msg;
    private  int size;

    public int getSize(){
        return size;
    }

    public void setSize(int size){
        this.size = size;
    }

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
