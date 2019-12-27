package com.solo.base;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import androidx.multidex.MultiDexApplication;

import com.is.lib_util.GlobalUtils;
import com.solo.base.utils.AppUtil;
import com.tencent.bugly.crashreport.CrashReport;
import com.tencent.mmkv.MMKV;

import java.util.ArrayList;

/**
 * Create by Totoro
 * 2019-11-07 14:42
 **/
public abstract class BaseApplication extends MultiDexApplication implements Application.ActivityLifecycleCallbacks {

    private static boolean isForeground = false;
    private static BaseApplication mApplication;
    private final String TAG = BaseApplication.class.getSimpleName();
    private int mActivityCount = 0;

    public static BaseApplication getApplication() {
        return mApplication;
    }

    //判断是否在前台
    public static boolean isApplicationForeground() {
        return isForeground;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        CrashReport.initCrashReport(getApplicationContext(),
                "b68477cb03",
                true);
        mApplication = this;
        GlobalUtils.init(this);
        initLog();
        //DoraemonKit.install(this);
        //进程判断
        if (AppUtil.getProcessName().equals(getString(R.string.key_0))) {
            registerActivityLifecycleCallbacks(this);
            String rootDir = MMKV.initialize(this);
            BaseLogUtil.D(TAG, "MMKV>>rootDir" + rootDir);
            init();
        }

    }

    protected abstract void init();


    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        BaseLogUtil.D(TAG, activity.getClass().getSimpleName() + ">>onActivityCreated");
    }

    @Override
    public void onActivityStarted(Activity activity) {
        BaseLogUtil.D(TAG, activity.getClass().getSimpleName() + ">>onActivityStarted");
        mActivityCount++;
        isForeground = true;
    }

    @Override
    public void onActivityResumed(Activity activity) {
        BaseLogUtil.D(TAG, activity.getClass().getSimpleName() + ">>onActivityResumed");

    }

    @Override
    public void onActivityPaused(Activity activity) {
        BaseLogUtil.D(TAG, activity.getClass().getSimpleName() + ">>onActivityPaused");
    }

    @Override
    public void onActivityStopped(Activity activity) {
        BaseLogUtil.D(TAG, activity.getClass().getSimpleName() + ">>onActivityStopped");
        mActivityCount--;
        if (mActivityCount < 1) {
            isForeground = false;
        }
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        BaseLogUtil.D(TAG, activity.getClass().getSimpleName() + ">>onActivitySaveInstanceState");
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        BaseLogUtil.D(TAG, activity.getClass().getSimpleName() + ">>onActivityDestroyed");
    }

    private void initLog() {
        BaseLogUtil.getConfig()
                .setLogSwitch(BuildConfig.isLogDebug)// 设置 log 总开关，包括输出到控制台和文件，默认开
                .setConsoleSwitch(BuildConfig.isLogDebug)// 设置是否输出到控制台开关，默认开
                .setGlobalTag("new_security")// 设置 log 全局标签，默认为空
                // 当全局标签不为空时，我们输出的 log 全部为该 tag，
                // 为空时，如果传入的 tag 为空那就显示类名，否则显示 tag
                .setLogHeadSwitch(true)// 设置 log 头信息开关，默认为开
                .setBorderSwitch(true)// 输出日志是否带边框开关，默认开
                .setSingleTagSwitch(true)// 一条日志仅输出一条，默认开，为美化 AS 3.1 的 Logcat
                .setConsoleFilter(BaseLogUtil.V)// log 的控制台过滤器，和 logcat 过滤器同理，默认 Verbose
                .setFileFilter(BaseLogUtil.V)// log 文件过滤器，和 logcat 过滤器同理，默认 Verbose
                .setSaveDays(1)// 设置日志可保留天数，默认为 -1 表示无限时长
                //ArrayList 格式化器，默认支持 Array, Throwable, Bundle, Intent 的格式化输出
                .addFormatter(new BaseLogUtil.IFormatter<ArrayList>() {
                    @Override
                    public String format(ArrayList arrayList) {
                        return "BaseLogUtil Formatter ArrayList { " + arrayList.toString() + " }";
                    }
                });
    }

    /**
     * @param o 内存泄漏检测
     */
    public abstract void whatObj(Object o);

}
