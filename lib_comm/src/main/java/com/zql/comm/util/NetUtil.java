package com.zql.comm.util;

import com.zql.base.BaseApplication;

public class NetUtil {

    public static String getImageUrl(String imgurl){
        return BaseApplication.getApplication().getString(com.zql.base.R.string.baseUrl) + "/static/" + imgurl;
    }
}
