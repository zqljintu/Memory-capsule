package com.zql.app_jinnang.Prestener;

import android.database.Cursor;

import java.util.List;

import com.zql.app_jinnang.Bean.NoteBean;
import com.zql.app_jinnang.Model.NoteInfoModel;
import com.zql.app_jinnang.Model.NoteInfoModelImp;
import com.zql.app_jinnang.UserSeting;
import com.zql.app_jinnang.View.SearchActivityImp;

/**
 * Created by 尽途 on 2018/5/1.
 */

public class Prestener_seacher implements PrestenerImp_seacher{
    private SearchActivityImp searchActivityImp;
    private NoteInfoModelImp noteInfoModelImp;
    private UserSeting userSeting;

    public Prestener_seacher(SearchActivityImp msearchActivityImp){
        this.searchActivityImp=msearchActivityImp;
        this.noteInfoModelImp=new NoteInfoModel(searchActivityImp.getSearchActivityContext());
        userSeting=(UserSeting)searchActivityImp.getSearchApplication();
    }

    @Override
    public Cursor getCursorfromtoSearch(String search) {
        return null;
    }

    @Override
    public List<NoteBean> getNotebeanfromDatatoSearch(String search) {
        return noteInfoModelImp.getSearchfromData(search);
    }

    @Override
    public void setBackgroundcolorfromSeting() {
        searchActivityImp.setBackgroundcolorfromSeting(userSeting.getcurrentColor());
    }
}
