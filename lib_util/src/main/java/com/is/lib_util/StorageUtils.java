package com.is.lib_util;

import android.os.Environment;
import android.os.StatFs;
import android.text.format.Formatter;

import java.util.Locale;

/**
 * @author 夜斗
 * @date 2019/11/13
 */
public class StorageUtils {

    private static StatFs statFs;

    /**
     * @return 获取存储空间可用总大小 不包括系统所占空间
     */
    public static String getStorageSize() {
        return Formatter.formatFileSize(GlobalUtils.getApp(),  getStatFs().getTotalBytes());
    }

    /**
     * @return 获取存储空间可用大小
     */
    public static String getStorageFreeSize() {
        return Formatter.formatFileSize(GlobalUtils.getApp(), getStatFs().getFreeBytes());
    }


      static StatFs getStatFs() {
        if (statFs == null) {
            return new StatFs(Environment.getExternalStorageDirectory().getPath());
        }
        return statFs;
    }

    /**
     * 比特单位换算
     */
    public static StorageModel convertStorage(long size) {
        float f = size;
        int unitIndex = 0;
        while (f >= 1024f && unitIndex < Unit.values().length - 1) {
            f /= 1024f;
            unitIndex++;
        }
        StorageModel storageModel = new StorageModel();
        storageModel.unit = Unit.values()[unitIndex];
        storageModel.size = String.format(Locale.ENGLISH, "%.1f", f);
        return storageModel;
    }
    /**
     * 比特单位换算
     */
    public static StorageModel convertStorageForInt(long size) {
        float f = size;
        int unitIndex = 0;
        while (f >= 1024f && unitIndex < Unit.values().length - 1) {
            f /= 1024f;
            unitIndex++;
        }
        StorageModel storageModel = new StorageModel();
        storageModel.unit = Unit.values()[unitIndex];
        storageModel.size = String.format(Locale.ENGLISH, "%d", (int)f);
        return storageModel;
    }

    public enum Unit {
        B, KB, MB, GB, TB, PB
    }

    public static class StorageModel {
        public String size;
        public Unit unit;
    }

}
