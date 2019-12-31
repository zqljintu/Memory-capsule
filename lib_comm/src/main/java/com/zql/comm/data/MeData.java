package com.zql.comm.data;

import com.is.lib_util.SPUtils;

public class MeData {

    private static final String KEY_ME_CHARING = "key_me_charing";

    public static boolean getIsCharing(){
        return SPUtils.getInstance().getBoolean(KEY_ME_CHARING, true);
    }

    public static void setIsCharing(boolean charing){
        SPUtils.getInstance().put(KEY_ME_CHARING, charing);
    }

}
