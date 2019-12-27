package com.zql.app_jinnang.View;

import android.app.Application;

import java.util.List;

/**
 * Created by 尽途 on 2018/4/26.
 */

public interface NoteinfoActivityImp {
    public void readNoteinfotoNotetext(String noteinfo);
    public void readLabelinfotoNoteTagrroup(List<String> tags);
    public void readPhotopathtoNoteImageview(String photopath,String type);
    public Application getNoteinfoApplication();
    public void setBackgroundcolorfromSeting(List<Integer>colorlist);
}
