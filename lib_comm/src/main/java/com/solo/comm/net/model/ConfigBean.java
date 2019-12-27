package com.solo.comm.net.model;

import java.io.Serializable;

/**
 * Create by Totoro
 * 2019-11-25 15:05
 **/
public class ConfigBean implements Serializable {


    /**
     * Installation_dialog : true
     * charging_close_dialog : true
     * low_power_dialog : true
     * low_power_ad : true
     * Uninstall_ad : true
     * Uninstall_dialog : true
     * charging_ad : true
     * auto_scan : 1
     * five_dialog : true
     * charging_complete_inter : true
     * wifi_change_dialog : true
     * charging_close_ad : true
     * Installation_ad : true
     * wifi_change_ad : true
     * charging_dialog : true
     */

    private boolean Installation_dialog;
    private boolean charging_close_dialog;
    private boolean low_power_dialog;
    private boolean low_power_ad;
    private boolean Uninstall_ad;
    private boolean Uninstall_dialog;
    private boolean charging_ad;
    private int auto_scan;
    private boolean five_dialog;
    private boolean charging_complete_inter;
    private boolean wifi_change_dialog;
    private boolean charging_close_ad;
    private boolean Installation_ad;
    private boolean wifi_change_ad;
    private boolean charging_dialog;
    private boolean charge_page = false;
    private boolean charge_result_page = false;

    public boolean isCharge_result_page() {
        return charge_result_page;
    }

    public boolean isCharge_page() {
        return charge_page;
    }

    public void setCharge_result_page(boolean charge_result_page) {
        this.charge_result_page = charge_result_page;
    }

    public void setCharge_page(boolean charge_page) {
        this.charge_page = charge_page;
    }



    public boolean isInstallation_dialog() {
        return Installation_dialog;
    }

    public void setInstallation_dialog(boolean Installation_dialog) {
        this.Installation_dialog = Installation_dialog;
    }

    public boolean isCharging_close_dialog() {
        return charging_close_dialog;
    }

    public void setCharging_close_dialog(boolean charging_close_dialog) {
        this.charging_close_dialog = charging_close_dialog;
    }

    public boolean isLow_power_dialog() {
        return low_power_dialog;
    }

    public void setLow_power_dialog(boolean low_power_dialog) {
        this.low_power_dialog = low_power_dialog;
    }

    public boolean isLow_power_ad() {
        return low_power_ad;
    }

    public void setLow_power_ad(boolean low_power_ad) {
        this.low_power_ad = low_power_ad;
    }

    public boolean isUninstall_ad() {
        return Uninstall_ad;
    }

    public void setUninstall_ad(boolean uninstall_ad) {
        Uninstall_ad = uninstall_ad;
    }

    public boolean isUninstall_dialog() {
        return Uninstall_dialog;
    }

    public void setUninstall_dialog(boolean uninstall_dialog) {
        Uninstall_dialog = uninstall_dialog;
    }

    public boolean isCharging_ad() {
        return charging_ad;
    }

    public void setCharging_ad(boolean charging_ad) {
        this.charging_ad = charging_ad;
    }

    public int getAuto_scan() {
        return auto_scan;
    }

    public void setAuto_scan(int auto_scan) {
        this.auto_scan = auto_scan;
    }

    public boolean isFive_dialog() {
        return five_dialog;
    }

    public void setFive_dialog(boolean five_dialog) {
        this.five_dialog = five_dialog;
    }

    public boolean isCharging_complete_inter() {
        return charging_complete_inter;
    }

    public void setCharging_complete_inter(boolean charging_complete_inter) {
        this.charging_complete_inter = charging_complete_inter;
    }

    public boolean isWifi_change_dialog() {
        return wifi_change_dialog;
    }

    public void setWifi_change_dialog(boolean wifi_change_dialog) {
        this.wifi_change_dialog = wifi_change_dialog;
    }

    public boolean isCharging_close_ad() {
        return charging_close_ad;
    }

    public void setCharging_close_ad(boolean charging_close_ad) {
        this.charging_close_ad = charging_close_ad;
    }

    public boolean isInstallation_ad() {
        return Installation_ad;
    }

    public void setInstallation_ad(boolean Installation_ad) {
        this.Installation_ad = Installation_ad;
    }

    public boolean isWifi_change_ad() {
        return wifi_change_ad;
    }

    public void setWifi_change_ad(boolean wifi_change_ad) {
        this.wifi_change_ad = wifi_change_ad;
    }

    public boolean isCharging_dialog() {
        return charging_dialog;
    }

    public void setCharging_dialog(boolean charging_dialog) {
        this.charging_dialog = charging_dialog;
    }
}
