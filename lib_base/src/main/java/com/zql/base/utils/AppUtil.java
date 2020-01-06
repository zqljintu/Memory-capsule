package com.zql.base.utils;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.zql.base.BaseApplication;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;


public class AppUtil {


    public static String getProcessName() {
        try {
            File file = new File("/proc/" + android.os.Process.myPid() + "/" + "cmdline");
            BufferedReader mBufferedReader = new BufferedReader(new FileReader(file));
            String processName = mBufferedReader.readLine().trim();
            mBufferedReader.close();
            return processName;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }


    /**
     * 获取版本名称
     *
     * @return "1.0.0"
     */
    public static String getVersionName() {
        String packageName = BaseApplication.getApplication().getPackageName();
        try {
            PackageInfo packageInfo = BaseApplication.getApplication().getPackageManager().getPackageInfo(packageName, 0);
            return packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "1.0.0";
    }


    /**
     * 获取版本号
     *
     * @return 1
     */
    public static int getVersionCode() {
        String packageName = BaseApplication.getApplication().getPackageName();
        try {
            PackageInfo packageInfo = BaseApplication.getApplication().getPackageManager().getPackageInfo(packageName, 0);
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 1;
    }


}
