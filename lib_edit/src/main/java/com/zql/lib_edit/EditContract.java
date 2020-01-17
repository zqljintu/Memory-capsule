package com.zql.lib_edit;


import android.app.Application;
import android.content.Context;

import com.zql.base.ui.mvp.im.IView;
import com.zql.comm.bean.NoteBean;
import com.zql.comm.bean.Noteinfo;

import java.util.List;

public interface EditContract {
    interface view extends IView{
        Context getbasecontext();//获取
        Application getapplication();
        void setbackgroundcolor(List<Integer> list);//修改背景色
        void showMessageOnView(String message);
    }

    interface presenter{
        void saveNoteinfotoDatabase(NoteBean noteBean);
        void saveNoteinfotoSecrectDatabase(NoteBean noteBean);
        void setBackgroundColorfromUserseting();
        void addNoteInfoToService(Noteinfo noteinfo);//将数据添加到数据库
    }
}
