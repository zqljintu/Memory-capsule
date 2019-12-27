package com.solo.comm.dao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Create by Totoro
 * 2019-08-08 18:19
 **/
@Entity
public class InstallData {

    @Id(autoincrement = true)
    private Long id;

    public String packageName = "";

    public String appName = "";

    public boolean installed = false;

    private String iconPath;

    @Generated(hash = 917007703)
    public InstallData(Long id, String packageName, String appName,
            boolean installed, String iconPath) {
        this.id = id;
        this.packageName = packageName;
        this.appName = appName;
        this.installed = installed;
        this.iconPath = iconPath;
    }

    @Generated(hash = 1834747302)
    public InstallData() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPackageName() {
        return this.packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getAppName() {
        return this.appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public boolean getInstalled() {
        return this.installed;
    }

    public void setInstalled(boolean installed) {
        this.installed = installed;
    }

    public String getIconPath() {
        return this.iconPath;
    }

    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }
}
