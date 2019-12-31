package com.zql.comm.net.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Create by Totoro
 * 2019-07-11 13:42
 **/
public class SdkModel implements Serializable {


    /**
     * sensor_time : 120
     * app : 0
     * sensor_percent : 50
     * freeze : 0
     * adx_status : 0
     * home : 0
     * tagper : 0
     * ads : 0
     * native_percent : 0
     * ad_freeze : 0
     * day_limit : []
     * app_list :
     * complete : 0
     * screen : 0
     * pratio_c : []
     * hours : 0
     * battery_percent : 0
     * sdk_status : 0
     * ad_delay : 0
     * launch : 0
     * interval : 0
     * battery_time : 1800
     */

    @SerializedName("sensor_time")
    private int sensor_time;
    private int app;
    @SerializedName("sensor_percent")
    private int sensor_percent;
    private int freeze;
    @SerializedName("adx_status")
    private int adx_status;
    private int home;
    private int tagper;
    private int ads;
    @SerializedName("native_percent")
    private int native_percent;
    @SerializedName("ad_freeze")
    private int ad_freeze;
    private String app_list;
    private int complete;
    private int screen;
    private int hours;
    @SerializedName("battery_percent")
    private int battery_percent;
    @SerializedName("sdk_status")
    private int sdk_status;
    @SerializedName("ad_delay")
    private int ad_delay;
    private int launch;
    private int interval;
    @SerializedName("battery_time")
    private int battery_time;
    private List<?> day_limit;
    private List<?> pratio_c;

    public int getSensor_time() {
        return sensor_time;
    }

    public SdkModel setSensor_time(int sensor_time) {
        this.sensor_time = sensor_time;
        return this;
    }

    public int getApp() {
        return app;
    }

    public SdkModel setApp(int app) {
        this.app = app;
        return this;
    }

    public int getSensor_percent() {
        return sensor_percent;
    }

    public SdkModel setSensor_percent(int sensor_percent) {
        this.sensor_percent = sensor_percent;
        return this;
    }

    public int getFreeze() {
        return freeze;
    }

    public SdkModel setFreeze(int freeze) {
        this.freeze = freeze;
        return this;
    }

    public int getAdx_status() {
        return adx_status;
    }

    public SdkModel setAdx_status(int adx_status) {
        this.adx_status = adx_status;
        return this;
    }

    public int getHome() {
        return home;
    }

    public SdkModel setHome(int home) {
        this.home = home;
        return this;
    }

    public int getTagper() {
        return tagper;
    }

    public SdkModel setTagper(int tagper) {
        this.tagper = tagper;
        return this;
    }

    public int getAds() {
        return ads;
    }

    public SdkModel setAds(int ads) {
        this.ads = ads;
        return this;
    }

    public int getNative_percent() {
        return native_percent;
    }

    public SdkModel setNative_percent(int native_percent) {
        this.native_percent = native_percent;
        return this;
    }

    public int getAd_freeze() {
        return ad_freeze;
    }

    public SdkModel setAd_freeze(int ad_freeze) {
        this.ad_freeze = ad_freeze;
        return this;
    }

    public String getApp_list() {
        return app_list;
    }

    public SdkModel setApp_list(String app_list) {
        this.app_list = app_list;
        return this;
    }

    public int getComplete() {
        return complete;
    }

    public void setComplete(int complete) {
        this.complete = complete;
    }

    public int getScreen() {
        return screen;
    }

    public SdkModel setScreen(int screen) {
        this.screen = screen;
        return this;
    }

    public int getHours() {
        return hours;
    }

    public SdkModel setHours(int hours) {
        this.hours = hours;
        return this;
    }

    public int getBattery_percent() {
        return battery_percent;
    }

    public SdkModel setBattery_percent(int battery_percent) {
        this.battery_percent = battery_percent;
        return this;
    }

    public int getSdk_status() {
        return sdk_status;
    }

    public SdkModel setSdk_status(int sdk_status) {
        this.sdk_status = sdk_status;
        return this;
    }

    public int getAd_delay() {
        return ad_delay;
    }

    public SdkModel setAd_delay(int ad_delay) {
        this.ad_delay = ad_delay;
        return this;
    }

    public int getLaunch() {
        return launch;
    }

    public SdkModel setLaunch(int launch) {
        this.launch = launch;
        return this;
    }

    public int getInterval() {
        return interval*1000;
    }

    public SdkModel setInterval(int interval) {
        this.interval = interval;
        return this;
    }

    public int getBattery_time() {
        return battery_time;
    }

    public SdkModel setBattery_time(int battery_time) {
        this.battery_time = battery_time;
        return this;
    }

    public List<?> getDay_limit() {
        return day_limit;
    }

    public void setDay_limit(List<?> day_limit) {
        this.day_limit = day_limit;
    }

    public List<?> getPratio_c() {
        return pratio_c;
    }

    public void setPratio_c(List<?> pratio_c) {
        this.pratio_c = pratio_c;
    }
}
