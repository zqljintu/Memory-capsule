package com.zql.lib_user.view.fragments;

import android.view.View;

import com.zql.base.ui.mvp.BaseLifecycleFragment;
import com.zql.lib_user.R;

public class LogupFragment extends BaseLifecycleFragment<LogupPresenter> implements LogupContract.view{



    public static LogupFragment newInstance(){
        return new LogupFragment();
    }

    @Override
    protected void initView(View view) {

    }



    @Override
    protected LogupPresenter getPresenter() {
        return new LogupPresenter(this);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_logup;
    }

}
