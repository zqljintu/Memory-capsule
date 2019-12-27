package com.is.lib_util;

import android.app.ActivityManager;
import android.app.AppOpsManager;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.os.Debug;
import android.provider.Settings;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresPermission;

import com.is.lib_util.constant.ProcessConstants;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static android.Manifest.permission.KILL_BACKGROUND_PROCESSES;

/**
 * 与进程相关的工具类
 */
public final class ProcessUtils {

    private ProcessUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * Return the foreground process name.
     * <p>Target APIs greater than 21 must hold
     * {@code <uses-permission android:name="android.permission.PACKAGE_USAGE_STATS" />}</p>
     *
     * @return the foreground process name
     */
    public static String getForegroundProcessName() {
        ActivityManager am =
                (ActivityManager) GlobalUtils.getApp().getSystemService(Context.ACTIVITY_SERVICE);
        //noinspection ConstantConditions
        List<ActivityManager.RunningAppProcessInfo> pInfo = am.getRunningAppProcesses();
        if (pInfo != null && pInfo.size() > 0) {
            for (ActivityManager.RunningAppProcessInfo aInfo : pInfo) {
                if (aInfo.importance
                        == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                    return aInfo.processName;
                }
            }
        }
        if (android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.LOLLIPOP) {
            PackageManager pm = GlobalUtils.getApp().getPackageManager();
            Intent intent = new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS);
            List<ResolveInfo> list =
                    pm.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
            Log.i("ProcessUtils", list.toString());
            if (list.size() <= 0) {
                Log.i("ProcessUtils",
                        "getForegroundProcessName: noun of access to usage information.");
                return "";
            }
            try {// Access to usage information.
                ApplicationInfo info =
                        pm.getApplicationInfo(GlobalUtils.getApp().getPackageName(), 0);
                AppOpsManager aom =
                        (AppOpsManager) GlobalUtils.getApp().getSystemService(Context.APP_OPS_SERVICE);
                //noinspection ConstantConditions
                if (aom.checkOpNoThrow(AppOpsManager.OPSTR_GET_USAGE_STATS,
                        info.uid,
                        info.packageName) != AppOpsManager.MODE_ALLOWED) {
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    GlobalUtils.getApp().startActivity(intent);
                }
                if (aom.checkOpNoThrow(AppOpsManager.OPSTR_GET_USAGE_STATS,
                        info.uid,
                        info.packageName) != AppOpsManager.MODE_ALLOWED) {
                    Log.i("ProcessUtils",
                            "getForegroundProcessName: refuse to device usage stats.");
                    return "";
                }
                UsageStatsManager usageStatsManager = (UsageStatsManager) GlobalUtils.getApp()
                        .getSystemService(Context.USAGE_STATS_SERVICE);
                List<UsageStats> usageStatsList = null;
                if (usageStatsManager != null) {
                    long endTime = System.currentTimeMillis();
                    long beginTime = endTime - 86400000 * 7;
                    usageStatsList = usageStatsManager
                            .queryUsageStats(UsageStatsManager.INTERVAL_BEST,
                                    beginTime, endTime);
                }
                if (usageStatsList == null || usageStatsList.isEmpty()) return "";
                UsageStats recentStats = null;
                for (UsageStats usageStats : usageStatsList) {
                    if (recentStats == null
                            || usageStats.getLastTimeUsed() > recentStats.getLastTimeUsed()) {
                        recentStats = usageStats;
                    }
                }
                return recentStats == null ? null : recentStats.getPackageName();
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        }
        return "";
    }

    /**
     * Return all background processes.
     * <p>Must hold {@code <uses-permission android:name="android.permission.KILL_BACKGROUND_PROCESSES" />}</p>
     *
     * @return all background processes
     */
    @RequiresPermission(KILL_BACKGROUND_PROCESSES)
    public static Set<String> getAllBackgroundProcesses() {
        ActivityManager am =
                (ActivityManager) GlobalUtils.getApp().getSystemService(Context.ACTIVITY_SERVICE);
        //noinspection ConstantConditions
        List<ActivityManager.RunningAppProcessInfo> info = am.getRunningAppProcesses();
        Set<String> set = new HashSet<>();
        if (info != null) {
            for (ActivityManager.RunningAppProcessInfo aInfo : info) {
                Collections.addAll(set, aInfo.pkgList);
            }
        }
        return set;
    }

