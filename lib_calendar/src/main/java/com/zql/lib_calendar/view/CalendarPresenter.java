package com.zql.lib_calendar.view;

import com.zql.base.ui.mvp.BasePresenter;
import com.zql.comm.Model.NoteInfoModel;
import com.zql.comm.Model.NoteInfoModelImp;
import com.zql.comm.UserSeting;

public class CalendarPresenter extends BasePresenter<CalendarContract.view> implements CalendarContract.presenter {
    private NoteInfoModelImp noteInfoModelImp;
    private UserSeting userSeting;

    public CalendarPresenter(CalendarContract.view view) {
        super(view);
        noteInfoModelImp=new NoteInfoModel(getView().getCalendarActivity());
        userSeting=(UserSeting)getView().getCalendarApplication();
    }

    @Override
    public void readNotecreatimeOnCalendar() {
        getView().initCalendarViewandgetCreattime(noteInfoModelImp.QueryNotecreatetime());
    }

    @Override
    public void readNotebeanOnRecycler(String createtime) {
        getView().readNotebeansfromDatabycreatetime(noteInfoModelImp.QueryNotebeanBycreatetime(createtime));
    }

    @Override
    public void setBackgroundcolorfromSeting() {
        getView().setBackgroundcolorfromSeting(userSeting.getcurrentColor());
    }
}
