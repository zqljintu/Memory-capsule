package com.zql.comm.bean;

import java.io.Serializable;

/**
 * Created by 尽途 on 2018/4/30.
 */

public class Noteinfo implements Serializable {
    private Long id;
    private String noteinfo;
    private String notetype;
    private String people;
    private String date;
    private String time;
    private String location;
    private String photopath;
    private Boolean isshow;
    private String createtime;
    public Noteinfo(Long id, String noteinfo, String notetype, String people,
                    String date, String time, String location, String photopath,
                    Boolean isshow, String createtime) {
        this.id = id;
        this.noteinfo = noteinfo;
        this.notetype = notetype;
        this.people = people;
        this.date = date;
        this.time = time;
        this.location = location;
        this.photopath = photopath;
        this.isshow = isshow;
        this.createtime = createtime;
    }
    public Noteinfo() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNoteinfo() {
        return this.noteinfo;
    }
    public void setNoteinfo(String noteinfo) {
        this.noteinfo = noteinfo;
    }
    public String getNotetype() {
        return this.notetype;
    }
    public void setNotetype(String notetype) {
        this.notetype = notetype;
    }
    public String getPeople() {
        return this.people;
    }
    public void setPeople(String people) {
        this.people = people;
    }
    public String getDate() {
        return this.date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getTime() {
        return this.time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public String getLocation() {
        return this.location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public String getPhotopath() {
        return this.photopath;
    }
    public void setPhotopath(String photopath) {
        this.photopath = photopath;
    }
    public Boolean getIsshow() {
        return this.isshow;
    }
    public void setIsshow(Boolean isshow) {
        this.isshow = isshow;
    }
    public String getCreatetime() {
        return this.createtime;
    }
    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }
}
