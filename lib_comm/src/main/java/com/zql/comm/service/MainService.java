package com.zql.comm.service;

import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.zql.base.BaseLogUtil;
import com.zql.comm.live.LiveManager;
import com.xdandroid.hellodaemon.AbsWorkService;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * 2019-11-11 13:40
 **/
public class MainService extends AbsWorkService {

    public static boolean sShouldStopService;
    public static Disposable sDisposable;


    @Override
    public Boolean shouldStopService(Intent intent, int flags, int startId) {
        return sShouldStopService;
    }

    @Override
    public void startWork(Intent intent, int flags, int startId) {
        LiveManager.getInstance().run(this);
        sDisposable = Observable
                .interval(30, TimeUnit.SECONDS)
                //取消任务时取消定时唤醒
                .doOnDispose(() -> {
                    BaseLogUtil.D("====", "保存数据到磁盘。");
                    cancelJobAlarmSub();
                })
                .subscribe(count -> {
                    BaseLogUtil.D("====", "每 30 秒采集一次数据... count = " + count);
                    if (count > 0 && count % 18 == 0)
                        BaseLogUtil.D("====", "保存数据到磁盘。 saveCount = " + (count / 18 - 1));
                }, throwable -> {

                });
    }

    @Override
    public void stopWork(Intent intent, int flags, int startId) {
        stopService();
    }

    @Override
    public Boolean isWorkRunning(Intent intent, int flags, int startId) {
        return sDisposable != null && !sDisposable.isDisposed();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent, Void alwaysNull) {
        return null;
    }

    @Override
    public void onServiceKilled(Intent rootIntent) {

    }


    public static void stopService() {
        //我们现在不再需要服务运行了, 将标志位置为 true
        sShouldStopService = true;
        //取消对任务的订阅
        if (sDisposable != null) sDisposable.dispose();
        //取消 Job / Alarm / Subscription
        cancelJobAlarmSub();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();

    }
}
