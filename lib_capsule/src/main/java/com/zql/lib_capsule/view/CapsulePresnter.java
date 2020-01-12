package com.zql.lib_capsule.view;

import com.zql.base.ui.mvp.BasePresenter;

public class CapsulePresnter extends BasePresenter<CapsuleContract.view> implements CapsuleContract.presenter {

    public CapsulePresnter(CapsuleContract.view view) {
        super(view);
    }
}
