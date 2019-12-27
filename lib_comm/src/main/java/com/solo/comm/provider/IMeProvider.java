package com.solo.comm.provider;

import androidx.fragment.app.Fragment;

import com.alibaba.android.arouter.facade.template.IProvider;

/**
 * Create by Totoro
 * 2019-11-11 15:58
 **/
public interface IMeProvider extends IProvider {

    Fragment getMeFragment();
}
