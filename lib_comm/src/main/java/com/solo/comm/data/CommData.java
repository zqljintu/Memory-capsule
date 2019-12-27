package com.solo.comm.data;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.solo.base.event.EventBusUtil;
import com.solo.base.sp.SpUtil;
import com.solo.comm.event.OrgChangeEvent;
import com.solo.comm.net.model.ConfigBean;
import com.solo.comm.net.model.ConfigModel;
import com.solo.comm.net.model.SdkModel;
import com.solo.comm.net.model.UpdateModel;

/**
 * Create by Totoro
 * 2019-11-11 10:00
 **/
public class CommData {

    private static final String KEY_ORG = "key_org";
    private static final String KEY_CONFIG = "key_config";
    private static final String KEY_SDK = "key_sdk";
    private static final String KEY_UPDATE = "key_update";


    //org
    public static boolean isOrg() {
        return SpUtil.getBoolean(KEY_ORG, true);
    }

    public static void setOrg(boolean isOrg) {
        SpUtil.putBoolean(KEY_ORG, isOrg);
        EventBusUtil.postEvent(new OrgChangeEvent());
    }


    //config接口数据
    public static void saveConfig(ConfigModel model) {
        SpUtil.putString(KEY_CONFIG, new Gson().toJson(model));
    }

    public static ConfigModel getConfigModel() {
        String string = SpUtil.getString(KEY_CONFIG);
        if (TextUtils.isEmpty(string)) {
            return new ConfigModel();
        } else {
            return new Gson().fromJson(string, ConfigModel.class);
        }
    }

    public static ConfigBean getConfigBean() {
        ConfigBean mConfigBean;
        try {
            mConfigBean = CommData.isOrg() ? CommData.getConfigModel().getOrganic() : CommData.getConfigModel().getNonOrganic();
        } catch (Exception e) {
            mConfigBean = new ConfigBean();
        }
        return mConfigBean;
    }


    //sdk 接口数据
    public static void saveSdk(SdkModel model) {
        SpUtil.putString(KEY_SDK, new Gson().toJson(model));
    }


    public static SdkModel getSdkModel() {
        String string = SpUtil.getString(KEY_SDK);
        if (TextUtils.isEmpty(string)) {
            return new SdkModel();
        } else {
            return new Gson().fromJson(string, SdkModel.class);
        }
    }


    //更新接口数据
    public static void saveUpdate(UpdateModel model) {
        SpUtil.putString(KEY_UPDATE, new Gson().toJson(model));
    }

    public static UpdateModel getUpdateModel() {
        String string = SpUtil.getString(KEY_UPDATE);
        if (TextUtils.isEmpty(string)) {
            return new UpdateModel();
        } else {
            return new Gson().fromJson(string, UpdateModel.class);
        }
    }


}
