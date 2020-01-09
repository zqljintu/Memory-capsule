package com.zql.lib_net.view;

import com.zql.base.ui.mvp.BasePresenter;

public class NetMainPresenter extends BasePresenter<NetMainContract.view> implements NetMainContract.presenter {

    public NetMainPresenter(NetMainContract.view view) {
        super(view);
    }
}
