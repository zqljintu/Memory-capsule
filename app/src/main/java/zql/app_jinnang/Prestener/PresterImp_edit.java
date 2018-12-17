package zql.app_jinnang.Prestener;

import zql.app_jinnang.Bean.NoteBean;

public interface PresterImp_edit {
    public void saveNoteinfotoDatabase(NoteBean noteBean);
    public void saveNoteinfotoSecrectDatabase(NoteBean noteBean);
    public void setBackgroundColorfromUserseting();
}
