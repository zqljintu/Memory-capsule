package zql.app_jinnang.Prestener;

import java.util.List;

import zql.app_jinnang.Bean.NoteBean;
import zql.app_jinnang.Model.NoteInfoModel;
import zql.app_jinnang.Model.NoteInfoModelImp;
import zql.app_jinnang.UserSeting;
import zql.app_jinnang.View.MainActivityImp;

/**
 * Created by 尽途 on 2018/4/4.
 */

public class Prestener_main implements PrestenerImp_main {
    private NoteInfoModelImp noteInfoModelImp;
    private MainActivityImp mainActivityImp;
    private UserSeting userSeting;

    public Prestener_main(MainActivityImp mmainActivityImp){
        this.mainActivityImp=mmainActivityImp;
        noteInfoModelImp=new NoteInfoModel(mmainActivityImp.getActivity_this());
        userSeting=(UserSeting)mainActivityImp.getMainApplication();
    }

    @Override
    public boolean iscurrentthepasswordfromSeting(String password) {
        return userSeting.iscurrentthePassword(password);
    }

    @Override
    public void openCalendarActivity() {
        mainActivityImp.startCalendarActivity();
    }

    @Override
    public void openAddActivity(int type) {
        mainActivityImp.startAddActivity(type);
    }

    @Override
    public void openAddActivityS(String path) {
        mainActivityImp.startAddActivityS(path);
    }

    @Override
    public void openSearchActivity() {
        mainActivityImp.startSearchActivity();
    }

    @Override
    public void openListActivity() {
        mainActivityImp.startListActivity();
    }

    @Override
    public void openListSecretActivity() {
        mainActivityImp.startListSecretActivity();
    }

    @Override
    public void openSetiongActivity() {
        mainActivityImp.startSetingActivity();
    }

    @Override
    public void readNotefromDatatoMain() {
        mainActivityImp.readNotefromData(noteInfoModelImp.QueryAllNotefromData());
    }

    @Override
    public void deleteNoteBean(NoteBean noteBean) {
        noteInfoModelImp.DeleteNotefromData(noteBean);
    }

    @Override
    public void setBackgroundcolorfromSeting() {
        mainActivityImp.setBackgroundcolorfromSeting(userSeting.getcurrentColor());
    }

    @Override
    public int getBackgroundcolorNumfromSering() {
        return userSeting.getcurrentColorNum();
    }
}
