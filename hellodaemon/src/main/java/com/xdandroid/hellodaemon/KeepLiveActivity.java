package com.xdandroid.hellodaemon;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PowerManager;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import java.util.Observable;
import java.util.Observer;

public class KeepLiveActivity extends Activity implements Observer {

    public static void start(Context context) {
        Intent intent = new Intent(context, KeepLiveActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
        try {
            pendingIntent.send();
        } catch (Exception e) {
            e.printStackTrace();
            context.startActivity(intent);
        }
    }

    public static void stop(Context context) {
        Intent intent = new Intent(FinishHelper.getAction(context));
        context.sendBroadcast(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FinishHelper.getInstance(this).addObserver(this);
        //设定一像素的activity
        Window window = getWindow();
        window.setGravity(Gravity.START | Gravity.TOP);
        WindowManager.LayoutParams params = window.getAttributes();
        params.x = 0;
        params.y = 0;
        params.height = 1;
        params.width = 1;
        window.setAttributes(params);
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkScreenOn("onResume");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        FinishHelper.getInstance(this).deleteObserver(this);
    }

    @Override
    public void update(Observable observable, Object o) {
        if (observable instanceof FinishHelper) {
            finish();
        }
    }

    private void checkScreenOn(String methodName) {
        PowerManager pm = (PowerManager) getApplicationContext().getSystemService(Context.POWER_SERVICE);
        boolean isScreenOn = pm.isScreenOn();
        if (isScreenOn) {
            finish();
        }
    }

}
