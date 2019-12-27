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
 * 2019-11-11 16:49
 * home键监听
 **/
public class HomeHelper {

    private final String TAG = HomeHelper.class.getSimpleName();

    private ArrayList<String> mStrings;

    private ArrayList<HomeIm> mIms;


    private static HomeHelper instance;

    private HomeReceiver mHomeReceiver;

    private boolean isRegister = false;

    public static HomeHelper getInstance() {
        if (instance == null) {
            synchronized (HomeHelper.class) {
                if (instance == null) {
                    instance = new HomeHelper();
                }
            }
        }
        return instance;
    }

    private HomeHelper() {
        mStrings = new ArrayList<>();
        mIms = new ArrayList<>();
        if (!isRegister) {
            IntentFilter filter = new IntentFilter(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
            mHomeReceiver = new HomeReceiver();
            BaseApplication.getApplication().registerReceiver(mHomeReceiver, filter);
            isRegister = true;
        }

    }


    public void register(HomeIm im) {
        if (mStrings.contains(im.getClass().getCanonicalName())) {
            return;
        }
        mIms.add(im);
        mStrings.add(im.getClass().getCanonicalName());
    }


    public void unRegister(HomeIm im) {
        if (mStrings.contains(im.getClass().getCanonicalName())) {
            mIms.remove(im);
            mStrings.remove(im.getClass().getCanonicalName());
        }
    }


    public interface HomeIm {
        void onHome();
    }


    private boolean isM = true;

    private class HomeReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent != null) {
                String action = intent.getAction();
                if (TextUtils.isEmpty(action)) {
                    return;
                }
                if (Intent.ACTION_CLOSE_SYSTEM_DIALOGS.equals(action)) {
                    String reason = intent.getStringExtra("reason");
                    if (!TextUtils.isEmpty(reason)) {
                        if (reason.equals("recentapps")) {
                            isM = false;
                        } else if ("homekey".equals(reason)) {
                            if (isM) {
                                BaseLogUtil.D(TAG, "home");
                                for (HomeIm mIm : mIms) {
                                    if (mIm != null) {
                                        mIm.onHome();
                                    }
                                }
                            }
                            isM = true;
                        }
                    }

                }
            }
        }
    }
}