    /**
     * Kill all background processes.
     * <p>Must hold {@code <uses-permission android:name="android.permission.KILL_BACKGROUND_PROCESSES" />}</p>
     *
     * @return background processes were killed
     */
    @RequiresPermission(KILL_BACKGROUND_PROCESSES)
    public static Set<String> killAllBackgroundProcesses() {
        ActivityManager am =
                (ActivityManager) GlobalUtils.getApp().getSystemService(Context.ACTIVITY_SERVICE);
        //noinspection ConstantConditions
        List<ActivityManager.RunningAppProcessInfo> info = am.getRunningAppProcesses();
        Set<String> set = new HashSet<>();
        if (info == null) return set;
        for (ActivityManager.RunningAppProcessInfo aInfo : info) {
            for (String pkg : aInfo.pkgList) {
                am.killBackgroundProcesses(pkg);
                set.add(pkg);
            }
        }
        info = am.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo aInfo : info) {
            for (String pkg : aInfo.pkgList) {
                set.remove(pkg);
            }
        }
        return set;
    }

    /**
     * Kill background processes.
     * <p>Must hold {@code <uses-permission android:name="android.permission.KILL_BACKGROUND_PROCESSES" />}</p>
     *
     * @param packageName The name of the package.
     * @return {@code true}: success<br>{@code false}: fail
     */
    @RequiresPermission(KILL_BACKGROUND_PROCESSES)
    public static boolean killBackgroundProcesses(@NonNull final String packageName) {
        ActivityManager am =
                (ActivityManager) GlobalUtils.getApp().getSystemService(Context.ACTIVITY_SERVICE);
        //noinspection ConstantConditions
        List<ActivityManager.RunningAppProcessInfo> info = am.getRunningAppProcesses();
        if (info == null || info.size() == 0) return true;
        for (ActivityManager.RunningAppProcessInfo aInfo : info) {
            if (Arrays.asList(aInfo.pkgList).contains(packageName)) {
                am.killBackgroundProcesses(packageName);
            }
        }
        info = am.getRunningAppProcesses();
        if (info == null || info.size() == 0) return true;
        for (ActivityManager.RunningAppProcessInfo aInfo : info) {
            if (Arrays.asList(aInfo.pkgList).contains(packageName)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Return whether app running in the main process.
     *
     * @return {@code true}: yes<br>{@code false}: no
     */
    public static boolean isMainProcess() {
        return GlobalUtils.getApp().getPackageName().equals(GlobalUtils.getCurrentProcessName());
    }

    /**
     * Return the name of current process.
     *
     * @return the name of current process
     */
    public static String getCurrentProcessName() {
        return GlobalUtils.getCurrentProcessName();
    }

    /**
     * @return 获取所有正在运行的进程信息
     */
    public static ArrayList<ProcessBean> getRunningAppInfo() {

        ArrayList<ProcessBean> processBeans = new ArrayList<>();

        PackageManager packageManager = GlobalUtils.getApp().getPackageManager();

        List<PackageInfo> infoList = packageManager.getInstalledPackages(0);

        for (PackageInfo packageInfo : infoList) {
            if ((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0) {
                if ((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_STOPPED) == 0) {
                    //有一个过滤器
                    if (!ProcessConstants.filterApp.contains(packageInfo.packageName)) {
                            String processName = packageInfo.packageName;
                            //图片
                            Drawable icon = null;
                            //名字
                            String name = null;
                            //所占内存
                            long memSize = 0;
                            //是否是系统应用
                            boolean isSys = false;
                            try {
                                ApplicationInfo applicationInfo = packageManager.getApplicationInfo(processName, 0);
                                //获取图片
                                icon = applicationInfo.loadIcon(packageManager);
                                //获取名字
                                name = applicationInfo.loadLabel(packageManager).toString();
                                // 是系统进程
                                // 是用户进程
                                isSys = (applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == ApplicationInfo.FLAG_SYSTEM;

                            } catch (PackageManager.NameNotFoundException e) {
                                e.printStackTrace();
                                name = processName;
                                icon = GlobalUtils.getApp().getResources().getDrawable(R.drawable.ic_process_app_icon);
                                isSys = true;
                            }
                            processBeans.add(new ProcessBean()
                                    .setPackageName(processName)
                                    .setName(name)
                                    .setIcon(icon)
                                    .setMemSize(memSize)
                                    .setSystem(isSys));

                    }
                }
            }
        }
        return processBeans;
    }

    /**
     * 获取所有应用
     * @return
     */
    public static ArrayList<ProcessBean> getAllAppInfo() {

        ArrayList<ProcessBean> processBeans = new ArrayList<>();

        PackageManager packageManager = GlobalUtils.getApp().getPackageManager();

        List<PackageInfo> infoList = packageManager.getInstalledPackages(0);

        for (PackageInfo packageInfo : infoList) {
            if ((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0) {

                    //有一个过滤器
                    if (!ProcessConstants.filterApp.contains(packageInfo.packageName)) {
                        String processName = packageInfo.packageName;
                        //图片
                        Drawable icon = null;
                        //名字
                        String name = null;
                        //所占内存
                        long memSize = 0;
                        //是否是系统应用
                        boolean isSys = false;
                        try {
                            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(processName, 0);
                            //获取图片
                            icon = applicationInfo.loadIcon(packageManager);
                            //获取名字
                            name = applicationInfo.loadLabel(packageManager).toString();
                            // 是系统进程
                            // 是用户进程
                            isSys = (applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == ApplicationInfo.FLAG_SYSTEM;

                        } catch (PackageManager.NameNotFoundException e) {
                            e.printStackTrace();
                            name = processName;
                            icon = GlobalUtils.getApp().getResources().getDrawable(R.drawable.ic_process_app_icon);
                            isSys = true;
                        }
                        processBeans.add(new ProcessBean()
                                .setPackageName(processName)
                                .setName(name)
                                .setIcon(icon)
                                .setMemSize(memSize)
                                .setSystem(isSys));

                    }
            }
        }
        return processBeans;
    }

}
