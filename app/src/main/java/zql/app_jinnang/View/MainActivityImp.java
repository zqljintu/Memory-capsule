package zql.app_jinnang.View;


import android.app.Application;
import android.content.Context;
import android.graphics.Color;

import java.util.List;

import zql.app_jinnang.Bean.NoteBean;
import zql.app_jinnang.Bean.Noteinfo;

/**
 * Created by 尽途 on 2018/4/4.
 */

public interface MainActivityImp {
    public Context getActivity_this();
    public void startAddActivity(int type);
    public void startAddActivityS(String path);
    public void startListActivity();
    public void startCalendarActivity();
    public void startSetingActivity();
    public void startSearchActivity();
    public void setMainBackground(Integer integer);
    public void openSheetDialog(NoteBean noteBean);
    public void readNotefromData(List<NoteBean>noteBeanList);
    public Application getMainApplication();
    public void setBackgroundcolorfromSeting(List<Integer>mlist);
}
