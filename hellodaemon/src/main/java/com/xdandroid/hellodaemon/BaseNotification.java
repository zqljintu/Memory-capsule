package com.xdandroid.hellodaemon;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.os.Build;

import androidx.core.app.NotificationManagerCompat;

public abstract class BaseNotification extends BroadcastReceiver {

    protected final NotificationManagerCompat mNotificationManager;

    protected boolean mStarted = false;

    public BaseNotification(Service service) {
        mNotificationManager = NotificationManagerCompat.from(service);
    }

    public abstract Notification createNotification();

    public abstract int getNotificationId();

    public abstract Service getService();

    public void initNotificationChannel(String channelString) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(String.valueOf(getNotificationId()),
                    channelString,
                    NotificationManager.IMPORTANCE_LOW);
            channel.setShowBadge(false);
            channel.setSound(null, null);
            mNotificationManager.createNotificationChannel(channel);
        }
    }

    /**
     * 更新通知管理的通知
     */
    protected void updateNotification() {
        Notification notification = createNotification();
        if (notification != null) {
            try {
                mNotificationManager.notify(getNotificationId(), notification);
            } catch (RuntimeException e) {
                // FIXME:Fix RuntimeException:bad array lengths
            }
        }
    }

    /**
     * 移除通知管理的通知
     */
    public void removeNotification() {
        if (mStarted) {
            mStarted = false;
            try {
                mNotificationManager.cancel(getNotificationId());
                getService().unregisterReceiver(this);
            } catch (IllegalArgumentException ex) {
                // ignore if the receiver is not registered.
            }
            getService().stopForeground(true);
        }
    }

}
