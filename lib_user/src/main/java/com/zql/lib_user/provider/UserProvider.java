package com.zql.lib_user.provider;

import android.content.Context;

import androidx.fragment.app.Fragment;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.zql.comm.provider.IUserProvider;
import com.zql.comm.route.RouteUrl;
import com.zql.lib_user.view.UserFragment;

@Route(path = RouteUrl.Url_UserFragment, name = "UserFragment")
public class UserProvider implements IUserProvider {
    @Override
    public Fragment getUserFragment() {
        return new UserFragment();
    }

    @Override
    public void init(Context context) {

    }
}
