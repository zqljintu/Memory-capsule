package com.xdandroid.hellodaemon;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class KeepLiveService extends Service {

    private KeepLiveBarManager mBarManager;

    public static void start(Context context) {
        try {
            Intent intent = new Intent(context, KeepLiveService.class);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                context.startForegroundService(intent);
            }
            context.startService(intent);
        } catch (Exception e) {
            //BugFix:java.lang.IllegalStateException: Not allowed to start service Intent
        }
    }

    public static void stop(Context context) {
        try {
            Intent intent = new Intent(context, KeepLiveService.class);
            context.stopService(intent);
        } catch (Exception e) {
            //BugFix:java.lang.IllegalStateException: Not allowed to start service Intent
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("KeepLiveService", "[KeepLiveService] onCreate");
        mBarManager = new KeepLiveBarManager(this);
        mBarManager.showNotification();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("KeepLiveService", "[KeepLiveService] onDestroy");
        if (mBarManager != null) {
            mBarManager.removeNotification();
        }
    }
}
