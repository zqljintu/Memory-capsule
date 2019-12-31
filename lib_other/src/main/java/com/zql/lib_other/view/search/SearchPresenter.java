package com.zql.lib_other.view.search;

import android.database.Cursor;

import com.zql.base.ui.mvp.BasePresenter;
import com.zql.comm.Model.NoteInfoModel;
import com.zql.comm.Model.NoteInfoModelImp;
import com.zql.comm.UserSeting;
import com.zql.comm.bean.NoteBean;

import java.util.List;

public class SearchPresenter extends BasePresenter<SearchContract.view> implements SearchContract.presenter {

    private NoteInfoModelImp noteInfoModelImp;
    private UserSeting userSeting;


    public SearchPresenter(SearchContract.view view) {
        super(view);
        this.noteInfoModelImp=new NoteInfoModel(getView().getSearchActivityContext());
        userSeting=(UserSeting)getView().getSearchApplication();
    }

    @Override
    public Cursor getCursorfromtoSearch(String search) {
        return null;
    }

    @Override
    public List<NoteBean> getNotebeanfromDatatoSearch(String search) {
        return noteInfoModelImp.getSearchfromData(search);
    }

    @Override
    public void setBackgroundcolorfromSeting() {
        getView().setBackgroundcolorfromSeting(userSeting.getcurrentColor());
    }
}
