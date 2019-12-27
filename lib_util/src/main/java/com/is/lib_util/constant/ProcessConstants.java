package com.is.lib_util.constant;

import com.is.lib_util.BuildConfig;
import com.is.lib_util.GlobalUtils;

import java.util.HashSet;
import java.util.Set;

public class ProcessConstants {

    public final static Set<String> filterApp = new HashSet<>();

    static {
        String myPackageName = GlobalUtils.getApp().getPackageName();
        filterApp.add(myPackageName);
        filterApp.add("com.tencent.mm");//微信
        filterApp.add("com.tencent.mobileqq");//qq
    }
}
