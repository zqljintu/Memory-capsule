package com.zql.lib_setting.view;

import com.zql.base.ui.mvp.BasePresenter;
import com.zql.comm.UserSeting;

public class AboutPresenter extends BasePresenter<AboutContract.view> implements AboutContract.presenter {
    private UserSeting userSeting;

    public AboutPresenter(AboutContract.view view) {
        super(view);
        userSeting=(UserSeting)getView().getAboutApplication();
    }

    @Override
    public boolean isnullthepasswordfromSeting() {
        return userSeting.isnullthepassword();
    }

    @Override
    public boolean isnullthequestionfromSeting() {
        return userSeting.isnullthequestion();
    }

    @Override
    public boolean iscurrentthepasswordfromSeting(String password) {
        return userSeting.iscurrentthePassword(password);
    }

    @Override
    public boolean iscurrentthequestionfromSeting(String question) {
        return userSeting.iscurrentthQuestion(question);
    }

    @Override
    public void putpasswordandquestionOnSeting(String password, String question) {
        userSeting.putpasswordonSeting(password);
        userSeting.putquestiononSeting(question);
    }

    @Override
    public void putpasswordOnSeting(String password) {
        userSeting.putpasswordonSeting(password);
    }

    @Override
    public void putquestionOnSeting(String question) {
        userSeting.putquestiononSeting(question);
    }

    @Override
    public void showthecurrentpasswordOnAboutactivity() {
        getView().showthecurrentPassword(userSeting.getpassswordfromSeting().toString());
    }

    @Override
    public void putcurrentcolorOnSeting(int color) {
        userSeting.putcurrentColor(color);
    }

    @Override
    public int getcurrentcolorNumfromSeting() {
        return userSeting.getcurrentColorNum();
    }

    @Override
    public void setBackgroundcolorfromSeting() {
        getView().setBackgroundcolorfromSeting(userSeting.getcurrentColor());
    }
}
