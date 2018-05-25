package zql.app_jinnang.Prestener;

import zql.app_jinnang.Bean.NoteBean;

/**
 * Created by 尽途 on 2018/4/12.
 */

public interface PrestenerImp_list {
    public void readNotefromDatatoList(int READ_TYPE);
    public void deleteNotebean(NoteBean noteBean);
    public void setBackgroundcolorfromSeting();//设置主题色
}
