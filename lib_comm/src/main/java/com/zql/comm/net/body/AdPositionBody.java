package com.zql.comm.net.body;

import com.google.gson.annotations.SerializedName;

public class AdPositionBody {

    @SerializedName("app_name")
    private String appName;
    @SerializedName("app_version_code")
    private int appVersionCode;
    @SerializedName("api_version")
    private int apiVersion;

    public AdPositionBody setAppName(String appName) {
        this.appName = appName;
        return this;
    }

    public AdPositionBody setAppVersionCode(int appVersionCode) {
        this.appVersionCode = appVersionCode;
        return this;
    }

    public AdPositionBody setApiVersion(int apiVersion) {
        this.apiVersion = apiVersion;
        return this;
    }
}
