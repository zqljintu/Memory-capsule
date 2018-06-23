package zql.app_jinnang.View;

import android.app.Application;
import android.content.Context;

import java.util.List;

import zql.app_jinnang.Bean.NoteBean;

public interface ListSecretActivityImp {
    public void opensheetdialog(NoteBean noteBean);
    public Context getListSerectActivityContext();
    public Application getListSerectApplication();
    public void readAllNoteSerectfromData(List<NoteBean>noteBeanList);
    public void setBackgroundcolorfromSeting(List<Integer>colorlist);
}
