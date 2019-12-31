package com.zql.comm.net.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AdInfoModel implements Serializable {

    @SerializedName("admob_id")
    private String admobId;
    @SerializedName("FB")
    private String FbId;
    private String mopub;
    private int percentage;
    private int fbp;
    @SerializedName("ad_id")
    private String adId;
    private int admobp;
    @SerializedName("Tiktok")
    private String Tiktok;
    @SerializedName("type")
    private String type;

    @SerializedName("ad_config")
    private AdConfig adConfig;

    @SerializedName("ps_slotId")
    private String psId;

    public AdConfig getAdConfig() {
        return adConfig;
    }

    public void setAdConfig(AdConfig adConfig) {
        this.adConfig = adConfig;
    }

    public String getPsId() {
        return psId;
    }

    public void setPsId(String psId) {
        this.psId = psId;
    }

    public String getMopub() {
        return mopub;
    }

    public void setMopub(String mopub) {
        this.mopub = mopub;
    }

    public String getAdmobId() {
        return admobId;
    }

    public void setAdmobId(String admob_id) {
        this.admobId = admob_id;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    public int getFbp() {
        return fbp;
    }

    public void setFbp(int fbp) {
        this.fbp = fbp;
    }

    public String getAdId() {
        return adId;
    }

    public void setAdId(String ad_id) {
        this.adId = ad_id;
    }

    public int getAdmobp() {
        return admobp;
    }

    public void setAdmobp(int admobp) {
        this.admobp = admobp;
    }

    public String getFbId() {
        return FbId;
    }

    public void setFbId(String fbId) {
        FbId = fbId;
    }

    public String getTiktok() {
        return Tiktok;
    }

    public void setTiktok(String tiktok) {
        Tiktok = tiktok;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
