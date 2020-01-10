package com.zql.comm.netbean.request;

public class LoginRequest {

    private String username;

    private String password;

    public LoginRequest(String user, String pass){
        username = user;
        password = pass;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
