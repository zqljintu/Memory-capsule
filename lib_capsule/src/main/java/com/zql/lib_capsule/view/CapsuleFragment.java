package com.zql.lib_capsule.view;

import android.view.View;

import com.zql.base.ui.mvp.BaseLifecycleFragment;
import com.zql.lib_capsule.R;

public class CapsuleFragment extends BaseLifecycleFragment<CapsulePresnter> implements CapsuleContract.view {

    @Override
    protected void initView(View view) {

    }

    @Override
    protected CapsulePresnter getPresenter() {
        return new CapsulePresnter(this);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_capsule;
    }
}
