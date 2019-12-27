package com.is.lib_util;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;

import androidx.annotation.NonNull;

import java.util.List;
import java.util.Locale;

/**
 *  多语言相关的工具类
 */
public class LanguageUtils {

    private static final String KEY_LOCALE          = "KEY_LOCALE";
    private static final String VALUE_FOLLOW_SYSTEM = "VALUE_FOLLOW_SYSTEM";

    private LanguageUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 应用系统语言。
     *
     * @param activityClz The class of activity will be started after apply system language.
     *                    activity类将在应用系统语言后启动。
     */
    public static void applySystemLanguage(final Class<? extends Activity> activityClz) {
        applyLanguage(Resources.getSystem().getConfiguration().locale, activityClz, true);
    }

    /**
     * 应用系统语言。
     *
     * @param activityClassName The full class name of activity will be started after apply system language.
     */
    public static void applySystemLanguage(final String activityClassName) {
        applyLanguage(Resources.getSystem().getConfiguration().locale, activityClassName, true);
    }

    /**
     * 应用语言。
     * @param locale     语言环境的语言。
     * @param activityClz  活动类将在应用系统语言后启动。
     *                      如果类为空，它将启动启动器活动。
     */
    public static void applyLanguage(@NonNull final Locale locale,
                                     final Class<? extends Activity> activityClz) {
        applyLanguage(locale, activityClz, false);
    }

    /**
     * Apply the language.
     *
     * @param locale            The language of locale.
     * @param activityClassName The class of activity will be started after apply system language.
     *                          It will start the launcher activity if the class name is null.
     */
    public static void applyLanguage(@NonNull final Locale locale,
                                     final String activityClassName) {
        applyLanguage(locale, activityClassName, false);
    }

    private static void applyLanguage(@NonNull final Locale locale,
                                      final Class<? extends Activity> activityClz,
                                      final boolean isFollowSystem) {
        if (activityClz == null) {
            applyLanguage(locale, "", isFollowSystem);
            return;
        }
        applyLanguage(locale, activityClz.getName(), isFollowSystem);
    }

    private static void applyLanguage(@NonNull final Locale locale,
                                      final String activityClassName,
                                      final boolean isFollowSystem) {
        if (isFollowSystem) {
            SPUtils.getInstance().put(KEY_LOCALE, VALUE_FOLLOW_SYSTEM);
        } else {
            String localLanguage = locale.getLanguage();
            String localCountry = locale.getCountry();
            SPUtils.getInstance().put(KEY_LOCALE, localLanguage + "$" + localCountry);
        }

        updateLanguage(GlobalUtils.getApp(), locale);

        Intent intent = new Intent();
        String realActivityClassName
                = TextUtils.isEmpty(activityClassName) ? getLauncherActivity() : activityClassName;
        intent.setComponent(new ComponentName(GlobalUtils.getApp(), realActivityClassName));
        intent.addFlags(
                Intent.FLAG_ACTIVITY_NEW_TASK
                        | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK
        );
        GlobalUtils.getApp().startActivity(intent);
    }

    static void applyLanguage(@NonNull final Activity activity) {
        final String spLocale = SPUtils.getInstance().getString(KEY_LOCALE);
        if (TextUtils.isEmpty(spLocale)) {
            return;
        }

        if (VALUE_FOLLOW_SYSTEM.equals(spLocale)) {
            Locale sysLocale = Resources.getSystem().getConfiguration().locale;
            updateLanguage(GlobalUtils.getApp(), sysLocale);
            updateLanguage(activity, sysLocale);
            return;
        }

        String[] language_country = spLocale.split("\\$");
        if (language_country.length != 2) {
            Log.e("LanguageUtils", "The string of " + spLocale + " is not in the correct format.");
            return;
        }

        Locale settingLocale = new Locale(language_country[0], language_country[1]);
        updateLanguage(GlobalUtils.getApp(), settingLocale);
        updateLanguage(activity, settingLocale);
    }

    private static void updateLanguage(Context context, Locale locale) {
        Resources resources = context.getResources();
        Configuration config = resources.getConfiguration();
        Locale contextLocale = config.locale;
        if (equals(contextLocale.getLanguage(), locale.getLanguage())
                && equals(contextLocale.getCountry(), locale.getCountry())) {
            return;
        }
        DisplayMetrics dm = resources.getDisplayMetrics();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            config.setLocale(locale);
            context.createConfigurationContext(config);
        } else {
            config.locale = locale;
        }
        resources.updateConfiguration(config, dm);
    }

    private static boolean equals(final CharSequence s1, final CharSequence s2) {
        if (s1 == s2) return true;
        int length;
        if (s1 != null && s2 != null && (length = s1.length()) == s2.length()) {
            if (s1 instanceof String && s2 instanceof String) {
                return s1.equals(s2);
            } else {
                for (int i = 0; i < length; i++) {
                    if (s1.charAt(i) != s2.charAt(i)) return false;
                }
                return true;
            }
        }
        return false;
    }

    private static String getLauncherActivity() {
        Intent intent = new Intent(Intent.ACTION_MAIN, null);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        intent.setPackage(GlobalUtils.getApp().getPackageName());
        PackageManager pm = GlobalUtils.getApp().getPackageManager();
        List<ResolveInfo> info = pm.queryIntentActivities(intent, 0);
        ResolveInfo next = info.iterator().next();
        if (next != null) {
            return next.activityInfo.name;
        }
        return "no launcher activity";
    }
}
