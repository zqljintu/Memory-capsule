package com.zql.base.sp;

import com.tencent.mmkv.MMKV;

/**
 * 2019-11-07 15:02
 **/
public final class SpUtil {

    public static void putString(String key, String value) {
        MMKV.defaultMMKV().encode(key, value);
    }

    public static String getString(String key) {
        return MMKV.defaultMMKV().decodeString(key, "");
    }

    public static void putInt(String key, int value) {
        MMKV.defaultMMKV().encode(key, value);
    }

    public static int getInt(String key) {
        return MMKV.defaultMMKV().decodeInt(key, -1);
    }

    public static void putLong(String key, long value) {
        MMKV.defaultMMKV().encode(key, value);
    }

    public static long getLong(String key) {
        return MMKV.defaultMMKV().decodeLong(key, -1);
    }

    public static long getLong(String key, long defValue) {
        return MMKV.defaultMMKV().decodeLong(key, defValue);
    }

    public static void putFloat(String key, float value) {
        MMKV.defaultMMKV().encode(key, value);
    }

    public static float getFloat(String key) {
        return MMKV.defaultMMKV().decodeFloat(key, -1.0f);
    }


    public static void putBoolean(String key, boolean value) {
        MMKV.defaultMMKV().encode(key, value);
    }

    public static boolean getBoolean(String key) {
        return MMKV.defaultMMKV().decodeBool(key, false);
    }

    public static boolean getBoolean(String key, boolean defaultValue) {
        return MMKV.defaultMMKV().decodeBool(key, defaultValue);
    }

}
