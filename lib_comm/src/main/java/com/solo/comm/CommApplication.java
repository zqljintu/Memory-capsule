package com.solo.comm;

import android.database.sqlite.SQLiteDatabase;

import com.alibaba.android.arouter.launcher.ARouter;
import com.appsflyer.AppsFlyerConversionListener;
import com.appsflyer.AppsFlyerLib;
import com.llew.huawei.verifier.LoadedApkHuaWei;
import com.solo.base.BaseApplication;
import com.solo.base.BaseLogUtil;
import com.solo.base.helper.RxHelper;
import com.solo.comm.dao.DaoMaster;
import com.solo.comm.dao.DaoSession;
import com.solo.comm.dao.InstallDBUtils;
import com.solo.comm.dao.utils.UpDataDaoHelper;
import com.solo.comm.data.CommData;
import com.solo.comm.net.NetRepository;
import com.solo.comm.service.MainService;
import com.xdandroid.hellodaemon.DaemonEnv;

import java.util.Map;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Create by Totoro
 * 2019-11-07 15:21
 **/
public abstract class CommApplication extends BaseApplication implements AppsFlyerConversionListener {

    private final String DB_NAME = "SecurityNew";

    private final String TAG = CommApplication.class.getSimpleName();

    private final String af_id = "qbKVyawAiwNX3b4D3Frijm";

    private NetRepository mNetRepository;

    private static DaoSession mDaoSession;

    private CompositeDisposable mCompositeDisposable;


    public static DaoSession getDaoSession() {
        return mDaoSession;
    }


    @Override
    protected void init() {
        LoadedApkHuaWei.hookHuaWeiVerifier(this);
        mCompositeDisposable = new CompositeDisposable();
        //初始化路由
        ARouter.init(this);
        ARouter.openDebug();
        initNetData();
        initAf();
        initLive();
        initDao();//数据库
        RxHelper.runOnPool(new Runnable() {
            @Override
            public void run() {
                InstallDBUtils.insertAll();
            }
        }, mCompositeDisposable);
    }

    protected void initDao() {
        UpDataDaoHelper upDataDaoHelper = new UpDataDaoHelper(this, DB_NAME, null);
        SQLiteDatabase writableDatabase = upDataDaoHelper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(writableDatabase);
        mDaoSession = daoMaster.newSession();
    }

    private void initNetData() {
        if (mNetRepository == null) {
            mNetRepository = new NetRepository();
        }
        mNetRepository.getInitData();
    }

    protected void initLive() {
        DaemonEnv.initialize(this, MainService.class, DaemonEnv.DEFAULT_WAKE_UP_INTERVAL);
        MainService.sShouldStopService = false;
        DaemonEnv.startServiceMayBind(MainService.class);
    }

    private void initAf() {
        AppsFlyerLib.getInstance().startTracking(this, af_id);
        AppsFlyerLib.getInstance().registerConversionListener(this, this);
    }

    @Override
    public void onInstallConversionDataLoaded(Map<String, String> conversionData) {
        if (conversionData != null && !conversionData.isEmpty())
            for (Map.Entry<String, String> entry : conversionData.entrySet()) {
                BaseLogUtil.D(TAG, entry.getKey(), entry.getValue());
                if ("af_status".equals(entry.getKey())) {
                    if ("Organic".equals(entry.getValue())) {
                        if (CommData.isOrg())
                            CommData.setOrg(true);
                    } else {
                        if (CommData.isOrg())
                            CommData.setOrg(false);
                    }
                }
            }
    }

    @Override
    public void onInstallConversionFailure(String errorMessage) {

    }

    @Override
    public void onAppOpenAttribution(Map<String, String> attributionData) {

    }

    @Override
    public void onAttributionFailure(String errorMessage) {

    }


    @Override
    public void onTerminate() {
        super.onTerminate();
        if (mNetRepository != null) {
            mNetRepository.destroy();
        }
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
        }
    }
}
