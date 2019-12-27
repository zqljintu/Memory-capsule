package com.is.lib_util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;

import java.util.ArrayList;
import java.util.List;

import static com.is.lib_util.ActivityUtils.startActivity;

/**
 * @author 黎曼
 * @date 2019/11/18
 */
public class PackUtil {
    //获取非系统应用包信息(排除本应用)
    public static List<PackageInfo> getInstallPacks(Context context){
        List<PackageInfo> result = new ArrayList<>();
        for (PackageInfo pack: context.getApplicationContext().getPackageManager().getInstalledPackages(0)){
            if ((pack.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0 && !pack.packageName.equals(context.getPackageName())){
                result.add(pack);
            }
        }
        return result;
    }

    //获取包名icon
    @SuppressLint("WrongConstant")
    public static Drawable getApplicationIcon(Context context, String packName){
        ApplicationInfo info;
        PackageManager pm = context.getApplicationContext().getPackageManager();
        try {
            info = pm.getApplicationInfo(packName, PackageManager.COMPONENT_ENABLED_STATE_DEFAULT);
            return info.loadIcon(pm);
        }catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return context.getApplicationContext().getResources().getDrawable(R.drawable.ic_place);
    }

    //获取包名Label
    @SuppressLint("WrongConstant")
    public static String getApplicationLable(Context context, String packName){
        try {
            PackageManager pm = context.getApplicationContext().getPackageManager();
            ApplicationInfo info = pm.getApplicationInfo(packName, PackageManager.COMPONENT_ENABLED_STATE_DEFAULT);
            return info.loadLabel(pm).toString();
        }catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return packName;
        }
    }

    //卸载应用
    public static void uninstall(String packName){
        Uri uri = Uri.fromParts("package", packName, null);
        Intent intent = new Intent(Intent.ACTION_DELETE, uri);
        startActivity(intent);
    }
}
