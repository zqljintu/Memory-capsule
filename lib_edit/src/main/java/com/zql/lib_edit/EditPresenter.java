package com.zql.lib_edit;

import com.zql.base.ui.mvp.BasePresenter;
import com.zql.comm.Model.NoteInfoModel;
import com.zql.comm.Model.NoteInfoModelImp;
import com.zql.comm.UserSeting;
import com.zql.comm.bean.NoteBean;

public class EditPresenter extends BasePresenter<EditContract.view> implements EditContract.presenter {
    private NoteInfoModelImp noteInfoModelImp;
    private UserSeting userSeting;


    public EditPresenter(EditContract.view view) {
        super(view);
        this.noteInfoModelImp=new NoteInfoModel(getView().getbasecontext());
        this.userSeting=(UserSeting)getView().getapplication();
    }

    /**
     * 添加到普通的数据库
     * @param noteBean
     */
    @Override
    public void saveNoteinfotoDatabase(NoteBean noteBean) {
        if (noteBean.getId()!=null){
            noteInfoModelImp.ChangeNotetoData(noteBean);
        }else {
            noteInfoModelImp.InsertNotetoData(noteBean);
        }
    }

    /**
     *添加到秘密数据库
     * @param noteBean
     */
    @Override
    public void saveNoteinfotoSecrectDatabase(NoteBean noteBean) {
        if (noteBean.getId()!=null){
            noteInfoModelImp.DeleteNotefromDataByid(noteBean.getId());
        }
        noteInfoModelImp.InsertNotetoData_secret(noteBean);
    }

    @Override
    public void setBackgroundColorfromUserseting() {
        getView().setbackgroundcolor(userSeting.getcurrentColor());
    }
}
