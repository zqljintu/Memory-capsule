package com.zql.comm.net;

import com.zql.base.BaseApplication;
import com.zql.base.event.EventBusUtil;

import com.zql.comm.R;


import io.reactivex.disposables.CompositeDisposable;

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

    }



    public void destroy() {
        if (mDisposable != null) {
            mDisposable.clear();
        }
        EventBusUtil.unRegister(this);
    }


}
