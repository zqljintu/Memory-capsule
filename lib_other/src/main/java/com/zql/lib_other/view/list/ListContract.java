package com.zql.lib_other.view.list;

import android.app.Application;
import android.content.Context;

import com.zql.base.ui.mvp.im.IView;
import com.zql.comm.bean.NoteBean;

import java.util.List;

public interface ListContract {
    interface view extends IView {
        void opensheeetdialog(NoteBean noteBean);
        Context getListActivityConent();
        void readAllNotefromData(List<NoteBean> noteBeanList);
        Application getListApplication();
        void setBackgroundcolorfromSeting(List<Integer> colorlist);
    }

    interface presenter {
        void readNotefromDatatoList(int READ_TYPE);
        void deleteNotebean(NoteBean noteBean);
        void setBackgroundcolorfromSeting();//设置主题色
        void changeNotetoPasswordFile(NoteBean noteBean);//转入秘密文件夹
        void opensheeetdialog(NoteBean noteBean);
        Context getListActivityConent();
    }
}
