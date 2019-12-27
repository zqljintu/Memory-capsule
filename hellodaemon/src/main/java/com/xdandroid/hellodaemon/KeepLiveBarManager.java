package com.xdandroid.hellodaemon;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;

import androidx.core.app.NotificationCompat;

/**
 * Created by pandajoy on 17-1-2.
 */

public class KeepLiveBarManager extends BaseNotification {

    private static final String TAG = KeepLiveBarManager.class.getName();
    private static final String ACTION_CONTENT = TAG + ".CONTENT";
    private static final int NOTIFICATION_ID = 413;
    private static final int REQUEST_CODE = 101;

    private final PendingIntent mContentIntent;

    private final KeepLiveService mService;

    private PackageManager mPm;

    public KeepLiveBarManager(KeepLiveService service) {
        super(service);
        mService = service;
        mPm = service.getPackageManager();
        String pkg = mService.getPackageName();
        mContentIntent = PendingIntent.getBroadcast(mService, REQUEST_CODE,
                new Intent(ACTION_CONTENT).setPackage(pkg), PendingIntent.FLAG_CANCEL_CURRENT);

        // Cancel all notifications to handle the case where the Service was killed and
        // restarted by the system.
        try {
            mNotificationManager.cancelAll();
        } catch (NullPointerException e) {

        } catch (SecurityException e) {

        }
    }

    /**
     * 显示通知管理的通知
     */
    public void showNotification() {
        initNotificationChannel("KeepLiveBarManager");
        if (!mStarted) {
            startNotification();
        } else {
            updateNotification();
        }
    }

    /**
     * 启动通知管理的通知
     */
    private void startNotification() {
        Notification notification = createNotification();
        if (notification != null) {
            IntentFilter filter = new IntentFilter();
            filter.addAction(ACTION_CONTENT);
            mService.registerReceiver(this, filter);
            mService.startForeground(NOTIFICATION_ID, notification);
            mStarted = true;
        }
    }

    @Override
    public int getNotificationId() {
        return NOTIFICATION_ID;
    }

    @Override
    public Service getService() {
        return mService;
    }

    @Override
    public Notification createNotification() {
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(mService, String.valueOf(getNotificationId()));

        notificationBuilder.setSmallIcon(R.mipmap.ic_notify)
                .setContentIntent(mContentIntent)
                .setWhen(0)
                .setUsesChronometer(false)
                .setContentTitle("1111")//设置通知标题
                .setContentText("22222")//设置通知内容
                .setAutoCancel(true) //用户触摸时，自动关闭
                .setOngoing(true);//设置处于运行状态

        return notificationBuilder.build();
    }


    @Override
    public void onReceive(Context context, Intent intent) {
        final String action = intent.getAction();
        if (ACTION_CONTENT.equals(action)) {

        }
    }

}
