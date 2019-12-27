package com.zql.app_jinnang.Prestener;

import java.util.ArrayList;
import java.util.List;

import com.zql.app_jinnang.Bean.Noteinfo;
import com.zql.app_jinnang.UserSeting;
import com.zql.app_jinnang.View.NoteinfoActivityImp;

/**
 * Created by 尽途 on 2018/4/26.
 */

public class Prestener_noteinfo implements PrestenerImp_noteinfo{
    private NoteinfoActivityImp noteinfoActivityImp;
    private UserSeting userSeting;
    public Prestener_noteinfo(NoteinfoActivityImp noteinfoActivityImp){
        this.noteinfoActivityImp=noteinfoActivityImp;
        userSeting=(UserSeting)noteinfoActivityImp.getNoteinfoApplication();
    }

    @Override
    public void readDatatoNoteinfo(Noteinfo noteinfo) {
        noteinfoActivityImp.readNoteinfotoNotetext(noteinfo.getNoteinfo());
       noteinfoActivityImp.readPhotopathtoNoteImageview(noteinfo.getPhotopath(),noteinfo.getNotetype());
        List<String>tags=new ArrayList<String>();
        if (noteinfo.getNotetype().equals("null")){
            tags.add("创建类型：无类型");
        }else {
            tags.add("创建类型："+noteinfo.getNotetype());
        }
        if (noteinfo.getPeople().equals("null")){

        }else {
            tags.add("相关的人："+noteinfo.getPeople());
        }
        if (noteinfo.getDate().equals("null")){

        }else {
            tags.add("指定日期："+noteinfo.getDate());
        }
        if (noteinfo.getTime().equals("null")){

        }else {
            tags.add("指定时间："+noteinfo.getTime());
        }
        if (noteinfo.getLocation().equals("null")){

        }else {
            tags.add("指定地点："+noteinfo.getLocation());
        }
        if (noteinfo.getCreatetime().equals("null")){

        }else {
            tags.add("创建于："+noteinfo.getCreatetime());
        }
        noteinfoActivityImp.readLabelinfotoNoteTagrroup(tags);
    }

    @Override
    public void setBackgroundcolorfromSeting() {
        noteinfoActivityImp.setBackgroundcolorfromSeting(userSeting.getcurrentColor());
    }
}
