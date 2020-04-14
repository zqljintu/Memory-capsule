package com.zql.comm.util;

import android.Manifest;
import android.content.pm.PackageManager;

import androidx.core.content.ContextCompat;

import com.zql.base.BaseApplication;


public class PermissionsSetting {
    /**
     * 应用所需要的权限
     */
    public final static String[] NEED_PERMISSIONS = {
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA,
    };

    /**
     * 检查权限是否被授予了 有一个为被授予就返回false
     * 授予了就返回true
     */
    public static boolean checkPermission(String... permissions) {
        for (String permission : permissions) {
            int i = ContextCompat.checkSelfPermission(BaseApplication.getApplication(), permission);
            if (i == PackageManager.PERMISSION_DENIED) {
                return false;
            }
        }
        return true;
    }

}
