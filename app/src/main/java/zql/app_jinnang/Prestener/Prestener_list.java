package zql.app_jinnang.Prestener;

import zql.app_jinnang.Bean.NoteBean;
import zql.app_jinnang.Model.NoteInfoModel;
import zql.app_jinnang.Model.NoteInfoModelImp;
import zql.app_jinnang.UserSeting;
import zql.app_jinnang.View.ListActivityImp;

/**
 * Created by 尽途 on 2018/4/12.
 */

public class Prestener_list implements PrestenerImp_list {
    private ListActivityImp listActivityImp;
    private NoteInfoModelImp noteInfoModelImp;
    private UserSeting userSeting;

    public Prestener_list(ListActivityImp mlistActivityImp){
        this.listActivityImp=mlistActivityImp;
        noteInfoModelImp=new NoteInfoModel(listActivityImp.getListActivityConent());
        userSeting=(UserSeting)listActivityImp.getListApplication();
    }

    @Override
    public void setBackgroundcolorfromSeting() {
        listActivityImp.setBackgroundcolorfromSeting(userSeting.getcurrentColor());
    }

    @Override
    public void changeNotetoPasswordFile(NoteBean noteBean) {
        if (noteBean.getId()!=null){
            noteInfoModelImp.DeleteNotefromData(noteBean);
            noteBean.setId(null);
        }
        noteInfoModelImp.InsertNotetoData_secret(noteBean);
    }

    @Override
    public void readNotefromDatatoList(int READ_TYPE) {
        switch (READ_TYPE){
            case 0:
                listActivityImp.readAllNotefromData(noteInfoModelImp.QueryAllNotefromData());
                break;
            case 1:
                listActivityImp.readAllNotefromData(noteInfoModelImp.QueryNoyefromDataByType("工作"));
                break;
            case 2:
                listActivityImp.readAllNotefromData(noteInfoModelImp.QueryNoyefromDataByType("学习"));
                break;
            case 3:
                listActivityImp.readAllNotefromData(noteInfoModelImp.QueryNoyefromDataByType("生活"));
                break;
            case 4:
                listActivityImp.readAllNotefromData(noteInfoModelImp.QueryNoyefromDataByType("日记"));
                break;
            case 5:
                listActivityImp.readAllNotefromData(noteInfoModelImp.QueryNoyefromDataByType("旅行"));
                break;
            case 6:
                break;
            default:
                break;
        }
    }

    @Override
    public void deleteNotebean(NoteBean noteBean) {
        noteInfoModelImp.DeleteNotefromData(noteBean);
    }
}
