package com.zql.base.utils;

import android.annotation.SuppressLint;
import android.os.Build;
import android.widget.Toast;

import com.zql.base.BaseApplication;
import com.zql.base.helper.RxHelper;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;


public class ToastUtil {

    private static Toast mToast;

    public static void showToast(final String msg) {
        Disposable subscribe = Observable.just(1)
                .compose(RxHelper.<Integer>rxSchedulerHelper())
                .subscribe(new Consumer<Integer>() {
                    @SuppressLint("ShowToast")
                    @Override
                    public void accept(Integer integer) throws Exception {
                        //9.0之上 已做优化
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                            Toast.makeText(BaseApplication.getApplication(), msg, Toast.LENGTH_LONG).show();
                        } else {
                            if (mToast == null) {
                                mToast = Toast.makeText(BaseApplication.getApplication(), msg, Toast.LENGTH_LONG);
                            } else {
                                mToast.setText(msg);
                            }
                            mToast.show();
                        }
                    }
                });
    }
}
