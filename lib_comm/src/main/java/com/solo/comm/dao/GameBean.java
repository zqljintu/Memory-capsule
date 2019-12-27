package com.solo.comm.dao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class GameBean {

    @Id(autoincrement = true)
    private Long id;

    @Index(unique = true)
    private String PackageName;

    private String AppName;

    @Generated(hash = 1602055356)
    public GameBean(Long id, String PackageName, String AppName) {
        this.id = id;
        this.PackageName = PackageName;
        this.AppName = AppName;
    }

    @Generated(hash = 1942203655)
    public GameBean() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPackageName() {
        return this.PackageName;
    }

    public void setPackageName(String PackageName) {
        this.PackageName = PackageName;
    }

    public String getAppName() {
        return this.AppName;
    }

    public void setAppName(String AppName) {
        this.AppName = AppName;
    }
}
