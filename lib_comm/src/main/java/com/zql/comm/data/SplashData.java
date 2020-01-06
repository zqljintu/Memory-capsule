package com.zql.comm.data;

import com.zql.base.sp.SpUtil;

/**
 * 2019-11-11 11:01
 **/
public class SplashData {

    private static final String KEY_FIRST_OPEN = "key_first_open";


    //是否为第一次开启
    public static boolean isFirstOpen() {
        return SpUtil.getBoolean(KEY_FIRST_OPEN, true);
    }

    //设置第一次
    public static void setOpen() {
        SpUtil.putBoolean(KEY_FIRST_OPEN, false);
    }


}
