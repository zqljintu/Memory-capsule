package com.zql.lib_other.view.serect;

import android.app.Application;
import android.content.Context;

import com.zql.base.ui.mvp.im.IView;
import com.zql.comm.bean.NoteBean;

import java.util.List;

public interface ListSecretContract {
    interface view extends IView {
        void opensheetdialog(NoteBean noteBean);
        Context getListSerectActivityContext();
        Application getListSerectApplication();
        void readAllNoteSerectfromData(List<NoteBean> noteBeanList);
        void setBackgroundcolorfromSeting(List<Integer> colorlist);
    }
    interface presenter {
        void readNoteserectfromDatatoList();
        void deleteNotebeanserect(NoteBean noteBean);
        void setBackgroundcolorfromSeting();//设置主题色
        void opensheetdialog(NoteBean noteBean);
        Context getListSerectActivityContext();
    }
}
