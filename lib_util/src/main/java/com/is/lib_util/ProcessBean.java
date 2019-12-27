package com.is.lib_util;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author 夜斗
 * @date 2019/11/12
 */
public class ProcessBean implements Parcelable {
    public static final Parcelable.Creator<ProcessBean> CREATOR = new Parcelable.Creator<ProcessBean>() {
        @Override
        public ProcessBean createFromParcel(Parcel source) {
            return new ProcessBean(source);
        }

        @Override
        public ProcessBean[] newArray(int size) {
            return new ProcessBean[size];
        }
    };
    /**
     * 应用报名
     */
    private String packageName;
    /**
     * 应用icon
     */
    private Drawable icon;
    /**
     * 应用名字
     */
    private String name;
    /**
     * 所占内存大小
     */
    private long memSize;
    /**
     * 是否是系统应用
     */
    private boolean isSystem = false;

    private boolean isCheck = true;

    public ProcessBean() {
    }

    protected ProcessBean(Parcel in) {
        this.packageName = in.readString();
        this.icon = ImageUtils.bitmap2Drawable(in.readParcelable(Bitmap.class.getClassLoader()));
        this.name = in.readString();
        this.memSize = in.readLong();
        this.isSystem = in.readByte() != 0;
        this.isCheck = in.readByte() != 0;
    }

    public String getPackageName() {
        return packageName;
    }

    public ProcessBean setPackageName(String packageName) {
        this.packageName = packageName;
        return this;
    }

    public Drawable getIcon() {
        return icon;
    }

    public ProcessBean setIcon(Drawable icon) {
        this.icon = icon;
        return this;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public String getName() {
        return name;
    }

    public ProcessBean setName(String name) {
        this.name = name;
        return this;
    }

    public long getMemSize() {
        return memSize;
    }

    public ProcessBean setMemSize(long memSize) {
        this.memSize = memSize;
        return this;
    }

    public boolean isSystem() {
        return isSystem;
    }

    public ProcessBean setSystem(boolean system) {
        isSystem = system;
        return this;
    }

    @Override
    public String toString() {
        return "ProcessBean{" +
                "packageName='" + packageName + '\'' +
                ", icon=" + icon +
                ", name='" + name + '\'' +
                ", memSize=" + memSize +
                ", isSystem=" + isSystem +
                ", isCheck=" + isCheck +
                '}';
    }

    @Override
    public int describeContents() {
        return CONTENTS_FILE_DESCRIPTOR;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.packageName);
        dest.writeParcelable(ImageUtils.drawable2Bitmap(this.icon), flags);
        dest.writeString(this.name);
        dest.writeLong(this.memSize);
        dest.writeByte(this.isSystem ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isCheck ? (byte) 1 : (byte) 0);
    }
}
