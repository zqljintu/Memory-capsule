package com.zql.lib_main.view;

import android.content.Context;

import com.zql.base.ui.mvp.BasePresenter;
import com.zql.comm.Model.NoteInfoModel;
import com.zql.comm.Model.NoteInfoModelImp;
import com.zql.comm.UserSeting;
import com.zql.comm.bean.NoteBean;

public class MainPresenter extends BasePresenter<MainContract.view> implements MainContract.presenter {
    private NoteInfoModelImp noteInfoModelImp;
    private UserSeting userSeting;


    public MainPresenter(MainContract.view view) {
        super(view);
        userSeting=(UserSeting)getView().getMainApplication();
        noteInfoModelImp=new NoteInfoModel(getView().getActivity_this());
    }

    @Override
    public boolean iscurrentthepasswordfromSeting(String password) {
        return userSeting.iscurrentthePassword(password);
    }

    @Override
    public void openCalendarActivity() {
        getView().startCalendarActivity();
    }

    @Override
    public void openSearchActivity() {
        getView().startSearchActivity();
    }

    @Override
    public void openListActivity() {
        getView().startListActivity();
    }

    @Override
    public void openListSecretActivity() {
        getView().startListSecretActivity();
    }

    @Override
    public void openSetiongActivity() {
        getView().startSetingActivity();
    }

    @Override
    public void readNotefromDatatoMain() {
        getView().readNotefromData(noteInfoModelImp.QueryAllNotefromData());
    }

    @Override
    public void readNotefromDtabyType(int READ_TYPE) {
        switch (READ_TYPE){
            case 0:
                getView().readNotefromData(noteInfoModelImp.QueryAllNotefromData());
                break;
            case 1:
                getView().readNotefromData(noteInfoModelImp.QueryNoyefromDataByType("工作"));
                break;
            case 2:
                getView().readNotefromData(noteInfoModelImp.QueryNoyefromDataByType("学习"));
                break;
            case 3:
                getView().readNotefromData(noteInfoModelImp.QueryNoyefromDataByType("生活"));
                break;
            case 4:
                getView().readNotefromData(noteInfoModelImp.QueryNoyefromDataByType("日记"));
                break;
            case 5:
                getView().readNotefromData(noteInfoModelImp.QueryNoyefromDataByType("旅行"));
                break;
            case 6:
                break;
            default:
                break;
        }
    }

    @Override
    public void openSheetDialog(NoteBean noteBean) {
        getView().openSheetDialog(noteBean);
    }

    @Override
    public Context getActivity_this() {
        return getView().getContext();
    }

    @Override
    public void deleteNoteBean(NoteBean noteBean) {
        noteInfoModelImp.DeleteNotefromData(noteBean);
    }

    @Override
    public void changeNotetoPasswordFile(NoteBean noteBean) {
        if (noteBean.getId()!=null){
            noteInfoModelImp.DeleteNotefromData(noteBean);
            noteBean.setId(null);
        }
        noteInfoModelImp.InsertNotetoData_secret(noteBean);
    }

    @Override
    public void setBackgroundcolorfromSeting() {
        getView().setBackgroundcolorfromSeting(userSeting.getcurrentColor());
    }

    @Override
    public int getBackgroundcolorNumfromSering() {
        return userSeting.getcurrentColorNum();
    }

}
