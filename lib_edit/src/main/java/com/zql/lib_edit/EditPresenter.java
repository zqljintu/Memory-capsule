package com.zql.lib_edit;

import androidx.lifecycle.LifecycleOwner;

import com.zql.base.ui.mvp.BasePresenter;
import com.zql.comm.Model.NoteInfoModel;
import com.zql.comm.Model.NoteInfoModelImp;
import com.zql.comm.UserSeting;
import com.zql.comm.bean.NoteBean;
import com.zql.comm.bean.Noteinfo;
import com.zql.comm.net.HttpData;
import com.zql.comm.net.OnHttpRequestListener;
import com.zql.comm.netbean.response.BaseResponse;

public class EditPresenter extends BasePresenter<EditContract.view> implements EditContract.presenter {
    private NoteInfoModelImp noteInfoModelImp;

    private UserSeting userSeting;

    private HttpData mHttpData;


    public EditPresenter(EditContract.view view) {
        super(view);
        this.noteInfoModelImp=new NoteInfoModel(getView().getbasecontext());
        this.userSeting=(UserSeting)getView().getapplication();
        this.mHttpData = new HttpData();
    }

    /**
     * 添加到普通的数据库
     * @param noteBean
     */
    @Override
    public void saveNoteinfotoDatabase(NoteBean noteBean) {
        if (noteBean.getId() != null){
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

    @Override
    public void addNoteInfoToService(Noteinfo noteinfo) {
        if (noteinfo.getId() != null){
            mHttpData.EditCapsule(noteinfo, new OnHttpRequestListener<BaseResponse>() {
                @Override
                public void onHttpRequestSuccess(BaseResponse result) {
                    if (result.getCode() == BaseResponse.EDIT_SUCCESS){
                        getView().showMessageOnView("修改成功");
                    }else {
                        getView().showMessageOnView(result.getMsg());
                    }
                }

                @Override
                public void onHttpRequestFailed(String error) {
                    getView().showMessageOnView(error);
                }
            });

        }else {
            mHttpData.AddCapsule(noteinfo, new OnHttpRequestListener<BaseResponse>() {
                @Override
                public void onHttpRequestSuccess(BaseResponse result) {
                    if (result.getCode() == BaseResponse.ADD_SUCCESS){
                        getView().showMessageOnView("添加成功");
                    }else {
                        getView().showMessageOnView(result.getMsg());
                    }
                }

                @Override
                public void onHttpRequestFailed(String error) {
                    getView().showMessageOnView(error);
                }
            });
        }
    }

    @Override
    public void onDestroy(LifecycleOwner owner) {
        super.onDestroy(owner);
        if (null != mHttpData){
            mHttpData.Destory();
        }
    }
}
