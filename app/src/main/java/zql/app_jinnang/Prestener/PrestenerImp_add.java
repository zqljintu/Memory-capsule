package zql.app_jinnang.Prestener;

import zql.app_jinnang.Bean.NoteBean;
import zql.app_jinnang.Bean.Noteinfo;

/**
 * Created by 尽途 on 2018/4/12.
 */

public interface PrestenerImp_add {
    public void addpeopleTagtoTaggroupandData(String people);
    public void addnotetypeTagtoTaggroupandData(String notetype);
    public void adddateTagtoTaggroupandData(String date);
    public void addtimeTagtoTaggroupandData(String time);
    public void addlocationTagtoTaggroupandData(String location);
    public void addphotoTagtoTaggroupandData(String photo);
    public void addnotetoData(String noteinfo);
    public void setchangeNoteinfoActivity(Noteinfo noteinfo);
    public void setBackgroundcolorfromSeting();//设置主题色
}
