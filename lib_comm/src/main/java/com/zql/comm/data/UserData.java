package com.zql.comm.data;

import com.zql.base.sp.SpUtil;

public class UserData {
    private static final String USER_NAME = "user_name";

    private static final String USER_PASS = "user_pass";

    private static final String USER_SEX = "user_sex";

    private static final String USER_NICKNAME = "user_nickname";

    private static final String USER_TITLE = "user_title";

    private static final String USER_ISLOGIN = "user_islogin";

    private static final String USER_LOGIN_FIRST = "user_login_first";

    private static final String USER_LOGIN_TOKEN = "user_login_token";

    private static final int OUT_TIME = 1000 *60 *60 *24 *5;//5天

    public static boolean getUserIsLogin(){
        return SpUtil.getBoolean(USER_ISLOGIN,false);
    }

    public static String getUserName() {
        return SpUtil.getString(USER_NAME,"");
    }

    public static String getUserPass() {
        return SpUtil.getString(USER_PASS,"");
    }

    public static long getUserLoginFirst(){
        return SpUtil.getLong(USER_LOGIN_FIRST);
    }

    public static String getUserLoginToken() {
        return SpUtil.getString(USER_LOGIN_TOKEN,"");
    }

    public static String getUserSex() {
        return SpUtil.getString(USER_SEX, "");
    }

    public static String getUserNickname() {
        return SpUtil.getString(USER_NICKNAME, "");
    }

    public static String getUserTitle() {
        return SpUtil.getString(USER_TITLE, "");
    }

    public static void setUserName(String user){
        SpUtil.putString(USER_NAME, user);
    }

    public static void setUserPass(String pass){
        SpUtil.putString(USER_PASS, pass);
    }

    public static void setUserSex(String sex){
        SpUtil.putString(USER_SEX, sex);
    }

    public static void setUserNickname(String nickname){
        SpUtil.putString(USER_NICKNAME, nickname);
    }

    public static void setUserTitle(String title){
        SpUtil.putString(USER_TITLE, title);
    }

    public static void setUserIsLogin(boolean isLogin){
        SpUtil.putBoolean(USER_ISLOGIN, isLogin);
    }

    public static void setUserLoginFirst(){
        SpUtil.putLong(USER_LOGIN_FIRST,System.currentTimeMillis());
    }

    public static void setUserLoginToken(String token){
        SpUtil.putString(USER_LOGIN_TOKEN, token);
    }


    public static void cleanUserAndPass(){
        SpUtil.removeKey(USER_NAME);
        SpUtil.removeKey(USER_PASS);
        SpUtil.removeKey(USER_ISLOGIN);
    }
}
