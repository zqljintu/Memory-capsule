package zql.app_jinnang.View;

import android.app.Application;
import android.content.Context;

import java.util.List;

import zql.app_jinnang.Bean.NoteBean;

/**
 * Created by 尽途 on 2018/5/10.
 */

public interface CalendarActivityImp {
    public Context getCalendarActivity();
    public void initCalendarViewandgetCreattime(List<String> mlist);
    public void readNotebeansfromDatabycreatetime(List<NoteBean>noteBeanList);
    public Application getCalendarApplication();
    public void setBackgroundcolorfromSeting(List<Integer>colorlist);
}
