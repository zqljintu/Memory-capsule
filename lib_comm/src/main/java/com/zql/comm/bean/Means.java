package com.zql.comm.bean;

import android.os.SystemClock;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by 尽途 on 2018/4/26.
 */

public class Means {
    public static String NOSTRING="null";
    public static int EDIT = 0;
    public static int CHANGE = 1;
    public static int WORK = 0;
    public static int STUDY = 1;
    public static int LIVE = 2;
    public static int DIARY = 3;
    public static int TRAYEL = 4;

    public static String STR_WORK = "工作";
    public static String STR_STUDY = "学习";
    public static String STR_LIVE = "生活";
    public static String STR_DIARY = "日记";
    public static String STR_TRAYEL = "旅行";

    public static String getNoteStringfromNoteInt(int type){//获取note类型
        switch (type){
            case 0:
                return "工作";
            case 1:
                return "学习";
            case 2:
                return "生活";
            case 3:
                return "日记";
            case 4:
                return "旅行";
            default:
                return null;
        }
    }
    public static String buildTransaction(final String type) {//微信分享使用到的功能
        return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
    }
    public static void setViewonlick(View view,float X,float Y){
        long dowmTime= SystemClock.uptimeMillis();
        MotionEvent dowmEvent=MotionEvent.obtain(dowmTime,dowmTime,MotionEvent.ACTION_DOWN,X,Y,0);
        dowmTime+=1000;
        MotionEvent upEvent=MotionEvent.obtain(dowmTime,dowmTime,MotionEvent.ACTION_UP,X,Y,0);
        view.onTouchEvent(dowmEvent);
        view.onTouchEvent(upEvent);
        dowmEvent.recycle();
        upEvent.recycle();
    }
    public static String getNotetextOnSearchCard(String note){
        int length=note.length();
        if (length>=20){
            return note.substring(0,20)+"...";
        }else {
            return note+"...";
        }
    }
    public static String getNoteTitleOnNoteinfoActivity(String note){
        int length=note.length();
        if (length<=5){
            return note;
        }else {
            return note.substring(0,5)+"...";
        }
    }
    public static String getNotetextOnRecyclerCard(String note){
        int length=note.length();
        if (length<=20){
            return note;
        }else if (length<=40){
            return note+"\n";
        }else {
            return note.substring(0,40)+"...";
        }
    }
    public static String getNotetextOnViewPagerCard(String note){
        int length=note.length();
        if (length<=20){
            return note+"\n"+"\n"+"\n";
        }else if (length<=50){
            return note+"\n"+"\n";
        }else {
            return note.substring(0,50)+"..."+"\n";
        }
    }
    public static boolean isphotouri(String path){//判定是不是图片地址
        if (path.length()>=10){
            String str1=".jpg";
            String str2=path.substring(path.length()-4,path.length());
            if (str1.equals(str2)){
                return true;
            }else {
                return false;
            }
        }else {
            return false;
        }
    }
    public static boolean isedittext_empty(String string){//判断是否为空
        if (string.isEmpty()){
            return true;
        }else {
            return false;
        }
    }
    public static boolean isedittext_empty(MaterialEditText editText){//判断editext是否为空
        if (editText.getText().toString().isEmpty()){
            return true;
        }else {
            return false;
        }
    }
    public static String getCreatetime(){//获取创建时间
        String[] weeks = {"星期天","星期一","星期二","星期三","星期四","星期五","星期六"};
        Calendar calendar=Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        String year = String.valueOf(calendar.get(Calendar.YEAR));
        String mounth = String.valueOf(calendar.get(Calendar.MONTH)+1);
        String day = String.valueOf(calendar.get(Calendar.DATE));
        int weekday = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        String hour=String.valueOf(calendar.get(Calendar.HOUR_OF_DAY));
        String minute=String.valueOf(calendar.get(Calendar.MINUTE));
        return year+"-"+mounth+"-"+day + "/" + weeks[weekday];
    }
    public static int getTheYearoncalendar(){
        Calendar calendar=Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        return calendar.get(Calendar.YEAR);
    }
    public static int getTheMonthoncalendar(){
        Calendar calendar=Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        return calendar.get(Calendar.MONTH)+1;
    }
    public static List<String> removeDuplicate(List<String> list){//去除lis中的重复元素
        List list1Temp=new ArrayList();
        for (int i=0;i<list.size();i++){
            if (!list1Temp.contains(list.get(i))){
                list1Temp.add(list.get(i));
            }
        }
        return list1Temp;
    }
    public static Noteinfo changefromNotebean(final NoteBean noteBean){
        Noteinfo noteinfo=new Noteinfo();
        String info,type,people,date,time,location,photo,createtime;
        boolean isshow;
        Long id;
        id=noteBean.getId();
        info=noteBean.getNoteinfo();
        type=noteBean.getNotetype();
        people=noteBean.getPeople();
        date=noteBean.getDate();
        time=noteBean.getTime();
        location=noteBean.getLocation();
        photo=noteBean.getPhotopath();
        createtime=noteBean.getCreatetime();
        isshow=noteBean.getIsshow();
        noteinfo.setId(id);
        noteinfo.setNoteinfo(info);
        noteinfo.setNotetype(type);
        noteinfo.setPeople(people);
        noteinfo.setDate(date);
        noteinfo.setTime(time);
        noteinfo.setLocation(location);
        noteinfo.setPhotopath(photo);
        noteinfo.setCreatetime(createtime);
        noteinfo.setIsshow(isshow);
        return noteinfo;
    }
}
