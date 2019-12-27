package com.solo.comm.ui;

import com.solo.base.ui.mvp.BaseLifecycleFragment;
import com.solo.base.ui.mvp.BasePresenter;

/**
 * Create by Totoro
 * 2019-11-21 14:01
 **/
public abstract class EndBaseFragment<P extends BasePresenter> extends BaseLifecycleFragment<P> {


    public abstract void toEnd();




}
