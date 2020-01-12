package com.zql.lib_capsule.provider;

import android.content.Context;

import androidx.fragment.app.Fragment;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.zql.comm.provider.ICapsuleProvider;
import com.zql.comm.route.RouteUrl;
import com.zql.lib_capsule.view.CapsuleFragment;

@Route(path = RouteUrl.Url_CapsuleFragment, name = "CapsuleFragment")
public class CapsuleProvider implements ICapsuleProvider {

    @Override
    public Fragment getCapsuleFragment() {
        return new CapsuleFragment();
    }

    @Override
    public void init(Context context) {

    }
}
