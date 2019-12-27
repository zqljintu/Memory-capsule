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
 * 2019-11-11 17:06
 * 屏幕监听
 **/
public class ScreenHelper {

    private final String TAG = ScreenHelper.class.getSimpleName();

    private ArrayList<String> mStrings;

    private ArrayList<ScreenHelper.ScreenIm> mIms;

    private ScreenReceiver mScreenReceiver;

    private boolean isRegister;

    private static ScreenHelper instance;

    public static ScreenHelper getInstance() {
        if (instance == null) {
            synchronized (ScreenHelper.class) {
                if (instance == null) {
                    instance = new ScreenHelper();
                }
            }
        }
        return instance;
    }


    private ScreenHelper() {
        mStrings = new ArrayList<>();
        mIms = new ArrayList<>();
        if (!isRegister) {
            IntentFilter filter = new IntentFilter();
            mScreenReceiver = new ScreenReceiver();
            filter.addAction(Intent.ACTION_SCREEN_ON);
            filter.addAction(Intent.ACTION_SCREEN_OFF);
            filter.addAction(Intent.ACTION_USER_PRESENT);
            BaseApplication.getApplication().registerReceiver(mScreenReceiver, filter);
            isRegister = true;
        }
    }

    public void register(ScreenHelper.ScreenIm im) {
        if (mStrings.contains(im.getClass().getCanonicalName())) {
            return;
        }
        mIms.add(im);
        mStrings.add(im.getClass().getCanonicalName());
    }


    public void unRegister(ScreenHelper.ScreenIm im) {
        if (mStrings.contains(im.getClass().getCanonicalName())) {
            mIms.remove(im);
            mStrings.remove(im.getClass().getCanonicalName());
        }
    }

    public interface ScreenIm {
        void onScreenOn();

        void onScreenOff();

        void onScreenPresent();
    }


    private class ScreenReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent != null) {
                String action = intent.getAction();
                if (!TextUtils.isEmpty(action)) {
                    if (Intent.ACTION_SCREEN_OFF.equalsIgnoreCase(action)) {
                        BaseLogUtil.D(TAG, "screen_off");
                        for (ScreenIm mIm : mIms) {
                            if (mIm != null) mIm.onScreenOff();
                        }
                    } else if (Intent.ACTION_SCREEN_ON.equalsIgnoreCase(action)) {
                        BaseLogUtil.D(TAG, "screen_on");
                        for (ScreenIm mIm : mIms) {
                            if (mIm != null) mIm.onScreenOn();
                        }
                    } else if (Intent.ACTION_USER_PRESENT.equalsIgnoreCase(action)) {
                        BaseLogUtil.D(TAG, "screen_present");
                        for (ScreenIm mIm : mIms) {
                            if (mIm != null) mIm.onScreenPresent();
                        }
                    }
                }
            }
        }
    }
}
