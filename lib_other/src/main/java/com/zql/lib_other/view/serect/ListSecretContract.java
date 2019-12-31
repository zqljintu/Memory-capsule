package com.zql.lib_other.view.serect;

import android.app.Application;
import android.content.Context;

import com.zql.base.ui.mvp.im.IView;
import com.zql.comm.bean.NoteBean;

import java.util.List;

public interface ListSecretContract {
    interface view extends IView {
        public void opensheetdialog(NoteBean noteBean);
        public Context getListSerectActivityContext();
        public Application getListSerectApplication();
        public void readAllNoteSerectfromData(List<NoteBean> noteBeanList);
        public void setBackgroundcolorfromSeting(List<Integer> colorlist);
    }
    interface presenter {
        public void readNoteserectfromDatatoList();
        public void deleteNotebeanserect(NoteBean noteBean);
        public void setBackgroundcolorfromSeting();//设置主题色
        public void opensheetdialog(NoteBean noteBean);
        public Context getListSerectActivityContext();
    }
}
