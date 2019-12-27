package com.solo.comm.net.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ConfigModel implements Serializable {

    @SerializedName("organic")
    private ConfigBean organic;

    @SerializedName("non-organic")
    private ConfigBean nonOrganic;


    public ConfigBean getOrganic() {
        return organic;
    }

    public void setOrganic(ConfigBean organic) {
        this.organic = organic;
    }

    public ConfigBean getNonOrganic() {
        return nonOrganic;
    }

    public void setNonOrganic(ConfigBean nonOrganic) {
        this.nonOrganic = nonOrganic;
    }
}
