package com.xdandroid.hellodaemon;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.TextUtils;

import java.util.Observable;
import java.util.Observer;

public class FinishHelper extends Observable {

    private static final Object sLock = new Object();
    private static FinishHelper sInstance;
    private Context mContext;
    private FinishReceiver mReceiver = new FinishReceiver();

    private FinishHelper(Context context) {
        mContext = context.getApplicationContext();// 一定要取ApplicationContext，避免资源无法释放
    }

    public static String getAction(Context context) {
        return context.getPackageName() + ".keep";
    }

    public static FinishHelper getInstance(Context context) {
        synchronized (sLock) {
            if (null == sInstance) {
                sInstance = new FinishHelper(context);
            }
            return sInstance;
        }
    }

    @Override
    public void addObserver(Observer observer) {
        super.addObserver(observer);
        if (countObservers() > 0 && !mReceiver.isRegisted()) {
            mReceiver.register(mContext);
        }
    }

    @Override
    public synchronized void deleteObserver(Observer observer) {
        super.deleteObserver(observer);
        if (countObservers() == 0 && mReceiver.isRegisted())
            mReceiver.unregister(mContext);
    }

    class FinishReceiver extends BroadcastReceiver {
        private boolean mRegisted = false;

        public boolean isRegisted() {
            return mRegisted;
        }

        public void unregister(Context context) {
            if (context != null) {
                context.unregisterReceiver(this);
                mRegisted = false;
            }
        }

        public void register(Context context) {
            if (context != null) {
                IntentFilter finishFilter = new IntentFilter();
                finishFilter.addAction(getAction(context));
                finishFilter.setPriority(1000);
                context.registerReceiver(this, finishFilter);
                mRegisted = true;
            }
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            if (TextUtils.equals(getAction(context), intent.getAction())) {
                setChanged();
                notifyObservers();
            }
        }
    }
}
