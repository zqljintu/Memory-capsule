package com.zql.lib_other.view.list;

import android.content.Context;

import com.zql.base.ui.mvp.BasePresenter;
import com.zql.comm.Model.NoteInfoModel;
import com.zql.comm.Model.NoteInfoModelImp;
import com.zql.comm.UserSeting;
import com.zql.comm.bean.NoteBean;

public class ListPresenter extends BasePresenter<ListContract.view> implements ListContract.presenter {

    private NoteInfoModelImp noteInfoModelImp;
    private UserSeting userSeting;
    

    public ListPresenter(ListContract.view view) {
        super(view);
        noteInfoModelImp=new NoteInfoModel(getView().getListActivityConent());
        userSeting=(UserSeting)getView().getListApplication();
    }

    @Override
    public void setBackgroundcolorfromSeting() {
        getView().setBackgroundcolorfromSeting(userSeting.getcurrentColor());
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
    public void opensheeetdialog(NoteBean noteBean) {
        getView().opensheeetdialog(noteBean);
    }

    @Override
    public Context getListActivityConent() {
        return getView().getListActivityConent();
    }

    @Override
    public void readNotefromDatatoList(int READ_TYPE) {
        switch (READ_TYPE){
            case 0:
                getView().readAllNotefromData(noteInfoModelImp.QueryAllNotefromData());
                break;
            case 1:
                getView().readAllNotefromData(noteInfoModelImp.QueryNoyefromDataByType("工作"));
                break;
            case 2:
                getView().readAllNotefromData(noteInfoModelImp.QueryNoyefromDataByType("学习"));
                break;
            case 3:
                getView().readAllNotefromData(noteInfoModelImp.QueryNoyefromDataByType("生活"));
                break;
            case 4:
                getView().readAllNotefromData(noteInfoModelImp.QueryNoyefromDataByType("日记"));
                break;
            case 5:
                getView().readAllNotefromData(noteInfoModelImp.QueryNoyefromDataByType("旅行"));
                break;
            case 6:
                break;
            default:
                break;
        }
    }

    @Override
    public void deleteNotebean(NoteBean noteBean) {
        noteInfoModelImp.DeleteNotefromData(noteBean);
    }
}
