package com.zql.comm.provider;

import androidx.fragment.app.Fragment;

import com.alibaba.android.arouter.facade.template.IProvider;

public interface ICapsuleProvider extends IProvider {

    Fragment getCapsuleFragment();
}
