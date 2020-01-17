package com.zql.lib_user.view.fragments;

import android.view.View;

import com.zql.base.event.EventBusUtil;
import com.zql.base.ui.mvp.BaseLifecycleFragment;
import com.zql.comm.bean.MessageEvent;
import com.zql.comm.data.UserData;
import com.zql.lib_user.R;
import com.zql.lib_user.view.UserFragment;

public class LogoutFragment extends BaseLifecycleFragment<LogoutPresenter> implements LogoutContract.view {

    public static LogoutFragment newInstance(){
        return new LogoutFragment();
    }


    @Override
    protected void initView(View view) {
        find(R.id.out).setOnClickListener(v -> {
            UserData.setUserIsLogin(false);
            UserData.setUserName("");
            UserData.setUserPass("");
            EventBusUtil.postEvent(new MessageEvent(MessageEvent.UPDATE_LOGOUT));
            if (getParentFragment() instanceof UserFragment){
                if (null != getParentFragment()){
                    ((UserFragment)getParentFragment()).initLoginFragment();
                }
            }
        });

    }

    @Override
    protected LogoutPresenter getPresenter() {
        return new LogoutPresenter(this);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_logout;
    }
}
