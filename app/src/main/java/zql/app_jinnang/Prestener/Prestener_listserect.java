package zql.app_jinnang.Prestener;

import zql.app_jinnang.Bean.NoteBean;
import zql.app_jinnang.Model.NoteInfoModel;
import zql.app_jinnang.Model.NoteInfoModelImp;
import zql.app_jinnang.UserSeting;
import zql.app_jinnang.View.ListSecretActivityImp;

public class Prestener_listserect implements PrestenerImp_listserect {
    private ListSecretActivityImp listSecretActivityImp;
    private NoteInfoModelImp noteInfoModelImp;
    private UserSeting userSeting;
    public Prestener_listserect(ListSecretActivityImp mlistSecretActivityImp){
        this.listSecretActivityImp=mlistSecretActivityImp;
        noteInfoModelImp=new NoteInfoModel(listSecretActivityImp.getListSerectActivityContext());
        userSeting=(UserSeting)listSecretActivityImp.getListSerectApplication();
    }

    @Override
    public void readNoteserectfromDatatoList() {
        listSecretActivityImp.readAllNoteSerectfromData(noteInfoModelImp.QueryAllNotefromData_secret());
    }

    @Override
    public void deleteNotebeanserect(NoteBean noteBean) {
        noteInfoModelImp.DeleteNotefromData_secret(noteBean);
    }

    @Override
    public void setBackgroundcolorfromSeting() {
        listSecretActivityImp.setBackgroundcolorfromSeting(userSeting.getcurrentColor());
    }
}
