package com.zql.comm.helper;

import java.io.Serializable;

/**
 * Create by Totoro
 * 2019-11-11 17:55
 **/
public class InstallInfo implements Serializable {

    private String packageName;

    private boolean isInstall;


    public InstallInfo(String packageName, boolean isInstall) {
        this.packageName = packageName;
        this.isInstall = isInstall;
    }


    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public boolean isInstall() {
        return isInstall;
    }

    public void setInstall(boolean install) {
        isInstall = install;
    }
}
