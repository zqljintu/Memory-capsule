package com.zql.base.utils;

import java.util.regex.Pattern;

public class RegularUtil {

    /**
     * 判断用户名是否合法
     * @param string
     * @return
     */
    public static boolean isCorrectUsername(String string){
        String str = "^[a-zA-Z0-9_-]{6,16}$";
        return Pattern.matches(str,string);
    }
    /**
     * 判断密码是否合法
     * @param string
     * @return
     */
    public static boolean isCorrectPassword(String string){
        String str = "^[a-zA-Z0-9_-]{6,16}$";
        return Pattern.matches(str,string);
    }
    /**
     * 判断邮箱是否合法
     * @param string
     * @return
     */
    public static boolean isCorrectEmail(String string){
        String str = "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$";
        return Pattern.matches(str,string);
    }
    /**
     * 判断手机号码是否合法
     * @param string
     * @return
     */
    public static boolean isCorrectPhoneNumber(String string){
        String str = "(0|86|17951)?(1[3-9][0-9])[0-9]{8}$";
        return Pattern.matches(str,string);
    }
}
