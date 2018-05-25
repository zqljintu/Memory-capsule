package zql.app_jinnang.View;

import android.app.Application;
import android.content.Context;

import java.util.List;

/**
 * Created by 尽途 on 2018/4/4.
 */

public interface AddActivityImp {
    public void addpeopleTagtoTaggroup(String people);
    public void addnotetypeTagtoTaggroup(String notetype);
    public void adddateTagtoTaggroup(String date);
    public void addtimeTagtoTaggroup(String time);
    public void addlocationTagtoTaggroup(String location);
    public void addphotoTagtoTaggroup(String photo);
    public void showmessageAbout_savenote(int MESSAGE);
    public String getnoteinfofromEdittext();
    public void addnoteinfotoEdittext(String noteinfo);
    public Context getAddActivityContext();
    public Application getAddApplication();
    public void setBackgroundcolorfromSeting(List<Integer>colorlist);
}
