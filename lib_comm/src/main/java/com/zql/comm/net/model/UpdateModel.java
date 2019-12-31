package com.zql.comm.net.model;

import java.io.Serializable;

/**
 * Create by Totoro
 * 2019-07-01 14:37
 **/
public class UpdateModel implements Serializable {


    /**
     * latest_version : 1
     * force_version : 1
     * path : com.yo.security.clean
     */

    private String latest_version;
    private String force_version;
    private String path;
    private String title;
    private String des;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getLatest_version() {
        return latest_version;
    }

    public UpdateModel setLatest_version(String latest_version) {
        this.latest_version = latest_version;
        return this;
    }

    public String getForce_version() {
        return force_version;
    }

    public UpdateModel setForce_version(String force_version) {
        this.force_version = force_version;
        return this;
    }

    public String getPath() {
        return path;
    }

    public UpdateModel setPath(String path) {
        this.path = path;
        return this;
    }
}
