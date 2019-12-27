package com.is.lib_util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;

/**
 * Create by Totoro
 * 2019-08-01 14:07
 **/
public class PackageUtil {

    @SuppressLint("WrongConstant")
    public static String getAppName(Context context, String packageName) {
        PackageManager packageManager = context.getPackageManager();
        try {
            ApplicationInfo info = packageManager.getApplicationInfo(packageName, PackageManager.COMPONENT_ENABLED_STATE_DEFAULT);
            return info.loadLabel(packageManager).toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    @SuppressLint("WrongConstant")
    public static Drawable getAppIcon(Context context, String packageName) {
        PackageManager packageManager = context.getPackageManager();
        try {
            ApplicationInfo info = packageManager.getApplicationInfo(packageName, PackageManager.COMPONENT_ENABLED_STATE_DEFAULT);
            return info.loadIcon(packageManager);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
