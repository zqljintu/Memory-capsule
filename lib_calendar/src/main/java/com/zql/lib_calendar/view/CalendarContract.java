package com.zql.lib_calendar.view;

import android.app.Application;
import android.content.Context;

import com.zql.base.ui.mvp.im.IView;
import com.zql.comm.bean.NoteBean;

import java.util.List;

public interface CalendarContract {

    interface view extends IView{
        Context getCalendarActivity();
        void initCalendarViewandgetCreattime(List<String> mlist);
        void readNotebeansfromDatabycreatetime(List<NoteBean> noteBeanList);
        Application getCalendarApplication();
        void setBackgroundcolorfromSeting(List<Integer> colorlist);
    }

    interface presenter {
        void readNotecreatimeOnCalendar();
        void readNotebeanOnRecycler(String createtime);
        void setBackgroundcolorfromSeting();//设置主题色
    }
}
