package com.solo.comm.net.body;

import com.google.gson.annotations.SerializedName;

public class ConfigBody {


    /**
     * app_name :
     * config_key :
     */

    @SerializedName("app_name")
    private String appName;
    @SerializedName("config_key")
    private String configKey;
    @SerializedName("app_version_code")
    private int versionCode;

    public ConfigBody setVersionCode(int versionCode) {
        this.versionCode = versionCode;
        return this;
    }

    public ConfigBody setAppName(String appName) {
        this.appName = appName;
        return this;
    }

    public ConfigBody setConfigKey(String configKey) {
        this.configKey = configKey;
        return this;
    }


}
