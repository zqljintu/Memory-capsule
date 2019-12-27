package com.zql.app_jinnang.Prestener;

import com.zql.app_jinnang.Bean.NoteBean;
import com.zql.app_jinnang.Model.NoteInfoModel;
import com.zql.app_jinnang.Model.NoteInfoModelImp;
import com.zql.app_jinnang.UserSeting;
import com.zql.app_jinnang.View.MainActivityImp;

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
    public void readNotefromDtabyType(int READ_TYPE) {
        switch (READ_TYPE){
            case 0:
                mainActivityImp.readNotefromData(noteInfoModelImp.QueryAllNotefromData());
                break;
            case 1:
                mainActivityImp.readNotefromData(noteInfoModelImp.QueryNoyefromDataByType("工作"));
                break;
            case 2:
                mainActivityImp.readNotefromData(noteInfoModelImp.QueryNoyefromDataByType("学习"));
                break;
            case 3:
                mainActivityImp.readNotefromData(noteInfoModelImp.QueryNoyefromDataByType("生活"));
                break;
            case 4:
                mainActivityImp.readNotefromData(noteInfoModelImp.QueryNoyefromDataByType("日记"));
                break;
            case 5:
                mainActivityImp.readNotefromData(noteInfoModelImp.QueryNoyefromDataByType("旅行"));
                break;
            case 6:
                break;
            default:
                break;
        }
    }

    @Override
    public void deleteNoteBean(NoteBean noteBean) {
        noteInfoModelImp.DeleteNotefromData(noteBean);
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
    public void setBackgroundcolorfromSeting() {
        mainActivityImp.setBackgroundcolorfromSeting(userSeting.getcurrentColor());
    }

    @Override
    public int getBackgroundcolorNumfromSering() {
        return userSeting.getcurrentColorNum();
    }

}
