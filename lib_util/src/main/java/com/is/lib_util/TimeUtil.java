package com.is.lib_util;

public class TimeUtil {
    /**
     *
     * @param time 格式化秒 不是毫秒
     */
    public static long[] formatSecond(long time) {

        long hour = time / 3600;

        long minute = (time % 3600) / 60;//分钟

        long second = (time % 3600) % 60;//秒

        return new long[]{hour, minute, second};
    }


}
