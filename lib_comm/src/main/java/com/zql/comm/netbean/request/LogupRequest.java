package com.zql.comm.netbean.request;

public class LogupRequest {

    private String username;

    private String password;

    private String email;

    private String sex;

    public LogupRequest setUsername(String username) {
        this.username = username;
        return this;
    }

    public LogupRequest setPassword(String password) {
        this.password = password;
        return this;
    }

    public LogupRequest setEmail(String email) {
        this.email = email;
        return this;
    }

    public LogupRequest setSex(String sex) {
        this.sex = sex;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getSex() {
        return sex;
    }
}
