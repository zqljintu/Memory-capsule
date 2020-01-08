package com.zql.lib_edit;


import android.app.Application;
import android.content.Context;

import com.zql.base.ui.mvp.im.IView;
import com.zql.comm.bean.NoteBean;

import java.util.List;

public interface EditContract {
    interface view extends IView{
        Context getbasecontext();//获取
        Application getapplication();
        void setbackgroundcolor(List<Integer> list);//修改背景色
    }

    interface presenter{
        void saveNoteinfotoDatabase(NoteBean noteBean);
        void saveNoteinfotoSecrectDatabase(NoteBean noteBean);
        void setBackgroundColorfromUserseting();
    }
}
