package com.is.lib_util;

import androidx.annotation.NonNull;

/**
 * @author 夜斗
 * @date 2019/11/14
 * <p>
 * 与应用程序相关的工具类
 */
public class AppUtils {

    private AppUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 注册应用程序更改监听器的状态。
     *
     * @param obj      注册的对象
     * @param listener 监听器
     */
    public static void registerAppStatusChangedListener(@NonNull final Object obj,
                                                        @NonNull final GlobalUtils.OnAppStatusChangedListener listener) {
        GlobalUtils.getActivityLifecycle().addOnAppStatusChangedListener(obj, listener);
    }


    /**
     * 解除应用程序更改监听器
     *
     * @param obj 需要解除的对象
     */
    public static void unregisterAppStatusChangedListener(@NonNull final Object obj) {
        GlobalUtils.getActivityLifecycle().removeOnAppStatusChangedListener(obj);
    }
}
