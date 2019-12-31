package com.zql.comm.helper;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.text.TextUtils;

import com.zql.base.BaseApplication;
import com.zql.base.BaseLogUtil;

import java.util.ArrayList;

/**
 * Create by Totoro
 * 2019-11-11 17:26
 * 电池监听
 **/
public class PowerHelper {
    private final String TAG = PowerHelper.class.getSimpleName();

    private ArrayList<String> mStrings;

    private ArrayList<PowerHelper.PowerIm> mIms;

    private PowerReceiver mPowerReceiver;

    private boolean isRegister = false;

    private static PowerHelper instance;

    private PowerInfo mInfo;

    public static PowerHelper getInstance() {
        if (instance == null) {
            synchronized (PowerHelper.class) {
                if (instance == null) {
                    instance = new PowerHelper();
                }
            }
        }
        return instance;
    }


    private PowerHelper() {
        mIms = new ArrayList<>();
        mStrings = new ArrayList<>();
        if (!isRegister) {
            IntentFilter filter = new IntentFilter();
            mPowerReceiver = new PowerReceiver();
            filter.addAction(Intent.ACTION_BATTERY_CHANGED);
            filter.addAction(Intent.ACTION_POWER_CONNECTED);
            filter.addAction(Intent.ACTION_POWER_DISCONNECTED);
            BaseApplication.getApplication().registerReceiver(mPowerReceiver, filter);
            isRegister = true;
        }
    }

    public void register(PowerHelper.PowerIm im) {
        if (mStrings.contains(im.getClass().getCanonicalName())) {
            return;
        }
        mIms.add(im);
        mStrings.add(im.getClass().getCanonicalName());
    }


    public void unRegister(PowerHelper.PowerIm im) {
        if (mStrings.contains(im.getClass().getCanonicalName())) {
            mIms.remove(im);
            mStrings.remove(im.getClass().getCanonicalName());
        }
    }


    public interface PowerIm {
        void onPowerConnect();

        void onPowerDisconnect();

        void onPowerChange(PowerInfo info);
    }


    private class PowerReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent != null) {
                String action = intent.getAction();
                if (!TextUtils.isEmpty(action)) {
                    if (Intent.ACTION_BATTERY_CHANGED.equalsIgnoreCase(action)) {
                        BaseLogUtil.D(TAG, "charge change");
                        int current = intent.getExtras().getInt("level");// 获得当前电量
                        int total = intent.getExtras().getInt("scale");// 获得总电量
                        int temp = intent.getIntExtra("temperature", 0);
                        int state = intent.getIntExtra("status", BatteryManager.BATTERY_STATUS_UNKNOWN);
                        if (mInfo == null) {
                            mInfo = new PowerInfo(current, total, temp, state);
                        } else {
                            mInfo.setCurrent(current);
                            mInfo.setTemp(temp);
                            mInfo.setState(state);
                            mInfo.setTotal(total);
                        }
                        for (PowerIm mIm : mIms) {
                            if (mIm != null) mIm.onPowerChange(mInfo);
                        }
                    } else if (Intent.ACTION_POWER_CONNECTED.equalsIgnoreCase(action)) {
                        BaseLogUtil.D(TAG, "power connected");
                        for (PowerIm mIm : mIms) {
                            if (mIm != null) mIm.onPowerConnect();
                        }
                    } else if (Intent.ACTION_POWER_DISCONNECTED.equalsIgnoreCase(action)) {
                        BaseLogUtil.D(TAG, "power disconnected");
                        for (PowerIm mIm : mIms) {
                            if (mIm != null) mIm.onPowerDisconnect();
                        }
                    }
                }
            }
        }
    }
}
