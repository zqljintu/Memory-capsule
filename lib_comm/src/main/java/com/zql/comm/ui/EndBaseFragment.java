package com.zql.comm.ui;

import com.zql.base.ui.mvp.BaseLifecycleFragment;
import com.zql.base.ui.mvp.BasePresenter;

/**
 * Create by Totoro
 * 2019-11-21 14:01
 **/
public abstract class EndBaseFragment<P extends BasePresenter> extends BaseLifecycleFragment<P> {


    public abstract void toEnd();




}
