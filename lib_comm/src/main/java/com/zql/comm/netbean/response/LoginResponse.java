package com.zql.comm.netbean.response;

import java.io.Serializable;

public class LoginResponse implements Serializable {

    public static final int LOGIN_SUCCESS = 203;

    /**
     * sex : 女
     * code : 0
     * msg : logup_success
     * token : eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VybmFtZSI6InpxbHN3YyIsImV4cCI6MTU3OTI1MTMzMSwidXNlcl9pZCI6MX0.9LBS3zfXboByUFukGnYJ8RXjnCAgxK6MoTNdujIZazI
     */

    private String sex;
    private int code;
    private String msg;
    private String token;

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
