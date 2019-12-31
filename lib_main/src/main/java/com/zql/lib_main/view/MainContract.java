package com.zql.lib_main.view;

import android.app.Application;
import android.content.Context;

import com.zql.base.ui.mvp.im.IView;
import com.zql.comm.bean.NoteBean;

import java.util.List;

public interface MainContract {
    interface view extends IView{
        Context getActivity_this();//获取此Activity的this
        void startListActivity();
        void startListSecretActivity();
        void startCalendarActivity();
        void startSetingActivity();
        void startSearchActivity();
        void setMainBackground(Integer integer);
        void setMainBackgroundIcon(int size);//如果数据库为空，界面加载一个图片显示。
        void openSheetDialog(NoteBean noteBean);
        void readNotefromData(List<NoteBean> noteBeanList);
        Application getMainApplication();
        void setBackgroundcolorfromSeting(List<Integer> mlist);
    }

    interface presenter{
        void openCalendarActivity();//打开日历界面
        void openSearchActivity();//打开新的搜索界面
        void openListActivity();//打开事件列表的界面
        void openSetiongActivity();//打开用户设置界面
        void openListSecretActivity();//打开秘密界面
        void readNotefromDatatoMain();//获取信息从数据库并展现在MainActivity上
        void deleteNoteBean(NoteBean noteBean);//删除一个Notebean
        void setBackgroundcolorfromSeting();//改变主题
        int getBackgroundcolorNumfromSering();//获取
        boolean iscurrentthepasswordfromSeting(String password);//判断密码是否正确
        void changeNotetoPasswordFile(NoteBean noteBean);//将文件转入秘密文件夹
        void readNotefromDtabyType(int TYPE);//通过标签类型展示数据
        void openSheetDialog(NoteBean noteBean);
        Context getActivity_this();//获取此Activity的this
    }
}
