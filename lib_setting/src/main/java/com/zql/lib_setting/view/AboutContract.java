package com.zql.lib_setting.view;

import android.app.Application;
import android.content.Context;

import com.zql.base.ui.mvp.im.IView;

import java.util.List;

public interface AboutContract {

    interface view extends IView{
        void showthecurrentPassword(String string);
        Context getAboutActivityContext();
        Application getAboutApplication();
        void setBackgroundcolorfromSeting(List<Integer> colorlist);
    }

    interface presenter {
        void showthecurrentpasswordOnAboutactivity();
        boolean isnullthepasswordfromSeting();//判断密码是不是初始化“null”
        boolean isnullthequestionfromSeting();//判断密保问题是不是初始化“null”
        void putpasswordandquestionOnSeting(String password, String question);//写入密码和密保到设置文件中
        boolean iscurrentthepasswordfromSeting(String password);//判断是否为当前密码
        boolean iscurrentthequestionfromSeting(String question);//判断是否为当前密保
        void putpasswordOnSeting(String password);//单独修改当前密码
        void putquestionOnSeting(String question);//单独修改当前密保
        void putcurrentcolorOnSeting(int color);//改变主题色
        int getcurrentcolorNumfromSeting();//获取最新的颜色代码
        void setBackgroundcolorfromSeting();//设置主题色
    }

}
