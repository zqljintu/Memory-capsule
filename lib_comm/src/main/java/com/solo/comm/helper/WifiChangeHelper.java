package com.solo.comm.helper;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;

import com.solo.base.BaseApplication;

import java.util.ArrayList;

import static android.net.wifi.WifiManager.EXTRA_WIFI_STATE;
import static android.net.wifi.WifiManager.WIFI_STATE_DISABLED;
import static android.net.wifi.WifiManager.WIFI_STATE_DISABLING;
import static android.net.wifi.WifiManager.WIFI_STATE_ENABLED;
import static android.net.wifi.WifiManager.WIFI_STATE_ENABLING;
import static android.net.wifi.WifiManager.WIFI_STATE_UNKNOWN;

/**
 * Create by 黎曼
 * 2019-11-25 14:01
 **/
public class WifiChangeHelper {
    private final String TAG = WifiChangeHelper.class.getSimpleName();

    private static WifiChangeHelper instance;

    private WiFiReceiver mWiFiReceiver;

    private boolean isRegister = false;

    private ArrayList<String> mStrings;

    private ArrayList<WifiChangeIm> mIms;

    public static WifiChangeHelper getInstance() {
        if (instance == null) {
            synchronized (WifiChangeHelper.class) {
                if (instance == null) {
                    instance = new WifiChangeHelper();
                }
            }
        }
        return instance;
    }

    private WifiChangeHelper() {
        mIms = new ArrayList<>();
        mStrings = new ArrayList<>();
        if (!isRegister){
            mWiFiReceiver = new WiFiReceiver();
            mWiFiReceiver.register();
            isRegister = true;
        }
    }

    public void register(WifiChangeIm im){
        if (mStrings.contains(im.getClass().getCanonicalName())){
            return;
        }
        mIms.add(im);
        mStrings.add(im.getClass().getCanonicalName());
    }

    public void unRegister(WifiChangeIm im){
        if (mStrings.contains(im.getClass().getCanonicalName())) {
            mIms.remove(im);
            mStrings.remove(im.getClass().getCanonicalName());
        }
    }

    public interface WifiChangeIm {
        //Wifi连上
        void onConnected();
        //Wifi断开
        void onBreak();
    }

    public class WiFiReceiver extends BroadcastReceiver {
        boolean isRegister = false;

        public void register() {
            if (!isRegister) {
                IntentFilter filter = new IntentFilter();
                filter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
//                filter.addAction("android.net.wifi.STATE_CHANGE");
//                filter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
                BaseApplication.getApplication().registerReceiver(this, filter);
                isRegister = true;
            }
        }

        public void unRegister() {
            if (isRegister) {
                BaseApplication.getApplication().unregisterReceiver(this);
                isRegister = false;
            }
        }

        private boolean mWiFiDisabled = false;

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action != null && action.equals(WifiManager.WIFI_STATE_CHANGED_ACTION)) {
                switch (intent.getIntExtra(EXTRA_WIFI_STATE, WIFI_STATE_UNKNOWN)) {
                    case WIFI_STATE_DISABLED://WIFI断开
                        mWiFiDisabled = true;
                        for (WifiChangeIm im : mIms){
                            if (im != null) im.onBreak();
                        }
                        break;
                    case WIFI_STATE_DISABLING:
                        break;
                    case WIFI_STATE_ENABLED://WiFi连接
                        if (mWiFiDisabled){//Wifi断开过
                            mWiFiDisabled = false;
                            for (WifiChangeIm im : mIms){
                                if (im != null) im.onConnected();
                            }
                        }
                        break;
                    case WIFI_STATE_ENABLING:
                    case WIFI_STATE_UNKNOWN:
                        break;
                }
            }
        }
    }
}
