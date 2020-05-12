package com.zql.lib_user.view.fragments;

import com.zql.base.ui.mvp.im.IView;

public interface LogoutContract {
    interface view extends IView {
        void logoutToView();
        void showToast(String string);
    }

    interface presenter {
        void logoutFromService(String pass);
    }
}
