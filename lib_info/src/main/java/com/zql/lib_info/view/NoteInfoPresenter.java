package com.zql.lib_info.view;

import com.zql.base.ui.mvp.BasePresenter;
import com.zql.comm.UserSeting;
import com.zql.comm.bean.Noteinfo;

import java.util.ArrayList;
import java.util.List;

public class NoteInfoPresenter extends BasePresenter<NoteInfoContract.view> implements NoteInfoContract.presenter {
    private UserSeting userSeting;
    public NoteInfoPresenter(NoteInfoContract.view view) {
        super(view);
        userSeting=(UserSeting)getView().getNoteinfoApplication();
    }

    @Override
    public void readDatatoNoteinfo(Noteinfo noteinfo) {
        getView().readNoteinfotoNotetext(noteinfo.getNoteinfo());
        getView().readPhotopathtoNoteImageview(noteinfo.getPhotopath(), noteinfo.getNotetype());
        List<String> tags = new ArrayList<String>();
        if (noteinfo.getNotetype().equals("null")) {
            tags.add("创建类型：无类型");
        } else {
            tags.add("创建类型：" + noteinfo.getNotetype());
        }
        if (noteinfo.getPeople().equals("null")) {

        } else {
            tags.add("相关的人：" + noteinfo.getPeople());
        }
        if (noteinfo.getDate().equals("null")) {

        } else {
            tags.add("指定日期：" + noteinfo.getDate());
        }
        if (noteinfo.getTime().equals("null")) {

        } else {
            tags.add("指定时间：" + noteinfo.getTime());
        }
        if (noteinfo.getLocation().equals("null")) {

        } else {
            tags.add("指定地点：" + noteinfo.getLocation());
        }
        if (noteinfo.getCreatetime().equals("null")) {

        } else {
            tags.add("创建于：" + noteinfo.getCreatetime());
        }
        getView().readLabelinfotoNoteTagrroup(tags);
    }

    @Override
    public void setBackgroundcolorfromSeting() {
        getView().setBackgroundcolorfromSeting(userSeting.getcurrentColor());
    }
}
