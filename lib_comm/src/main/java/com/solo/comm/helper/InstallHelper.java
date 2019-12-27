package com.solo.comm.helper;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.TextUtils;

import com.solo.base.BaseApplication;
import com.solo.base.BaseLogUtil;

import java.util.ArrayList;

/**
 * Create by Totoro
 * 2019-11-11 17:26
 * 安装卸载监听
 **/
public class InstallHelper {

    private final String TAG = InstallHelper.class.getSimpleName();

    private static InstallHelper instance;

    private InstallReceiver mInstallReceiver;

    private boolean isRegister = false;

    private ArrayList<String> mStrings;

    private ArrayList<InstallHelper.InstallIm> mIms;


    public static InstallHelper getInstance() {
        if (instance == null) {
            synchronized (InstallHelper.class) {
                if (instance == null) {
                    instance = new InstallHelper();
                }
            }
        }
        return instance;
    }

    private final String ACTION_PACKAGE_ADDED = "android.intent.action.PACKAGE_ADDED";
    private final String ACTION_PACKAGE_REPLACED = "android.intent.action.PACKAGE_REPLACED";
    private final String ACTION_PACKAGE_REMOVED = "android.intent.action.PACKAGE_REMOVED";


    private InstallHelper() {
        mIms = new ArrayList<>();
        mStrings = new ArrayList<>();
        if (!isRegister) {
            IntentFilter filter = new IntentFilter();
            mInstallReceiver = new InstallReceiver();
            filter.addAction(ACTION_PACKAGE_ADDED);
            filter.addAction(ACTION_PACKAGE_REPLACED);
            filter.addAction(ACTION_PACKAGE_REMOVED);
            filter.addDataScheme("package");
            BaseApplication.getApplication().registerReceiver(mInstallReceiver, filter);
            isRegister = true;
        }
    }


    public void register(InstallHelper.InstallIm im) {
        if (mStrings.contains(im.getClass().getCanonicalName())) {
            return;
        }
        mIms.add(im);
        mStrings.add(im.getClass().getCanonicalName());
    }


    public void unRegister(InstallHelper.InstallIm im) {
        if (mStrings.contains(im.getClass().getCanonicalName())) {
            mIms.remove(im);
            mStrings.remove(im.getClass().getCanonicalName());
        }
    }


    public interface InstallIm {
        void onInstall(InstallInfo info);

        void onUnInstall(InstallInfo info);
    }

    private InstallInfo info;

    private class InstallReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent != null) {
                String action = intent.getAction();
                if (!TextUtils.isEmpty(action)) {
                    if (action.equals(ACTION_PACKAGE_ADDED)) {
                        //安装
                        BaseLogUtil.D(TAG, "安装");
                        String packageName = intent.getDataString();
                        String[] pkgNames = packageName.split(":");
                        info = new InstallInfo(pkgNames[1], true);
                        for (InstallIm mIm : mIms) {
                            if (mIm != null) mIm.onInstall(info);
                        }
                    } else if (action.equals(ACTION_PACKAGE_REMOVED)) {
                        //卸载
                        BaseLogUtil.D(TAG, "卸载");
                        String packageName = intent.getDataString();
                        String[] pkgNames = packageName.split(":");
                        info = new InstallInfo(pkgNames[1], false);
                        for (InstallIm mIm : mIms) {
                            if (mIm != null) mIm.onUnInstall(info);
                        }
                    }
                }
            }
        }
    }

}
