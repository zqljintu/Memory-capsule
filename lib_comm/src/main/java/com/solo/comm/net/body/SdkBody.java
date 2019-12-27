package com.solo.comm.net.body;

import com.google.gson.annotations.SerializedName;

/**
 * Create by Totoro
 * 2019-07-11 13:42
 **/
public class SdkBody {

    @SerializedName("app_name")
    private String name;

    @SerializedName("app_version_code")
    private int versionCoded;

    @SerializedName("organic")
    private int oragnic;


    public SdkBody setName(String name) {
        this.name = name;
        return this;
    }

    public SdkBody setVersionCoded(int versionCoded) {
        this.versionCoded = versionCoded;
        return this;
    }

    public SdkBody setOragnic(int oragnic) {
        this.oragnic = oragnic;
        return this;
    }
}
