package com.zql.comm.provider;

import androidx.fragment.app.Fragment;

import com.alibaba.android.arouter.facade.template.IProvider;

public interface IUserProvider extends IProvider {

    Fragment getUserFragment();
}
