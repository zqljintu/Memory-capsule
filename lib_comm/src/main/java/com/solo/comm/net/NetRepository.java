package com.solo.comm.net;

import com.google.gson.Gson;
import com.solo.base.BaseApplication;
import com.solo.base.event.EventBusUtil;
import com.solo.base.helper.RxHelper;
import com.solo.base.net.HttpClient;
import com.solo.base.utils.AesCbcUtil;
import com.solo.base.utils.AppUtil;
import com.solo.comm.R;
import com.solo.comm.data.CommData;
import com.solo.comm.event.OrgChangeEvent;
import com.solo.comm.net.body.AdPositionBody;
import com.solo.comm.net.body.ConfigBody;
import com.solo.comm.net.body.SdkBody;
import com.solo.comm.net.model.AdInfoModel;
import com.solo.comm.net.model.BaseModel;
import com.solo.comm.net.model.BaseModels;
import com.solo.comm.net.model.ConfigModel;
import com.solo.comm.net.model.SdkModel;
import com.solo.comm.net.model.UpdateModel;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Create by Totoro
 * 2019-11-11 09:56
 **/
public class NetRepository {

    private String KEY;

    private CompositeDisposable mDisposable;


    public NetRepository() {
        KEY = BaseApplication.getApplication().getString(R.string.app_key);
        mDisposable = new CompositeDisposable();
        EventBusUtil.register(this);
    }


    /**
     * 获取默认网络数据
     */
    public void getInitData() {

       /* getAds();
        getSdk();
        getConfig();
        getUpdate();*/
    }

    public void getSdk() {
        SdkBody body = new SdkBody().setName(KEY).setVersionCoded(AppUtil.getVersionCode()).setOragnic(CommData.isOrg() ? 1 : 0);
        String s = new Gson().toJson(body, SdkBody.class);
        RequestBody params = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), AesCbcUtil.encrypt(s));
        mDisposable.add(HttpClient.getInstance().create(Api.class)
                .getSdk(params)
                .compose(RxHelper.rxSchedulerHelperIO())
                .subscribe(new Consumer<BaseModels<SdkModel>>() {
                    @Override
                    public void accept(BaseModels<SdkModel> sdkModelBaseModels) throws Exception {
                        if (sdkModelBaseModels.getCode() == 200) {
                            CommData.saveSdk(sdkModelBaseModels.getDatas());
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                }));
    }

    private void getUpdate() {
        String update = BaseApplication.getApplication().getString(R.string.key_update);
        ConfigBody body = new ConfigBody().setAppName(KEY).setConfigKey(update).setVersionCode(AppUtil.getVersionCode());
        String s = new Gson().toJson(body, ConfigBody.class);
        RequestBody params = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), AesCbcUtil.encrypt(s));
        mDisposable.add(HttpClient.getInstance().create(Api.class)
                .getUpdateConfig(params)
                .compose(RxHelper.rxSchedulerHelperIO())
                .subscribe(new Consumer<BaseModel<UpdateModel>>() {
                    @Override
                    public void accept(BaseModel<UpdateModel> updateModelBaseModel) throws Exception {
                        if (updateModelBaseModel.getCode() == 200) {
                            CommData.saveUpdate(updateModelBaseModel.getData());
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                }));
    }


    private void getConfig() {
        String config = BaseApplication.getApplication().getString(R.string.key_config);
        ConfigBody body = new ConfigBody().setAppName(KEY).setConfigKey(config).setVersionCode(AppUtil.getVersionCode());
        String s = new Gson().toJson(body, ConfigBody.class);
        RequestBody params = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), AesCbcUtil.encrypt(s));
        mDisposable.add(HttpClient.getInstance().create(Api.class)
                .getServiceConfig(params)
                .compose(RxHelper.rxSchedulerHelperIO())
                .subscribe(new Consumer<BaseModel<ConfigModel>>() {
                    @Override
                    public void accept(BaseModel<ConfigModel> configModelBaseModel) throws Exception {
                        if (configModelBaseModel.getCode() == 200) {
                            CommData.saveConfig(configModelBaseModel.getData());
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                }));
    }

    private void getAds() {
        AdPositionBody body = new AdPositionBody().setApiVersion(0).setAppName(KEY).setAppVersionCode(AppUtil.getVersionCode());
        String s = new Gson().toJson(body, AdPositionBody.class);
        RequestBody params = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), AesCbcUtil.encrypt(s));
        mDisposable.add(HttpClient.getInstance().create(Api.class)
                .getAd(params)
                .compose(RxHelper.rxSchedulerHelperIO())
                .subscribe(new Consumer<BaseModels<List<AdInfoModel>>>() {
                    @Override
                    public void accept(BaseModels<List<AdInfoModel>> listBaseModels) throws Exception {

                        if (listBaseModels.getCode() == 200 && listBaseModels.getDatas() != null &&
                                !listBaseModels.getDatas().isEmpty()) {
                        }

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                }));
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void changeOrg(OrgChangeEvent event) {
        getSdk();
    }


    public void destroy() {
        if (mDisposable != null) {
            mDisposable.clear();
        }
        EventBusUtil.unRegister(this);
    }


}
