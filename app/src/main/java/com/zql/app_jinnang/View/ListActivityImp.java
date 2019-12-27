package com.zql.app_jinnang.View;

import android.app.Application;
import android.content.Context;

import java.util.List;

import com.zql.app_jinnang.Bean.NoteBean;

/**
 * Created by 尽途 on 2018/4/4.
 */

public interface ListActivityImp {
    public void opensheeetdialog(NoteBean noteBean);
    public Context getListActivityConent();
    public void readAllNotefromData(List<NoteBean>noteBeanList);
    public Application getListApplication();
    public void setBackgroundcolorfromSeting(List<Integer>colorlist);
}
