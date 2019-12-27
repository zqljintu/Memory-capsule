package com.zql.app_jinnang.Prestener;

import com.zql.app_jinnang.Bean.NoteBean;

public interface PresterImp_edit {
    public void saveNoteinfotoDatabase(NoteBean noteBean);
    public void saveNoteinfotoSecrectDatabase(NoteBean noteBean);
    public void setBackgroundColorfromUserseting();
}
