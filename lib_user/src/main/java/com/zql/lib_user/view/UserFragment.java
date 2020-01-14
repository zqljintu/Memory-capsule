package com.zql.lib_user.view;
import android.view.View;

import com.zql.base.ui.mvp.BaseLifecycleFragment;
import com.zql.base.utils.FragmentUtils;
import com.zql.lib_user.R;
import com.zql.lib_user.view.fragments.LogupFragment;

public class UserFragment extends BaseLifecycleFragment<UserPresenter> implements UserContract.view {

    @Override
    protected void initView(View view) {
        initLogupFragment();
    }

    /**
     * 进入登陆界面
     */

    private void initLogupFragment(){
        FragmentUtils.replace(getChildFragmentManager(), LogupFragment.newInstance(),R.id.container);

    }

    /**
     * 进入注册界面
     */

    private void initLoginFragment(){

    }

    /**
     * 进入退出和注销界面
     */

    private void initLogoutFragment(){

    }



    @Override
    protected UserPresenter getPresenter() {
        return new UserPresenter(this);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_user;
    }

}
