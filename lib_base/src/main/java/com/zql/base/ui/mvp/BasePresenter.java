package com.zql.base.ui.mvp;

import androidx.annotation.StringRes;
import androidx.lifecycle.LifecycleOwner;

import com.zql.base.ui.mvp.im.BaseLifecyclePresenter;
import com.zql.base.ui.mvp.im.IView;

import java.lang.ref.WeakReference;

/**
 * Create by Totoro
 * 2019-11-07 16:27
 **/
public abstract class BasePresenter<V extends IView> implements BaseLifecyclePresenter {


    private WeakReference<V> mWeakReference;


    public BasePresenter(V view) {
        mWeakReference = new WeakReference<V>(view);
    }

    protected V getView() {
        if (mWeakReference != null && mWeakReference.get() != null) {
            return mWeakReference.get();
        }
        return null;
    }


    protected String getString(@StringRes int id) {
        return getView().getContext().getString(id);
    }

    protected String getString(@StringRes int id, Object... formatArgs) {
        return getView().getContext().getString(id, formatArgs);
    }

    @Override
    public void onCreate(LifecycleOwner owner) {

    }

    @Override
    public void onStart(LifecycleOwner owner) {

    }

    @Override
    public void onResume(LifecycleOwner owner) {

    }

    @Override
    public void onPause(LifecycleOwner owner) {

    }

    @Override
    public void onStop(LifecycleOwner owner) {

    }

    @Override
    public void onDestroy(LifecycleOwner owner) {
        mWeakReference.clear();
        mWeakReference = null;
    }
}
