package com.zql.comm.data;


import com.zql.base.sp.SpUtil;

/**
 * 2019-11-11 10:00
 **/
public class CommData {
    private static final String USER_PASSWORD = "comm_user_password";

    private static final String USER_QUESTION = "comm_user_question";

    private static final String USER_DEFAULT = "null";

    private static final String USER_COLOR = "comm_user_color";

    private static final String VERSON_LOCAL = "comm_verson_local";

    public static String getUserPassword() {
        return SpUtil.getString(USER_PASSWORD,USER_DEFAULT);
    }

    public static void setUserPassword(String pass) {
        SpUtil.putString(USER_PASSWORD, pass);
    }

    public static String getUserQuestion() {
        return  SpUtil.getString(USER_QUESTION, USER_DEFAULT);
    }

    public static void setUserQuestion(String que){
        SpUtil.putString(USER_QUESTION, que);
    }

    public static int getUserColor(){
        return SpUtil.getInt(USER_COLOR,0);
    }

    public static void setUserColor(int color){
        SpUtil.putInt(USER_COLOR, color);
    }

    public static boolean getIsLocalVersion(){
        return SpUtil.getBoolean(VERSON_LOCAL, true);
    }

    public static void  setLocalVerson(){
        SpUtil.putBoolean(VERSON_LOCAL, true);
    }

    public static void  setNetVersion(){
        SpUtil.putBoolean(VERSON_LOCAL, false);
    }

}
