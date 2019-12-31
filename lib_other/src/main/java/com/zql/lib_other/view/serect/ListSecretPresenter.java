package com.zql.lib_other.view.serect;

import android.content.Context;

import com.zql.base.ui.mvp.BasePresenter;
import com.zql.comm.Model.NoteInfoModel;
import com.zql.comm.Model.NoteInfoModelImp;
import com.zql.comm.UserSeting;
import com.zql.comm.bean.NoteBean;

public class ListSecretPresenter extends BasePresenter<ListSecretContract.view> implements ListSecretContract.presenter {
    private NoteInfoModelImp noteInfoModelImp;
    private UserSeting userSeting;

    public ListSecretPresenter(ListSecretContract.view view) {
        super(view);
        noteInfoModelImp=new NoteInfoModel(getView().getListSerectActivityContext());
        userSeting=(UserSeting)getView().getListSerectApplication();
    }

    @Override
    public void readNoteserectfromDatatoList() {
        getView().readAllNoteSerectfromData(noteInfoModelImp.QueryAllNotefromData_secret());
    }

    @Override
    public void deleteNotebeanserect(NoteBean noteBean) {
        noteInfoModelImp.DeleteNotefromData_secret(noteBean);
    }

    @Override
    public void setBackgroundcolorfromSeting() {
        getView().setBackgroundcolorfromSeting(userSeting.getcurrentColor());
    }

    @Override
    public void opensheetdialog(NoteBean noteBean) {
        getView().opensheetdialog(noteBean);
    }

    @Override
    public Context getListSerectActivityContext() {
        return getView().getListSerectActivityContext();
    }
}
