package zql.app_jinnang.Prestener;

import zql.app_jinnang.Bean.NoteBean;
import zql.app_jinnang.Model.NoteInfoModel;
import zql.app_jinnang.Model.NoteInfoModelImp;
import zql.app_jinnang.UserSeting;
import zql.app_jinnang.View.EditActivityImp;

public class Prestener_edit implements PresterImp_edit {
    private EditActivityImp editActivityImp;
    private NoteInfoModelImp noteInfoModelImp;
    private UserSeting userSeting;

    public Prestener_edit(EditActivityImp editActivityImp){
        this.editActivityImp=editActivityImp;
        this.noteInfoModelImp=new NoteInfoModel(editActivityImp.getbasecontext());
        this.userSeting=(UserSeting)editActivityImp.getapplication();
    }

    /**
     * 添加到普通的数据库
     * @param noteBean
     */
    @Override
    public void saveNoteinfotoDatabase(NoteBean noteBean) {
        if (editActivityImp.getStatement()==1){
            noteInfoModelImp.ChangeNotetoData(noteBean);
        }else {
            noteInfoModelImp.InsertNotetoData(noteBean);
        }
    }

    /**
     *添加到秘密数据库
     * @param noteBean
     */
    @Override
    public void saveNoteinfotoSecrectDatabase(NoteBean noteBean) {
        noteInfoModelImp.InsertNotetoData_secret(noteBean);
    }

    @Override
    public void setBackgroundColorfromUserseting() {
        editActivityImp.setbackgroundcolor(userSeting.getcurrentColor());
    }
}
