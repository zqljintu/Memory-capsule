package com.zql.lib_info.view;

import android.app.Application;

import com.zql.base.ui.mvp.im.IView;
import com.zql.comm.bean.Noteinfo;

import java.util.List;

public interface NoteInfoContract {
    interface view extends IView{
        void readNoteinfotoNotetext(String noteinfo);
        void readLabelinfotoNoteTagrroup(List<String> tags);
        void readPhotopathtoNoteImageview(String photopath, String type);
        Application getNoteinfoApplication();
        void setBackgroundcolorfromSeting(List<Integer> colorlist);
    }

    interface presenter {
        void readDatatoNoteinfo(Noteinfo noteinfo);
        void setBackgroundcolorfromSeting();//设置主题色
    }
}
