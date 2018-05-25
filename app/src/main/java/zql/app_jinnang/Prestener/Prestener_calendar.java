package zql.app_jinnang.Prestener;

import zql.app_jinnang.Model.NoteInfoModel;
import zql.app_jinnang.Model.NoteInfoModelImp;
import zql.app_jinnang.UserSeting;
import zql.app_jinnang.View.CalendarActivityImp;

/**
 * Created by 尽途 on 2018/5/10.
 */

public class Prestener_calendar implements PrestenerImp_calendar {
    private NoteInfoModelImp noteInfoModelImp;
    private CalendarActivityImp calendarActivityImp;
    private UserSeting userSeting;

    public Prestener_calendar(CalendarActivityImp calendarActivityImp){
        this.calendarActivityImp=calendarActivityImp;
        noteInfoModelImp=new NoteInfoModel(calendarActivityImp.getCalendarActivity());
        userSeting=(UserSeting)calendarActivityImp.getCalendarApplication();
    }

    @Override
    public void readNotecreatimeOnCalendar() {
        calendarActivityImp.initCalendarViewandgetCreattime(noteInfoModelImp.QueryNotecreatetime());
    }

    @Override
    public void readNotebeanOnRecycler(String createtime) {
        calendarActivityImp.readNotebeansfromDatabycreatetime(noteInfoModelImp.QueryNotebeanBycreatetime(createtime));
    }

    @Override
    public void setBackgroundcolorfromSeting() {
        calendarActivityImp.setBackgroundcolorfromSeting(userSeting.getcurrentColor());
    }
}
