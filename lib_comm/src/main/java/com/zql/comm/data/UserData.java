package com.zql.comm.data;

import com.zql.base.sp.SpUtil;

public class UserData {
    private static final String USER_NAME = "user_name";

    private static final String USER_PASS = "user_pass";

    private static final String USER_SEX = "user_sex";

    private static final String USER_NICKNAME = "user_nickname";

    private static final String USER_TITLE = "user_title";

    private static final String USER_ISLOGIN = "user_islogin";

    public static boolean getUserIsLogin(){
        return SpUtil.getBoolean(USER_ISLOGIN);
    }

    public static String getUserName() {
        return SpUtil.getString(USER_NAME,"");
    }

    public static String getUserPass() {
        return SpUtil.getString(USER_PASS,"");
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
}
