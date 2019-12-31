package com.zql.lib_other.view.search;

import android.app.Application;
import android.content.Context;
import android.database.Cursor;

import com.zql.base.ui.mvp.im.IView;
import com.zql.comm.bean.NoteBean;

import java.util.List;

public interface SearchContract {
    interface view extends IView {
        Context getSearchActivityContext();
        Application getSearchApplication();
        void setBackgroundcolorfromSeting(List<Integer> colorlist);
    }

    interface presenter {
        Cursor getCursorfromtoSearch(String search);
        List<NoteBean> getNotebeanfromDatatoSearch(String search);
        void setBackgroundcolorfromSeting();//设置主题色
    }
}
