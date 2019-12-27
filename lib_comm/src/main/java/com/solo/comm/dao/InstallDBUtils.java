package com.solo.comm.dao;

import android.content.pm.PackageInfo;
import android.graphics.Bitmap;

import com.is.lib_util.FileUtils;
import com.is.lib_util.ImageUtils;
import com.is.lib_util.PackUtil;
import com.solo.base.BaseApplication;
import com.solo.comm.CommApplication;
import com.solo.comm.R;

import org.greenrobot.greendao.query.Query;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by Totoro
 * 2019/11/27 10:17 AM
 **/
public class InstallDBUtils {

    private static InstallDataDao getInstance() {
        return CommApplication.getDaoSession().getInstallDataDao();
    }


    public synchronized static void insertAll() {
        ArrayList<InstallData> list = new ArrayList<>();
        List<PackageInfo> installPacks = PackUtil.getInstallPacks(BaseApplication.getApplication());
        for (PackageInfo packageInfo : installPacks) {
            InstallData data = new InstallData();
            data.setPackageName(packageInfo.packageName);
            data.setAppName(packageInfo.applicationInfo.loadLabel(BaseApplication.getApplication().getPackageManager()).toString());
            byte[] imgData = ImageUtils.drawable2Bytes(packageInfo.applicationInfo == null ? BaseApplication.getApplication().getResources().getDrawable(R.drawable.ic_launcher)
                    : packageInfo.applicationInfo.loadIcon(BaseApplication.getApplication().getPackageManager()), Bitmap.CompressFormat.PNG);
            long time = System.currentTimeMillis();
            String photoSmallName = packageInfo.packageName + "_" + time + ".png";
            String iconPath = FileUtils.saveAppPhotoToInternal(BaseApplication.getApplication(), imgData, photoSmallName, "AppPhoto");
            data.setIconPath(iconPath);
            data.setInstalled(true);
            list.add(data);
        }
        getInstance().deleteAll();
        getInstance().insertInTx(list);

    }


    public synchronized static String getAppName(String packName) {
        List<InstallData> list = getInstance().queryBuilder().where(InstallDataDao.Properties.PackageName.eq(packName)).build().list();
        if (list != null && !list.isEmpty()) {
            return list.get(0).appName;
        }
        return "app";
    }
}
