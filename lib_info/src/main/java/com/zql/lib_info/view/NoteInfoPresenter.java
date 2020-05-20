package com.zql.lib_info.view;

import android.util.Log;

import com.zql.base.ui.mvp.BasePresenter;
import com.zql.base.utils.TimeUtils;
import com.zql.comm.UserSeting;
import com.zql.comm.bean.Means;
import com.zql.comm.bean.Noteinfo;
import com.zql.comm.data.CommData;
import com.zql.comm.util.DateUtils;

import java.util.ArrayList;
import java.util.Calendar;
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
        List<String> tags = new ArrayList<>();
        if (Means.isEmpty(noteinfo.getNotetype())) {
            tags.add("创建类型：无类型");
        } else {
            tags.add("创建类型：" + noteinfo.getNotetype());
        }
        if (Means.isEmpty(noteinfo.getPeople())) {

        } else {
            tags.add("相关的人：" + noteinfo.getPeople());
        }
        if (Means.isEmpty(noteinfo.getDate())) {

        } else {
            if (CommData.getIsLocalVersion()){
                tags.add("指定日期：" + noteinfo.getDate());
            }else {
                tags.add("指定日期：" + showTime(noteinfo.getDate()));
            }
        }
        if (Means.isEmpty(noteinfo.getTime())) {

        } else {
            tags.add("指定时间：" + noteinfo.getTime());
        }
        if (Means.isEmpty(noteinfo.getLocation())) {

        } else {
            tags.add("指定地点：" + noteinfo.getLocation());
        }
        if (!Means.isEmpty(noteinfo.getCreatetime())) {
            if (CommData.getIsLocalVersion()){
                tags.add("创建于：" + noteinfo.getCreatetime());
            }else {
                tags.add(showTime(noteinfo.getCreatetime()));
            }
        }
        getView().readLabelinfotoNoteTagrroup(tags);
    }

    private String showTime(String time){
        Calendar calendar = TimeUtils.getDataFromUTCTimeZone(time);
        StringBuffer stringBuffer = new StringBuffer();
        int year = calendar.get(Calendar.YEAR);
        stringBuffer.append(year + "年");
        int month = calendar.get(Calendar.MONTH) + 1;
        if (month < 10){
            stringBuffer.append( "0" + month + "月");
        }else {
            stringBuffer.append(month + "月");
        }
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        if (day < 10){
            stringBuffer.append( "0" + day + "日");
        }else {
            stringBuffer.append(day + "日");
        }
        Log.d("zzzzzzzzzzz",stringBuffer.toString());
        return stringBuffer.toString();
    }

    @Override
    public void setBackgroundcolorfromSeting() {
        getView().setBackgroundcolorfromSeting(userSeting.getcurrentColor());
    }
}
